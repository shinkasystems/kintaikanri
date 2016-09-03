package net.shinkasystems.kintai.entity;

import java.time.LocalDate;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

/**
 * 
 */
@Entity(listener = UserListener.class)
@Table(name = "USER")
public class User {

	/**  */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	Integer id;

	/**  */
	@Column(name = "AUTHORITY_ID")
	Integer authorityId;

	/**  */
	@Column(name = "USER_NAME")
	String userName;

	/**  */
	@Column(name = "PASSWORD")
	String password;

	/**  */
	@Column(name = "DISPLAY_NAME")
	String displayName;

	/**  */
	@Column(name = "EMAIL_ADDRESS")
	String emailAddress;

	/**  */
	@Column(name = "ACTIVATED")
	Boolean activated;

	/**  */
	@Column(name = "EXPIRE_DATE")
	LocalDate expireDate;

	/**  */
	@Column(name = "ROLE")
	String role;

	/**  */
	@Column(name = "ONLY_APPROVED")
	Boolean onlyApproved;

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
	 * Returns the authorityId.
	 * 
	 * @return the authorityId
	 */
	public Integer getAuthorityId() {
		return authorityId;
	}

	/** 
	 * Sets the authorityId.
	 * 
	 * @param authorityId the authorityId
	 */
	public void setAuthorityId(Integer authorityId) {
		this.authorityId = authorityId;
	}

	/** 
	 * Returns the userName.
	 * 
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/** 
	 * Sets the userName.
	 * 
	 * @param userName the userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/** 
	 * Returns the password.
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/** 
	 * Sets the password.
	 * 
	 * @param password the password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/** 
	 * Returns the displayName.
	 * 
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}

	/** 
	 * Sets the displayName.
	 * 
	 * @param displayName the displayName
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/** 
	 * Returns the emailAddress.
	 * 
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/** 
	 * Sets the emailAddress.
	 * 
	 * @param emailAddress the emailAddress
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/** 
	 * Returns the activated.
	 * 
	 * @return the activated
	 */
	public Boolean getActivated() {
		return activated;
	}

	/** 
	 * Sets the activated.
	 * 
	 * @param activated the activated
	 */
	public void setActivated(Boolean activated) {
		this.activated = activated;
	}

	/** 
	 * Returns the expireDate.
	 * 
	 * @return the expireDate
	 */
	public LocalDate getExpireDate() {
		return expireDate;
	}

	/** 
	 * Sets the expireDate.
	 * 
	 * @param expireDate the expireDate
	 */
	public void setExpireDate(LocalDate expireDate) {
		this.expireDate = expireDate;
	}

	/** 
	 * Returns the role.
	 * 
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/** 
	 * Sets the role.
	 * 
	 * @param role the role
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/** 
	 * Returns the onlyApproved.
	 * 
	 * @return the onlyApproved
	 */
	public Boolean getOnlyApproved() {
		return onlyApproved;
	}

	/** 
	 * Sets the onlyApproved.
	 * 
	 * @param onlyApproved the onlyApproved
	 */
	public void setOnlyApproved(Boolean onlyApproved) {
		this.onlyApproved = onlyApproved;
	}
}