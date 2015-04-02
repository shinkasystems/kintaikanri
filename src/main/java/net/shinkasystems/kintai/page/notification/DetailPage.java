package net.shinkasystems.kintai.page.notification;

import java.time.format.DateTimeFormatter;
import java.util.Date;

import net.shinkasystems.kintai.KintaiConstants;
import net.shinkasystems.kintai.KintaiRole;
import net.shinkasystems.kintai.KintaiSession;
import net.shinkasystems.kintai.component.ConfirmSubmitButton;
import net.shinkasystems.kintai.component.StatusValidator;
import net.shinkasystems.kintai.domain.NotificationStatus;
import net.shinkasystems.kintai.domain.NotificationType;
import net.shinkasystems.kintai.entity.Notification;
import net.shinkasystems.kintai.entity.User;
import net.shinkasystems.kintai.mail.Mailer;
import net.shinkasystems.kintai.page.DefaultLayoutPage;
import net.shinkasystems.kintai.panel.AlertPanel;
import net.shinkasystems.kintai.panel.InfomationPanel;
import net.shinkasystems.kintai.service.notification.DetailService;
import net.shinkasystems.kintai.util.DateUtils;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.datetime.markup.html.basic.DateLabel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.HiddenField;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.IRequestParameters;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.value.ValueMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

/**
 * 申請情報の詳細画面です。
 * 
 * @author Aogiri
 *
 */
@AuthorizeInstantiation({ KintaiRole.ADMIN, KintaiRole.USER })
public class DetailPage extends DefaultLayoutPage {

	/** ロガー */
	private static final Logger log = LoggerFactory.getLogger(DetailPage.class);

	/**
	 * アラートパネルです。
	 */
	private final Panel alertPanel = new AlertPanel("alert-panel");

	/**
	 * 情報パネルです。
	 */
	private final InfomationPanel infomationPanel = new InfomationPanel("infomation-panel");

	/**
	 * 詳細画面のフォームです。
	 */
	private final Form<ValueMap> form = new Form<ValueMap>("detail-form") {

		@Override
		protected void onError() {

			alertPanel.setVisible(true);
		}

	};

	private final IModel<Integer> idModel = new Model<Integer>();

	private final HiddenField<Integer> idField = new HiddenField<Integer>("id-hidden", idModel);

	private final IModel<NotificationStatus> statusModel = new Model<NotificationStatus>();

	private final HiddenField<NotificationStatus> statusField = new HiddenField<>("status-hidden", statusModel);

	private final IModel<String> commentAuthorityModel = new Model<String>();

	private final TextArea<String> commentAuthorityTextArea =
			new TextArea<>("comment-authority-textarea", commentAuthorityModel);

	private final Label idLabel = new Label("id", idModel);

	private final IModel<Date> termModel = new Model<Date>();

	private final Label termLabel = DateLabel.forDatePattern("term", termModel, KintaiConstants.DATE_PATTERN);

	private final IModel<String> applicantModel = new Model<String>();

	private final Label applicantLabel = new Label("applicant", applicantModel);

	private final IModel<NotificationType> typeModel = new Model<NotificationType>();

	private final Label typeLabel = new Label("type", typeModel);

	private final IModel<String> commentModel = new Model<String>();

	private final MultiLineLabel commentLabel = new MultiLineLabel("comment", commentModel);

	private final IModel<Date> createdModel = new Model<Date>();

	private final Label createdLabel = DateLabel.forDatePattern("created", createdModel, KintaiConstants.DATE_PATTERN);

	private final Label statusLabel = new Label("status", statusModel);

	private final IModel<String> authorityModel = new Model<String>();

	private final Label authorityLabel = new Label("authority", authorityModel);

	private final MultiLineLabel commentAuthorityLabel = new MultiLineLabel("comment-authority", commentAuthorityModel);

	private final IModel<Date> updatedModel = new Model<Date>();

	private final Label updatedLabel = DateLabel.forDatePattern("updated", updatedModel, KintaiConstants.DATE_PATTERN);

