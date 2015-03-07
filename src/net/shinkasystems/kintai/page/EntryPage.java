package net.shinkasystems.kintai.page;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.shinkasystems.kintai.KintaiConstants;
import net.shinkasystems.kintai.KintaiDB;
import net.shinkasystems.kintai.KintaiRole;
import net.shinkasystems.kintai.KintaiSession;
import net.shinkasystems.kintai.KintaiStatus;
import net.shinkasystems.kintai.KintaiType;
import net.shinkasystems.kintai.component.UserChoiceRenderer;
import net.shinkasystems.kintai.component.UserOption;
import net.shinkasystems.kintai.component.UserOptionUtility;
import net.shinkasystems.kintai.entity.Application;
import net.shinkasystems.kintai.entity.ApplicationDao;
import net.shinkasystems.kintai.entity.User;
import net.shinkasystems.kintai.entity.UserDao;
import net.shinkasystems.kintai.mail.KintaiMail;
import net.shinkasystems.kintai.mail.KintaiMailArgument;
import net.shinkasystems.kintai.panel.AlertPanel;
import net.shinkasystems.kintai.util.DaoFactory;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.value.ValueMap;
import org.seasar.doma.jdbc.tx.TransactionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 申請情報の登録画面です。
 * 
 * @author Aogiri
 * 
 */
@AuthorizeInstantiation({ KintaiRole.ADMIN, KintaiRole.USER })
public class EntryPage extends DefaultLayoutPage {

	/** ロガー */
	private static final Logger log = LoggerFactory.getLogger(EntryPage.class);

	/**
	 * アラートパネルです。
	 */
	private final Panel alertPanel = new AlertPanel("alert-panel");

	/**
	 * 申請を行うフォームです。
	 */
	private final Form<ValueMap> entryForm = new Form<ValueMap>("entry-form") {

		@Override
		protected void onValidate() {

			alertPanel.setVisible(true);
		}

		@Override
		protected void onSubmit() {

			/*
			 * 申請処理
			 */
			final Application application = new Application();

			TransactionManager transactionManager = KintaiDB.singleton().getTransactionManager();

			transactionManager.required(() -> {

				final ApplicationDao dao = DaoFactory.createDaoImplements(ApplicationDao.class);

				if (applicantDropDownChoice.getModelObject() != null) {
					application.setApplicantId(applicantDropDownChoice.getModelObject().getId());
					application.setProxyId(((KintaiSession) KintaiSession.get()).getUser().getId());
				} else {
					application.setApplicantId(((KintaiSession) KintaiSession.get()).getUser().getId());
				}
				application.setType(typeDropDownChoice.getModelObject().name());
				application.setTerm(new Date(termTextField.getModelObject().getTime()));
				application.setCommentApplycant(commentTextArea.getModelObject());
				application.setCreateDate(new Date(new java.util.Date().getTime()));
				application.setStatus(KintaiStatus.PENDING.name());

				dao.insert(application);

				log.info("勤怠情報を申請しました。" + application);
			});

			/*
			 * メール送信処理
			 */
			final User sender = getUser(application.getApplicantId());
			final User receiver = getUser(sender.getAuthorityId());

			final KintaiMailArgument argument = new KintaiMailArgument();
			argument.setReceiverName(receiver.getDisplayName());
			argument.setReceiverMailAddress(receiver.getEmailAddress());
			argument.setSenderName(sender.getDisplayName());
			argument.setSenderMailAddress(sender.getEmailAddress());
			argument.setTerm(KintaiConstants.DATE_FORMAT.format(termTextField.getModelObject().getTime()));
			argument.setForm(typeDropDownChoice.getModelObject().display);
			argument.setComment(commentTextArea.getModelObject());

			KintaiMail.ENTRY.send(argument);

			setResponsePage(IndexPage.class);
		}

	};

	/**
	 * 勤怠申請の種類を管理するドロップダウンです。
	 */
	private final DropDownChoice<KintaiType> typeDropDownChoice = new DropDownChoice<KintaiType>(
			"type",
			new Model<KintaiType>(),
			Arrays.asList(KintaiType.values()),
			new KintaiTypeChoiceRendere());

	/**
	 * 期日の入力フィールドです。
	 */
	private final DateTextField termTextField = new DateTextField("term", new Model<java.util.Date>(),
			KintaiConstants.DATE_PATTERN);

	/**
	 * 事由等のコメントを入力するテキストエリアです。
	 */
	private final TextArea<String> commentTextArea = new TextArea<>("comment", new Model<String>());

	/**
	 * 代理申請が可能な申請者のリストです。
	 */
	private final List<UserOption> applicantOptions = new ArrayList<UserOption>();

	/**
	 * 代理申請を行う場合の申請者を管理するドロップダウンです。
	 */
	private final DropDownChoice<UserOption> applicantDropDownChoice = new DropDownChoice<UserOption>(
			"applicant", new Model<UserOption>(), applicantOptions, new UserChoiceRenderer());

	/**
	 * コンストラクタです。
	 */
	public EntryPage() {
		super();

		/*
		 * コンポーネントの編集
		 */
		alertPanel.setVisible(false);
		typeDropDownChoice.setRequired(true);
		termTextField.setRequired(true);
		termTextField.add(new DatePicker());
		commentTextArea.setRequired(true);

		applicantOptions.addAll(UserOptionUtility.getUserOptions(
				((KintaiSession) KintaiSession.get()).getUser().getId()));
		if (applicantOptions.size() > 0) {
			applicantDropDownChoice.setEnabled(true);
		} else {
			applicantDropDownChoice.setEnabled(false);
		}

		/*
		 * コンポーネントの組立
		 */
		entryForm.add(typeDropDownChoice);
		entryForm.add(termTextField);
		entryForm.add(commentTextArea);
		entryForm.add(applicantDropDownChoice);

		add(alertPanel);
		add(entryForm);
	}

	/**
	 * 指定IDのユーザーを取得します。
	 * 
	 * @param id
	 * @return
	 */
	private static User getUser(int id) {

		TransactionManager transactionManager = KintaiDB.singleton()
				.getTransactionManager();

		return transactionManager.required(() -> {

			final UserDao dao = DaoFactory.createDaoImplements(UserDao.class);

			return dao.selectById(id);
		});
	}
}

/**
 * 
 * @author Aogiri
 *
 */
class KintaiTypeChoiceRendere implements IChoiceRenderer<KintaiType> {

	@Override
	public Object getDisplayValue(KintaiType type) {
		return type.display;
	}

	@Override
	public String getIdValue(KintaiType type, int index) {
		return type.name();
	}

}