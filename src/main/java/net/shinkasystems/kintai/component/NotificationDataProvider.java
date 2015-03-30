package net.shinkasystems.kintai.component;

import java.util.Date;
import java.util.Iterator;

import net.shinkasystems.kintai.domain.NotificationStatus;
import net.shinkasystems.kintai.entity.sub.NotificationData;
import net.shinkasystems.kintai.service.notification.IndexService;

import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * 申請情報一覧のデータプロバイダーです。
 * 
 * @author Aogiri
 *
 */
public class NotificationDataProvider extends SortableDataProvider<NotificationData, String> {

	private java.sql.Date from;

	private java.sql.Date to;

	private Integer applicantId;

	private NotificationStatus status;

	private final IndexService indexService;

	/**
	 * コンストラクタです。
	 */
	public NotificationDataProvider(IndexService indexService) {
		this(new java.sql.Date(new Date().getTime()), null, null, null, indexService);
	}

	public NotificationDataProvider(
			java.sql.Date from,
			java.sql.Date to,
			Integer applicantId,
			NotificationStatus status,
			IndexService indexService) {

		super();
		this.from = from;
		this.to = to;
		this.applicantId = applicantId;
		this.status = status;

		this.indexService = indexService;

		setSort("TERM", SortOrder.ASCENDING);

	}

	@Override
	public Iterator<? extends NotificationData> iterator(long first, long count) {

		/*
		 * 
		 */
		return indexService.getApplivcationDataIterator(
				from,
				to,
				applicantId,
				status,
				first,
				count,
				getSort().getProperty(),
				getSort().isAscending());
	}

	@Override
	public IModel<NotificationData> model(NotificationData notificationData) {
		return new Model<NotificationData>(notificationData);
	}

	@Override
	public long size() {

		return indexService.countNotification();
	}

	public java.sql.Date getFrom() {
		return from;
	}

	public void setFrom(java.sql.Date from) {
		this.from = from;
	}

	public java.sql.Date getTo() {
		return to;
	}

	public void setTo(java.sql.Date to) {
		this.to = to;
	}

	public Integer getApplicantId() {
		return applicantId;
	}

	public void setApplicantId(Integer applicantId) {
		this.applicantId = applicantId;
	}

	public NotificationStatus getStatus() {
		return status;
	}

	public void setStatus(NotificationStatus status) {
		this.status = status;
	}
}