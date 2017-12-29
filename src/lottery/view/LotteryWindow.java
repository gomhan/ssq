package lottery.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;

import lottery.model.LotteryModel;
import lottery.model.LotteryTableModel;
import lottery.util.Context;
import lottery.util.LotteryConst;
import lottery.util.XML;
import lottery.view.menu.common.SummationBarChartMenu;
import lottery.view.menu.common.SummationTableMenu;
import lottery.view.menu.common.WinningNumberTableMenu;
import lottery.view.menu.shortterm.OddEvenTableMenu;
import lottery.view.menu.shortterm.HighLowTableMenu;
import lottery.view.menu.shortterm.SumDeviationBarChartMenu;
import lottery.view.menu.shortterm.NumberAreaTableMenu;
import lottery.view.menu.shortterm.PopularTableMenu;
import lottery.view.menu.shortterm.MissTableMenu;
import lottery.view.renderer.LotteryTableRenderer;
import lottery.view.table.DefaultTable;

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
		JMenu menu = new JMenu("�ļ�");
		menuBar.add(menu, LotteryConst.MENU_INDEX_0);
		menu = new JMenu("����ͳ��");
		menuBar.add(menu, LotteryConst.MENU_INDEX_1);
		menu = new JMenu("����ͳ��");
		menuBar.add(menu, LotteryConst.MENU_INDEX_2);
		menu = new JMenu("����ͳ��");
		menuBar.add(menu, LotteryConst.MENU_INDEX_3);
		menu = new JMenu("����ͳ��");
		menuBar.add(menu, LotteryConst.MENU_INDEX_4);
		menu = new JMenu("�Զ���ͳ��");
		menuBar.add(menu, LotteryConst.MENU_INDEX_5);

		table = new DefaultTable();
		tableModel = new LotteryTableModel();
		tableModel.setModel(model);
		table.setModel(tableModel);
		((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer())
				.setHorizontalAlignment(JLabel.CENTER);
		LotteryTableRenderer renderer = new LotteryTableRenderer() {
			@Override
			protected void customizeRenderer(JTable table, Object value,
					boolean isSelected, boolean hasFocus, int row, int column,
					Component renderComponent) {
				if (column > 2 && column < 9) {
					setForeground(Color.RED);
				}

				if (column == 9) {
					setForeground(Color.BLUE);
				}
			}
		};
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(renderer);
		}
		table.getTableHeader().setReorderingAllowed(false);

		TableRowSorter<LotteryTableModel> sorter = new TableRowSorter<LotteryTableModel>(
				tableModel);
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
		JMenuItem m = new JMenuItem("�˳�ϵͳ");
		m.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				close();
			}
		});
		menu.add(m);
		menu.addSeparator();

		m = new JMenuItem("����");
		m.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				XML xml = new XML();
				xml.createLotteryXML(Context.getInstance().getLotteryList(),
						LotteryConst.PROJECT_PATH + "ssq.xml");
				JOptionPane.showConfirmDialog(LotteryWindow.this, "�����ɹ�", "��Ϣ",
						JOptionPane.CLOSED_OPTION);
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
		m = new SummationBarChartMenu();
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
	}

	private void registerMidTermMenu() {
		// JMenu menu = menuBar.getMenu(LotteryConst.MENU_INDEX_3);
		// JMenuItem m;
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
		buf.append("33ѡ6��").append(":").append("����ϣ������ֵ")
				.append(LotteryConst.DEVIATION_BEST).append(",")
				.append("����ϣ����Χ").append("[")
				.append(LotteryConst.DEVIATION_LOWEST).append(" - ")
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
