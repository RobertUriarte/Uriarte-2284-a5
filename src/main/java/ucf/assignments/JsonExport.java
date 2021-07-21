package ucf.assignments;

import javafx.collections.ObservableList;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;

public class JsonExport extends Component {
    public void export_json(ObservableList<Inventory_Item> item_list){
        JFileChooser fileChooser= new JFileChooser();
        // Some init code, if you need one, like setting title
        fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooser.setSelectedFile(new File(".json"));
        fileChooser.setFileFilter(new FileNameExtensionFilter("json file","json"));
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try{
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
