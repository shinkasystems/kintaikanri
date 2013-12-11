package net.shinkasystems.kintai.component;

import net.shinkasystems.kintai.KintaiDB;
import net.shinkasystems.kintai.entity.User;
import net.shinkasystems.kintai.entity.UserDao;
import net.shinkasystems.kintai.entity.UserDaoImpl;
import net.shinkasystems.kintai.util.Authentication;

import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;
import org.seasar.doma.jdbc.tx.LocalTransaction;

/**
 * 入力されたパスワードが現在のパスワードと同じかどうかをチェックします。
 * 
 * @author Aogiri
 *
 */
public class PasswordDuplicateValidator implements IValidator<String> {

	private final int userID;

	public PasswordDuplicateValidator(int userID) {
		super();
		this.userID = userID;
	}

	@Override
	public void validate(IValidatable<String> validatable) {

		final LocalTransaction transaction = KintaiDB.getLocalTransaction();
		try {
			transaction.begin();

			final UserDao dao = new UserDaoImpl();

			final User user = dao.selectById(userID);

			if (new Authentication(user.getUserName(), validatable.getValue()).getPasswordHash().equals(
					user.getPassword())) {
				
				validatable.error(new ValidationError());
			}

		} finally {
			transaction.rollback();
		}
	}

}
