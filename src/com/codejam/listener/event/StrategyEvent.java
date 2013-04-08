package com.codejam.listener.event;

public class StrategyEvent
{
	private String message;
	
	public StrategyEvent(String msg)
	{
		this.setMessage(msg);
	}

	/**
	 * @return the message
	 */
	public String getMessage()
	{
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message)
	{
		this.message = message;
	}
	
	
}	
