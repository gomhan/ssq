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

	/** default result identifier */
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
}
