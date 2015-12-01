package net.shinkasystems.kintai.component;

import net.shinkasystems.kintai.KintaiDB;
import net.shinkasystems.kintai.entity.User;
import net.shinkasystems.kintai.entity.UserDao;
import net.shinkasystems.kintai.util.DaoFactory;

import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;
import org.seasar.doma.jdbc.tx.TransactionManager;

/**
 * 入力されたユーザー名が既に登録されていないかチェックします。
 * 
 * @author Aogiri
 *
 */
public class UserNameDuplicateValidator implements IValidator<String> {

	@Override
	public void validate(IValidatable<String> validatable) {

		TransactionManager transactionManager = KintaiDB.singleton().getTransactionManager();

		transactionManager.required(() -> {

			final UserDao dao = DaoFactory.createDaoImplements(UserDao.class);

			final User user = dao.selectByUserName(validatable.getValue());

			if (user != null) {

				validatable.error(new ValidationError(this));
			}
		});
	}
}
