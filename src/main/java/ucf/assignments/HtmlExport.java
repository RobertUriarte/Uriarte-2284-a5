package ucf.assignments;

import javafx.collections.ObservableList;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class HtmlExport extends Component {
    public void export_html(ObservableList<Inventory_Item> item_list) {
        JFileChooser fileChooser = new JFileChooser();
        // Some init code, if you need one, like setting title
        //set it to be a save dialog
        fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
        //set a default filename (this is where you default extension first comes in)
        fileChooser.setSelectedFile(new File(".html"));
        //Set an extension filter, so the user sees other XML files
        fileChooser.setFileFilter(new FileNameExtensionFilter("html file","html"));
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try {
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
