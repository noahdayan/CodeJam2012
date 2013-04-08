package com.codejam.gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EtchedBorder;

public class LogField extends JPanel
{
	private String text;
	private JTextPane textField;
	
	public LogField()
	{
		text = "Trading Log: \n";
		textField = new JTextPane();
		textField.setText(text);
		
		this.setLayout(new BorderLayout());
		this.setVisible(true);
		this.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		this.add(textField);
	}
	
	void appendText(String text)
	{
		this.text += text;
		textField.setText(this.text);
	}
	
	public void appendBuy(double price)
	{
		this.appendText("Bought: "+price+ "\n");
	}
	
	public void appendSell(double price)
	{
		this.appendText("Sold: "+price+ "\n");
	}
	
}
