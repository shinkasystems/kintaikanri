package net.shinkasystems.kintai.component;

import net.shinkasystems.kintai.KintaiDB;
import net.shinkasystems.kintai.entity.User;
import net.shinkasystems.kintai.entity.UserDao;
import net.shinkasystems.kintai.util.Authentication;
import net.shinkasystems.kintai.util.DaoFactory;

import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;
import org.seasar.doma.jdbc.tx.TransactionManager;

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

		TransactionManager transactionManager = KintaiDB.singleton().getTransactionManager();

		transactionManager.required(() -> {
			

			final UserDao dao = DaoFactory.createDaoImplements(UserDao.class);

			final User user = dao.selectById(userID);

			if (new Authentication(user.getUserName(), validatable.getValue()).getPasswordHash().equals(
					user.getPassword())) {
				
				validatable.error(new ValidationError(this));;
			}

		});
	}

}
