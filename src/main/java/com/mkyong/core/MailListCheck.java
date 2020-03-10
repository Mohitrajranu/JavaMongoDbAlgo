package com.mkyong.core;

public interface MailListCheck {

	public boolean validMailBox(String username,String emailId);
	public boolean invalidMailBox(String username,String emailId);
	public void insermailList(String username,String emailId,String collection);
}
