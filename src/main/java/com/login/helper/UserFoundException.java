package com.login.helper;

public class UserFoundException extends Exception
{

	public UserFoundException()
	{
		super("This username is already there in DB !!  try with another one.......");
	}
	
	public UserFoundException(String msg)
	{
		super(msg);
	}
}
