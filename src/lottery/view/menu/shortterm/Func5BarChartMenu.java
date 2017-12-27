package lottery.view.menu.shortterm;

import java.awt.Color;
import java.awt.Font;
import java.awt.RenderingHints;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import lottery.function.FunctionResult;
import lottery.function.shortterm.Function5;
import lottery.function.shortterm.Function5.SummationDeviation;
import lottery.itf.Function;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
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

public class Func5BarChartMenu extends ShortTermMenu {

	public Func5BarChartMenu() {
		// TODO Auto-generated constructor stub
		super();
		setName("Func5BarChartMenu");
		setText("����ֵƫ��");
		
		field.setText("40");
		function = new Function5();
	}

	@Override
	public void showChart(FunctionResult fr) {
		// TODO Auto-generated method stub
		final JFreeChart chart = createChart(fr);
		int size = ((List<SummationDeviation>) fr.getValue()).size();
		int length = size % 10 == 0 ? size / 10 : size / 10 + 1;
		int width = length * 250;
		if (width < 1024) {
			width = 1024;
		}
		final ChartPanel cp = new ChartPanel(chart, width, 768, 1024, 768,
				width, 768, true, false, false, false, false, true, true);
		JScrollPane jsp = new JScrollPane(cp);

		final JFrame frame = new JFrame("����ֵƫ��_[" + (end - issue) + "��]");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().add(jsp);
		frame.setSize(1280, 850);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	@Override
	protected Function getFunction() {
		// TODO Auto-generated method stub
		return function;
	}

	private JFreeChart createChart(FunctionResult fr) {
		List<SummationDeviation> sds = (List<SummationDeviation>) fr.getValue();
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (SummationDeviation sd : sds) {
			dataset.addValue(sd.getSd(), "����ֵƫ��", sd.getIssue() + " - ["+sd.getSummation()+"]");
		}
		JFreeChart chart = ChartFactory.createBarChart("����ֵ�ֲ�ͼ", // chart
				"����", // domain axis label
				"ƫ��ֵ", // range axis label
				dataset, // data
				PlotOrientation.VERTICAL, // orientation
				true, // include legend
				true, // tooltips?
				false // URLs?
				);

		chart.getTitle().setFont(new Font("����", Font.BOLD, 15));// ���ñ���
		chart.getLegend().setItemFont(new Font("����", Font.BOLD, 15)); // ����ͼ���������
		chart.setBackgroundPaint(Color.WHITE);
		/*----------������������ľ����Ⱦ������������⣩--------------*/
		chart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);

		CategoryPlot categoryPlot = chart.getCategoryPlot();// ����ϵͼ������
		categoryPlot.setBackgroundPaint(Color.WHITE); // ����
		categoryPlot.setDomainGridlinePaint(Color.BLACK);// ����������������ɫ��x�ᣩ
		categoryPlot.setDomainGridlinesVisible(true);
		categoryPlot.setRangeGridlinePaint(Color.GREEN);// ����������������ɫ��y�ᣩ
		categoryPlot.setRangeGridlinesVisible(true);
		categoryPlot.setForegroundAlpha(1.0f);// ��״ͼ͸����
		// categoryPlot.setDomainAxisLocation(AxisLocation.TOP_OR_RIGHT);

		CategoryAxis domainAxis = categoryPlot.getDomainAxis(); // x�����
		domainAxis.setLabelFont(new Font("����", Font.BOLD, 14)); // x��ı���
		domainAxis.setTickLabelFont(new Font("����", Font.PLAIN, 14)); // x�������ϵ�����
		domainAxis.setTickLabelsVisible(true);// X��ı��������Ƿ���ʾ
		domainAxis.setMaximumCategoryLabelWidthRatio(0.7f); // X��ı���������ʾ����
		domainAxis.setCategoryMargin(0.03); // X���ǩ֮��ľ���10%
		domainAxis.setLowerMargin(0.0);
		domainAxis.setUpperMargin(0.001);
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_90);

		ValueAxis rangeAxis = categoryPlot.getRangeAxis();// y�����
		rangeAxis.setLabelFont(new Font("����", Font.BOLD, 14)); // y��ı���
		rangeAxis.setTickLabelFont(new Font("����", Font.BOLD, 12)); // y�������ϵ�����
		rangeAxis.setRange(-81, 81); // Y��ȡֵ��Χ
		NumberAxis na = (NumberAxis) categoryPlot.getRangeAxis(); // y�ᾫ��
		na.setAutoRangeIncludesZero(true);
		DecimalFormat df = new DecimalFormat("#0");
		na.setNumberFormatOverride(df);// ���������ݱ�ǩ����ʾ��ʽ

		BarRenderer renderer = (BarRenderer) categoryPlot.getRenderer();
		// ��ʾ��Ŀ��ǩ
		renderer.setBaseItemLabelsVisible(true);
		// ������Ŀ��ǩ������,��JFreeChart1.0.6֮ǰ����ͨ��renderer.setItemLabelGenerator(CategoryItemLabelGenerator
		// generator)����ʵ�֣����ǴӰ汾1.0.6��ʼ�����淽������
		renderer.setBaseItemLabelPaint(Color.BLUE);// ������ֵ��ɫ��Ĭ�Ϻ�ɫ
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		// ������Ŀ��ǩ��ʾ��λ��,outline��ʾ����Ŀ������,baseline_center��ʾ���ڻ����Ҿ���
		renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(
				ItemLabelAnchor.OUTSIDE12, TextAnchor.CENTER_LEFT));
		renderer.setMaximumBarWidth(0.03); // �������ӿ���
		categoryPlot.setRenderer(renderer);

		return chart;
	}

}