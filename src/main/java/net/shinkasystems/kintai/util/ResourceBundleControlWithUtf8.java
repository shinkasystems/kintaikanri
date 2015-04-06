/**
 * 
 */
package net.shinkasystems.kintai.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * @author Aogiri
 *
 */
public class ResourceBundleControlWithUtf8 extends ResourceBundle.Control {

	@Override
	public ResourceBundle newBundle(
			String baseName,
			Locale locale,
			String format,
			ClassLoader loader,
			boolean reload) throws IllegalAccessException, InstantiationException, IOException {

		String bundleName = toBundleName(baseName, locale);
		String resourceName = toResourceName(bundleName, "properties");

		InputStream is = loader.getResourceAsStream(resourceName);
		InputStreamReader isr = new InputStreamReader(is, "UTF-8");
		BufferedReader reader = new BufferedReader(isr);

		return new PropertyResourceBundle(reader);
	}
}
