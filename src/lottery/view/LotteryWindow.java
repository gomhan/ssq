package lottery.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

import lottery.model.DoubleChromosphere;
import lottery.model.LotteryModel;
import lottery.model.LotteryTableModel;
import lottery.util.Context;
import lottery.util.Excel;
import lottery.util.LotteryConst;
import lottery.util.XML;
import lottery.view.menu.common.SummationBarChartMenu;
import lottery.view.menu.common.SummationTableMenu;
import lottery.view.menu.common.WinningNumberTableMenu;
import lottery.view.menu.midterm.IntervalIssueTableMenu;
import lottery.view.menu.midterm.LotteryIssueTableMenu;
import lottery.view.menu.midterm.MiniLotteryIssueTableMenu;
import lottery.view.menu.shortterm.HighLowTableMenu;
import lottery.view.menu.shortterm.LpNumberTableMenu;
import lottery.view.menu.shortterm.MissTableMenu;
import lottery.view.menu.shortterm.NumberAreaTableMenu;
import lottery.view.menu.shortterm.OddEvenTableMenu;
import lottery.view.menu.shortterm.PercentTableMenu;
import lottery.view.menu.shortterm.PopularTableMenu;
import lottery.view.menu.shortterm.SumDeviationBarChartMenu;
import lottery.view.renderer.LotteryTableRenderer;
import lottery.view.table.DefaultTable;
import table.header.mtx.MultiplexHeader;
import table.header.mtx.MultiplexHeaderRenderer;
import table.header.mtx.MultiplexTableHeader;
import table.header.mtx.MultiplexTableHeaderUI;

public class LotteryWindow extends JFrame {
	private static final long serialVersionUID = 7116715090667604045L;

	private JMenuBar menuBar;
	private JTable table;
	private LotteryTableModel tableModel;
	private LotteryModel model;

	public LotteryWindow(LotteryModel model) {
		// TODO Auto-generated constructor stub
		this.model = model;
		initialize();
		registerMenu();
		build();
	}

