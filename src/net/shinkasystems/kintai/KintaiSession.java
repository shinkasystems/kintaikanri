package net.shinkasystems.kintai;

import java.util.Date;

import net.shinkasystems.kintai.entity.User;
import net.shinkasystems.kintai.entity.UserDao;
import net.shinkasystems.kintai.util.Authentication;
import net.shinkasystems.kintai.util.DaoFactory;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;
import org.seasar.doma.jdbc.tx.LocalTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 勤怠管理ツールの認証セッションを管理します。
 * 
 * @author Aogiri
 * 
 */
public class KintaiSession extends AuthenticatedWebSession {
	
	/** ロガー */
	private static final Logger log = LoggerFactory
			.getLogger(KintaiSession.class);

	private User user;

	private Roles roles;

	public KintaiSession(Request request) {
		super(request);
	}

	@Override
	public boolean authenticate(String username, String password) {

		String hashedPassword = new Authentication(username, password).getPasswordHash();
		
		log.info(hashedPassword);

		User user= null;
		LocalTransaction transaction = KintaiDB.getLocalTransaction();
		try {
			transaction.begin();

			UserDao dao = DaoFactory.createDaoImplements(UserDao.class);

			user = dao.selectByUserName(username);

			transaction.commit();
		} finally {
			transaction.rollback();
		}



		if (user == null) {
			
			log.info("ユーザー：" + username + " は存在しません。");
			return false;
			
		} else if (user.getPassword().equals(hashedPassword) && user.getActivated()) {
			
			this.user = user;

			if (user.getExpireDate() != null && new Date().after(user.getExpireDate())) {
				roles = new Roles(KintaiRole.EXPIRED_USER);
			} else if (KintaiRole.USER.equals(user.getRole())) {
				roles = new Roles(KintaiRole.USER);
			} else if (KintaiRole.ADMIN.equals(user.getRole())) {
				roles = new Roles(KintaiRole.ADMIN);
			} else {
				roles = null;
			}

			return true;
			
		} else {

			log.info("ユーザー：" + username + " のパスワード：" + password + " が一致しません。");
			return false;
		}
	}

	@Override
	public Roles getRoles() {

		if (isSignedIn()) {
			return roles;
		} else {
			return null;
		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setRoles(Roles roles) {
		this.roles = roles;
	}

}
