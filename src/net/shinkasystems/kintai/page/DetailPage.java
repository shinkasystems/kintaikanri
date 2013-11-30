package net.shinkasystems.kintai.page;

import java.sql.Date;

import net.shinkasystems.kintai.KintaiConstants;
import net.shinkasystems.kintai.KintaiDB;
import net.shinkasystems.kintai.KintaiRole;
import net.shinkasystems.kintai.KintaiSession;
import net.shinkasystems.kintai.KintaiStatus;
import net.shinkasystems.kintai.KintaiType;
import net.shinkasystems.kintai.entity.Application;
import net.shinkasystems.kintai.entity.ApplicationDao;
import net.shinkasystems.kintai.entity.ApplicationDaoImpl;
import net.shinkasystems.kintai.entity.User;
import net.shinkasystems.kintai.entity.UserDao;
import net.shinkasystems.kintai.entity.UserDaoImpl;
import net.shinkasystems.kintai.panel.AlertPanel;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.basic.Label;
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
		}

		/*
		 * コンポーネントの生成
		 */
		final Label idLabel = new Label("id", application.getId());
		final Label termLabel = new Label("term", KintaiConstants.DATE_FORMAT.format(application.getTerm()));
		final Label applicantLabel = new Label("applicant", applicant.getDisplayName());
		final Label typeLabel = new Label("type", type.display);
		final Label commentLabel = new Label("comment", application.getCommentApplycant());
		final Label createdLabel = new Label("created", KintaiConstants.DATE_FORMAT.format(application.getCreateDate()));
		final Label statusLabel = new Label("status", status.display);
		final Label authorityLabel = new Label("authority", authority.getDisplayName());
		final Label updatedLabel = new Label("updated", updated);

		final HiddenField<Integer> idField = new HiddenField<Integer>("id-hidden", new Model<Integer>(
				application.getId()));
		final HiddenField<KintaiStatus> statusField = new HiddenField<>("status-hidden",
				new Model<KintaiStatus>(status));

		final Button approveButton = new Button("approve") {

			@Override
			public void onSubmit() {
				updateStatus(id, KintaiStatus.APPROVED);
			}
		};
		final Button rejectButton = new Button("reject") {

			@Override
			public void onSubmit() {
				updateStatus(id, KintaiStatus.REJECTED);
			}
		};
		final Button passbackButton = new Button("passback") {

			@Override
			public void onSubmit() {
				updateStatus(id, KintaiStatus.PASSBACK);
			}
		};
		final Button withdrawButton = new Button("withdraw") {

			@Override
			public void onSubmit() {
				updateStatus(id, KintaiStatus.WITHDRAWN);
			}
		};

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

		updatedLabel.setDefaultModelObject("まだ決裁されていません");

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

			final ApplicationDao dao = new ApplicationDaoImpl();

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

			final UserDao dao = new UserDaoImpl();

			return dao.selectById(id);

		} finally {
			transaction.rollback();
		}
	}

	/**
	 * 申請情報のステータスを更新します。
	 * 
	 * @param status
	 */
	private static void updateStatus(int id, KintaiStatus status) {

		LocalTransaction transaction = KintaiDB.getLocalTransaction();
		try {
			transaction.begin();

			final ApplicationDao dao = new ApplicationDaoImpl();
			
			final Application application = dao.selectById(id);

			application.setStatus(status.name());
			application.setUpdateDate(new Date(new java.util.Date().getTime()));
			dao.update(application);

			transaction.commit();

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

			final ApplicationDao dao = new ApplicationDaoImpl();

			current = dao.selectById(id);

		} finally {
			transaction.rollback();
		}

		if (status != KintaiStatus.valueOf(current.getStatus())) {
			error(null);
		}
	}
}
