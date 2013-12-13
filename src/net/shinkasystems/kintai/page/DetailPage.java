package net.shinkasystems.kintai.page;

import java.sql.Date;

import net.shinkasystems.kintai.KintaiConstants;
import net.shinkasystems.kintai.KintaiDB;
import net.shinkasystems.kintai.KintaiRole;
import net.shinkasystems.kintai.KintaiSession;
import net.shinkasystems.kintai.KintaiStatus;
import net.shinkasystems.kintai.KintaiType;
import net.shinkasystems.kintai.component.ConfirmSubmitButton;
import net.shinkasystems.kintai.entity.Application;
import net.shinkasystems.kintai.entity.ApplicationDao;
import net.shinkasystems.kintai.entity.User;
import net.shinkasystems.kintai.entity.UserDao;
import net.shinkasystems.kintai.mail.KintaiMail;
import net.shinkasystems.kintai.mail.KintaiMailArgument;
import net.shinkasystems.kintai.panel.AlertPanel;
import net.shinkasystems.kintai.util.DaoFactory;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.HiddenField;
import org.apache.wicket.markup.html.form.validation.AbstractFormValidator;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.IRequestParameters;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.util.value.ValueMap;
import org.seasar.doma.jdbc.tx.LocalTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	 * 詳細画面のフォームです。
	 */
	private final Form<ValueMap> form = new Form<ValueMap>("detail-form") {

		@Override
		protected void onError() {

			alertPanel.setVisible(true);
		}

	};

	/**
	 * 
	 */
	private final HiddenField<Integer> idField = new HiddenField<Integer>("id-hidden", new Model<Integer>());

	/**
	 * 
	 */
	private final HiddenField<KintaiStatus> statusField = new HiddenField<>("status-hidden", new Model<KintaiStatus>());

	/**
	 * 承認ボタンです。
	 */
	private final Button approveButton = new ConfirmSubmitButton("approve", getString("confirm-approve-message")) {

		@Override
		public void onSubmit() {

			final Integer applicationID = idField.getModelObject();

			Application application;

			final LocalTransaction transaction = KintaiDB.getLocalTransaction();
			try {
				transaction.begin();

				final ApplicationDao dao = DaoFactory.createDaoImplements(ApplicationDao.class);

				application = dao.selectById(applicationID);

				application.setStatus(KintaiStatus.APPROVED.name());
				application.setUpdateDate(new Date(new java.util.Date().getTime()));
				dao.update(application);

				transaction.commit();

			} finally {
				transaction.rollback();
			}

			log.info("承認しました");

			/*
			 * メール送信処理
			 */
			final User applicant = getUser(application.getApplicantId());
			final User authority = getUser(applicant.getAuthorityId());

			final KintaiMailArgument argument = new KintaiMailArgument();
			argument.setReceiverName(applicant.getDisplayName());
			argument.setReceiverMailAddress(applicant.getEmailAddress());
			argument.setSenderName(authority.getDisplayName());
			argument.setSenderMailAddress(authority.getEmailAddress());
			argument.setTerm(KintaiConstants.DATE_FORMAT.format(application.getTerm()));
			argument.setForm(KintaiType.valueOf(application.getType()).display);
			argument.setComment(application.getCommentApplycant());

			KintaiMail.APPROVAL.send(argument);
		}
	};

	/**
	 * 却下ボタンです。
	 */
	private final Button rejectButton = new ConfirmSubmitButton("reject", getString("confirm-reject-message")) {

		@Override
		public void onSubmit() {

			final Integer applicationID = idField.getModelObject();

			Application application;

			final LocalTransaction transaction = KintaiDB.getLocalTransaction();
			try {
				transaction.begin();

				final ApplicationDao dao = DaoFactory.createDaoImplements(ApplicationDao.class);

				application = dao.selectById(applicationID);

				application.setStatus(KintaiStatus.REJECTED.name());
				application.setUpdateDate(new Date(new java.util.Date().getTime()));
				dao.update(application);

				transaction.commit();

			} finally {
				transaction.rollback();
			}

			log.info("却下しました");

			/*
			 * メール送信処理
			 */
			final User applicant = getUser(application.getApplicantId());
			final User authority = getUser(applicant.getAuthorityId());

			final KintaiMailArgument argument = new KintaiMailArgument();
			argument.setReceiverName(applicant.getDisplayName());
			argument.setReceiverMailAddress(applicant.getEmailAddress());
			argument.setSenderName(authority.getDisplayName());
			argument.setSenderMailAddress(authority.getEmailAddress());
			argument.setTerm(KintaiConstants.DATE_FORMAT.format(application.getTerm()));
			argument.setForm(KintaiType.valueOf(application.getType()).display);
			argument.setComment(application.getCommentApplycant());

			KintaiMail.REJECTION.send(argument);
		}
	};

	/**
	 * 差戻しボタンです。
	 */
	private final Button passbackButton = new ConfirmSubmitButton("passback", getString("confirm-withdraw-message")) {

		@Override
		public void onSubmit() {

			final int applicationID = idField.getModelObject();

			Application application;

			final LocalTransaction transaction = KintaiDB.getLocalTransaction();
			try {
				transaction.begin();

				final ApplicationDao dao = DaoFactory.createDaoImplements(ApplicationDao.class);

				application = dao.selectById(applicationID);

				application.setStatus(KintaiStatus.PASSBACK.name());
				application.setUpdateDate(new Date(new java.util.Date().getTime()));
				dao.update(application);

				transaction.commit();

			} finally {
				transaction.rollback();
			}

			log.info("差戻しました");

			/*
			 * メール送信処理
			 */
			final User applicant = getUser(application.getApplicantId());
			final User authority = getUser(applicant.getAuthorityId());

			final KintaiMailArgument argument = new KintaiMailArgument();
			argument.setReceiverName(applicant.getDisplayName());
			argument.setReceiverMailAddress(applicant.getEmailAddress());
			argument.setSenderName(authority.getDisplayName());
			argument.setSenderMailAddress(authority.getEmailAddress());
			argument.setTerm(KintaiConstants.DATE_FORMAT.format(application.getTerm()));
			argument.setForm(KintaiType.valueOf(application.getType()).display);
			argument.setComment(application.getCommentApplycant());

			KintaiMail.PASSBACK.send(argument);
		}
	};

	/**
	 * 取下げボタンです。
	 */
	private final Button withdrawButton = new ConfirmSubmitButton("withdraw", getString("confirm-passback-message")) {

		@Override
		public void onSubmit() {

			final Integer applicationID = idField.getModelObject();

			Application application;

			final LocalTransaction transaction = KintaiDB.getLocalTransaction();
			try {
				transaction.begin();

				final ApplicationDao dao = DaoFactory.createDaoImplements(ApplicationDao.class);

				application = dao.selectById(applicationID);

				application.setStatus(KintaiStatus.WITHDRAWN.name());
				application.setUpdateDate(new Date(new java.util.Date().getTime()));
				dao.update(application);

				transaction.commit();

			} finally {
				transaction.rollback();
			}

			log.info("取下げました");

			/*
			 * メール送信処理
			 */
			final User applicant = getUser(application.getApplicantId());
			final User authority = getUser(applicant.getAuthorityId());

			final KintaiMailArgument argument = new KintaiMailArgument();
			argument.setReceiverName(authority.getDisplayName());
			argument.setReceiverMailAddress(authority.getEmailAddress());
			argument.setSenderName(applicant.getDisplayName());
			argument.setSenderMailAddress(applicant.getEmailAddress());
			argument.setTerm(KintaiConstants.DATE_FORMAT.format(application.getTerm()));
			argument.setForm(KintaiType.valueOf(application.getType()).display);
			argument.setComment(application.getCommentApplycant());

			KintaiMail.WITHDRAWAL.send(argument);
		}
	};

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

		final Application application = getApplication(id);
		final User applicant = getUser(application.getApplicantId());
		final User authority = getUser(applicant.getAuthorityId());

		final KintaiType type = KintaiType.valueOf(application.getType());
		final KintaiStatus status = KintaiStatus.valueOf(application.getStatus());

		String updated = null;
		if (application.getUpdateDate() != null) {
			updated = KintaiConstants.DATE_FORMAT.format(application.getUpdateDate());
		} else {
			updated = "決裁されていません";
		}

		/*
		 * コンポーネントの生成
		 */
		final Label idLabel = new Label("id", application.getId());
		final Label termLabel = new Label("term", KintaiConstants.DATE_FORMAT.format(application.getTerm()));
		final Label applicantLabel = new Label("applicant", applicant.getDisplayName());
		final Label typeLabel = new Label("type", type.display);
		final MultiLineLabel commentLabel = new MultiLineLabel("comment", application.getCommentApplycant());
		final Label createdLabel = new Label("created", KintaiConstants.DATE_FORMAT.format(application.getCreateDate()));
		final Label statusLabel = new Label("status", status.display);
		final Label authorityLabel = new Label("authority", authority.getDisplayName());
		final Label updatedLabel = new Label("updated", updated);

		/*
		 * コンポーネントの編集
		 */
		alertPanel.setVisible(false);

		form.add(new StatusValidator(idField, statusField));

		if (status == KintaiStatus.PENDING) {
			statusLabel.add(new AttributeModifier("class", "label label-info"));
		} else if (status == KintaiStatus.APPROVED) {
			statusLabel.add(new AttributeModifier("class", "label label-success"));
		} else if (status == KintaiStatus.REJECTED) {
			statusLabel.add(new AttributeModifier("class", "label label-important"));
		} else if (status == KintaiStatus.WITHDRAWN) {
			statusLabel.add(new AttributeModifier("class", "label label-inverse"));
		} else if (status == KintaiStatus.PASSBACK) {
			statusLabel.add(new AttributeModifier("class", "label label-inverse"));
		}

		idField.setType(Integer.class);
		idField.setModelObject(id);
		statusField.setModelObject(status);

		if (loginUser.getId() == authority.getId()
				&& (status == KintaiStatus.PENDING || status == KintaiStatus.PASSBACK)) {
			approveButton.setVisible(true);
			approveButton.setVisible(true);
		} else {
			approveButton.setVisible(false);
			rejectButton.setVisible(false);
		}
		withdrawButton.setVisible(loginUser.getId() == applicant.getId() && status == KintaiStatus.PENDING);
		passbackButton.setVisible(loginUser.getId() == authority.getId()
				&& (status == KintaiStatus.APPROVED || status == KintaiStatus.REJECTED));

		/*
		 * コンポーネントの組立
		 */
		add(alertPanel);
		add(idLabel);
		add(termLabel);
		add(applicantLabel);
		add(typeLabel);
		add(commentLabel);
		add(createdLabel);
		add(statusLabel);
		add(authorityLabel);
		add(updatedLabel);

		form.add(idField);
		form.add(statusField);
		form.add(approveButton);
		form.add(rejectButton);
		form.add(withdrawButton);
		form.add(passbackButton);

		add(form);
	}

	/**
	 * 指定IDの申請情報を取得します。
	 * 
	 * @param id
	 * @return
	 */
	private static Application getApplication(int id) {

		LocalTransaction transaction = KintaiDB.getLocalTransaction();
		try {
			transaction.begin();

			final ApplicationDao dao = DaoFactory.createDaoImplements(ApplicationDao.class);

			return dao.selectById(id);

		} finally {
			transaction.rollback();
		}
	}

	/**
	 * 指定IDのユーザーを取得します。
	 * 
	 * @param id
	 * @return
	 */
	private static User getUser(int id) {

		LocalTransaction transaction = KintaiDB.getLocalTransaction();
		try {
			transaction.begin();

			final UserDao dao = DaoFactory.createDaoImplements(UserDao.class);

			return dao.selectById(id);

		} finally {
			transaction.rollback();
		}
	}
}

/**
 * 申請情報のステータスが表示時から変更されていないかチェックします。
 * 
 * @author Aogiri
 *
 */
class StatusValidator extends AbstractFormValidator {

	private final FormComponent<Integer> idComponent;

	private final FormComponent<KintaiStatus> statusComponent;

	public StatusValidator(FormComponent<Integer> idComponent, FormComponent<KintaiStatus> statusComponent) {
		super();
		this.idComponent = idComponent;
		this.statusComponent = statusComponent;
	}

	@Override
	public FormComponent<?>[] getDependentFormComponents() {
		return new FormComponent<?>[] { idComponent, statusComponent };
	}

	@Override
	public void validate(Form<?> form) {

		final int id = idComponent.getModelObject();
		final KintaiStatus status = statusComponent.getModelObject();

		Application current;

		LocalTransaction transaction = KintaiDB.getLocalTransaction();
		try {
			transaction.begin();

			final ApplicationDao dao = DaoFactory.createDaoImplements(ApplicationDao.class);

			current = dao.selectById(id);

		} finally {
			transaction.rollback();
		}

		if (status != KintaiStatus.valueOf(current.getStatus())) {
			error(null);
		}
	}
}
