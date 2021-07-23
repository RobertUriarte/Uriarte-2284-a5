/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Robert Uriarte
 */
package ucf.assignments;

//Set up imports
import javafx.collections.ObservableList;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;

public class TsvExport extends Component {
    public void export_tsv(ObservableList<Inventory_Item> item_list){
        //Initialize file chooser
        //Set up save dialog
        //Set file name to .txt to save tsv files
        //Set up filter to show tsv files

        JFileChooser fileChooser= new JFileChooser();
        fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooser.setSelectedFile(new File(".txt"));
        fileChooser.setFileFilter(new FileNameExtensionFilter("txt file","txt"));

        //If file is approved, create html file
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            write_tsv(item_list,fileToSave);
        }
    }

    public void write_tsv(ObservableList<Inventory_Item> item_list, File fileToSave){
        try{ //Create html file with user data and separate by tab spaces
            FileWriter writer = new FileWriter(fileToSave);
            writer.write("Value\tSerial Number\tName\n");
            for (Inventory_Item inventory_item : item_list) {
                writer.write(inventory_item.getValue() + "\t");
                writer.write(inventory_item.getSerial() + "\t");
                writer.write(inventory_item.getName() + "\n");
            }
            JOptionPane.showMessageDialog(null, "File Successfully Saved");
            writer.close();
        }
        catch(Exception e){
            ErrorMessages.displayFileError();
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
