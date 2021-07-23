/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Robert Uriarte
 */

package ucf.assignments;

import javafx.beans.property.SimpleStringProperty;

public class Inventory_Item {
    //Set value
    //Set serialnumber
    //Set name

    private SimpleStringProperty value;
    private SimpleStringProperty serial;
    private SimpleStringProperty name;

    //constructor for item
    public Inventory_Item(String value, String serial, String name) {
        this.value = new SimpleStringProperty(value);
        this.serial = new SimpleStringProperty(serial);
        this.name = new SimpleStringProperty(name);
    }

    //getter for item
    public String getValue() { return value.get(); }
    public String getSerial() { return serial.get(); }
    public String getName() { return name.get(); }



    //setter for item
    public void setValue(String value) { this.value = new SimpleStringProperty(value); }
    public void setSerial(String serial) { this.serial = new SimpleStringProperty(serial); }
    public void setName(String name) { this.name = new SimpleStringProperty(name); }
    
}
