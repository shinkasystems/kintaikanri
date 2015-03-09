package net.shinkasystems.kintai.page.kintai;

import java.util.Date;

import net.shinkasystems.kintai.KintaiConstants;
import net.shinkasystems.kintai.KintaiRole;
import net.shinkasystems.kintai.KintaiSession;
import net.shinkasystems.kintai.KintaiStatus;
import net.shinkasystems.kintai.KintaiType;
import net.shinkasystems.kintai.component.ConfirmSubmitButton;
import net.shinkasystems.kintai.component.StatusValidator;
import net.shinkasystems.kintai.entity.Application;
import net.shinkasystems.kintai.entity.User;
import net.shinkasystems.kintai.mail.KintaiMail;
import net.shinkasystems.kintai.mail.KintaiMailArgument;
import net.shinkasystems.kintai.page.DefaultLayoutPage;
import net.shinkasystems.kintai.panel.AlertPanel;
import net.shinkasystems.kintai.panel.InfomationPanel;
import net.shinkasystems.kintai.service.kintai.DetailService;

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
import org.apache.wicket.request.cycle.RequestCycle;
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

	private final IModel<KintaiStatus> statusModel = new Model<KintaiStatus>();

	private final HiddenField<KintaiStatus> statusField = new HiddenField<>("status-hidden", statusModel);

	private final IModel<String> commentAuthorityModel = new Model<String>();

	private final TextArea<String> commentAuthorityTextArea =
			new TextArea<>("comment-authority-textarea", commentAuthorityModel);

	private final Label idLabel = new Label("id", idModel);

	private final IModel<Date> termModel = new Model<Date>();

	private final Label termLabel = DateLabel.forDatePattern("term", termModel, KintaiConstants.DATE_PATTERN);

	private final IModel<String> applicantModel = new Model<String>();

	private final Label applicantLabel = new Label("applicant", applicantModel);

	private final IModel<KintaiType> typeModel = new Model<KintaiType>();

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

			final Integer applicationID = idField.getModelObject();
			final Application application = detailService.getApplication(applicationID);

			application.setCommentAuthority(commentAuthorityTextArea.getModelObject());

			detailService.approve(application);

			/*
			 * メール送信処理
			 */
			final User applicant = detailService.getUser(application.getApplicantId());
			final User authority = detailService.getUser(applicant.getAuthorityId());

			final KintaiMailArgument argument = new KintaiMailArgument();
			argument.setReceiverName(applicant.getDisplayName());
			argument.setReceiverMailAddress(applicant.getEmailAddress());
			argument.setSenderName(authority.getDisplayName());
			argument.setSenderMailAddress(authority.getEmailAddress());
			argument.setTerm(KintaiConstants.DATE_FORMAT.format(application.getTerm()));
			argument.setForm(KintaiType.valueOf(application.getType()).display);
			argument.setComment(application.getCommentAuthority());

			KintaiMail.APPROVAL.send(argument);

			/*
			 * ページ情報の更新
			 */
			updatePage(application, applicant, authority, ((KintaiSession) KintaiSession.get()).getUser());

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

			final Integer applicationID = idField.getModelObject();
			final Application application = detailService.getApplication(applicationID);

			application.setCommentAuthority(commentAuthorityTextArea.getModelObject());

			detailService.reject(application);

			/*
			 * メール送信処理
			 */
			final User applicant = detailService.getUser(application.getApplicantId());
			final User authority = detailService.getUser(applicant.getAuthorityId());

			final KintaiMailArgument argument = new KintaiMailArgument();
			argument.setReceiverName(applicant.getDisplayName());
			argument.setReceiverMailAddress(applicant.getEmailAddress());
			argument.setSenderName(authority.getDisplayName());
			argument.setSenderMailAddress(authority.getEmailAddress());
			argument.setTerm(KintaiConstants.DATE_FORMAT.format(application.getTerm()));
			argument.setForm(KintaiType.valueOf(application.getType()).display);
			argument.setComment(application.getCommentAuthority());

			KintaiMail.REJECTION.send(argument);

			/*
			 * ページ情報の更新
			 */
			updatePage(application, applicant, authority, ((KintaiSession) KintaiSession.get()).getUser());

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

			final Integer applicationID = idField.getModelObject();
			final Application application = detailService.getApplication(applicationID);

			application.setCommentAuthority(commentAuthorityTextArea.getModelObject());

			detailService.withdraw(application);

			/*
			 * メール送信処理
			 */
			final User applicant = detailService.getUser(application.getApplicantId());
			final User authority = detailService.getUser(applicant.getAuthorityId());

			final KintaiMailArgument argument = new KintaiMailArgument();
			argument.setReceiverName(authority.getDisplayName());
			argument.setReceiverMailAddress(authority.getEmailAddress());
			argument.setSenderName(applicant.getDisplayName());
			argument.setSenderMailAddress(applicant.getEmailAddress());
			argument.setTerm(KintaiConstants.DATE_FORMAT.format(application.getTerm()));
			argument.setForm(KintaiType.valueOf(application.getType()).display);
			argument.setComment(application.getCommentApplycant());

			KintaiMail.WITHDRAWAL.send(argument);

			/*
			 * ページ情報の更新
			 */
			updatePage(application, applicant, authority, ((KintaiSession) KintaiSession.get()).getUser());

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

		final Application application = detailService.getApplication(id);
		final User applicant = detailService.getUser(application.getApplicantId());
		final User authority = detailService.getUser(applicant.getAuthorityId());

		/*
		 * コンポーネントの編集
		 */
		alertPanel.setVisible(false);
		infomationPanel.setVisible(false);

		idField.setType(Integer.class);
		idModel.setObject(id);

		form.add(new StatusValidator(idField, statusField, detailService));

		updatePage(application, applicant, authority, loginUser);

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
	 * @param application
	 * @param applicant
	 * @param authority
	 * @param loginUser
	 */
	private void updatePage(Application application, User applicant, User authority, User loginUser) {

		final KintaiType type = KintaiType.valueOf(application.getType());
		final KintaiStatus status = KintaiStatus.valueOf(application.getStatus());

		idModel.setObject(application.getId());
		statusModel.setObject(status);
		termModel.setObject(application.getTerm());
		applicantModel.setObject(applicant.getDisplayName());
		typeModel.setObject(type);
		commentModel.setObject(application.getCommentApplycant());
		createdModel.setObject(application.getCreateDate());
		authorityModel.setObject(authority.getDisplayName());
		updatedModel.setObject(application.getUpdateDate());
		commentAuthorityModel.setObject(application.getCommentAuthority());

		if (status == KintaiStatus.PENDING) {
			statusLabel.add(new AttributeModifier("class", "label label-info"));
		} else if (status == KintaiStatus.APPROVED) {
			statusLabel.add(new AttributeModifier("class", "label label-success"));
		} else if (status == KintaiStatus.REJECTED) {
			statusLabel.add(new AttributeModifier("class", "label label-important"));
		} else if (status == KintaiStatus.WITHDRAWN) {
			statusLabel.add(new AttributeModifier("class", "label label-inverse"));
		}

		approveButton.setVisible(loginUser.getId() == authority.getId()
				&& (status == KintaiStatus.PENDING || status == KintaiStatus.REJECTED));
		rejectButton.setVisible(loginUser.getId() == authority.getId()
				&& (status == KintaiStatus.PENDING || status == KintaiStatus.APPROVED));
		commentAuthorityTextArea.setEnabled(approveButton.isVisible() || rejectButton.isVisible());
		withdrawButton.setVisible(loginUser.getId() == applicant.getId() && status == KintaiStatus.PENDING);
	}

}
