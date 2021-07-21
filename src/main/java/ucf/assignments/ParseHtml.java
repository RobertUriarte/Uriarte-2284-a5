package ucf.assignments;

import javafx.scene.control.TableView;
import javafx.stage.FileChooser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ParseHtml {
    public void import_html(TableView<Inventory_Item> tableView){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open file");
        File file = fileChooser.showOpenDialog(tableView.getScene().getWindow());
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if(line.contains("$")){
                    String[] split = line.split(" ");
                    String[] split2= split[0].split(">");
                    //Create item
                    Inventory_Item item = new Inventory_Item(split2[1],split[1],split[2]);
                    //Add item
                    tableView.getItems().add(item);
                }
            }
        } catch (IOException e) {
            ErrorMessages.displayFileNotFoundError();
        }
    }
}

