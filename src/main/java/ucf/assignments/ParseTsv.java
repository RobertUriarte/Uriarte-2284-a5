/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Robert Uriarte
 */
package ucf.assignments;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;

import java.io.*;

public class ParseTsv {
    public void import_tsv(TableView<Inventory_Item> tableView)  {
        //Initialize file chooser
        //Set title
        //Get file from user
        //Read file from user

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open file");
        File file = fileChooser.showOpenDialog(tableView.getScene().getWindow());
        read_tsv_file(tableView.getItems(),file);
    }

    public void read_tsv_file(ObservableList<Inventory_Item> item_list, File file){
        //Try to read file and separate strings by tab spaces
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] split = line.split("\t");

                //Create item
                Inventory_Item item = new Inventory_Item(split[0],split[1],split[2]);
                //Add item
                item_list.add(item);
            }
        } catch (IOException e) {
            ErrorMessages.displayFileNotFoundError();
        }
    }
}
