/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Robert Uriarte
 */
package ucf.assignments;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ParseHtml {
    public void import_html(TableView<Inventory_Item> tableView){
        //Initialize file chooser
        //Set title
        //Get file from user
        //Read html file

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open file");
        File file = fileChooser.showOpenDialog(tableView.getScene().getWindow());
        read_html(tableView.getItems(),file);
    }

    public void read_html(ObservableList<Inventory_Item> item_list, File file){
        //Try to read data from file
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) { //Read till end of file
                if(line.contains("$")){ //Check once the first money value is reached
                    String[] split = line.split("&emsp;");
                    String[] split2 = split[0].split(">");
                    //Create item
                    Inventory_Item item = new Inventory_Item(split2[1],split[1],split[2]);
                    //Add item
                    item_list.add(item);
                }
            }
        } catch (IOException e) {
            ErrorMessages errorMessages = new ErrorMessages();
            errorMessages.displayFileNotFoundError();
        }
    }
}

