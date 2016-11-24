package net.shinkasystems.kintai.component;

import net.shinkasystems.kintai.KintaiDB;
import net.shinkasystems.kintai.entity.User;
import net.shinkasystems.kintai.entity.UserDao;
import net.shinkasystems.kintai.util.DaoFactory;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.validation.AbstractFormValidator;
import org.seasar.doma.jdbc.tx.TransactionManager;

/**
 * 入力された表示名が既に登録されていないかチェックします。
 * 
 * @author Aogiri
 *
 */
public class DisplayNameDuplicateEditValidator extends AbstractFormValidator {

	/**
	 * ユーザーIDのフォーム部品です。
	 */
	private final FormComponent<?> userId;

	/**
	 * 表示名のフォーム部品です。
	 */
	private final FormComponent<?> displayName;

	public DisplayNameDuplicateEditValidator(FormComponent<?> userId, FormComponent<?> displayName) {
		super();
		this.userId = userId;
		this.displayName = displayName;
	}

	@Override
	public FormComponent<?>[] getDependentFormComponents() {
		return new FormComponent<?>[] { userId, displayName };
	}

	@Override
	public void validate(Form<?> form) {

		TransactionManager transactionManager = KintaiDB.singleton().getTransactionManager();

		transactionManager.required(() -> {

			final UserDao dao = DaoFactory.createDaoImplements(UserDao.class);

			final User user = dao.selectByDisplayName(displayName.getValue());

			if (user != null
					&& user.getId().intValue() != Integer.valueOf(userId.getDefaultModelObjectAsString()).intValue()) {

				error(displayName);
			}
		});

	}

}
