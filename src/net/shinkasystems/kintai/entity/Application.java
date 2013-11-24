package net.shinkasystems.kintai.entity;

import java.sql.Date;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

/**
 * 
 */
@Entity(listener = ApplicationListener.class)
@Table(name = "APPLICATION")
public class Application {

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
    String type;

    /**  */
    @Column(name = "TERM")
    Date term;

    /**  */
    @Column(name = "COMMENT_APPLYCANT")
    String commentApplycant;

    /**  */
    @Column(name = "COMMENT_AUTHORITY")
    String commentAuthority;

    /**  */
    @Column(name = "STATUS")
    String status;

    /**  */
    @Column(name = "CREATE_DATE")
    Date createDate;

    /**  */
    @Column(name = "UPDATE_DATE")
    Date updateDate;

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
    public String getType() {
        return type;
    }

    /** 
     * Sets the type.
     * 
     * @param type the type
     */
    public void setType(String type) {
        this.type = type;
    }

    /** 
     * Returns the term.
     * 
     * @return the term
     */
    public Date getTerm() {
        return term;
    }

    /** 
     * Sets the term.
     * 
     * @param term the term
     */
    public void setTerm(Date term) {
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
    public String getStatus() {
        return status;
    }

    /** 
     * Sets the status.
     * 
     * @param status the status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /** 
     * Returns the createDate.
     * 
     * @return the createDate
     */
    public Date getCreateDate() {
        return createDate;
    }

    /** 
     * Sets the createDate.
     * 
     * @param createDate the createDate
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /** 
     * Returns the updateDate.
     * 
     * @return the updateDate
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /** 
     * Sets the updateDate.
     * 
     * @param updateDate the updateDate
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}