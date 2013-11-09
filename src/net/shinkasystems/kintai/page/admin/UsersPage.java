package net.shinkasystems.kintai.page.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.shinkasystems.kintai.KintaiConstants;
import net.shinkasystems.kintai.KintaiDB;
import net.shinkasystems.kintai.KintaiRole;
import net.shinkasystems.kintai.entity.User;
import net.shinkasystems.kintai.entity.UserDao;
import net.shinkasystems.kintai.entity.UserDaoImpl;
import net.shinkasystems.kintai.page.DefaultLayoutPage;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
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
public class UsersPage extends DefaultLayoutPage {

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
			item.add(new Label("role", user.getRoleName()));
			item.add(new Label("expire-date", KintaiConstants.DATE_FORMAT.format(user.getExpireDate())));
			item.add(new Label("activated", user.getStatus()));

		}
	};

	public UsersPage() {
		super();

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
		
		List<Map<String, Object>> maps = null;
		List<UserData> userDatas = new ArrayList<UserData>();
		
		LocalTransaction transaction = KintaiDB.getLocalTransaction();
		try {
			transaction.begin();

			final UserDao dao = new UserDaoImpl();

			maps = dao.selectUserData(options);

			transaction.commit();
		} finally {
			transaction.rollback();
		}
		
		for (Map<String, Object> map : maps) {
			
		}
		
		return userDatas.iterator();
	}

	@Override
	public IModel<UserData> model(UserData user) {
		return new Model<UserData>(user);
	}

	@Override
	public long size() {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}
}

/**
 * 
 * @author Aogiri
 * 
 */
class UserData extends User implements Serializable {

	private String authorityDisplayName;

	private String roleName;

	private String status;

	public String getAuthorityDisplayName() {
		return authorityDisplayName;
	}

	public void setAuthorityDisplayName(String authorityDisplayName) {
		this.authorityDisplayName = authorityDisplayName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}