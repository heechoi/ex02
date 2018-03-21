package com.dgit.domain;

public class SampleVO {
	private int mno;
	private String firstName;
	private String lastName;
	

	public SampleVO() {}
	
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return String.format("SimpleVO [mno=%s, firstName=%s, lastName=%s]", mno, firstName, lastName);
	}
	
}