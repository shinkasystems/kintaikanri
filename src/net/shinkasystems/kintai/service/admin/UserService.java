package net.shinkasystems.kintai.service.admin;

import java.util.Iterator;
import java.util.List;

import net.shinkasystems.kintai.KintaiDB;
import net.shinkasystems.kintai.entity.UserDao;
import net.shinkasystems.kintai.entity.sub.UserData;
import net.shinkasystems.kintai.util.DaoFactory;

import org.seasar.doma.jdbc.SelectOptions;
import org.seasar.doma.jdbc.tx.TransactionManager;

/**
 * ユーザー画面用サービスです。
 * @author Aogiri
 *
 */
public class UserService {

	/**
	 * ユーザー一覧の表示用データのイテレータを返します
	 * @param first オフセット
	 * @param count 件数
	 * @return ユーザー一覧の表示用データのイテレータ
	 */
	public Iterator<UserData> getUserDataIterator(long first, long count) {

		SelectOptions options = SelectOptions.get().offset((int) first).limit((int) count);

		TransactionManager transactionManager = KintaiDB.singleton().getTransactionManager();

		List<UserData> userDatas = transactionManager.required(() -> {

			final UserDao dao = DaoFactory.createDaoImplements(UserDao.class);

			return dao.selectUserData(options);
		});

		return userDatas.iterator();
	}

	/**
	 * ユーザーの件数を返します。
	 * @return ユーザーの件数
	 */
	public long countUser() {

		TransactionManager transactionManager = KintaiDB.singleton().getTransactionManager();

		return transactionManager.required(() -> {

			final UserDao dao = DaoFactory.createDaoImplements(UserDao.class);

			return dao.selectCountUser();
		});
	}
}
