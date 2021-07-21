package ucf.assignments;

import javafx.collections.ObservableList;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;

public class TsvExport extends Component {
    public void export_tsv(ObservableList<Inventory_Item> item_list){
        JFileChooser fileChooser= new JFileChooser();
        // Some init code, if you need one, like setting title
        fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooser.setSelectedFile(new File(".txt"));
        fileChooser.setFileFilter(new FileNameExtensionFilter("txt file","txt"));
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try{
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
}
