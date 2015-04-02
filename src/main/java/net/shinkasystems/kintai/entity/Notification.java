package net.shinkasystems.kintai.entity;

import java.time.LocalDate;

import net.shinkasystems.kintai.domain.NotificationStatus;
import net.shinkasystems.kintai.domain.NotificationType;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

/**
 * 
 */
@Entity(listener = NotificationListener.class)
@Table(name = "NOTIFICATION")
public class Notification {

	/**  */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	Integer id;

	/**  */
	@Column(name = "APPLICANT_ID")
	Integer applicantId;

	/**  */
	@Column(name = "PROXY_ID")
	Integer proxyId;

	/**  */
	@Column(name = "TYPE")
	NotificationType type;

	/**  */
	@Column(name = "TERM")
	LocalDate term;

	/**  */
	@Column(name = "COMMENT_APPLYCANT")
	String commentApplycant;

	/**  */
	@Column(name = "COMMENT_AUTHORITY")
	String commentAuthority;

	/**  */
	@Column(name = "STATUS")
	NotificationStatus status;

	/**  */
	@Column(name = "CREATE_DATE")
	LocalDate createDate;

	/**  */
	@Column(name = "UPDATE_DATE")
	LocalDate updateDate;

	/** 
	 * Returns the id.
	 * 
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/** 
	 * Sets the id.
	 * 
	 * @param id the id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/** 
	 * Returns the applicantId.
	 * 
	 * @return the applicantId
	 */
	public Integer getApplicantId() {
		return applicantId;
	}

	/** 
	 * Sets the applicantId.
	 * 
	 * @param applicantId the applicantId
	 */
	public void setApplicantId(Integer applicantId) {
		this.applicantId = applicantId;
	}

	/** 
	 * Returns the proxyId.
	 * 
	 * @return the proxyId
	 */
	public Integer getProxyId() {
		return proxyId;
	}

	/** 
	 * Sets the proxyId.
	 * 
	 * @param proxyId the proxyId
	 */
	public void setProxyId(Integer proxyId) {
		this.proxyId = proxyId;
	}

	/** 
	 * Returns the type.
	 * 
	 * @return the type
	 */
	public NotificationType getType() {
		return type;
	}

	/** 
	 * Sets the type.
	 * 
	 * @param type the type
	 */
	public void setType(NotificationType type) {
		this.type = type;
	}

	/** 
	 * Returns the term.
	 * 
	 * @return the term
	 */
	public LocalDate getTerm() {
		return term;
	}

	/** 
	 * Sets the term.
	 * 
	 * @param term the term
	 */
	public void setTerm(LocalDate term) {
		this.term = term;
	}

	/** 
	 * Returns the commentApplycant.
	 * 
	 * @return the commentApplycant
	 */
	public String getCommentApplycant() {
		return commentApplycant;
	}

	/** 
	 * Sets the commentApplycant.
	 * 
	 * @param commentApplycant the commentApplycant
	 */
	public void setCommentApplycant(String commentApplycant) {
		this.commentApplycant = commentApplycant;
	}

	/** 
	 * Returns the commentAuthority.
	 * 
	 * @return the commentAuthority
	 */
	public String getCommentAuthority() {
		return commentAuthority;
	}

	/** 
	 * Sets the commentAuthority.
	 * 
	 * @param commentAuthority the commentAuthority
	 */
	public void setCommentAuthority(String commentAuthority) {
		this.commentAuthority = commentAuthority;
	}

	/** 
	 * Returns the status.
	 * 
	 * @return the status
	 */
	public NotificationStatus getStatus() {
		return status;
	}

	/** 
	 * Sets the status.
	 * 
	 * @param status the status
	 */
	public void setStatus(NotificationStatus status) {
		this.status = status;
	}

	/** 
	 * Returns the createDate.
	 * 
	 * @return the createDate
	 */
	public LocalDate getCreateDate() {
		return createDate;
	}

	/** 
	 * Sets the createDate.
	 * 
	 * @param createDate the createDate
	 */
	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}

	/** 
	 * Returns the updateDate.
	 * 
	 * @return the updateDate
	 */
	public LocalDate getUpdateDate() {
		return updateDate;
	}

	/** 
	 * Sets the updateDate.
	 * 
	 * @param updateDate the updateDate
	 */
	public void setUpdateDate(LocalDate updateDate) {
		this.updateDate = updateDate;
	}
}