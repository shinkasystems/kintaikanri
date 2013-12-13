package net.shinkasystems.kintai.page;

import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import net.shinkasystems.kintai.KintaiConstants;
import net.shinkasystems.kintai.KintaiDB;
import net.shinkasystems.kintai.KintaiRole;
import net.shinkasystems.kintai.KintaiStatus;
import net.shinkasystems.kintai.KintaiType;
import net.shinkasystems.kintai.component.UserChoiceRenderer;
import net.shinkasystems.kintai.component.UserOption;
import net.shinkasystems.kintai.component.UserOptionUtility;
import net.shinkasystems.kintai.entity.ApplicationDao;
import net.shinkasystems.kintai.entity.sub.ApplicationData;
import net.shinkasystems.kintai.panel.PaginationPanel;
import net.shinkasystems.kintai.util.DaoFactory;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.OrderByBorder;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.link.StatelessLink;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.value.ValueMap;
import org.seasar.doma.jdbc.SelectOptions;
import org.seasar.doma.jdbc.tx.LocalTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 申請情報の一覧画面です。 一覧画面は勤怠管理ツールのホーム画面でもあります。
 * 
 * アクセス可能な、ユーザー権限は以下の通りです。
 * <ul>
 * <li>管理ユーザー</li>
 * <li>一般ユーザー</li>
 * <li>期限切れ一般ユーザー</li>
 * </ul>
 * 
 * 期限切れ一般ユーザーには、パスワードの有効期限が切れている旨のメッセージが表示されます。
 * 
 * @author Aogiri
 * 
 */
@AuthorizeInstantiation({ KintaiRole.ADMIN, KintaiRole.USER, KintaiRole.EXPIRED_USER })
public class IndexPage extends DefaultLayoutPage {

	/** ロガー */
	private static final Logger log = LoggerFactory.getLogger(IndexPage.class);

	/**
	 * パラメータ「id」のキー名称です。
	 */
	public static final String PARAMETER_ID = "id";

	/**
	 * 申請情報一覧のデータプロバイダーです。
	 */
	private ApplicationDataProvider applicationDataProvider = new ApplicationDataProvider();

	/**
	 * 申請情報一覧です。
	 */
	private final DataView<ApplicationData> applicationDataView = new DataView<ApplicationData>(
			"index-data-view", applicationDataProvider, 100) {

		@Override
		protected void populateItem(Item<ApplicationData> item) {

			final ApplicationData applicationData = item.getModelObject();

			if (applicationData.getCommentApplycant().length() > 20) {
				applicationData.setCommentApplycant(applicationData.getCommentApplycant().substring(0, 20) + "...");
			}
			
			/*
			 * コンポーネントの生成
			 */
			final Label idLabel = new Label("id", applicationData.getId());
			final Label termLabel = new Label("term", KintaiConstants.DATE_FORMAT.format(applicationData.getTerm()));
			final Label typeLabel = new Label("type", KintaiType.valueOf(applicationData.getType()).display);
			final Label commentLabel = new Label("comment", applicationData.getCommentApplycant());
			final Label dateLabel = new Label("date", KintaiConstants.DATE_FORMAT.format(applicationData
					.getCreateDate()));
			final Label applicantLabel = new Label("applicant", applicationData.getApplicantDisplayName());
			final Label statusLabel = new Label("status", KintaiStatus.valueOf(applicationData.getStatus()).display);

			final Link<String> typeLink = new StatelessLink<String>("link") {

				@Override
				public void onClick() {

					final PageParameters parameters = new PageParameters();

					parameters.set(PARAMETER_ID, applicationData.getId());

					IndexPage.this.setResponsePage(DetailPage.class, parameters);
				}

			};

			/*
			 * コンポーネントの編集
			 */
			typeLink.add(typeLabel);

			/*
			 * コンポーネントの組立
			 */
			item.add(idLabel);
			item.add(termLabel);
			item.add(typeLink);
			item.add(commentLabel);
			item.add(dateLabel);
			item.add(applicantLabel);
			item.add(statusLabel);
		}
	};

	/**
	 * 検索フォームです。
	 */
	private final Form<ValueMap> form = new Form<ValueMap>("search-form") {

		@Override
		protected void onError() {
			// TODO 自動生成されたメソッド・スタブ
			super.onError();
		}

		@Override
		protected void onSubmit() {

			java.sql.Date from = null;
			java.sql.Date to = null;
			Integer applicantId = null;
			KintaiStatus status = null;

			if (fromTextField.getModelObject() != null) {
				from = new java.sql.Date(fromTextField.getModelObject().getTime());
			}
			if (toTextField.getModelObject() != null) {
				to = new java.sql.Date(toTextField.getModelObject().getTime());
			}
			if (userDropDownChoice.getModelObject() != null) {
				applicantId = userDropDownChoice.getModelObject().getId();
			}
			if (statusDropDownChoice.getModelObject() != null) {
				status = statusDropDownChoice.getModelObject();
			}

			applicationDataProvider.setFrom(from);
			applicationDataProvider.setTo(to);
			applicationDataProvider.setApplicantId(applicantId);
			applicationDataProvider.setStatus(status);
			applicationDataProvider.setSort("term", SortOrder.DESCENDING);
		}

	};

