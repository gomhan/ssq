package lottery.view.table;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

public class DefaultTable extends JTable implements ActionListener {

	private static final long serialVersionUID = 7806338769384535578L;
	protected JPopupMenu jpm;
	protected JMenuItem mi;

	public DefaultTable() {
		// TODO Auto-generated constructor stub
		super();
		initTableHeader();
		initListener();
		initPopMenu();
	}

	protected void initTableHeader() {
		// TODO Auto-generated method stub
		JTableHeader jth = getTableHeader();
		jth.setReorderingAllowed(false);
		((DefaultTableCellRenderer) jth.getDefaultRenderer())
				.setHorizontalAlignment(SwingConstants.CENTER);
	}

	protected void initPopMenu() {
		// TODO Auto-generated method stub
		jpm = new JPopupMenu();
		mi = new JMenuItem("记录条数");
		mi.addActionListener(this);
		jpm.add(mi);
	}

	protected void initListener() {
		// TODO Auto-generated method stub
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if (e.getButton() == MouseEvent.BUTTON3) {
					jpm.show(DefaultTable.this, e.getX(), e.getY());
				}
			}
		});

		registerKeyboardAction(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DefaultTable.this.clearSelection();
			}
		}, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), 1);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		if (obj == mi) {
			JOptionPane.showMessageDialog(null, "一共有[" + getSelectedRowCount()
					+ "]条记录。");
		}
	}
}
