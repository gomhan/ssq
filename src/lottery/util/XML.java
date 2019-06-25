package lottery.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import lottery.model.DoubleChromosphere;

public class XML {

	public void createLotteryXML(List<DoubleChromosphere> dcs, String path) {
		XMLWriter cmdWriter = null;
		XMLWriter fileWriter = null;
		try {
			// ����document����
			Document document = DocumentHelper.createDocument();
			Element root = document.addElement("Lottery");// ����ĵ���
			root.addComment("this is lottery root node.");// ����һ��ע��
			for (DoubleChromosphere dc : dcs) {
				addDcNode(root, dc);
			}

			// ���ȫ��ԭʼ���ݣ��ڱ���������ʾ
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("UTF-8");// ������Ҫ���ñ���
			cmdWriter = new XMLWriter(System.out, format);
			document.normalize();
			cmdWriter.write(document);
			// ���ȫ��ԭʼ���ݣ������������µ�������Ҫ��XML�ļ�
			fileWriter = new XMLWriter(new FileWriter(new File(path)), format);
			fileWriter.write(document); // ������ļ�
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (cmdWriter != null) {
					cmdWriter.close();
				}
				if (fileWriter != null) {
					fileWriter.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void addDcNode(Element root, DoubleChromosphere dc) {
		Element dcNode = root.addElement("dc");
		dcNode.addElement("issue").addText(dc.getIssue());
		dcNode.addElement("time").addText(dc.getTime());
		dcNode.addElement("red").addText(dc.getRedString());
		dcNode.addElement("blue").addText(Integer.toString(dc.getBlue()));
		dcNode.addElement("summation").addText(
				Integer.toString(dc.getSummation()));
	}

	public List<DoubleChromosphere> parseLotteryXML(String path) {
		List<DoubleChromosphere> dcs = new ArrayList<DoubleChromosphere>();
		try {
			SAXReader sax = new SAXReader();
			Document xmlDoc = sax.read(new File(path));
			Element root = xmlDoc.getRootElement();// ���ڵ�
			Iterator it = root.elementIterator("dc");
			Element dcNode = null;
			DoubleChromosphere dc = null;
			while (it.hasNext()) {
				dcNode = (Element) it.next();
				dc = parseDcNode(dcNode);
				if (dc != null) {
					dcs.add(dc);
				}
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dcs;
	}

	private DoubleChromosphere parseDcNode(Element dcNode) {
		DoubleChromosphere dc = new DoubleChromosphere();
		Element issue = dcNode.element("issue");
		dc.setIssue(issue.getTextTrim());
		Element time = dcNode.element("time");
		dc.setTime(time.getTextTrim());
		Element red = dcNode.element("red");
		String allRed = red.getTextTrim();
		String[] reds = allRed.split(",");
		dc.setRed1(Byte.parseByte(reds[0]));
		dc.setRed2(Byte.parseByte(reds[1]));
		dc.setRed3(Byte.parseByte(reds[2]));
		dc.setRed4(Byte.parseByte(reds[3]));
		dc.setRed5(Byte.parseByte(reds[4]));
		dc.setRed6(Byte.parseByte(reds[5]));
		Element blue = dcNode.element("blue");
		dc.setBlue(Byte.parseByte(blue.getTextTrim()));
		dc.summation();
		return dc;
	}

}