	/**
	 * 承認ボタンです。
	 */
	private final Button approveButton = new ConfirmSubmitButton("approve", getString("confirm-approve-message")) {

		@Override
		public void onSubmit() {

			final Integer notificationID = idField.getModelObject();
			final Notification notification = detailService.getNotification(notificationID);

			notification.setCommentAuthority(commentAuthorityTextArea.getModelObject());

			detailService.approve(notification);

			/*
			 * メール送信処理
			 */
			final User applicant = detailService.getUser(notification.getApplicantId());
			final User authority = detailService.getUser(applicant.getAuthorityId());

			Mailer.NOTIFICATION_APPROVAL.send(
					applicant.getEmailAddress(),
					authority.getEmailAddress(),
					applicant.getDisplayName(),
					authority.getDisplayName(),
					notification.getTerm().format(DateTimeFormatter.ISO_LOCAL_DATE),
					notification.getType().display,
					notification.getCommentAuthority(),
					getDetailPageUrlString(notification));

			/*
			 * ページ情報の更新
			 */
			updatePage(notification, applicant, authority, ((KintaiSession) KintaiSession.get()).getUser());

			infomationPanel.setMessage(getString("approve-message"));
			infomationPanel.setVisible(true);
		}
	};

	/**
	 * 却下ボタンです。
	 */
	private final Button rejectButton = new ConfirmSubmitButton("reject", getString("confirm-reject-message")) {

		@Override
		public void onSubmit() {

			final Integer notificationID = idField.getModelObject();
			final Notification notification = detailService.getNotification(notificationID);

			notification.setCommentAuthority(commentAuthorityTextArea.getModelObject());

			detailService.reject(notification);

			/*
			 * メール送信処理
			 */
			final User applicant = detailService.getUser(notification.getApplicantId());
			final User authority = detailService.getUser(applicant.getAuthorityId());

			Mailer.NOTIFICATION_REJECTION.send(
					applicant.getEmailAddress(),
					authority.getEmailAddress(),
					applicant.getDisplayName(),
					authority.getDisplayName(),
					notification.getTerm().format(DateTimeFormatter.ISO_LOCAL_DATE),
					notification.getType().display,
					notification.getCommentAuthority(),
					getDetailPageUrlString(notification));

			/*
			 * ページ情報の更新
			 */
			updatePage(notification, applicant, authority, ((KintaiSession) KintaiSession.get()).getUser());

			infomationPanel.setMessage(getString("reject-message"));
			infomationPanel.setVisible(true);

		}
	};

	/**
	 * 取下げボタンです。
	 */
	private final Button withdrawButton = new ConfirmSubmitButton("withdraw", getString("confirm-withdraw-message")) {

		@Override
		public void onSubmit() {

			final Integer notificationID = idField.getModelObject();
			final Notification notification = detailService.getNotification(notificationID);

			notification.setCommentAuthority(commentAuthorityTextArea.getModelObject());

			detailService.withdraw(notification);

			/*
			 * メール送信処理
			 */
			final User applicant = detailService.getUser(notification.getApplicantId());
			final User authority = detailService.getUser(applicant.getAuthorityId());

			Mailer.NOTIFICATION_WITHDRAWAL.send(
					applicant.getEmailAddress(),
					authority.getEmailAddress(),
					applicant.getDisplayName(),
					authority.getDisplayName(),
					notification.getTerm().format(DateTimeFormatter.ISO_LOCAL_DATE),
					notification.getType().display,
					notification.getCommentAuthority(),
					getDetailPageUrlString(notification));

			/*
			 * ページ情報の更新
			 */
			updatePage(notification, applicant, authority, ((KintaiSession) KintaiSession.get()).getUser());

			infomationPanel.setMessage(getString("withdraw-message"));
			infomationPanel.setVisible(true);
		}
	};

	@Inject
	private DetailService detailService;

