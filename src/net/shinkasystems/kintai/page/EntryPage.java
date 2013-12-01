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
import net.shinkasystems.kintai.entity.Application;
import net.shinkasystems.kintai.entity.ApplicationDao;
import net.shinkasystems.kintai.entity.ApplicationDaoImpl;
import net.shinkasystems.kintai.entity.User;
import net.shinkasystems.kintai.entity.UserDao;
import net.shinkasystems.kintai.entity.UserDaoImpl;
import net.shinkasystems.kintai.panel.AlertPanel;

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
import org.seasar.doma.jdbc.tx.LocalTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 申請情報の登録画面です。
 * 
 * @author Aogiri
 * 
 */
@AuthorizeInstantiation({ KintaiRole.USER })
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

			LocalTransaction transaction = KintaiDB.getLocalTransaction();
			try {
				transaction.begin();

				final ApplicationDao dao = new ApplicationDaoImpl();

				final Application application = new Application();

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

				transaction.commit();

				log.info("勤怠情報を申請しました。" + application);

			} finally {
				transaction.rollback();
			}
			
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

		/*
		 * TODO
		 * 代理申請の活性制御
		 */
		applicantOptions.addAll(getApplicant(((KintaiSession) KintaiSession.get()).getUser().getId()));
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
	 * 代理申請が可能な申請者を取得します。
	 * ログインユーザーが決裁者となっている申請者の申請を代理することができます。
	 * 
	 * @param authorityId
	 * @return
	 */
	private List<UserOption> getApplicant(int authorityId) {
		
		final List<UserOption> applicantOptions = new ArrayList<UserOption>();

		LocalTransaction transaction = KintaiDB.getLocalTransaction();
		try {
			transaction.begin();

			final UserDao dao = new UserDaoImpl();
			
			for (User user : dao.selectByAuthorityID(authorityId)) {
				applicantOptions.add(new UserOption(user.getId(), user.getDisplayName()));
			}

		} finally {
			transaction.rollback();
		}
		
		return applicantOptions;
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