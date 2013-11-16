package net.shinkasystems.kintai.component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.shinkasystems.kintai.KintaiDB;
import net.shinkasystems.kintai.entity.User;
import net.shinkasystems.kintai.entity.UserDao;
import net.shinkasystems.kintai.entity.UserDaoImpl;

import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteTextField;
import org.seasar.doma.jdbc.tx.LocalTransaction;

public class AuthorityAutoCompleteTextField<T extends Authority> extends AutoCompleteTextField<Authority> {

	public AuthorityAutoCompleteTextField(String id) {
		super(id);
	}

	@Override
	protected Iterator<Authority> getChoices(String input) {

		List<User> users;
		List<Authority> authorities = new ArrayList<>();

		LocalTransaction transaction = KintaiDB.getLocalTransaction();
		try {
			transaction.begin();

			final UserDao dao = new UserDaoImpl();

			users = dao.selectByPartialUserName(input);

		} finally {
			transaction.rollback();
		}

		int i = 0;
		for (User user : users) {
			if (++i > 5) {
				break;
			}
			authorities.add(new Authority(user.getId(), user.getUserName(), user.getDisplayName()));
		}

		return authorities.iterator();
	}

}
