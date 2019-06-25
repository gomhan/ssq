package lottery.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lottery.model.DoubleChromosphere;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.NPOIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {

	private List<DoubleChromosphere> lottery;

	public Excel() {
		// TODO Auto-generated constructor stub
		lottery = new ArrayList<DoubleChromosphere>();
	}

	public List<DoubleChromosphere> result() {
		return lottery;
	}

	public void uninstall() {
		lottery = null;
	}

	public boolean load(String path) {
		boolean b = false;
		File file = null;
		try {
			file = new File(path);
			if (file != null && file.isFile()) {
				lottery.clear();
				if (path.endsWith(".xls")) {
					b = handleHSSF(file);
				} else if (path.endsWith(".xlsx")) {
					b = handleXSSF(file);
				}
			}
		} catch (NullPointerException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

		}

		return b;
	}

	private boolean handleHSSF(File file) {
		boolean b = false;
		NPOIFSFileSystem nfs = null;
		HSSFWorkbook wb = null;
		HSSFSheet sheet = null;
		try {
			nfs = new NPOIFSFileSystem(file);
			wb = new HSSFWorkbook(nfs.getRoot(), true);
			sheet = wb.getSheetAt(0);
			b = readData(sheet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (wb != null) {
				try {
					wb.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (nfs != null) {
				try {
					nfs.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return b;
	}

	private boolean readData(HSSFSheet sheet) {
		boolean b = true;
		int firstRowNum = sheet.getFirstRowNum() + 2;
		int lastRowNum = sheet.getLastRowNum();
		HSSFRow row;
		HSSFCell cell;
		DoubleChromosphere dc;
		Object value;
		int j;
		for (int i = firstRowNum; i <= lastRowNum; i++) {
			row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			dc = new DoubleChromosphere();
			// issue
			j = 1;
			cell = row.getCell(j);
			if (cell == null) {
				b = false;
				break;
			}
			value = getCellValue(cell);
			dc.setIssue(value.toString());
			// time
			j++;
			cell = row.getCell(j);
			if (cell == null) {
				b = false;
				break;
			}
			value = getCellValue(cell);
			dc.setTime(value.toString());
			// red1
			j++;
			cell = row.getCell(j);
			if (cell == null) {
				b = false;
				break;
			}
			value = getCellValue(cell);
			dc.setRed1(Byte.parseByte(value.toString()));
			// red2
			j++;
			cell = row.getCell(j);
			if (cell == null) {
				b = false;
				break;
			}
			value = getCellValue(cell);
			dc.setRed2(Byte.parseByte(value.toString()));
			// red3
			j++;
			cell = row.getCell(j);
			if (cell == null) {
				b = false;
				break;
			}
			value = getCellValue(cell);
			dc.setRed3(Byte.parseByte(value.toString()));
			// red4
			j++;
			cell = row.getCell(j);
			if (cell == null) {
				b = false;
				break;
			}
			value = getCellValue(cell);
			dc.setRed4(Byte.parseByte(value.toString()));
			// red5
			j++;
			cell = row.getCell(j);
			if (cell == null) {
				b = false;
				break;
			}
			value = getCellValue(cell);
			dc.setRed5(Byte.parseByte(value.toString()));
			// red6
			j++;
			cell = row.getCell(j);
			if (cell == null) {
				b = false;
				break;
			}
			value = getCellValue(cell);
			dc.setRed6(Byte.parseByte(value.toString()));
			// blue
			j++;
			cell = row.getCell(j);
			if (cell == null) {
				b = false;
				break;
			}
			value = getCellValue(cell);
			dc.setBlue(Byte.parseByte(value.toString()));
			dc.summation();
			lottery.add(dc);
		}

		return b;
	}

	private Object getCellValue(HSSFCell cell) {
		Object value = "";
		if (cell != null) {
			int cellType = cell.getCellType();
			HSSFCellStyle style = cell.getCellStyle();
			short format = style.getDataFormat();
			switch (cellType) {
			case HSSFCell.CELL_TYPE_NUMERIC:
			case HSSFCell.CELL_TYPE_FORMULA:
				double numTxt = cell.getNumericCellValue();
				if (format == 22 || format == 14) {
					value = HSSFDateUtil.getJavaDate(numTxt).toString();
				} else {
					value = String.valueOf(numTxt);
				}
				break;
			case HSSFCell.CELL_TYPE_BLANK:
				break;
			case HSSFCell.CELL_TYPE_STRING:
				HSSFRichTextString rtxt = cell.getRichStringCellValue();
				if (rtxt == null) {
					break;
				}
				value = rtxt.getString();
				break;
			case HSSFCell.CELL_TYPE_ERROR:
				value = "#N/A";
				System.err.println("Cell with error");
				break;
			default:
				break;
			}
		}

		return value;
	}

	private boolean handleXSSF(File file) {
		boolean b = false;
		XSSFWorkbook book = null;
		XSSFSheet sheet = null;
		try {
			book = new XSSFWorkbook(file);
			sheet = book.getSheetAt(0);
			b = readData(sheet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (book != null) {
				try {
					book.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return b;
	}

	private boolean readData(XSSFSheet sheet) {
		boolean b = true;
		int firstRowNum = sheet.getFirstRowNum() + 2;
		int lastRowNum = sheet.getLastRowNum();
		XSSFRow row;
		XSSFCell cell;
		DoubleChromosphere dc;
		Object value;
		int j;
		for (int i = firstRowNum; i <= lastRowNum; i++) {
			row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			dc = new DoubleChromosphere();
			// issue
			j = 1;
			cell = row.getCell(j);
			if (cell == null) {
				b = false;
				break;
			}
			value = getCellValue(cell);
			dc.setIssue(value.toString());
			// time
			j++;
			cell = row.getCell(j);
			if (cell == null) {
				b = false;
				break;
			}
			value = getCellValue(cell);
			dc.setTime(value.toString());
			// red1
			j++;
			cell = row.getCell(j);
			if (cell == null) {
				b = false;
				break;
			}
			value = getCellValue(cell);
			dc.setRed1(Byte.parseByte(value.toString()));
			// red2
			j++;
			cell = row.getCell(j);
			if (cell == null) {
				b = false;
				break;
			}
			value = getCellValue(cell);
			dc.setRed2(Byte.parseByte(value.toString()));
			// red3
			j++;
			cell = row.getCell(j);
			if (cell == null) {
				b = false;
				break;
			}
			value = getCellValue(cell);
			dc.setRed3(Byte.parseByte(value.toString()));
			// red4
			j++;
			cell = row.getCell(j);
			if (cell == null) {
				b = false;
				break;
			}
			value = getCellValue(cell);
			dc.setRed4(Byte.parseByte(value.toString()));
			// red5
			j++;
			cell = row.getCell(j);
			if (cell == null) {
				b = false;
				break;
			}
			value = getCellValue(cell);
			dc.setRed5(Byte.parseByte(value.toString()));
			// red6
			j++;
			cell = row.getCell(j);
			if (cell == null) {
				b = false;
				break;
			}
			value = getCellValue(cell);
			dc.setRed6(Byte.parseByte(value.toString()));
			// blue
			j++;
			cell = row.getCell(j);
			if (cell == null) {
				b = false;
				break;
			}
			value = getCellValue(cell);
			dc.setBlue(Byte.parseByte(value.toString()));
			dc.summation();
			lottery.add(dc);
		}

		return b;
	}

	private Object getCellValue(XSSFCell cell) {
		Object value = "";
		if (cell != null) {
			int cellType = cell.getCellType();
			XSSFCellStyle style = cell.getCellStyle();
			short format = style.getDataFormat();
			switch (cellType) {
			case XSSFCell.CELL_TYPE_NUMERIC:
			case XSSFCell.CELL_TYPE_FORMULA:
				double numTxt = cell.getNumericCellValue();
				if (format == 22 || format == 14) {
					value = HSSFDateUtil.getJavaDate(numTxt).toString();
				} else {
					value = String.valueOf(numTxt);
				}
				break;
			case XSSFCell.CELL_TYPE_BLANK:
				break;
			case XSSFCell.CELL_TYPE_STRING:
				XSSFRichTextString rtxt = cell.getRichStringCellValue();
				if (rtxt == null) {
					break;
				}
				value = rtxt.getString();
				break;
			case XSSFCell.CELL_TYPE_ERROR:
				value = "#N/A";
				System.err.println("Cell with error");
				break;
			default:
				break;
			}
		}
		return value;
	}
}
