package net.shinkasystems.kintai.entity.sub;

import java.io.Serializable;

import net.shinkasystems.kintai.entity.Application;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;

@Entity
public class ApplicationData extends Application implements Serializable {
	
	/**
	 * 申請者の表示名です。
	 */
	@Column(name = "APPLICANT_DISPLAY_NAME")
	private String applicantDisplayName;

	/**
	 * 
	 * @return
	 */
	public String getApplicantDisplayName() {
		return applicantDisplayName;
	}

	/**
	 * 
	 * @param applicantDisplayName
	 */
	public void setApplicantDisplayName(String applicantDisplayName) {
		this.applicantDisplayName = applicantDisplayName;
	}

}
