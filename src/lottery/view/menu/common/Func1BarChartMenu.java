package lottery.view.menu.common;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.RenderingHints;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import lottery.function.FunctionResult;
import lottery.function.common.Function1;
import lottery.function.common.Function1.SummationCount;
import lottery.itf.Function;
import lottery.util.LotteryConst;
import lottery.view.menu.FunctionMenu;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
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

public class Func1BarChartMenu extends FunctionMenu {
	private static final long serialVersionUID = -9263520548285241L;

	JLabel label;
	JLabel label2;
	JCheckBox box;
	boolean abbreviate;
	Function function;

	public Func1BarChartMenu() {
		// TODO Auto-generated constructor stub
		super();
		setName("Func1BarChartMenu");
		setText("����ֵ�ֲ���״ͼ");
		addActionListener(this);
		function = new Function1();

		label = new JLabel("[���ԣ�");
		label2 = new JLabel("]");
		box = new JCheckBox();

		JPanel p = new JPanel(new FlowLayout(FlowLayout.LEADING));
		p.add(label);
		p.add(box);
		p.add(label2);
		p.setBackground(LotteryConst.COMPONENT_DEFAULT_BG);
		box.setBackground(LotteryConst.COMPONENT_DEFAULT_BG);

		setPreferredSize(new Dimension(230, 30));
		setMinimumSize(new Dimension(230, 30));
		setMaximumSize(new Dimension(230, 30));
		setLayout(new BorderLayout());
		add(p, BorderLayout.EAST);
	}

	@Override
	protected Function getFunction() {
		// TODO Auto-generated method stub
		return function;
	}

	public void showChart(FunctionResult fr) {
		JFreeChart chart = createChart(fr);
		abbreviate = box.isSelected();
		box.setSelected(false);
		if (abbreviate) {
			ChartFrame frame = new ChartFrame("����ֵ�ֲ�ͼ", chart, true);
			frame.setSize(1280, 768);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		} else {
			ChartPanel cp = new ChartPanel(chart, 5500, 960, 1280, 960, 5500,
					960, true, true, true, true, true, true);
			JScrollPane jsp = new JScrollPane(cp);

			JFrame frame = new JFrame("����ֵ�ֲ�ͼ");
			frame.getContentPane().add(jsp);
			frame.setSize(1280, 960);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		}
	}

	private JFreeChart createChart(FunctionResult fr) {
		List<SummationCount> scs = (List<SummationCount>) fr.getValue();
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (SummationCount sc : scs) {
			dataset.addValue(sc.getCount(), "summation", sc.getSummation());
		}
		JFreeChart chart = ChartFactory.createBarChart("����ֵ�ֲ�ͼ", // chart
				"����ֵ", // domain axis label
				"����", // range axis label
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

		CategoryPlot categoryPlot = chart.getCategoryPlot();// ����ϵͼ�����
		categoryPlot.setBackgroundPaint(Color.WHITE); // ����
		categoryPlot.setDomainGridlinePaint(Color.BLACK);// ����������������ɫ��x�ᣩ
		categoryPlot.setDomainGridlinesVisible(true);
		categoryPlot.setRangeGridlinePaint(Color.GREEN);// ����������������ɫ��y�ᣩ
		categoryPlot.setRangeGridlinesVisible(true);

		CategoryAxis domainAxis = categoryPlot.getDomainAxis(); // x�����
		domainAxis.setLabelFont(new Font("����", Font.BOLD, 14)); // x��ı���
		domainAxis.setTickLabelFont(new Font("����", Font.PLAIN, 14)); // x�������ϵ�����
		domainAxis.setTickLabelsVisible(true);// X��ı��������Ƿ���ʾ
		domainAxis.setMaximumCategoryLabelWidthRatio(0.7f); // X��ı���������ʾ����
		domainAxis.setCategoryMargin(0.1); // X���ǩ֮��ľ���10%

		ValueAxis rangeAxis = categoryPlot.getRangeAxis();// y�����
		rangeAxis.setLabelFont(new Font("����", Font.BOLD, 14)); // y��ı���
		rangeAxis.setTickLabelFont(new Font("����", Font.BOLD, 12)); // y�������ϵ�����
		rangeAxis.setRange(0, 60); // Y��ȡֵ��Χ
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
		renderer.setMaximumBarWidth(0.8); // �������ӿ��
		categoryPlot.setRenderer(renderer);

		return chart;
	}

}
