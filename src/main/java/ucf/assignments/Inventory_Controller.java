/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Initialize cellValueFactory of cells of tableview
        value.setCellValueFactory(new PropertyValueFactory<>("value"));
        serial.setCellValueFactory(new PropertyValueFactory<>("serial"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        //Initialize allowing multiple selection mode
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        //Set tableView to be editable
        tableView.setEditable(true);

        //Initialize text fields of cells of tableview
        value.setCellFactory(TextFieldTableCell.forTableColumn());
        serial.setCellFactory(TextFieldTableCell.forTableColumn());
        name.setCellFactory(TextFieldTableCell.forTableColumn());

        //Set our tableView to empty item list
        tableView.setItems(item_list);
    }

    @FXML
    public void searchButtonClicked(ActionEvent actionEvent){
        //Create a sorted list based on user input
        SortedList<Inventory_Item> sortedList = filterList(item_list,searchTextField);

        //Set the tableview to the sorted list
        sortedList.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedList);
    }

    public SortedList<Inventory_Item> filterData(FilteredList<Inventory_Item> filteredList, String newValue) {
        //Set filtered list predicate
        //Check if user_input is contained in our list
        //Return sorted list of filtered list

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
        return new SortedList<>(filteredList);
    }

    public SortedList<Inventory_Item> filterList(ObservableList<Inventory_Item> item_list,TextField searchTextField){
        //Create a filtered list
        //Add a listener to the search bar text field
        //Set the predicates to search for values,serial numbers, and names in the observable list
        //return our sorted list

        FilteredList<Inventory_Item> filteredList = new FilteredList<>(item_list,b->true);

        searchTextField.textProperty().addListener((observable,oldValue,newValue) -> {
            SortedList<Inventory_Item> sortedList = filterData(filteredList, newValue);
            sortedList.comparatorProperty().bind(tableView.comparatorProperty());
            tableView.setItems(sortedList);
        });

        return new SortedList<>(filteredList);
    }

    @FXML
    public void addItemButtonClicked(ActionEvent actionEvent){
        //Get serial number from user input and check if it is a duplicate value
        //Get serial number from user input and check if it is a valid serial number
        //Get the money value from user input and check if it is a valid money value
        //Get the name value from user input and check if it is a valid name
        //If conditions are met Call add item function
        //Set the tableview to our item list

        boolean duplicate = is_duplicate(serialTextField.getText().toUpperCase(),item_list);
        boolean correct_serial = valid_serial(serialTextField.getText().toUpperCase(),item_list);
        String money_value = format_money(valueTextField.getText(),item_list);
        String name_value = valid_name(nameTextField.getText(),item_list);

        if(money_value != null && !duplicate && correct_serial && name_value != null){
            addItemFunction(money_value,serialTextField.getText().toUpperCase(),name_value,item_list);
            tableView.setItems(item_list);
        }

    }

    public void addItemFunction(String money_value,String serialText,String name_value,ObservableList<Inventory_Item> item_list2){
        //Create new item
        //Add new item to list

        Inventory_Item new_item = new Inventory_Item(money_value,serialText, name_value);
        item_list2.add(new_item);
    }

    public boolean is_duplicate(String val, ObservableList<Inventory_Item> item_list2){
        //Loop for all items in the list
        //Check if the serial number already exist
        //If serial number exist, display error message
        //Return true if it exist
        //Return false if it does not already exist

        for (Inventory_Item inventory_item : item_list2) {
            if (inventory_item.getSerial().equals(val)) {
                if(item_list2 == item_list) {
                    ErrorMessages errorMessages = new ErrorMessages();
                    errorMessages.displayDuplicateError();
                }
                return true;
            }
        }
        return false;
    }

    boolean valid_serial(String serial_number,ObservableList<Inventory_Item> item_list2) {
        //Check if serial number is of correct length of 10
        //Display error message if not
        if (serial_number == null || serial_number.length() != 10){
            if(item_list2 == item_list){
                ErrorMessages errorMessages = new ErrorMessages();
                errorMessages.displaySerialLengthError();
            }
            return false;
        }

        //Loop for length of serial number
        //Check if each character is a number or letter
        for(int i = 0; i < serial_number.length(); i++) {
            if ((!Character.isLetterOrDigit(serial_number.charAt(i)))) {
                if(item_list2 == item_list){
                    ErrorMessages errorMessages = new ErrorMessages();
                    errorMessages.displaySerialFormatError();
                }
                return false;
            }
        }

        //Return true if both conditions are meant
        return true;
    }
    public String valid_name(String user_input,ObservableList<Inventory_Item> item_list2){
        //Checks if name is longer than 1 character
        //Display Error if not

        if(user_input.length() < 2){
            if(item_list2 == item_list){
                ErrorMessages errorMessages = new ErrorMessages();
                errorMessages.displayNameError();
            }
            return null;
        }

        //Checks if name is not greater than 256 characters
        //If not, name is cut off at 256 characters
        else if(user_input.length() > 256){
            if(item_list2 == item_list){
                ErrorMessages errorMessages = new ErrorMessages();
                errorMessages.displayNameWarning();
            }
            return user_input.substring(0, 256);
        }
        else
            return user_input;
    }

    public String format_money(String money_value,ObservableList<Inventory_Item> item_list2){
        //Formats user input into USD money format
        //Displays error if format if user input is incorrect
        try{
            NumberFormat fmt = NumberFormat.getCurrencyInstance();
            return fmt.format(Double.parseDouble(money_value));
        } catch(NumberFormatException e) {
            if(item_list2 == item_list){
                ErrorMessages errorMessages = new ErrorMessages();
                errorMessages.displayMoneyError();
            }

        }
        return null;
    }

    @FXML
    public void removeItemButtonClicked(ActionEvent actionEvent) {
        //Set tableView to all items list
        //Initialize observable list
        //Get all the items currently in list
        //Get selected rows
        //Check if there are any selected rows
        //If there are selected rows, remove them

        tableView.setItems(item_list);
        ObservableList<Inventory_Item> selectedRows, allItems;
        allItems = tableView.getItems();
        selectedRows = tableView.getSelectionModel().getSelectedItems();
        remove_items(allItems,selectedRows);
    }

    public void remove_items(ObservableList<Inventory_Item> allItems, ObservableList<Inventory_Item> selectedRows){
        if(selectedRows != null)
            allItems.removeAll(selectedRows);
    }

    @FXML
    public void clearAllButtonClicked(ActionEvent actionEvent) {
        //Initialize observable list
        //Set tableView to all items list
        //Get all the items currently in list
        //Remove all the items in the tableView

        ObservableList<Inventory_Item> allItems, all;
        tableView.setItems(item_list);
        allItems = tableView.getItems();
        all = tableView.getItems();
        remove_all_items(allItems,all);
    }

    public void remove_all_items(ObservableList<Inventory_Item> allItems, ObservableList<Inventory_Item> all){
        allItems.removeAll(all);
    }

    @FXML
    public void loadTsvButtonClicked(ActionEvent actionEvent){
        //Set the tableview to all items list
        //Call Json parser
        tableView.setItems(item_list);
        ParseTsv parseTsv = new ParseTsv();
        parseTsv.import_tsv(tableView);
    }
    @FXML
    public void LoadHtmlButtonClicked(ActionEvent actionEvent){
        //Set the tableview to all items list
        //Call html parser
        tableView.setItems(item_list);
        ParseHtml parseHtml = new ParseHtml();
        parseHtml.import_html(tableView);
    }
    @FXML
    public void loadJsonButtonClicked(ActionEvent actionEvent){
        //Set the tableview to all items list
        //Call Json parser
        tableView.setItems(item_list);
        ParseJson parseJson = new ParseJson();
        parseJson.parse(tableView);
    }

    @FXML
    public void changeValueCell(TableColumn.CellEditEvent newCell) {
        //Get the selected cell
        //Check if user input is valid
        //If user input is valid set cell as user input

        Inventory_Item item_selected = tableView.getSelectionModel().getSelectedItem();
        String money_value = format_money(newCell.getNewValue().toString(),item_list);
        changeValue(money_value,item_selected);
        tableView.setItems(item_list);
    }

    public void changeValue(String money_value, Inventory_Item item_selected){
        if(money_value != null)
            item_selected.setValue(money_value);
    }

    @FXML
    public void changeSerialNumberCell(TableColumn.CellEditEvent newCell) {
        //Get selected cell
        //Check if serial number is not a duplicate and is valid
        //If conditions are met set cell as user input

        Inventory_Item item_selected = tableView.getSelectionModel().getSelectedItem();
        String val = newCell.getNewValue().toString().toUpperCase();
        if(!is_duplicate(val,item_list) && valid_serial(val,item_list))
            changeSerialNumber(item_selected,val);
        tableView.setItems(item_list);
    }

    public void changeSerialNumber(Inventory_Item item_selected, String val){
        item_selected.setSerial(val);
    }

    @FXML
    public void changeNameCell(TableColumn.CellEditEvent newCell) {
        //Get the selected cell
        //Check if selected cell is a valid name
        //If name is valid, set cell as user input

        Inventory_Item item_selected = tableView.getSelectionModel().getSelectedItem();
        String user_input = valid_name(newCell.getNewValue().toString(),item_list);
        if(user_input != null){
            changeName(user_input, item_selected);
        }
        tableView.setItems(item_list);
    }

    public void changeName(String user_input, Inventory_Item item_selected){
        item_selected.setName(user_input);
    }

    @FXML
    public void saveAsTsvButtonClicked(ActionEvent actionEvent){
        //Set tableview to item list
        //Call tsv export

        tableView.setItems(item_list);
        TsvExport tsvExport = new TsvExport();
        tsvExport.export_tsv(item_list);
    }

    @FXML
    public void saveAsHtmlButtonClicked(ActionEvent actionEvent) {
        //Set tableview to item list
        //Call html export

        tableView.setItems(item_list);
        HtmlExport htmlExport = new HtmlExport();
        htmlExport.export_html(item_list);
    }

    @FXML
    public void saveAsJsonButtonClicked(ActionEvent actionEvent) {

        //Set tableview to item list
        //Call json export
        tableView.setItems(item_list);
        JsonExport jsonExport = new JsonExport();
        jsonExport.export_json(item_list);
    }

    @FXML
    public void saveExcelButtonClicked(ActionEvent actionEvent) {
        //Set the tableView to all items list
        //Initialize new ExcelExporter
        //Call exporter

        tableView.setItems(item_list);
        ExcelExport<Inventory_Item> exporter = new ExcelExport<>();
        exporter.export(tableView);
    }
}

