package net.shinkasystems.kintai.page.kintai;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.shinkasystems.kintai.KintaiConstants;
import net.shinkasystems.kintai.KintaiRole;
import net.shinkasystems.kintai.KintaiSession;
import net.shinkasystems.kintai.component.KintaiTypeChoiceRendere;
import net.shinkasystems.kintai.component.UserChoiceRenderer;
import net.shinkasystems.kintai.component.UserOption;
import net.shinkasystems.kintai.component.UserOptionUtility;
import net.shinkasystems.kintai.domain.KintaiType;
import net.shinkasystems.kintai.entity.Application;
import net.shinkasystems.kintai.entity.User;
import net.shinkasystems.kintai.mail.KintaiMail;
import net.shinkasystems.kintai.mail.KintaiMailArgument;
import net.shinkasystems.kintai.page.DefaultLayoutPage;
import net.shinkasystems.kintai.panel.AlertPanel;
import net.shinkasystems.kintai.service.kintai.EntryService;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.value.ValueMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

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

			if (applicantDropDownChoice.getModelObject() != null) {
				application.setApplicantId(applicantDropDownChoice.getModelObject().getId());
				application.setProxyId(((KintaiSession) KintaiSession.get()).getUser().getId());
			} else {
				application.setApplicantId(((KintaiSession) KintaiSession.get()).getUser().getId());
			}
			application.setType(typeDropDownChoice.getModelObject());
			application.setTerm(new Date(termTextField.getModelObject().getTime()));
			application.setCommentApplycant(commentTextArea.getModelObject());

			entryService.entry(application);

			/*
			 * 詳細ページの URL を作成する
			 */
			PageParameters pageParameters = new PageParameters();
			pageParameters.set("id", application.getId());

			String urlString = RequestCycle.get().getUrlRenderer().renderFullUrl(
					Url.parse(urlFor(DetailPage.class, pageParameters).toString()));

			/*
			 * メール送信処理
			 */
			final User sender = entryService.getUser(application.getApplicantId());
			final User receiver = entryService.getUser(sender.getAuthorityId());

			final KintaiMailArgument argument = new KintaiMailArgument();
			argument.setReceiverName(receiver.getDisplayName());
			argument.setReceiverMailAddress(receiver.getEmailAddress());
			argument.setSenderName(sender.getDisplayName());
			argument.setSenderMailAddress(sender.getEmailAddress());
			argument.setTerm(KintaiConstants.DATE_FORMAT.format(termTextField.getModelObject().getTime()));
			argument.setForm(typeDropDownChoice.getModelObject().display);
			argument.setComment(commentTextArea.getModelObject());
			argument.setUrl(urlString);

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

	@Inject
	EntryService entryService;

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
}