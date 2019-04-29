package guruBanking.loginTest;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;

public class Util {
    public static final String DRIVER_PATH = "C:\\Selenium\\drivers\\geckodriver.exe";
    public static final String FIREFOX_BINARY_PATH = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
    public static final String BASE_URL = "http://www.demo.guru99.com/";
    public static final String USER_ID = "mngr190731";
    public static final String PSSWRD = "uqugyju";
    public static final String EXPECTED_TITLE = "Guru99 Bank Manager HomePage";
    public static final int WAIT_TIME = 30;
    public static final String EXPECT_ERROR = "User or Password is not valid";

    public static final String FILE_PATH = System.getProperty("user.dir") + "\\src\\loginTest\\testData.xlsx"; // File Path
    //    public static final String FILE_PATH = "loginTest\\testData.xlsx"; // File Path
    public static final String SHEET_NAME = "Data"; // Sheet name
    public static final String TABLE_NAME = "testData"; // Name of data table

    public static final String PATTERN = ":";
    public static final String FIRST_PATTERN = "mngr";
    public static final String SECOND_PATTERN = "[0-9]+";


    /**
     * This method reads the data from the Sheet name "Data" of file
     * "testData.xls"
     *
     * @param xlFilePath : Path of excel file
     * @param sheetName  : Sheet name which contains test data
     * @param tableName  : Table name is used to mark the start and end position of the
     *                   test data table. The method will find the cell which contains
     *                   the table name to find position of data table
     * @return a 2 dimensions array to store all the test data read from excel
     * @throws Exception
     */
    public static String[][] getDataFromExcel(String xlFilePath,
                                              String sheetName, String tableName) throws Exception {
        // Declare a 2 dimensions array to store all the test data read from
        // excel
        String[][] tabArray = null;

        // get the workbook file. Provide the path of excel file
//        Workbook workbook = Workbook.getWorkbook(new File(xlFilePath));
        Workbook workbook = new XSSFWorkbook(new FileInputStream(new File(xlFilePath)));
        // get the sheet name
        Sheet sheet = workbook.getSheet(sheetName);

        // find cell position which contain first appear table name
        Cell startCell = null;
        findCell:
        {
            for (Row row : sheet) {
                for (Cell cell : row) {
                    if (cell.getCellType() == CellType.STRING) {
                        if (cell.getStringCellValue().trim().equals(tableName)) {
                            startCell = cell;
                            break findCell;
                        }
                    }
                }
            }
        }
        int startRow, startCol, endRow, endCol, ci, cj;
        // Row position of FIRST appear table name
        startRow = startCell.getRowIndex();
        // Col position of FIRST appear table name
        startCol = startCell.getColumnIndex();

        System.out.println("Start row-col: " + startRow + "-" + startCol);

        // find cell position which contain last appear table name
        Cell endCell = null;
        for (Row row : sheet) {
            for (Cell cell : row) {
                if (cell.getCellType() == CellType.STRING) {
                    if (!cell.equals(startCell) && cell.getStringCellValue().trim().equals(tableName)) {
                        endCell = cell;
                    }
                }
            }
        }
        // Row position of LAST appear table name
        endRow = endCell.getRowIndex();
        // Col position of LAST appear table name
        endCol = endCell.getColumnIndex();

        System.out.println("End row-col: " + endRow + "-" + endCol);

        tabArray = new String[endRow - startRow - 1][endCol - startCol - 1];

        ci = 0;
        // Store all data in an array
        for (int i = startRow + 1; i < endRow; i++, ci++) {
            cj = 0;
            for (int j = startCol + 1; j < endCol; j++, cj++) {
                //Get content of each cell and store to each array element.
                tabArray[ci][cj] = sheet.getRow(i).getCell(j).getStringCellValue();
            }
        }

        return (tabArray);
    }
}
