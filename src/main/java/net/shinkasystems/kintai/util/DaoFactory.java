package net.shinkasystems.kintai.util;


/**
 * DAOインスタンスを生成するファクトリークラスです。
 * 
 * このクラスは javac のバグによって Doma の DaoImpl クラスが正常にコンパイルされない事象を回避します。
 * DaoImpl のインスタンスが必要な場合、必ずこのファクトリークラスを使用して取得してください。
 * 
 * 参考資料
 * http://doma.seasar.org/reference/app_build.html
 * http://d.hatena.ne.jp/taedium/20100401/p1
 * 
 * @author Aogiri
 *
 */
public class DaoFactory {

	/**
	 * インスタンスの生成は制限されています。
	 */
	private DaoFactory() {
		
	}
	
	/**
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T createDaoImplements(Class<T> daoInterface) {
		
		try {
			return (T) Class.forName(daoInterface.getName() + "Impl").newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

}
