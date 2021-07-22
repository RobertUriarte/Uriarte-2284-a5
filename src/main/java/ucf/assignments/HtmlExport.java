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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class HtmlExport extends Component {
    public void export_html(ObservableList<Inventory_Item> item_list) {
        //Initialize file chooser
        //Set up save dialog
        //Set file name to .html to save html files
        //Set up filter to show html files

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooser.setSelectedFile(new File(".html"));
        fileChooser.setFileFilter(new FileNameExtensionFilter("html file","html"));

        //If file is approved, create an html file
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try { //String build html file and add values from tableview
                StringBuilder sb = new StringBuilder();
                sb.append("<html>\n");
                sb.append("<head>\n");
                sb.append("<title>Inventory\n");
                sb.append("</title>\n");
                sb.append("</head>\n");
                sb.append("<body> <b>\n");
                sb.append("Value Serial Number Name\n");
                for (Inventory_Item inventory_item : item_list) {
                    sb.append("<br>");
                    sb.append(inventory_item.getValue() + " ");
                    sb.append(inventory_item.getSerial()+ " ");
                    sb.append(inventory_item.getName()).append("\n");
                }
                sb.append("</b>");
                sb.append("</body>\n");
                sb.append("</html>");

                //Initialize file writer
                //Write data to file
                //Close file
                //Tell user file is saved
                //Set up error case

                FileWriter writer = new FileWriter(fileToSave);
                BufferedWriter out = new BufferedWriter(writer);
                out.write(sb.toString());
                out.close();
                JOptionPane.showMessageDialog(null, "File Successfully Saved");
                writer.close();
            } catch (Exception e) {
                ErrorMessages.displayFileError();
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
}
