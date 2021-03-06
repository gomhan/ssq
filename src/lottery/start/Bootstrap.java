package lottery.start;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.SwingUtilities;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import lottery.function.DefaultFunctionExecutor;
import lottery.itf.FunctionExecutor;
import lottery.model.DoubleChromosphere;
import lottery.model.LotteryModel;
import lottery.util.Context;
import lottery.view.LotteryWindow;

public class Bootstrap {

	public Bootstrap() {
		// TODO Auto-generated constructor stub

	}

//	private void startupWithExcel(String path) {
////		String path = new StringBuilder()
////				.append(System.getProperty("user.dir"))
////				.append(File.separatorChar).append("ssqexcle_result.xls")
////				.toString();
//		Excel excel = new Excel();
//		boolean b = excel.load(path);
//		if (b) {
//			FunctionExecutor fe = new DefaultFunctionExecutor();
//			Context.getInstance().putObject(Context.EXECUTOR, fe);
//			LotteryModel model = new LotteryModel();
//			model.setLottery(excel.result());
//			excel.uninstall();
//			Context.getInstance().putObject(Context.LOTTERY_MODEL, model);
//			final LotteryWindow window = new LotteryWindow(model);
//			SwingUtilities.invokeLater(new Runnable() {
//				@Override
//				public void run() {
//					// TODO Auto-generated method stub
//					window.display();
//				}
//			});
//		} else {
//			System.out.println("load file failed.");
//		}
//	}

//	private void startupWithXml(String path) {
////		String path = new StringBuilder().append(LotteryConst.PROJECT_PATH)
////				.append("ssq.xml").toString();
//		XML xml = new XML();
//		List<DoubleChromosphere> dcs = xml.parseLotteryXML(path);
//		if (dcs != null && dcs.size() > 0) {
//			FunctionExecutor fe = new DefaultFunctionExecutor();
//			Context.getInstance().putObject(Context.EXECUTOR, fe);
//			LotteryModel model = new LotteryModel();
//			model.setLottery(dcs);
//			Context.getInstance().putObject(Context.LOTTERY_MODEL, model);
//			final LotteryWindow window = new LotteryWindow(model);
//			Context.getInstance().putObject(Context.MAIN_FRAME, window);
//			SwingUtilities.invokeLater(new Runnable() {
//				@Override
//				public void run() {
//					// TODO Auto-generated method stub
//					window.display();
//				}
//			});
//		} else {
//			System.out.println("load file failed.");
//		}
//	}
	
	private void startup() {
		FunctionExecutor fe = new DefaultFunctionExecutor();
		Context.getInstance().putObject(Context.EXECUTOR, fe);
		LotteryModel model = new LotteryModel();
		model.setLottery(new ArrayList<DoubleChromosphere>());
		Context.getInstance().putObject(Context.LOTTERY_MODEL, model);
		final LotteryWindow window = new LotteryWindow(model);
		Context.getInstance().putObject(Context.MAIN_FRAME, window);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				window.display();
			}
		});
	}
	
//	public void start() {
//		String excel = new StringBuilder().append(System.getProperty("user.dir")).append(File.separatorChar)
//				.append("ssqexcle_result.xls").toString();
//
//		String xml = new StringBuilder().append(LotteryConst.PROJECT_PATH).append("ssq.xml").toString();
//
//		boolean readXml = false;
//		String path = null;
//		if (checkFile(xml)) {
//			path = xml;
//			readXml = true;
//		} else if (checkFile(excel)) {
//			path = excel;
//		} else {
//			System.exit(1);
//		}
//
//		if (readXml) {
//			startupWithXml(path);
//		} else {
//			startupWithExcel(path);
//		}
//
//	}
	
//	private static boolean checkFile(String path) {
//		boolean b = false;
//		if (path == null || path.equals("")) {
//			return b;
//		}
//		try {
//			File file = new File(path);
//			if (file.exists() && file.isFile()) {
//				b = true;
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return b;
//	}

	public static void main(String[] args) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
					.getInstalledLookAndFeels()) {
				if ("Windows".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
		} catch (InstantiationException ex) {
		} catch (IllegalAccessException ex) {
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
		}
		System.setProperty("java.awt.im.style", "no-spot");
		UIDefaults defaults = UIManager.getDefaults();
		defaults.put("Menu.font", new FontUIResource("Dialog", Font.BOLD, 12));
		Color backgroud = new Color(0xFA, 0XFA, 0XFA);
		Color linkbackgroud = new Color(0xC8, 0xC8, 0xC8);
		Color scrollbackgroud = new Color(0xe4, 0xe4, 0xe4);
		UIManager.put("Panel.background", backgroud);
		UIManager.put("CheckBox.background", backgroud);
		UIManager.put("TableHeader.background", backgroud);
		UIManager.put("TableHeader.cellBorder",
				BorderFactory.createLineBorder(linkbackgroud));
		UIManager.put("TabbedPane.border", BorderFactory.createEmptyBorder());
		UIManager.put("ScrollBar.foreground", scrollbackgroud);
		UIManager.put("RadioButton.background", backgroud);
		UIManager.put("Slider.background", backgroud);
		UIManager.put("Viewport.background", Color.white);
		UIManager.put("Table.dropLineColor", Color.black);
		UIManager.put("Table.dropLineShortColor", Color.black);
//		new Start().startupWithExcel();
//		new Start().startupWithXml();
//		new Bootstrap().start();
		new Bootstrap().startup();
	}
}
