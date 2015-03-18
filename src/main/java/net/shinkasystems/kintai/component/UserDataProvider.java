package net.shinkasystems.kintai.component;

import java.util.Iterator;

import net.shinkasystems.kintai.entity.sub.UserData;
import net.shinkasystems.kintai.service.admin.UserService;

import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * 
 * @author Aogiri
 * 
 */
public class UserDataProvider extends SortableDataProvider<UserData, String> {

	/**
	 * ユーザー画面用サービスです
	 */
	private final UserService userService;

	/**
	 * コンストラクタです。
	 * @param userService
	 */
	public UserDataProvider(UserService userService) {
		super();
		this.userService = userService;
	}

	@Override
	public Iterator<UserData> iterator(long first, long count) {

		return userService.getUserDataIterator(first, count);
	}

	@Override
	public IModel<UserData> model(UserData user) {
		return new Model<UserData>(user);
	}

	@Override
	public long size() {

		return userService.countUser();
	}
}