	private void initialize() {
		setTitle("Lottery");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				close();
			}
		});

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu menu = new JMenu("文件");
		menuBar.add(menu, LotteryConst.MENU_INDEX_0);
		menu = new JMenu("基础统计");
		menuBar.add(menu, LotteryConst.MENU_INDEX_1);
		menu = new JMenu("短期统计");
		menuBar.add(menu, LotteryConst.MENU_INDEX_2);
		menu = new JMenu("中期统计");
		menuBar.add(menu, LotteryConst.MENU_INDEX_3);
		menu = new JMenu("长期统计");
		menuBar.add(menu, LotteryConst.MENU_INDEX_4);
		menu = new JMenu("自定义统计");
		menuBar.add(menu, LotteryConst.MENU_INDEX_5);

		table = new DefaultTable() {
			private static final long serialVersionUID = 3831942668089166839L;

			// prevent execute default operation
			protected void initTableHeader() {
			}

			protected JTableHeader createDefaultTableHeader() {
				return new MultiplexTableHeader(columnModel);
			}
		};
		tableModel = new LotteryTableModel();
		tableModel.setModel(model);
		table.setModel(tableModel);
		table.setRowHeight(30);
		LotteryTableRenderer renderer = new LotteryTableRenderer() {
			private static final long serialVersionUID = -8455249334689710868L;
			Font font = new Font(Font.SERIF, Font.BOLD, 14);
			volatile int columnIndex;
			volatile boolean rowSelected;

			@Override
			protected void prepareRenderer(JTable table, Object value, boolean isSelected, boolean hasFocus, int row,
					int column, Component renderComponent) {
				// TODO Auto-generated method stub
				int[] rows = table.getSelectedRows();
				if (rows != null && rows.length > 0) {
					rowSelected = Arrays.binarySearch(rows, row) >= 0;
				} else {
					rowSelected = false;
				}

				columnIndex = column;
			}

			@Override
			protected void customizeUnSelectedRenderer(JTable table, Object value, boolean isSelected, boolean hasFocus,
					int row, int column, Component renderComponent) {
				if (column > 2 && column < 9) {
					setForeground(Color.RED);
				}

				if (column == 9) {
					setForeground(Color.BLUE);
				}
			}

			@Override
			protected void paintComponent(Graphics g) {
				// paint for default column
				super.paintComponent(g);

				// check if the column display win number
				if (columnIndex < 3 || columnIndex > 9) {
					return;
				}

				Rectangle clip = g.getClipBounds();
				Dimension size = getSize();
				// bound of one cell in table
				Rectangle cellBound = new Rectangle(0, 0, size.width, size.height);
				if (!clip.intersects(cellBound)) {
					return;
				}

				// paint for ball
				Graphics g1 = g.create();
				try {
					paintBall(g1, size.width, size.height);
				} catch (Exception e) {
					// TODO: handle exception
				} finally {
					g1.dispose();
				}
			}

			private void paintBall(Graphics g, int width, int height) {
				Graphics2D g2d = (Graphics2D) g;
				int x = width / 2 - 12;
				int y = height / 2 - 12;
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				if (rowSelected) {
					g2d.setColor(Color.white);
					g2d.drawOval(x, y, 24, 24);
				} else {
					g2d.setColor(getForeground());
					g2d.drawOval(x, y, 24, 24);
					g2d.fillOval(x, y, 24, 24);

					g2d.setColor(Color.white);
					g2d.setFont(font);
					x += 5;
					y += 17;
					if (getText().length() == 1) {
						x += 3;
					}
					g2d.drawString(getText(), x, y);
				}
			}
		};
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(renderer);
			table.getColumnModel().getColumn(i).setHeaderRenderer(new MultiplexHeaderRenderer());
		}

		MultiplexHeader mh = new MultiplexHeader("奖球", 3, 9);
		MultiplexTableHeader mth = (MultiplexTableHeader) table.getTableHeader();
		mth.addMultiplexColumn(mh.createMultiplexColumn(table.getColumnModel()));
		mth.setUI(new MultiplexTableHeaderUI());

		TableRowSorter<LotteryTableModel> sorter = new TableRowSorter<LotteryTableModel>(tableModel);
		sorter.setSortable(3, false);
		sorter.setSortable(4, false);
		sorter.setSortable(5, false);
		sorter.setSortable(6, false);
		sorter.setSortable(7, false);
		sorter.setSortable(8, false);
		sorter.setSortable(9, false);
		table.setRowSorter(sorter);
	}

	private void registerMenu() {
		registerFileMenu();
		registerCommonFunctionMenu();
		registerShortTermMenu();
		registerMidTermMenu();
		registerLongTermMenu();
	}

	private void registerFileMenu() {
		JMenu menu = menuBar.getMenu(LotteryConst.MENU_INDEX_0);
		JMenuItem m = new JMenuItem("退出系统");
		m.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				close();
			}
		});
		menu.add(m);
		menu.addSeparator();
		
		m = new JMenuItem("excel导入");
		m.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String path = new StringBuilder().append(System.getProperty("user.dir")).append(File.separatorChar)
						.append("ssqexcle_result.xls").toString();
				Excel excel = new Excel();
				boolean b = excel.load(path);
				if (b) {
					model.setLottery(excel.result());
					tableModel.fireTableDataChanged();
				} else {
					System.out.println("load excel failed.");
				}
				excel.uninstall();
			}
		});
		menu.add(m);
		
		m = new JMenuItem("xml导入");
		m.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String path = new StringBuilder().append(LotteryConst.PROJECT_PATH).append("ssq.xml").toString();
				XML xml = new XML();
				List<DoubleChromosphere> dcs = xml.parseLotteryXML(path);
				if (dcs != null && dcs.size() > 0) {
					model.setLottery(dcs);
					tableModel.fireTableDataChanged();
				} else {
					System.out.println("load file failed.");
				}
			}
		});
		menu.add(m);
		menu.addSeparator();

		m = new JMenuItem("导出xml");
		m.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				XML xml = new XML();
				xml.createLotteryXML(Context.getInstance().getLotteryList(), LotteryConst.PROJECT_PATH + "ssq.xml");
				JOptionPane.showConfirmDialog(LotteryWindow.this, "导出成功", "消息", JOptionPane.CLOSED_OPTION);
			}
		});
		menu.add(m);
	}

	private void registerCommonFunctionMenu() {
		JMenu menu = menuBar.getMenu(LotteryConst.MENU_INDEX_1);
		JMenuItem m;
		m = new SummationBarChartMenu();
		menu.add(m);
		menu.addSeparator();
		m = new SummationTableMenu();
		menu.add(m);
		menu.addSeparator();
		m = new WinningNumberTableMenu();
		menu.add(m);
		menu.addSeparator();
	}

	private void registerShortTermMenu() {
		JMenu menu = menuBar.getMenu(LotteryConst.MENU_INDEX_2);
		JMenuItem m;
		m = new OddEvenTableMenu();
		menu.add(m);
		menu.addSeparator();
		m = new HighLowTableMenu();
		menu.add(m);
		menu.addSeparator();
		m = new SumDeviationBarChartMenu();
		menu.add(m);
		menu.addSeparator();
		m = new NumberAreaTableMenu();
		menu.add(m);
		menu.addSeparator();
		m = new PopularTableMenu();
		menu.add(m);
		menu.addSeparator();
		m = new MissTableMenu();
		menu.add(m);
		menu.addSeparator();
		m = new LpNumberTableMenu();
		menu.add(m);
		menu.addSeparator();
		m = new PercentTableMenu();
		menu.add(m);
	}

	private void registerMidTermMenu() {
		JMenu menu = menuBar.getMenu(LotteryConst.MENU_INDEX_3);
		JMenuItem m;
		m = new LotteryIssueTableMenu();
		menu.add(m);
		menu.addSeparator();
		m = new MiniLotteryIssueTableMenu();
		menu.add(m);
		menu.addSeparator();
		m = new IntervalIssueTableMenu();
		menu.add(m);
	}

	private void registerLongTermMenu() {
		// JMenu menu = menuBar.getMenu(LotteryConst.MENU_INDEX_4);
		// JMenuItem m;
	}

	public void registerCustomizeMenu(JMenuItem m) {
		JMenu menu = menuBar.getMenu(LotteryConst.MENU_INDEX_5);
		menu.add(m);
		menu.addSeparator();
	}

	private void build() {
		JScrollPane jsp = new JScrollPane();
		jsp.setViewportView(table);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(jsp, BorderLayout.CENTER);

		StringBuilder buf = new StringBuilder();
		buf.append("33选6型").append(":").append("最有希望和数值").append(LotteryConst.DEVIATION_BEST).append(",")
				.append("最有希望范围").append("[").append(LotteryConst.DEVIATION_LOWEST).append(" - ")
				.append(LotteryConst.DEVIATION_HIGHEST).append("]");
		JLabel label = new JLabel(buf.toString());
		label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		getContentPane().add(label, BorderLayout.SOUTH);
	}

	public void close() {
		dispose();
		System.exit(0);
	}

	public void display() {
		setSize(800, 400);
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
