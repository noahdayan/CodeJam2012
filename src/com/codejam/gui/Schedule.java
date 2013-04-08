package com.codejam.gui;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.event.*;
import javax.swing.table.*;

import com.codejam.listener.ListenerManager;
import com.codejam.listener.StrategyType;
import com.codejam.listener.event.StrategyEvent;
import com.codejam.schedule.Manager;

public class Schedule extends JPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPane;
	private JTable table;
	private DefaultTableModel rowModel;
	private TableRowSorter<TableModel> sorter;
	private JTable headerTable;
	private List<Manager> managers;

	private Point selection;
	
	public Schedule(List<Manager> list)
	{
		this.managers = list;
		selection = new Point(0,0);

		this.setLayout(new BorderLayout());
		this.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));

		DefaultTableModel model = new DefaultTableModel(list.size(), 18);

		table = new JTable(model)
		{
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int colIndex)
			{
				return false; // Disallow the editing of any cell
			}
		};
		table.getTableHeader().setEnabled(false);
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener()
		{

			@Override
			public void valueChanged(ListSelectionEvent e)
			{
				 if (e.getValueIsAdjusting())
			            return;
			        ListSelectionModel lsm = (ListSelectionModel)e.getSource();
			        if (lsm.isSelectionEmpty()) {
			            System.out.println("No rows selected");
			        }
			        else{
			            selection.x = lsm.getMinSelectionIndex();
			            changeGraphSelection();
			        }
			}
		});


		table.getColumnModel().getSelectionModel().addListSelectionListener(new ListSelectionListener()
		{

			@Override
			public void valueChanged(ListSelectionEvent e)
			{
				 if (e.getValueIsAdjusting())
			            return;
			        ListSelectionModel lsm = (ListSelectionModel)e.getSource();
			        if (lsm.isSelectionEmpty()) {
			            System.out.println("No rows selected");
			        }
			        else{
			            selection.y = lsm.getMinSelectionIndex();
			            changeGraphSelection();
			        }
			}
		});

		// table = new JTable(list.size(), 18);

		String columnNames[] = new String[18];
		for (int i = 0; i < table.getColumnCount(); i++)
		{
			if (i % 2 == 1)
			{
				columnNames[i] = String.valueOf(8 + i / 2) + "h30";
			} else
			{
				columnNames[i] = String.valueOf(8 + i / 2) + "h";
			}
		}

		for (int i = 0; i < table.getColumnCount(); i++)
		{
			table.getColumnModel().getColumn(i).setHeaderValue(columnNames[i]);
			table.getColumnModel().getColumn(i).setPreferredWidth(50);
		}

		sorter = new TableRowSorter<TableModel>(table.getModel());
		table.setRowSorter(sorter);
		rowModel = new DefaultTableModel()
		{

			private static final long serialVersionUID = 1L;

			@Override
			public int getColumnCount()
			{
				return 1;
			}

			@Override
			public boolean isCellEditable(int row, int col)
			{
				return false;
			}

			@Override
			public int getRowCount()
			{
				return table.getRowCount();
			}

			@Override
			public Class<?> getColumnClass(int colNum)
			{
				switch (colNum)
				{
				case 0:
					return String.class;
				default:
					return super.getColumnClass(colNum);
				}
			}
		};
		headerTable = new JTable(rowModel);
		for (int i = 0; i < table.getRowCount(); i++)
		{
			String name = "Manager : " + managers.get(i).getId();
			headerTable.setValueAt(name, i, 0);
		}
		headerTable.setShowGrid(false);
		headerTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		headerTable.setPreferredScrollableViewportSize(new Dimension(100, 0));
		headerTable.getColumnModel().getColumn(0).setPreferredWidth(100);
		headerTable.getColumnModel().getColumn(0).setCellRenderer(new TableCellRenderer()
		{

			@Override
			public Component getTableCellRendererComponent(JTable x, Object value, boolean isSelected,
					boolean hasFocus, int row, int column)
			{

				boolean selected = table.getSelectionModel().isSelectedIndex(row);
				Component component = table.getTableHeader().getDefaultRenderer()
						.getTableCellRendererComponent(table, value, false, false, -1, -2);
				((JLabel) component).setHorizontalAlignment(SwingConstants.CENTER);
				if (selected)
				{
					component.setFont(component.getFont().deriveFont(Font.BOLD));
					component.setForeground(Color.red);
				} else
				{
					component.setFont(component.getFont().deriveFont(Font.PLAIN));
				}
				return component;
			}
		});
		table.getRowSorter().addRowSorterListener(new RowSorterListener()
		{

			@Override
			public void sorterChanged(RowSorterEvent e)
			{
				rowModel.fireTableDataChanged();
			}
		});
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener()
		{

			@Override
			public void valueChanged(ListSelectionEvent e)
			{
				rowModel.fireTableRowsUpdated(0, rowModel.getRowCount() - 1);
			}
		});

		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer()
		{
			private static final long serialVersionUID = 1L;

			public Component getTableCellRendererComponent(JTable table, Object valueCell, boolean isSelected,
					boolean hasFocus, int row, int column)
			{
				Component c = super.getTableCellRendererComponent(table, valueCell, isSelected, hasFocus, row, column);

				int value = managers.get(row).getState(column);
				if (value == 1)
				{
					c.setBackground(new Color(72, 168, 62));
				} else if (value == 2)
				{
					c.setBackground(new Color(247, 236, 84));
				} else if (value == 0)
				{
					c.setBackground(new Color(100, 100, 100));
				}

				return c;
			}
		});
		scrollPane = new JScrollPane(table);
		scrollPane.setRowHeaderView(headerTable);
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		add(scrollPane);

		setLocation(150, 150);
		setVisible(true);
	}

	public void changeGraphSelection()
	{
		Manager manager = managers.get(selection.x);
		if(manager.getState(selection.y) == 1)
		{
			List<StrategyType> strategies = new ArrayList<StrategyType>();
			strategies.add(manager.getCurrentStrategyTypeAtIndex(selection.y, 1));
			strategies.add(manager.getCurrentStrategyTypeAtIndex(selection.y, 2));
			
			ListenerManager.sendStrategyEventToAll(strategies, new StrategyEvent("Selected"));
			ListenerManager.sendStrategyEventToAllBut(strategies, new StrategyEvent("UnSelected"));
		}
		else
		{
			ListenerManager.sendStrategyEventToAll(new StrategyEvent("UnSelected"));
		}
	
	}
	/**
	 * @return the managers
	 */
	public List<Manager> getManagers()
	{
		return managers;
	}

	/**
	 * @param managers
	 *            the managers to set
	 */
	public void setManagers(List<Manager> managers)
	{
		this.managers = managers;
	}

}