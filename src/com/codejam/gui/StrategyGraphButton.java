package com.codejam.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;

import com.codejam.listener.StrategyType;
import com.codejam.process.StrategyPacket;

public class StrategyGraphButton extends JButton
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3850305578295206202L;
	private StrategyType type;
	

	private StrategyGraph graph;
	private StrategyGraph graphClone;
	private JPanel log;

	public StrategyGraphButton(StrategyType n)
	{
		this.type = n;
	
		
		this.setLayout(new BorderLayout());
		this.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		graph = new StrategyGraph(type);

		this.add(graph);

		graphClone = new StrategyGraph(type);
		addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				//Check if the clone graph is currently displayed on the screen
				if (!graphClone.isShowing()) //If not Create a new window containg the graphclone
				{
					JFrame frame = new JFrame(type + " Duplicate");
					frame.setSize(400, 300);
					frame.setVisible(true);
					frame.getContentPane().add(graphClone);

					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				}
				else //If yes then move it to the first plan
				{
					JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(graphClone);
					topFrame.setVisible(true);
				}
			}
		});
	}

	public void addPoint(StrategyPacket packet)
	{
		graph.addPoint(packet);
		graphClone.addPoint(packet);
	}

	public StrategyType getType()
	{
		return type;
	}

	public void setType(StrategyType type)
	{
		this.type = type;
	}

}
