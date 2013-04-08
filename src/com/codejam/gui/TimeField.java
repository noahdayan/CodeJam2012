package com.codejam.gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EtchedBorder;

public class TimeField extends JPanel
{

	private int time;
	private JTextPane textField;
	
	public TimeField()
	{
		textField = new JTextPane();
		time = 0;
		
		this.setLayout(new BorderLayout());
		this.setVisible(true);
		this.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		this.add(textField);
	}
	
	public void setTime(int time)
	{
		textField.setText(getParsedTime(time));
	}
	

	static String getParsedTime(int time)
	  {
		  String hours = ""+(time)/3600%60;
		  String minutes = ""+(time)/60%60;
		  String seconds = "" + (time)%60;
		  
		  return hours+":"+minutes+":"+seconds;
		  
	  }
	
	
}

