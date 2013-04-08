package com.codejam.gui;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

public class InfoPane extends Panel {
	private String[] images = { "ecsess.jpg", "morganstanley.jpg",
			"silanis.jpg", "kronos.jpg", "ciena.png", "ericsson.jpg" };

	private String[] link = { "http://www.ecsess.com/",
			"http://www.morganstanley.com/", "http://www.silanis.com/",
			"http://www.kronos.ca/", "http://www.ciena.com/",
			"http://www.ericsson.com/ca"};

	private int count;
	public InfoPane() {

		for (count = 0; count < 6; count++) {

			final int id= count;
			ImageIcon icon = new ImageIcon("images/sponsor/" + images[count]);
			JButton button = new JButton(icon);
			button.setLayout(new BorderLayout());
			button.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			
			
			button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					Desktop desktop = Desktop.isDesktopSupported() ? Desktop
							.getDesktop() : null;
					if (desktop != null
							&& desktop.isSupported(Desktop.Action.BROWSE)) {

						try {

							URI uri = new URI(link[id]);
							desktop.browse(uri);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (URISyntaxException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}

				}
			});
			add(button);
		}
	}
}
