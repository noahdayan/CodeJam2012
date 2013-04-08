package com.codejam.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.codejam.connection.ConnectionThread;
import com.codejam.connection.Johnson;
import com.codejam.listener.ConnectionListener;
import com.codejam.listener.ListenerManager;
import com.codejam.listener.StrategyType;
import com.codejam.listener.event.ConnectionEvent;
import com.codejam.schedule.ScheduleManager;
import com.codejam.trade.Trade;

public class GraphTest {
	public final static int WINDOW_WIDTH = 1250;
	public final static int WINDOW_HEIGHT = 650;

	private JFrame frame;
	private JButton startDayButton;
	private JButton reportButton;

	private StrategyPane graphOne;
	private StrategyPane graphTwo;
	private StrategyPane graphThree;
	private StrategyPane graphFour;

	public GraphTest() {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		frame = new JFrame("MSET TimWork");
		setFrameSize(WINDOW_WIDTH, WINDOW_HEIGHT);

		setupGraphs();
		setupStartDayButton();
		setupReportButton();

		Schedule s = new Schedule(ScheduleManager.getManagersWithSchedules());

		s.setBounds(10, 525, 1000, 115);
		frame.getContentPane().add(s);

		
		InfoPane info= new InfoPane();
		info.setBounds(1030, 287, 200, 300);
		frame.add(info);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	private void setupGraphs() {
		// Graphs:
		graphOne = new StrategyPane(StrategyType.SMA);
		frame.getContentPane().add(graphOne);
		graphOne.setBounds(10, 10, 495, 250);

		graphTwo = new StrategyPane(StrategyType.LWMA);
		frame.getContentPane().add(graphTwo);
		graphTwo.setBounds(515, 10, 495, 250);

		graphThree = new StrategyPane(StrategyType.EMA);
		frame.getContentPane().add(graphThree);
		graphThree.setBounds(10, 270, 495, 250);

		graphFour = new StrategyPane(StrategyType.TMA);
		frame.getContentPane().add(graphFour);
		graphFour.setBounds(515, 270, 495, 250);
	}

	private void setupStartDayButton() {
		startDayButton = new JButton("Start day");
		startDayButton.setBounds(1030, 32, 200, 50);
		startDayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				startDayButton.setEnabled(false);
				(new ConnectionThread()).start();
			}
		});

		ListenerManager.addListener(new ConnectionListener() {

			@Override
			public void init() {
				// TODO Auto-generated method stub

			}

			@Override
			public void onConnectionEvent(ConnectionEvent event) {
				if (event == ConnectionEvent.CLOSE) {
					startDayButton.setEnabled(true);
				}

			}
		});

		frame.getContentPane().add(startDayButton);
	}

	private void setupReportButton() {
		reportButton = new JButton("Report Trades");
		reportButton.setBounds(1030, 92, 200, 50);
		reportButton.setEnabled(false);
		reportButton.setName("report");

		reportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(!reportButton.getName().contains("report"))
				{		
					JOptionPane.showMessageDialog(null, reportButton.getText(), "Report Successful", JOptionPane.PLAIN_MESSAGE);
				}
				else
				{
					reportButton.setText("Reporting ...");
					reportButton.setEnabled(false);
	
					(new Thread() {
						public void run() {
							List<Trade> trades = graphOne.getAllTrades();
							trades.addAll(graphTwo.getAllTrades());
							trades.addAll(graphThree.getAllTrades());
							trades.addAll(graphFour.getAllTrades());
	
							String JJ = Johnson.JohnsonHeader;
	
							for (Trade trade : trades) {
								JJ = JJ + Johnson.convertTradeToJohnson(trade);
							}
	
							JJ = JJ + Johnson.JohnsonTail;
							
							try {
								String ceremonyid = Johnson.JohnsonPOST(JJ);
								
								if (ceremonyid != null) {
									reportButton.setName("Reported");
									reportButton.setText("Reported with id: "
											+ ceremonyid);
								} else {
									reportButton.setText("Report Error, try again");
								}
							} catch (IOException e) {
								e.printStackTrace();
							}
							reportButton.setEnabled(true);
	
						};
					}).start();
				}
			}
		});

		ListenerManager.addListener(new ConnectionListener() {

			@Override
			public void init() {
				// TODO Auto-generated method stub

			}

			@Override
			public void onConnectionEvent(ConnectionEvent event) {
				if (event == ConnectionEvent.CLOSE) {
					reportButton.setEnabled(true);
				}

			}
		});

		frame.getContentPane().add(reportButton);
	}

	public void setFrameSize(int width, int height) {
		frame.pack();
		Insets insets = frame.getInsets();
		frame.setSize(new Dimension(insets.left + insets.right + width,
				insets.top + insets.bottom + height));
		frame.getContentPane().setLayout(null);
	}

	public static void main(String[] args) {

		new GraphTest();
	}

}