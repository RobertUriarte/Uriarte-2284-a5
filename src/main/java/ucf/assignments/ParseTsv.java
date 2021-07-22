/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Robert Uriarte
 */
package ucf.assignments;

import javafx.scene.control.TableView;
import javafx.stage.FileChooser;

import java.io.*;

public class ParseTsv {
    public void import_tsv(TableView<Inventory_Item> tableView)  {
        //Initialize file chooser
        //Set title
        //Get file from user

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open file");
        File file = fileChooser.showOpenDialog(tableView.getScene().getWindow());

        //Try to read file and separate strings by tab spaces
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
