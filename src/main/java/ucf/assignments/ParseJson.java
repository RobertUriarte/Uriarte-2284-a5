/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 first_name last_name
 */
package ucf.assignments;

//Set up imports
import java.io.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;

public class ParseJson {
    public static void parse(TableView<Inventory_Item> tableView) {
        //Initialize items
        ObservableList<Inventory_Item> items = FXCollections.observableArrayList();
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open file");
            File file = fileChooser.showOpenDialog(tableView.getScene().getWindow());
            //Initialize File reader
            JsonElement fileElement = JsonParser.parseReader(new FileReader(file));
            //Get file as json object
            JsonObject fileObject = fileElement.getAsJsonObject();

            //Get items from json file
            JsonArray jsonArrayofItems = fileObject.get("items").getAsJsonArray();
            //Get item values from json
            //Loop for number of items
            for(JsonElement productElement : jsonArrayofItems){
                //Get json product
                JsonObject productJsonObject = productElement.getAsJsonObject();

                //Get values
                String value = productJsonObject.get("value").getAsString();
                String serial = productJsonObject.get("serial").getAsString();
                String name = productJsonObject.get("name").getAsString();


                //Create item
                Inventory_Item item = new Inventory_Item(value,serial,name);
                //Add item
                tableView.getItems().add(item);
            }
        }
        //Error case
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}