	/**
	 * 「From」の入力フィールドです。
	 */
	private final DateTextField fromTextField = new DateTextField("from", new Model<Date>(),
			KintaiConstants.DATE_PATTERN);

	/**
	 * 「To」の入力フィールドです。
	 */
	private final DateTextField toTextField = new DateTextField("to", new Model<Date>(), KintaiConstants.DATE_PATTERN);

	/**
	 * 申請者の選択コンポーネントです。
	 */
	private final DropDownChoice<UserOption> userDropDownChoice = new DropDownChoice<UserOption>(
			"applicant", new Model<UserOption>(), UserOptionUtility.getUserOptions(), new UserChoiceRenderer());

	/**
	 * ステータスの選択コンポーネントです。
	 */
	private final DropDownChoice<KintaiStatus> statusDropDownChoice = new DropDownChoice<KintaiStatus>(
			"status", new Model<KintaiStatus>(), Arrays.asList(KintaiStatus.values()), new StatusChoiceRenderer());

	/**
	 * コンストラクタです。
	 */
	public IndexPage() {
		super();

		/*
		 * コンポーネントの生成
		 */
		final OrderByBorder<String> termOrderByBorder = new OrderByBorder<String>("orderByTerm", "term",
				applicationDataProvider) {

			@Override
			protected void onSortChanged() {
				applicationDataView.setCurrentPage(0);
			}

		};
		final PagingNavigator pagingNavigator = new PaginationPanel("page-navigator", applicationDataView);

		/*
		 * コンポーネントの編集
		 */
		fromTextField.add(new DatePicker());
		toTextField.add(new DatePicker());

		/*
		 * コンポーネントの組立
		 */
		add(termOrderByBorder);
		add(applicationDataView);
		add(pagingNavigator);

		form.add(fromTextField);
		form.add(toTextField);
		form.add(userDropDownChoice);
		form.add(statusDropDownChoice);

		add(form);
	}
}

/**
 * 申請情報一覧のデータプロバイダーです。
 * 
 * @author Aogiri
 *
 */
class ApplicationDataProvider extends SortableDataProvider<ApplicationData, String> {

	private java.sql.Date from;

	private java.sql.Date to;

	private Integer applicantId;

	private KintaiStatus status;

	/**
	 * コンストラクタです。
	 */
	public ApplicationDataProvider() {
		this(new java.sql.Date(new Date().getTime()), null, null, null);
	}

	public ApplicationDataProvider(java.sql.Date from, java.sql.Date to, Integer applicantId, KintaiStatus status) {
		super();
		this.from = from;
		this.to = to;
		this.applicantId = applicantId;
		this.status = status;

		setSort("term", SortOrder.ASCENDING);
	}

	@Override
	public Iterator<? extends ApplicationData> iterator(long first, long count) {

		SelectOptions options = SelectOptions.get().offset((int) first).limit((int) count);
		String orderBy = "order by " + getSort().getProperty() + (getSort().isAscending() ? " ASC" : " DESC");

		List<ApplicationData> applicationDatas = null;

		LocalTransaction transaction = KintaiDB.getLocalTransaction();
		try {
			transaction.begin();

			final ApplicationDao dao = DaoFactory.createDaoImplements(ApplicationDao.class);

			applicationDatas = dao.selectApplicationData(
					options,
					from,
					to,
					applicantId,
					status != null ? status.name() : null,
					orderBy);

		} finally {
			transaction.rollback();
		}

		return applicationDatas.iterator();
	}

	@Override
	public IModel<ApplicationData> model(ApplicationData applicationData) {
		return new Model<ApplicationData>(applicationData);
	}

	@Override
	public long size() {

		long size = 0;

		LocalTransaction transaction = KintaiDB.getLocalTransaction();
		try {
			transaction.begin();

			final ApplicationDao dao = DaoFactory.createDaoImplements(ApplicationDao.class);

			size = dao.selectCountApplication();

		} finally {
			transaction.rollback();
		}
		return size;
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

	public KintaiStatus getStatus() {
		return status;
	}

	public void setStatus(KintaiStatus status) {
		this.status = status;
	}
}

/**
 * ステータス選択コンポーネントのレンダラーです。
 * 
 * @author Aogiri
 *
 */
class StatusChoiceRenderer implements IChoiceRenderer<KintaiStatus> {

	@Override
	public Object getDisplayValue(KintaiStatus status) {
		return status.display;
	}

	@Override
	public String getIdValue(KintaiStatus status, int index) {
		return status.name();
	}

}
