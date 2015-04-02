package net.shinkasystems.kintai.page.admin;

import java.time.format.DateTimeFormatter;

import net.shinkasystems.kintai.KintaiRole;
import net.shinkasystems.kintai.component.UserDataProvider;
import net.shinkasystems.kintai.entity.sub.UserData;
import net.shinkasystems.kintai.panel.PaginationPanel;
import net.shinkasystems.kintai.service.admin.UserService;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.link.StatelessLink;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.google.inject.Inject;

/**
 * 
 * @author Aogiri
 * 
 */
@AuthorizeInstantiation({ KintaiRole.ADMIN })
public class UsersPage extends AdminLayoutPage {

	/**
	 * 
	 */
	public static final String PARAMETER_ID = "id";

	/**
	 * ユーザー一覧画面用のサービスです。
	 */
	@Inject
	private UserService userService;

	/**
	 * ユーザー一覧情報のデータプロバイダーです。
	 */
	UserDataProvider userDataProvider = new UserDataProvider(userService);

	/**
	 * 
	 */
	private final DataView<UserData> userDataView = new DataView<UserData>(
			"user-data-view", userDataProvider, 20) {

		@Override
		protected void populateItem(Item<UserData> item) {

			final UserData user = item.getModelObject();

			final Label userIDLabel = new Label("user-id", user.getId());
			final Label userNameLabel = new Label("user-name", user.getUserName());
			final Label userDisplayNameLabel = new Label("user-display-name", user.getDisplayName());
			final Label userAuthorityDisplayNameLabel = new Label("authority-display-name",
					user.getAuthorityDisplayName());
			final Label userRoleLabel = new Label("role", user.getRole());
			final Label userExpiredLabel = new Label("expire-date",
					user.getExpireDate().format(DateTimeFormatter.ISO_LOCAL_DATE));
			final Label userActivatedLabel = new Label("activated", user.getActivated() ? "有効" : "無効");

			final Link<String> userLink = new StatelessLink<String>("link") {

				@Override
				public void onClick() {

					final PageParameters parameters = new PageParameters();

					parameters.set(PARAMETER_ID, user.getId());

					UsersPage.this.setResponsePage(UserEditPage.class, parameters);
				}

			};
			userLink.add(userNameLabel);

			item.add(userIDLabel);
			item.add(userLink);
			item.add(userDisplayNameLabel);
			item.add(userAuthorityDisplayNameLabel);
			item.add(userRoleLabel);
			item.add(userExpiredLabel);
			item.add(userActivatedLabel);
		}
	};

	/**
	 * コンストラクタです。
	 */
	public UsersPage() {
		super();

		/*
		 * コンポーネントの生成
		 */
		final PagingNavigator pagingNavigator = new PaginationPanel("page-navigator", userDataView);

		/*
		 * コンポーネントの編集
		 */
		userDataView.setItemsPerPage(20);

		/*
		 * コンポーネントの組立
		 */
		add(userDataView);
		add(pagingNavigator);
	}

}
