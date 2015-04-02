package net.shinkasystems.kintai.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Date と LocalDate を相互に変換するユーティリティです。
 * @author Aogiri
 *
 */
public class DateUtils {

	/**
	 * Date 型を LocalDate 型に変換します。
	 * @param date Date 型インスタンス
	 * @return LocalDate 型インスタンス
	 */
	public static LocalDate toLocalDate(Date date) {
		return toLocalDateTime(date).toLocalDate();
	}

	/**
	 * Date 型を LocalTime 型に変換します。
	 * @param date Date 型インスタンス
	 * @return　LocalTime 型インスタンス
	 */
	public static LocalTime toLocalTime(Date date) {
		return toLocalDateTime(date).toLocalTime();
	}

	/**
	 * Date 型を LocalDateTime 型に変換します。
	 * @param date Date 型インスタンス
	 * @return　LocalDateTime 型インスタンス
	 */
	public static LocalDateTime toLocalDateTime(Date date) {
		return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
	}

	/**
	 * LocalDateTime 型を Date 型に変換します。
	 * @param localDateTime　LocalDateTime 型インスタンス
	 * @return　Date 型インスタンス
	 */
	public static Date toDate(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * LocalDate 型を Date 型に変換します。
	 * @param localDate　LocalDate 型インスタンス
	 * @return　Date 型インスタンス
	 */
	public static Date toDate(LocalDate localDate) {

		if (localDate == null) {
			return null;
		}
		return toDate(localDate.atStartOfDay());
	}

	/**
	 * LocalTime 型を Date 型に変換します。
	 * @param localTime　LocalTime 型インスタンス
	 * @return　Date 型インスタンス
	 */
	public static Date toDate(LocalTime localTime) {
		return toDate(LocalDate.ofEpochDay(0L).atTime(localTime));
	}
}
