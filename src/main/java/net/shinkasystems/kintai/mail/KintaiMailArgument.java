package net.shinkasystems.kintai.mail;


/**
 * 
 * @author Aogiri
 *
 */
public class KintaiMailArgument {

	private String receiverName;
	
	private String receiverMailAddress;
	
	private String senderName;
	
	private String senderMailAddress;
	
	private String term;
	
	private String form;
	
	private String comment;
	
	private String url;

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverMailAddress() {
		return receiverMailAddress;
	}

	public void setReceiverMailAddress(String receiverMailAddress) {
		this.receiverMailAddress = receiverMailAddress;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getSenderMailAddress() {
		return senderMailAddress;
	}

	public void setSenderMailAddress(String senderMailAddress) {
		this.senderMailAddress = senderMailAddress;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getForm() {
		return form;
	}

	public void setForm(String form) {
		this.form = form;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
