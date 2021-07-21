package ucf.assignments;
import javafx.scene.control.TableView;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelExport<Todo_Item> {
    //Initialize Export Function
    public void export(TableView<Todo_Item> tableView){
        //Initialize workbook
        //Initialize sheet
        //Initialize rows

        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet hssfSheet = hssfWorkbook.createSheet("Inventory");
        HSSFRow firstRow = hssfSheet.createRow(0);

        //Loop for number of cells in table
        for (int i=0; i<tableView.getColumns().size();i++){
            firstRow.createCell(i).setCellValue(tableView.getColumns().get(i).getText());
        }

        //Loop for number of rows in table
        for (int row=0; row<tableView.getItems().size();row++){

            HSSFRow hssfRow= hssfSheet.createRow(row+1);

            for (int col=0; col<tableView.getColumns().size(); col++){

                Object celValue = tableView.getColumns().get(col).getCellObservableValue(row).getValue();

                try {
                    if (celValue != null && Double.parseDouble(celValue.toString()) != 0.0) {
                        hssfRow.createCell(col).setCellValue(Double.parseDouble(celValue.toString()));
                    }
                } catch (  NumberFormatException e ){

                    hssfRow.createCell(col).setCellValue(celValue.toString());
                }

            }

        }

        //Initialize File
        //Save to excel file
        //Set up try catch
        try {
            File file = new File("Inventory.xls");
            file.createNewFile(); // if file already exists will do nothing
            hssfWorkbook.write(new FileOutputStream(file, false));
            hssfWorkbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}