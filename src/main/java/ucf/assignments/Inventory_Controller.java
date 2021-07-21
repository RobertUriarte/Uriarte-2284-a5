/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Robert Uriarte
 */
package ucf.assignments;

//Set up imports
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import java.net.URL;
import java.text.NumberFormat;
import java.util.*;

public class Inventory_Controller implements Initializable  {
    //Initialize TableView and all its columns
    @FXML
    public TableView<Inventory_Item> tableView;
    @FXML
    public TableColumn<Inventory_Item, String> value;
    @FXML
    public TableColumn<Inventory_Item, String> serial;
    @FXML
    public TableColumn<Inventory_Item, String> name;

    //Initialize three text field and datePicker for adding a new item
    @FXML
    public TextField valueTextField;
    @FXML
    public TextField serialTextField;
    @FXML
    public TextField nameTextField;
    @FXML
    public TextField searchTextField;

    //Initialize Observable inventory list
    public ObservableList<Inventory_Item> item_list = FXCollections.observableArrayList();

    //Initialize file chooser
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Initialize cellValueFactory of cells of tableview
        value.setCellValueFactory(new PropertyValueFactory<>("value"));
        serial.setCellValueFactory(new PropertyValueFactory<>("serial"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        //Initialize allowing multiple selection mode
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        //Set our tableView to empty item list

        //Set tableView to be editable
        tableView.setEditable(true);

        //Initialize text fields of cells of tableview
        value.setCellFactory(TextFieldTableCell.forTableColumn());
        serial.setCellFactory(TextFieldTableCell.forTableColumn());
        name.setCellFactory(TextFieldTableCell.forTableColumn());

        tableView.setItems(item_list);
    }

    @FXML
    public void SearchButtonClicked(ActionEvent actionEvent){
        FilteredList<Inventory_Item> filteredList = new FilteredList<>(item_list,b->true);
        searchTextField.textProperty().addListener((observable,oldValue,newValue) ->{
            filteredList.setPredicate(inventory_item -> {
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }

                if(inventory_item.getValue().contains(newValue))
                    return true;
                else if(inventory_item.getSerial().contains(newValue))
                    return true;
                else return inventory_item.getName().contains(newValue);
            });
        });

        SortedList<Inventory_Item> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(tableView.comparatorProperty());

        tableView.setItems(sortedList);
    }

    @FXML
    public void AddItemButtonClicked(ActionEvent actionEvent){
        //Create new item with textField values from user input
        //set tableView back to all items
        //Add new item to all items

        boolean duplicate = is_duplicate(serialTextField.getText().toUpperCase());
        boolean correct_serial = valid_serial(serialTextField.getText().toUpperCase());
        String money_value = format_money(valueTextField.getText());
        String name_value = valid_name(nameTextField.getText());

        if(money_value != null && !duplicate && correct_serial && name_value != null){
            Inventory_Item new_item = new Inventory_Item(money_value,serialTextField.getText().toUpperCase(), nameTextField.getText());
            tableView.setItems(item_list);
            tableView.getItems().add(new_item);
        }
    }

    public boolean is_duplicate(String val){
        for (Inventory_Item inventory_item : item_list) {
            if (inventory_item.getSerial().equals(val)) {
                ErrorMessages.displayDuplicateError();
                return true;
            }
        }
        return false;
    }


    boolean valid_serial(String serial_number) {
        if (serial_number == null || serial_number.length() != 10){
            ErrorMessages.displaySerialLengthError();
            return false;
        }
        for(int i = 0; i < serial_number.length(); i++) {
            if ((!Character.isLetterOrDigit(serial_number.charAt(i)))) {
                ErrorMessages.displaySerialFormatError();
                return false;
            }
        }
        return true;
    }
    public String valid_name(String user_input){
        if(user_input.length() < 2){
            ErrorMessages.displayNameError();
            return null;
        }
        else if(user_input.length() > 256){
            ErrorMessages.displayNameWarning();
            return user_input.substring(0, 256);
        }
        else
            return user_input;
    }

    public String format_money(String money_value){
        try{
            NumberFormat fmt = NumberFormat.getCurrencyInstance();
            return fmt.format(Double.parseDouble(money_value));
        } catch(NumberFormatException e) {
            ErrorMessages.displayMoneyError();
        }
        return null;
    }

