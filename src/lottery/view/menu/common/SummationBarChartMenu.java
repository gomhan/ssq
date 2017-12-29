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

import lottery.function.common.SummationStatistic;
import lottery.function.common.SummationStatistic.SummationCount;
import lottery.itf.Result;
import lottery.util.LotteryConst;
import lottery.view.menu.FunctionMenu;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
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

public class SummationBarChartMenu extends FunctionMenu {
	private static final long serialVersionUID = -9263520548285241L;

	JPanel p;
	JLabel label;
	JLabel label2;
	JCheckBox box;
	boolean abbreviate;

	public SummationBarChartMenu() {
		super();
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		setName("SummationBarChartMenu");
		setText("����ֵ�ֲ���״ͼ");
		setPreferredSize(new Dimension(230, 30));
		setMinimumSize(new Dimension(230, 30));
		setMaximumSize(new Dimension(230, 30));
		function = new SummationStatistic();

		label = new JLabel("[���ԣ�");
		label2 = new JLabel("]");
		box = new JCheckBox();
		p = new JPanel(new FlowLayout(FlowLayout.LEADING));
		p.setBackground(LotteryConst.COMPONENT_DEFAULT_BG);
		box.setBackground(LotteryConst.COMPONENT_DEFAULT_BG);
		label.setOpaque(false);
		label2.setOpaque(false);
		p.setOpaque(false);
	}

	protected void build() {
		p.add(label);
		p.add(box);
		p.add(label2);
		
		setLayout(new BorderLayout());
		add(p, BorderLayout.EAST);
	}

	@Override
	protected void handleUserInput() {
		// TODO Auto-generated method stub
		abbreviate = box.isSelected();
		box.setSelected(false);
	}

	public void showGraphics(Result result) {
		JFreeChart chart = createChart(result);
		if (abbreviate) {
			ChartFrame frame = new ChartFrame("����ֵ�ֲ�ͼ", chart, true);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setSize(1280, 960);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		} else {
			int size = ((List<SummationCount>) result.getValue()).size();
			int length = size % 10 == 0 ? size / 10 : size / 10 + 1;
			int width = length * 400;
			ChartPanel cp = new ChartPanel(chart, width, 768, 1024, 768, width,
					768, true, true, true, true, true, true);
			JScrollPane jsp = new JScrollPane(cp);

			JFrame frame = new JFrame("����ֵ�ֲ�ͼ");
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.getContentPane().add(jsp);
			frame.setSize(1280, 960);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		}
	}

	private JFreeChart createChart(Result result) {
		List<SummationCount> scs = (List<SummationCount>) result.getValue();
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

		CategoryPlot categoryPlot = chart.getCategoryPlot();// ����ϵͼ������
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
		domainAxis.setCategoryMargin(0.03); // X���ǩ֮��ľ���10%
		domainAxis.setLowerMargin(0.0);
		domainAxis.setUpperMargin(0.0);
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);

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
		renderer.setMaximumBarWidth(0.02); // �������ӿ���
		categoryPlot.setRenderer(renderer);

		return chart;
	}

}