	/**
	 * コンストラクタです。
	 */
	public DetailPage() {
		super();

		/*
		 * ログインユーザー情報の取得
		 */
		final User loginUser = ((KintaiSession) KintaiSession.get()).getUser();

		/*
		 * 申請情報の取得
		 */
		IRequestParameters parameters = RequestCycle.get().getRequest().getQueryParameters();

		final int id = parameters.getParameterValue(IndexPage.PARAMETER_ID).toInt();

		final Notification notification = detailService.getNotification(id);
		final User applicant = detailService.getUser(notification.getApplicantId());
		final User authority = detailService.getUser(applicant.getAuthorityId());

		/*
		 * コンポーネントの編集
		 */
		alertPanel.setVisible(false);
		infomationPanel.setVisible(false);

		idField.setType(Integer.class);
		idModel.setObject(id);

		form.add(new StatusValidator(idField, statusField, detailService));

		updatePage(notification, applicant, authority, loginUser);

		/*
		 * コンポーネントの組立
		 */
		add(alertPanel);
		add(infomationPanel);
		add(idLabel);
		add(termLabel);
		add(applicantLabel);
		add(typeLabel);
		add(commentLabel);
		add(createdLabel);
		add(statusLabel);
		add(authorityLabel);
		add(updatedLabel);
		add(commentAuthorityLabel);

		form.add(idField);
		form.add(statusField);
		form.add(commentAuthorityTextArea);
		form.add(approveButton);
		form.add(rejectButton);
		form.add(withdrawButton);

		add(form);
	}

	/**
	 * ページの表示情報を更新します。
	 * 
	 * @param notification
	 * @param applicant
	 * @param authority
	 * @param loginUser
	 */
	private void updatePage(Notification notification, User applicant, User authority, User loginUser) {

		final NotificationType type = notification.getType();
		final NotificationStatus status = notification.getStatus();

		idModel.setObject(notification.getId());
		statusModel.setObject(status);
		termModel.setObject(DateUtils.toDate(notification.getTerm()));
		applicantModel.setObject(applicant.getDisplayName());
		typeModel.setObject(type);
		commentModel.setObject(notification.getCommentApplycant());
		createdModel.setObject(DateUtils.toDate(notification.getCreateDate()));
		authorityModel.setObject(authority.getDisplayName());
		updatedModel.setObject(DateUtils.toDate(notification.getUpdateDate()));
		commentAuthorityModel.setObject(notification.getCommentAuthority());

		if (status == NotificationStatus.PENDING) {
			statusLabel.add(new AttributeModifier("class", "label label-info"));
		} else if (status == NotificationStatus.APPROVED) {
			statusLabel.add(new AttributeModifier("class", "label label-success"));
		} else if (status == NotificationStatus.REJECTED) {
			statusLabel.add(new AttributeModifier("class", "label label-important"));
		} else if (status == NotificationStatus.WITHDRAWN) {
			statusLabel.add(new AttributeModifier("class", "label label-inverse"));
		}

		approveButton.setVisible(loginUser.getId() == authority.getId()
				&& (status == NotificationStatus.PENDING || status == NotificationStatus.REJECTED));
		rejectButton.setVisible(loginUser.getId() == authority.getId()
				&& (status == NotificationStatus.PENDING || status == NotificationStatus.APPROVED));
		commentAuthorityTextArea.setEnabled(approveButton.isVisible() || rejectButton.isVisible());
		withdrawButton.setVisible(loginUser.getId() == applicant.getId() && status == NotificationStatus.PENDING);
	}

	/**
	 * 詳細画面のURL文字列を返します。
	 * 
	 * @param notification 申請情報
	 * @return 詳細画面のURL
	 */
	private String getDetailPageUrlString(Notification notification) {

		PageParameters pageParameters = new PageParameters();
		pageParameters.set("id", notification.getId());

		String urlString = RequestCycle.get().getUrlRenderer().renderFullUrl(
				Url.parse(urlFor(DetailPage.class, pageParameters).toString()));

		return urlString;
	}
}
