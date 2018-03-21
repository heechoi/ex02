package com.dgit.domain;

import java.util.Date;

public class MessageVO {
	private int mno;
	private String targetid;
	private String sender;
	private String message;
	private Date opendate;
	private Date senddate;
	
	
	public MessageVO() {}


	public int getMno() {
		return mno;
	}


	public void setMno(int mno) {
		this.mno = mno;
	}


	public String getTargetid() {
		return targetid;
	}


	public void setTargetid(String targetid) {
		this.targetid = targetid;
	}


	public String getSender() {
		return sender;
	}


	public void setSender(String sender) {
		this.sender = sender;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public Date getOpendate() {
		return opendate;
	}


	public void setOpendate(Date opendate) {
		this.opendate = opendate;
	}


	public Date getSenddate() {
		return senddate;
	}


	public void setSenddate(Date senddate) {
		this.senddate = senddate;
	}


	@Override
	public String toString() {
		return String.format("MessageVO [mno=%s, targetid=%s, sender=%s, message=%s, opendate=%s, senddate=%s]", mno,
				targetid, sender, message, opendate, senddate);
	}

}
