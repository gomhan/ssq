package lottery.view.funcview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;

import lottery.function.shortterm.PopularDeviationStatistic;
import lottery.function.shortterm.PopularDeviationStatistic.Popular;
import lottery.model.DoubleChromosphere;
import lottery.util.Context;
import lottery.util.LotteryConst;
import lottery.util.Utilities;
import lottery.view.renderer.LotteryTableRenderer;
import lottery.view.table.DefaultTable;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;

public class PopularInTableFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5488159906591486152L;

	class Func7TableModel extends AbstractTableModel {

		/**
		 * 
		 */
		private static final long serialVersionUID = -8975416585186406217L;

		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return popular.size();
		}

		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return 7;
		}

		@Override
		public String getColumnName(int column) {
			// TODO Auto-generated method stub
			switch (column) {
			case 0:
				return "期号";
			case 1:
				return "日期";
			case 2:
				return "奖球";
			case 3:
				return "各奖号遗漏数";
			case 4:
				return "遗漏少于10次的个数";
			case 5:
				return "当期遗漏总数";
			case 6:
				return "当期平均遗漏数";
			default:
				break;
			}
			return super.getColumnName(column);
		}

		@Override
		public Class<?> getColumnClass(int columnIndex) {
			// TODO Auto-generated method stub
			return getValueAt(0, columnIndex).getClass();
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			// TODO Auto-generated method stub
			Popular p = popular.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return p.getDc().getIssue();
			case 1:
				return p.getDc().getTime();
			case 2:
				return p.getDc().getRedString();
			case 3:
				return new StringBuilder()
						.append(Utilities.getAlignString(p.getBallMissCount()[0]))
						.append("-")
						.append(Utilities.getAlignString(p.getBallMissCount()[1]))
						.append("-")
						.append(Utilities.getAlignString(p.getBallMissCount()[2]))
						.append("-")
						.append(Utilities.getAlignString(p.getBallMissCount()[3]))
						.append("-")
						.append(Utilities.getAlignString(p.getBallMissCount()[4]))
						.append("-")
						.append(Utilities.getAlignString(p.getBallMissCount()[5]))
						.toString();
			case 4:
				return p.getHotNumberCount();
			case 5:
				return p.getMissCount();
			case 6:
				return p.getAverageMiss();
			default:
				return null;
			}
		}
	}

	JTable table;
	List<Popular> popular;
	int issue;
	int averageHotCount;
	int averageMissCount;

	public PopularInTableFrame(List<Popular> popular, int issue) {
		// TODO Auto-generated constructor stub
		this.popular = new ArrayList<Popular>(popular);
		this.issue = issue;
		setTitle("冷热门偏差_[" + issue + "期]");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		initialize();
		build();
	}

	private void initialize() {
		// TODO Auto-generated method stub
		Func7TableModel model = new Func7TableModel();
		table = new DefaultTable() {
			JMenuItem m1;
			JMenuItem m2;

			protected void initPopMenu() {
				// TODO Auto-generated method stub
				super.initPopMenu();

				m1 = new JMenuItem("遗漏少于10次统计图");
				m1.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						showHotNumberChart();
					}
				});

				m2 = new JMenuItem("遗漏次数统计图");
				m2.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						showMissCountChart();
					}
				});

				jpm.addSeparator();
				jpm.add(m1);
				jpm.addSeparator();
				jpm.add(m2);
			}
		};
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setModel(model);
		LotteryTableRenderer renderer = new LotteryTableRenderer() {
			@Override
			protected void customizeRenderer(JTable table, Object value,
					boolean isSelected, boolean hasFocus, int row, int column,
					Component renderComponent) {
				// TODO Auto-generated method stub
				if (column == 4) {
					Integer v = (Integer) value;
					if (v == 0) {
						setBackground(Color.LIGHT_GRAY);
					}
				}
			}
		};
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(renderer);
			table.getColumnModel().getColumn(i).setMinWidth(150);
		}

		TableRowSorter<Func7TableModel> sorter = new TableRowSorter<Func7TableModel>(
				model);
		table.setRowSorter(sorter);
	}

	private void build() {
		// TODO Auto-generated method stub
		JScrollPane jsp = new JScrollPane();
		jsp.setViewportView(table);

		PopularDeviationStatistic f = new PopularDeviationStatistic();
		List<DoubleChromosphere> dcs = Context.getInstance().getLotteryList();
		int size = dcs.size();
		f.invoke(dcs);
		List<Popular> tp = (List<Popular>) f.getResult(
				LotteryConst.DEFAULT_IDENTIFIER).getValue();
		int total = 0;
		for (Popular p : tp) {
			total += p.getHotNumberCount();
		}
		averageHotCount = total / size;
		double rate = total * 1.0d / size;
		String hotRate = LotteryConst.DF.format(rate);
		JLabel label = new JLabel("遗漏少于10次的数字的历史平均值：" + hotRate);
		label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		total = 0;
		for (Popular p : tp) {
			total += p.getMissCount();
		}
		averageMissCount = total / size;
		rate = total * 1.0d / size;
		hotRate = LotteryConst.DF.format(rate);
		JLabel label1 = new JLabel("遗漏总数历史平均值：" + hotRate);
		label1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		int[] redBall = new int[LotteryConst.RED_BALL_COUNT];
		int hit = 0;
		for (int i = 1; i <= LotteryConst.RED_BALL_COUNT; i++) {
			hit = f.lastHitIndex(0, (byte) i, dcs);
			redBall[i - 1] = hit;
		}
		JLabel label2 = new JLabel(getRedBallMiss(redBall));
		label2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		JLabel label3 = new JLabel(getSortedRedBallMiss(redBall));
		label3.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		JPanel lp = new JPanel(new FlowLayout(FlowLayout.LEADING));
		lp.add(label);
		lp.add(label1);

		JPanel wrap = new JPanel(new BorderLayout());
		wrap.add(lp, BorderLayout.NORTH);
		wrap.add(label3, BorderLayout.CENTER);
		wrap.add(label2, BorderLayout.SOUTH);

		getContentPane().add(jsp, BorderLayout.CENTER);
		getContentPane().add(wrap, BorderLayout.SOUTH);

		// release function
		f.reset();
		f = null;
	}

	private String getRedBallMiss(int[] value) {
		StringBuilder buf = new StringBuilder();
		buf.append("<html>").append("\n").append("<body>").append("\n")
				.append(createHorizontalTable(value)).append("\n")
				.append("</body>").append("</html>");
		return buf.toString();
	}

	private String createHorizontalTable(int[] value) {
		StringBuilder buf = new StringBuilder();

		buf.append("<table border=\"1\">").append("\n");
		buf.append("<tr>").append("\n");
		for (int i = 1; i <= LotteryConst.RED_BALL_COUNT; i++) {
			buf.append("<td>").append(Utilities.getAlignString(i))
					.append("</td>");
		}
		buf.append("\n").append("</tr>").append("\n");

		buf.append("<tr>").append("\n");
		int miss;
		for (int i = 0; i < LotteryConst.RED_BALL_COUNT; i++) {
			miss = value[i];
			if (miss == 0) {
				buf.append("<td>").append("<div style=\"color:red\">")
						.append(Utilities.getAlignString(miss))
						.append("</div>").append("</td>");
			} else {
				buf.append("<td>").append(Utilities.getAlignString(miss))
						.append("</td>");
			}
		}
		buf.append("\n").append("</tr>").append("\n").append("</table>");

		return buf.toString();
	}

	private String getSortedRedBallMiss(int[] value) {
		StringBuilder buf = new StringBuilder();
		buf.append("<html>").append("\n").append("<body>").append("\n")
				.append(createVerticalTable(value)).append("\n")
				.append("</body>").append("</html>");
		return buf.toString();
	}

	private String createVerticalTable(int[] value) {
		TreeMap<Integer, ArrayList<Integer>> map = new TreeMap<Integer, ArrayList<Integer>>();
		int miss;
		ArrayList<Integer> ball;
		for (int index = 0; index < value.length; index++) {
			miss = value[index];
			ball = map.get(miss);
			if (ball == null) {
				ball = new ArrayList<Integer>();
				map.put(miss, ball);
			}
			ball.add(index + 1);
		}

		StringBuilder buf = new StringBuilder();

		buf.append("<table border=\"1\">").append("\n");

		buf.append("<tr>").append("\n");
		buf.append("<td align=\"center\">").append("遗漏次数").append("</td>")
				.append("\n");
		buf.append("<td align=\"center\">").append("遗漏球号").append("</td>")
				.append("\n");
		buf.append("</tr>").append("\n");

		Iterator<Integer> keys = map.keySet().iterator();
		int key;
		while (keys.hasNext()) {
			key = keys.next();
			buf.append("<tr>").append("\n");
			buf.append("<td align=\"center\">")
					.append(Utilities.getAlignString(key)).append("</td>")
					.append("\n");
			buf.append("<td align=\"left\">")
					.append(Arrays.toString(map.get(key)
							.toArray(new Integer[0]))).append("</td>")
					.append("\n");
			buf.append("</tr>").append("\n");
		}
		buf.append("</table>");

		return buf.toString();
	}

	public void display() {
		setSize(1100, 920);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private List<Popular> getSelectedPopular() {
		List<Popular> ps = new ArrayList<>();
		int[] rows = table.getSelectedRows();
		Popular p;
		for (int i = 0; i < rows.length; i++) {
			p = popular.get(table.convertRowIndexToModel(rows[i]));
			ps.add(p);
		}

		return ps;
	}

	private void showHotNumberChart() {
		List<Popular> value = getSelectedPopular();
		if (value == null || value.size() == 0) {
			return;
		}

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (Popular p : value) {
			dataset.addValue(p.getHotNumberCount() - averageHotCount,
					"遗漏少于10次", p.getDc().getIssue());
		}
		JFreeChart chart = ChartFactory.createBarChart("遗漏少于10次统计图", // chart
				"期数", // domain axis label
				"遗漏少于10次个数偏差", // range axis label
				dataset, // data
				PlotOrientation.VERTICAL, // orientation
				true, // include legend
				true, // tooltips?
				false // URLs?
				);
		configChart(chart);
		showChart(chart, value.size());
	}

	private void showMissCountChart() {
		List<Popular> value = getSelectedPopular();
		if (value == null || value.size() == 0) {
			return;
		}

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (Popular p : value) {
			dataset.addValue(p.getMissCount() - averageMissCount, "遗漏次数", p
					.getDc().getIssue());
		}
		JFreeChart chart = ChartFactory.createBarChart("遗漏次数统计图", // chart
				"期数", // domain axis label
				"遗漏次数偏差", // range axis label
				dataset, // data
				PlotOrientation.VERTICAL, // orientation
				true, // include legend
				true, // tooltips?
				false // URLs?
				);
		configChart(chart);
		showChart(chart, value.size());
	}

	public void showChart(JFreeChart chart, int size) {
		int length = size % 10 == 0 ? size / 10 : size / 10 + 1;
		int width = length * 250;
		if (width < 1024) {
			width = 1024;
		}
		ChartPanel cp = new ChartPanel(chart, width, 768, 1024, 768, width,
				768, true, true, true, true, true, true);
		JScrollPane jsp = new JScrollPane(cp);

		JFrame frame = new JFrame("冷热偏差统计图");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().add(jsp);
		frame.setSize(1280, 960);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private void configChart(JFreeChart chart) {
		chart.getTitle().setFont(new Font("宋体", Font.BOLD, 15));// 设置标题
		chart.getLegend().setItemFont(new Font("宋体", Font.BOLD, 15)); // 设置图例类别字体
		chart.setBackgroundPaint(Color.WHITE);
		/*----------设置消除字体的锯齿渲染（解决中文问题）--------------*/
		chart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);

		CategoryPlot categoryPlot = chart.getCategoryPlot();// 坐标系图表对象
		categoryPlot.setBackgroundPaint(Color.WHITE); // 背景
		categoryPlot.setDomainGridlinePaint(Color.BLACK);// 分类轴网格线条颜色（x轴）
		categoryPlot.setDomainGridlinesVisible(true);
		categoryPlot.setRangeGridlinePaint(Color.GREEN);// 数据轴网格线条颜色（y轴）
		categoryPlot.setRangeGridlinesVisible(true);
		categoryPlot.setForegroundAlpha(1.0f);// 柱状图透明度

		CategoryAxis domainAxis = categoryPlot.getDomainAxis(); // x轴对象
		domainAxis.setLabelFont(new Font("宋体", Font.BOLD, 14)); // x轴的标题
		domainAxis.setTickLabelFont(new Font("宋体", Font.PLAIN, 14)); // x轴坐标上的文字
		domainAxis.setTickLabelsVisible(true);// X轴的标题文字是否显示
		domainAxis.setMaximumCategoryLabelWidthRatio(0.7f); // X轴的标题文字显示比例
		domainAxis.setCategoryMargin(0.03); // X轴标签之间的距离10%
		domainAxis.setLowerMargin(0.0);
		domainAxis.setUpperMargin(0.03);
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_90);

		ValueAxis rangeAxis = categoryPlot.getRangeAxis();// y轴对象
		rangeAxis.setLabelFont(new Font("宋体", Font.BOLD, 14)); // y轴的标题
		rangeAxis.setTickLabelFont(new Font("宋体", Font.BOLD, 12)); // y轴坐标上的文字
		rangeAxis.setRange(-51, 51); // Y轴取值范围
		NumberAxis na = (NumberAxis) categoryPlot.getRangeAxis(); // y轴精度
		na.setAutoRangeIncludesZero(true);
		DecimalFormat df = new DecimalFormat("#0");
		na.setNumberFormatOverride(df);// 数据轴数据标签的显示格式

		BarRenderer renderer = (BarRenderer) categoryPlot.getRenderer();
		// 显示条目标签
		renderer.setBaseItemLabelsVisible(true);
		// 设置条目标签生成器,在JFreeChart1.0.6之前可以通过renderer.setItemLabelGenerator(CategoryItemLabelGenerator
		// generator)方法实现，但是从版本1.0.6开始有下面方法代替
		renderer.setBaseItemLabelPaint(Color.BLUE);// 设置数值颜色，默认黑色
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		// 设置条目标签显示的位置,outline表示在条目区域外,baseline_center表示基于基线且居中
		renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(
				ItemLabelAnchor.OUTSIDE12, TextAnchor.CENTER_LEFT));
		renderer.setMaximumBarWidth(0.03); // 设置柱子宽度
		categoryPlot.setRenderer(renderer);

	}
}
