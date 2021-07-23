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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Inventory_ControllerTest {

    //Initialize lists
    Inventory_Controller inventory_controller = new Inventory_Controller();
    ObservableList<Inventory_Item> item_listTest = FXCollections.observableArrayList();

    @BeforeEach
    void setUp() {
        //Add Sample data to list
        item_listTest.add(new Inventory_Item("100", "ABCDE12345", "Tv"));
        item_listTest.add(new Inventory_Item("75", "12345ABCDE", "Watch"));
        item_listTest.add(new Inventory_Item("50", "A1B2C3D4E5", "Car"));
        item_listTest.add(new Inventory_Item("25", "0987654321", "Tv"));
    }


    @Test
    public void filterDataTest(){
        //Create a filtered list
        //Sort the list based on a string
        //Assert if list is sorted correctly

        FilteredList<Inventory_Item> list = new FilteredList<>(item_listTest, b->true);

        SortedList<Inventory_Item> actual = inventory_controller.filterData(list,"Tv");
        Assertions.assertNotNull(actual);
        assertEquals(actual.size(),2);
        assertEquals(actual.get(0).getValue(), "100");

        SortedList<Inventory_Item> actual2 = inventory_controller.filterData(list,"100");
        Assertions.assertNotNull(actual2);
        assertEquals(actual2.size(),1);
        assertEquals(actual2.get(0).getSerial(),"ABCDE12345");

        SortedList<Inventory_Item> actual3 = inventory_controller.filterData(list,"123");
        Assertions.assertNotNull(actual3);
        assertEquals(actual3.size(),2);
        assertEquals(actual3.get(0).getSerial(),"ABCDE12345");
    }


    @Test
    void addItemButtonClickedTest() {
        //Add item to empty list
        //Test if item has been added

        inventory_controller.addItemFunction("$50.00","EFGHIJKL10","Laptop",item_listTest);
        Inventory_Item new_item = new Inventory_Item("$50.00","EFGHIJKL10","Laptop");

        assertEquals(item_listTest.get(4).getName(),new_item.getName());
    }

    @Test
    void is_duplicateTest() {
        //Create a duplicate serial number
        //Test if duplicate function correctly if it is
        boolean actual = inventory_controller.is_duplicate("0987654321",item_listTest);
        assertTrue(actual);

        //Create non-duplicate serial number
        //Test if duplicate function correctly if it is
        boolean actual2 = inventory_controller.is_duplicate("ABC123EFG9",item_listTest);
        assertFalse(actual2);
    }

    @Test
    void valid_serialTest() {
        //Create various Serial Numbers of different formats and lengths
        //Assert if valid function returns correct result

        boolean actual = inventory_controller.valid_serial("1234567890",item_listTest);
        assertTrue(actual);

        boolean actual2 = inventory_controller.valid_serial("21241",item_listTest);
        assertFalse(actual2);

        boolean actual3 = inventory_controller.valid_serial("ABCDE1234*",item_listTest);
        assertFalse(actual3);
    }

    @Test
    void valid_nameTest() {
        //Create various Names of different lengths
        //Assert if valid function returns correct result

        String actual = inventory_controller.valid_name("Watch",item_listTest);
        assertEquals("Watch",actual);

        String actual2 = inventory_controller.valid_name("W",item_listTest);
        assertNull(actual2);

        String actual3 = inventory_controller.valid_name("THIS STRING IS 256 CHARACTERS xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx123xxx",item_listTest);
        assertEquals("THIS STRING IS 256 CHARACTERS xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx123",actual3);
    }

    @Test
    void format_moneyTest() {
        //Create various money values to be formatted
        //Assert if valid function returns correct result

        String actual = inventory_controller.format_money("50",item_listTest);
        assertEquals("$50.00",actual);

        String actual2 = inventory_controller.format_money("125.59",item_listTest);
        assertEquals("$125.59",actual2);

        String actual3 = inventory_controller.format_money("56.89999999",item_listTest);
        assertEquals("$56.90",actual3);

        String actual4 = inventory_controller.format_money("Hello",item_listTest);
        assertNull(actual4);

        String actual5 = inventory_controller.format_money("50.50.50",item_listTest);
        assertNull(actual5);
    }

    @Test
    void removeItemButtonClickedTest() {
        //Initialize list for selected rows
        //Add item to be removed to selected rows
        //Test if values of selected index has changed

        ObservableList<Inventory_Item> selectedRows = FXCollections.observableArrayList();
        selectedRows.add(item_listTest.get(3));
        inventory_controller.remove_items(item_listTest,selectedRows);

        assertEquals(3, item_listTest.size());

        selectedRows.remove(0);
        selectedRows.add(item_listTest.get(0));
        inventory_controller.remove_items(item_listTest,selectedRows);

        assertNotEquals("Tv",item_listTest.get(0).getName());
    }

    @Test
    void clearAllButtonClickedTest() {
        //Initialize list
        //Remove all items
        //Add item to test
        //Test if list only contains newly added item

        ObservableList<Inventory_Item> allItems = item_listTest;

        inventory_controller.remove_all_items(item_listTest,allItems);
        item_listTest.add(new Inventory_Item("$50.00","EFGHIJKL10","Laptop"));

        assertEquals(item_listTest.size(), 1);
    }

    @Test
    void changeValueCellTest() {
        //Enter string to change
        //Assert if value has been changed

        inventory_controller.changeValue("$500.00",item_listTest.get(0));
        assertEquals("$500.00",item_listTest.get(0).getValue());
    }

    @Test
    void changeSerialNumberCellTest() {
        //Enter string to change
        //Assert if value has been changed

        inventory_controller.changeSerialNumber(item_listTest.get(0),"XMLHTMLTSV");
        assertEquals("XMLHTMLTSV",item_listTest.get(0).getSerial());
    }

    @Test
    void changeNameCellTest() {
        //Enter string to change
        //Assert if value has been changed

        inventory_controller.changeName("Laptop",item_listTest.get(0));
        assertEquals("Laptop",item_listTest.get(0).getName());
    }
}