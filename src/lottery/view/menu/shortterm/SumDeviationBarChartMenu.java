package lottery.view.menu.shortterm;

import java.awt.Color;
import java.awt.Font;
import java.awt.RenderingHints;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import lottery.function.shortterm.SummationDeviationStatistic;
import lottery.function.shortterm.SummationDeviationStatistic.SummationDeviation;
import lottery.itf.Result;
import lottery.view.menu.AbstractTermMenu;

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

public class SumDeviationBarChartMenu extends AbstractTermMenu {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6064810859984901933L;

	public SumDeviationBarChartMenu() {
		// TODO Auto-generated constructor stub
		super();
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		super.initialize();
		setName("SumDeviationBarChartMenu");
		setText("和数值偏差");
		function = new SummationDeviationStatistic();
		field.setText("50");
	}

	@Override
	public void showGraphics(Result result) {
		// TODO Auto-generated method stub
		final JFreeChart chart = createChart(result);
		int size = ((List<SummationDeviation>) result.getValue()).size();
		int length = size % 10 == 0 ? size / 10 : size / 10 + 1;
		int width = length * 250;
		if (width < 1024) {
			width = 1024;
		}
		final ChartPanel cp = new ChartPanel(chart, width, 768, 1024, 768,
				width, 768, true, false, false, false, false, true, true);
		JScrollPane jsp = new JScrollPane(cp);

		final JFrame frame = new JFrame("和数值偏差_[" + this.length + "期]");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().add(jsp);
		frame.setSize(1280, 850);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private JFreeChart createChart(Result result) {
		List<SummationDeviation> sds = (List<SummationDeviation>) result.getValue();
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (SummationDeviation sd : sds) {
			dataset.addValue(sd.getSd(), "和数值偏差",
					sd.getIssue() + " - [" + sd.getSummation() + "]");
		}
		JFreeChart chart = ChartFactory.createBarChart("和数值分布图", // chart
				"期数", // domain axis label
				"偏差值", // range axis label
				dataset, // data
				PlotOrientation.VERTICAL, // orientation
				true, // include legend
				true, // tooltips?
				false // URLs?
				);

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
		// categoryPlot.setDomainAxisLocation(AxisLocation.TOP_OR_RIGHT);

		CategoryAxis domainAxis = categoryPlot.getDomainAxis(); // x轴对象
		domainAxis.setLabelFont(new Font("宋体", Font.BOLD, 14)); // x轴的标题
		domainAxis.setTickLabelFont(new Font("宋体", Font.PLAIN, 14)); // x轴坐标上的文字
		domainAxis.setTickLabelsVisible(true);// X轴的标题文字是否显示
		domainAxis.setMaximumCategoryLabelWidthRatio(0.7f); // X轴的标题文字显示比例
		domainAxis.setCategoryMargin(0.03); // X轴标签之间的距离10%
		domainAxis.setLowerMargin(0.0);
		domainAxis.setUpperMargin(0.001);
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_90);

		ValueAxis rangeAxis = categoryPlot.getRangeAxis();// y轴对象
		rangeAxis.setLabelFont(new Font("宋体", Font.BOLD, 14)); // y轴的标题
		rangeAxis.setTickLabelFont(new Font("宋体", Font.BOLD, 12)); // y轴坐标上的文字
		rangeAxis.setRange(-81, 81); // Y轴取值范围
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

		return chart;
	}

}
