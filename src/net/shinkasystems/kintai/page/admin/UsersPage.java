package net.shinkasystems.kintai.page.admin;

import java.util.Iterator;
import java.util.List;

import net.shinkasystems.kintai.KintaiConstants;
import net.shinkasystems.kintai.KintaiDB;
import net.shinkasystems.kintai.KintaiRole;
import net.shinkasystems.kintai.entity.UserDao;
import net.shinkasystems.kintai.entity.UserDaoImpl;
import net.shinkasystems.kintai.entity.sub.UserData;
import net.shinkasystems.kintai.panel.PaginationPanel;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.link.StatelessLink;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.seasar.doma.jdbc.SelectOptions;
import org.seasar.doma.jdbc.tx.LocalTransaction;

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
	 * 
	 */
	private final DataView<UserData> userDataView = new DataView<UserData>(
			"user-data-view", new UserDataProvider(), 20) {

		@Override
		protected void populateItem(Item<UserData> item) {

			final UserData user = item.getModelObject();
			
			final Label userIDLabel = new Label("user-id", user.getId());
			final Label userNameLabel = new Label("user-name", user.getUserName());
			final Label userDisplayNameLabel = new Label("user-display-name", user.getDisplayName());
			final Label userAuthorityDisplayNameLabel = new Label("authority-display-name", user.getAuthorityDisplayName());
			final Label userRoleLabel = new Label("role", user.getRole());
			final Label userExpiredLabel = new Label("expire-date", KintaiConstants.DATE_FORMAT.format(user.getExpireDate()));
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

/**
 * 
 * @author Aogiri
 * 
 */
class UserDataProvider extends SortableDataProvider<UserData, String> {

	@Override
	public Iterator<UserData> iterator(long first, long count) {
		
		SelectOptions options = SelectOptions.get().offset((int) first).limit((int) count);
		
		List<UserData> userDatas = null;
		
		LocalTransaction transaction = KintaiDB.getLocalTransaction();
		try {
			transaction.begin();

			final UserDao dao = new UserDaoImpl();

			userDatas = dao.selectUserData(options);

		} finally {
			transaction.rollback();
		}
		
		return userDatas.iterator();
	}

	@Override
	public IModel<UserData> model(UserData user) {
		return new Model<UserData>(user);
	}

	@Override
	public long size() {
		
		long size = 0;
		
		LocalTransaction transaction = KintaiDB.getLocalTransaction();
		try {
			transaction.begin();

			final UserDao dao = new UserDaoImpl();

			size = dao.selectCountUser();

			transaction.commit();
		} finally {
			transaction.rollback();
		}
		return size;
	}
}
