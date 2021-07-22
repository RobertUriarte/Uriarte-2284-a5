/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Robert Uriarte
 */
package ucf.assignments;

import javafx.collections.ObservableList;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;

public class JsonExport extends Component {
    public void export_json(ObservableList<Inventory_Item> item_list){
        //Initialize file chooser
        //Set up save dialog
        //Set file name to .json to save html files
        //Set up filter to show json files

        JFileChooser fileChooser= new JFileChooser();
        fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooser.setSelectedFile(new File(".json"));
        fileChooser.setFileFilter(new FileNameExtensionFilter("json file","json"));

        //If file is approved, create an html file
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try{ //Write base for json file, then add data to json
                FileWriter writer = new FileWriter(fileToSave);
                writer.write("""
                        {
                            "items" : [
                        """);
                for (int i = 0; i < item_list.size(); i++) {
                    writer.write("        {\"value\": \"" + item_list.get(i).getValue());
                    writer.write("\", \"serial\": \"" + item_list.get(i).getSerial());
                    if(i != item_list.size()-1)
                        writer.write("\", \"name\": \"" + item_list.get(i).getName() + "\"},\n");
                    else
                        writer.write("\", \"name\": \"" + item_list.get(i).getName() + "\"}\n");
                }
                writer.write("    ]\n" +
                        "}");
                JOptionPane.showMessageDialog(null, "File Successfully Saved");
                writer.close();
            }
            catch(Exception e){
                ErrorMessages.displayFileError();
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
}
