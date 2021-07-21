package ucf.assignments;

import javafx.scene.control.TableView;
import javafx.stage.FileChooser;

import java.io.*;

public class ParseTsv {
    public void import_tsv(TableView<Inventory_Item> tableView)  {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open file");
        File file = fileChooser.showOpenDialog(tableView.getScene().getWindow());
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] split = line.split("\t");

                //Create item
                Inventory_Item item = new Inventory_Item(split[0],split[1],split[2]);
                //Add item
                tableView.getItems().add(item);
            }
        } catch (IOException e) {
            ErrorMessages.displayFileNotFoundError();
        }
    }
}
