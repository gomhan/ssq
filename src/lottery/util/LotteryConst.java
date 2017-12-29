package lottery.util;

import java.awt.Color;
import java.io.File;
import java.text.DecimalFormat;

public class LotteryConst {

	public LotteryConst() {
		// TODO Auto-generated constructor stub
	}

	/** decimal format with "#.000" */
	public static final DecimalFormat DF = new DecimalFormat("#.000");

	/** default table cell back ground */
	public static final Color TABLE_CELL_DEFUALT_BG = new Color(247, 248, 245);
	/** default color of back ground of component */
	public static final Color COMPONENT_DEFAULT_BG = new Color(0xEF, 0xEF, 0xEF);

	/**
	 * One function at most have 255 results, the default result is associate
	 * with {@code DEFAULT_IDENTIFIER}.
	 */
	public static final int DEFAULT_IDENTIFIER = 0xFF;
	/** count of red ball */
	public static final int RED_BALL_COUNT = 33;

	/** main panel menu item index 0 */
	public static final int MENU_INDEX_0 = 0;
	/** main panel menu item index 1 */
	public static final int MENU_INDEX_1 = 1;
	/** main panel menu item index 2 */
	public static final int MENU_INDEX_2 = 2;
	/** main panel menu item index 3 */
	public static final int MENU_INDEX_3 = 3;
	/** main panel menu item index 4 */
	public static final int MENU_INDEX_4 = 4;
	/** main panel menu item index 5 */
	public static final int MENU_INDEX_5 = 5;

	public static final String PROJECT_PATH = System.getProperty("user.dir")
			+ File.separatorChar;

	public static final int LOW_HIGH_SEPARATOR = 17;

	public static final int DEVIATION_BEST = 102;
	public static final int DEVIATION_LOWEST = 79;
	public static final int DEVIATION_HIGHEST = 125;

	/** define common function offset, between 0x0 ~ 0xF */
	public static final int COMMON_OFFSET = 0x0;
	/** define short-term function offset, between 0x10 ~ 0x1F */
	public static final int SHORT_TERM_OFFSET = 0x10;
	/** define middle-term function offset, between 0x20 ~ 0x2F */
	public static final int MIDDLE_TERM_OFFSET = 0x20;
	/** define long-term function offset, between 0x30 ~ 0x3F */
	public static final int LONG_TERM_OFFSET = 0x30;
	/** define customize function offset, begin at 0x100 */
	public static final int CUSTOMIZE_OFFSET = 0x100;
}
