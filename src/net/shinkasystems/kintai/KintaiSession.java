package net.shinkasystems.kintai;

import net.shinkasystems.kintai.entity.User;
import net.shinkasystems.kintai.entity.UserDao;
import net.shinkasystems.kintai.entity.UserDaoImpl;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;


/**
 * 勤怠管理ツールの認証セッションを管理します。
 * 
 * @author Aogiri
 *
 */
public class KintaiSession extends AuthenticatedWebSession {
	
	private final Roles roles = KintaiRole.createRoles();

	public KintaiSession(Request request) {
		super(request);
	}

	@Override
	public boolean authenticate(String username, String password) {
		
		String hashedPassword = DigestUtils.sha512Hex(password);
		
		UserDao dao = new UserDaoImpl();
		
		User user = dao.selectByUserName(username);
		
		if (user == null || user.getPassword() != hashedPassword) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public Roles getRoles() {
		return roles;
	}

}
