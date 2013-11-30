package net.shinkasystems.kintai.page;

import java.util.Iterator;
import java.util.List;

import net.shinkasystems.kintai.KintaiConstants;
import net.shinkasystems.kintai.KintaiDB;
import net.shinkasystems.kintai.KintaiRole;
import net.shinkasystems.kintai.KintaiStatus;
import net.shinkasystems.kintai.KintaiType;
import net.shinkasystems.kintai.entity.ApplicationDao;
import net.shinkasystems.kintai.entity.ApplicationDaoImpl;
import net.shinkasystems.kintai.entity.sub.ApplicationData;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.link.StatelessLink;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.seasar.doma.jdbc.SelectOptions;
import org.seasar.doma.jdbc.tx.LocalTransaction;

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
	
	/**
	 * 
	 */
	public static final String PARAMETER_ID = "id";

	/**
	 * 
	 */
	private final DataView<ApplicationData> applicationDataView = new DataView<ApplicationData>(
			"index-data-view", new ApplicationDataProvider(), 100) {

		@Override
		protected void populateItem(Item<ApplicationData> item) {

			final ApplicationData applicationData = item.getModelObject();
			
			/*
			 * 
			 */
			final Label idLabel = new Label("id", applicationData.getId());
			final Label termLabel = new Label("term", KintaiConstants.DATE_FORMAT.format(applicationData.getTerm()));
			final Label typeLabel = new Label("type", KintaiType.valueOf(applicationData.getType()).display);
			final Label commentLabel = new Label("comment", applicationData.getCommentApplycant());
			final Label dateLabel = new Label("date", KintaiConstants.DATE_FORMAT.format(applicationData.getCreateDate()));
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
			 * 
			 */
			typeLink.add(typeLabel);
			
			
			/*
			 * 
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
	 * コンストラクタです。
	 */
	public IndexPage() {
		super();

		/*
		 * コンポーネントの生成
		 */
		final PagingNavigator pagingNavigator = new PagingNavigator("page-navigator", applicationDataView);

		/*
		 * コンポーネントの組立
		 */
		add(applicationDataView);
		add(pagingNavigator);
	}
}

/**
 * 
 * @author Aogiri
 *
 */
class ApplicationDataProvider extends SortableDataProvider<ApplicationData, String> {

	@Override
	public Iterator<? extends ApplicationData> iterator(long first, long count) {

		SelectOptions options = SelectOptions.get().offset((int) first).limit((int) count);

		List<ApplicationData> applicationDatas = null;

		LocalTransaction transaction = KintaiDB.getLocalTransaction();
		try {
			transaction.begin();

			final ApplicationDao dao = new ApplicationDaoImpl();

			applicationDatas = dao.selectApplicationData(options);

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

			final ApplicationDao dao = new ApplicationDaoImpl();

			size = dao.selectCountApplication();

		} finally {
			transaction.rollback();
		}
		return size;
	}

}