    @FXML
    public void RemoveItemButtonClicked(ActionEvent actionEvent) {
        //Initialize observable list
        //Set tableView to all items list
        //Get all the items currently in list
        //Get selected rows
        //Check if there are any selected rows
        //If there are selected rows, remove them

        ObservableList<Inventory_Item> selectedRows, allItems;
        tableView.setItems(item_list);
        allItems = tableView.getItems();
        selectedRows = tableView.getSelectionModel().getSelectedItems();
        if(selectedRows != null)
            allItems.removeAll(selectedRows);

    }

    @FXML
    public void ClearAllButtonClicked(ActionEvent actionEvent) {
        //Initialize observable list
        //Set tableView to all items list
        //Get all the items currently in list
        //Remove all the items in the tableView

        ObservableList<Inventory_Item> allItems, all;
        tableView.setItems(item_list);
        allItems = tableView.getItems();
        all = tableView.getItems();
        allItems.removeAll(all);
    }

    @FXML
    public void SaveExcelButtonClicked(ActionEvent actionEvent) {
        //Set the tableView to all items list
        //Initialize new ExcelExporter
        //Call exporter

        tableView.setItems(item_list);
        ExcelExport<Inventory_Item> exporter = new ExcelExport<>();
        exporter.export(tableView);
    }

    @FXML
    public void LoadTsvButtonClicked(ActionEvent actionEvent){
        //Set the tableview to all items list
        //Call Json parser
        tableView.setItems(item_list);
        ParseTsv parseTsv = new ParseTsv();
        parseTsv.import_tsv(tableView);
    }
    @FXML
    public void LoadHtmlButtonClicked(ActionEvent actionEvent){
        //Set the tableview to all items list
        //Call Json parser
        tableView.setItems(item_list);
        ParseHtml parseHtml = new ParseHtml();
        parseHtml.import_html(tableView);
    }
    @FXML
    public void LoadJsonButtonClicked(ActionEvent actionEvent){
        //Set the tableview to all items list
        //Call Json parser
        tableView.setItems(item_list);
        ParseJson.parse(tableView);
    }

    @FXML
    public void ChangeValueCell(TableColumn.CellEditEvent newCell) {
        //Get the selected cell
        //Set the selected cell to user input

        Inventory_Item item_selected = tableView.getSelectionModel().getSelectedItem();
        String money_value = format_money(newCell.getNewValue().toString());
        if(money_value != null)
            item_selected.setValue(money_value);
    }

    @FXML
    public void ChangeSerialNumberCell(TableColumn.CellEditEvent newCell) {
        //Get the selected cell
        //Get user input
        //Check if user input is within 1-256 range.
        //If user is above 256 character trim
        //If user is under 1 do nothing
        //If user is within range set selected cell to user input

        Inventory_Item item_selected = tableView.getSelectionModel().getSelectedItem();
        String val = newCell.getNewValue().toString().toUpperCase();
        if(!is_duplicate(val) && valid_serial(val))
            item_selected.setSerial(newCell.getNewValue().toString().toUpperCase());
    }

    @FXML
    public void ChangeNameCell(TableColumn.CellEditEvent newCell) {
        //Get the selected cell
        //Check if selected cell is a valid date input
        //If date is valid we set selected cell to user input

        Inventory_Item item_selected = tableView.getSelectionModel().getSelectedItem();
        String user_input = valid_name(newCell.getNewValue().toString());
        if(user_input != null){
            item_selected.setName(user_input);
        }

    }

    @FXML
    public void SaveAsTsvButtonClicked(ActionEvent actionEvent){
        tableView.setItems(item_list);
        TsvExport tsvExport = new TsvExport();
        tsvExport.export_tsv(item_list);
    }

    @FXML
    public void SaveAsHtmlButtonClicked(ActionEvent actionEvent) {
        tableView.setItems(item_list);
        HtmlExport htmlExport = new HtmlExport();
        htmlExport.export_html(item_list);
    }

    @FXML
    public void SaveAsJsonButtonClicked(ActionEvent actionEvent) {
        tableView.setItems(item_list);
        JsonExport jsonExport = new JsonExport();
        jsonExport.export_json(item_list);
    }
}

