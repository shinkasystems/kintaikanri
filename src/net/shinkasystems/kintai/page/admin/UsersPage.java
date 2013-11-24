package net.shinkasystems.kintai.page.admin;

import java.util.Iterator;
import java.util.List;

import net.shinkasystems.kintai.KintaiConstants;
import net.shinkasystems.kintai.KintaiDB;
import net.shinkasystems.kintai.KintaiRole;
import net.shinkasystems.kintai.entity.UserDao;
import net.shinkasystems.kintai.entity.UserDaoImpl;
import net.shinkasystems.kintai.entity.sub.UserData;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.value.ValueMap;
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
	private final Form<ValueMap> userForm = new Form<ValueMap>("users-form");

	/**
	 * 
	 */
	private final DataView<UserData> userDataView = new DataView<UserData>(
			"user-data-view", new UserDataProvider(), 100) {

		@Override
		protected void populateItem(Item<UserData> item) {

			final UserData user = item.getModelObject();

			item.add(new Label("user-id", user.getId()));
			item.add(new Label("user-name", user.getUserName()));
			item.add(new Label("user-display-name", user.getDisplayName()));
			item.add(new Label("authority-display-name", user.getAuthorityDisplayName()));
			item.add(new Label("role", user.getRole()));
			item.add(new Label("expire-date", KintaiConstants.DATE_FORMAT.format(user.getExpireDate())));
			item.add(new Label("activated", user.getActivated() ? "有効" : "無効"));
			
			/*
			 * TODO
			 * 編集／有効化／無効化／削除のボタンを追加
			 */

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
		final PagingNavigator pagingNavigator = new PagingNavigator("page-navigator", userDataView);
		
		
		
		/*
		 * コンポーネントの編集
		 */
		userDataView.setItemsPerPage(100);
		
		
		
		/*
		 * コンポーネントの組立
		 */
		userForm.add(pagingNavigator);

		userForm.add(userDataView);
		add(userForm);
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
