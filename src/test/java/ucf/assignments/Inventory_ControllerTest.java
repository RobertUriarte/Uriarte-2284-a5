package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Inventory_ControllerTest {

    Inventory_Controller inventory_controller = new Inventory_Controller();
    ObservableList<Inventory_Item> item_listTest = FXCollections.observableArrayList();

    @BeforeEach
    void setUp() {
            item_listTest.add(new Inventory_Item("100", "ABCDE12345", "Tv"));
            item_listTest.add(new Inventory_Item("75", "12345ABCDE", "Watch"));
            item_listTest.add(new Inventory_Item("50", "A1B2C3D4E5", "Car"));
            item_listTest.add(new Inventory_Item("25", "0987654321", "Tv"));
    }


    @Test
    public void filterDataTest(){
        FilteredList<Inventory_Item> list = new FilteredList<>(item_listTest, b->true);

        SortedList<Inventory_Item> actual = inventory_controller.filterData(list,"Tv");
        Assertions.assertNotNull(actual);
        assertEquals(actual.size(),2);
        assertEquals(actual.get(0).getValue(), "100");

        SortedList<Inventory_Item> actual2 = inventory_controller.filterData(list,"100");
        Assertions.assertNotNull(actual2);
        assertEquals(actual2.size(),1);
        assertEquals(actual2.get(0).getSerial(),"ABCDE12345");
    }


    @Test
    void addItemButtonClickedTest() {
        inventory_controller.addItemFunction("$50.00","EFGHIJKL10","Laptop",item_listTest);
        Inventory_Item new_item = new Inventory_Item("$50.00","EFGHIJKL10","Laptop");

        assertEquals(item_listTest.get(4).getName(),new_item.getName());
    }

    @Test
    void is_duplicateTest() {
        boolean actual = inventory_controller.is_duplicate("0987654321",item_listTest);
        assertTrue(actual);

        boolean actual2 = inventory_controller.is_duplicate("ABC123EFG9",item_listTest);
        assertFalse(actual2);
    }

    @Test
    void valid_serialTest() {
        boolean actual = inventory_controller.valid_serial("1234567890",item_listTest);
        assertTrue(actual);

        boolean actual2 = inventory_controller.valid_serial("21241",item_listTest);
        assertFalse(actual2);

        boolean actual3 = inventory_controller.valid_serial("ABCDE1234*",item_listTest);
        assertFalse(actual3);
    }

    @Test
    void valid_nameTest() {
        String actual = inventory_controller.valid_name("Watch",item_listTest);
        assertEquals("Watch",actual);

        String actual2 = inventory_controller.valid_name("W",item_listTest);
        assertNull(actual2);

        String actual3 = inventory_controller.valid_name("THIS STRING IS 256 CHARACTERS xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx123xxx",item_listTest);
        assertEquals("THIS STRING IS 256 CHARACTERS xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx123",actual3);
    }

    @Test
    void format_moneyTest() {
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
    }

    @Test
    void clearAllButtonClickedTest() {
    }

    @Test
    void saveExcelButtonClickedTest() {
    }

    @Test
    void loadTsvButtonClickedTest() {
    }

    @Test
    void loadHtmlButtonClickedTest() {
    }

    @Test
    void loadJsonButtonClickedTest() {
    }

    @Test
    void changeValueCellTest() {
    }

    @Test
    void changeSerialNumberCellTest() {
    }

    @Test
    void changeNameCellTest() {
    }

    @Test
    void saveAsTsvButtonClickedTest() {
    }

    @Test
    void saveAsHtmlButtonClickedTest() {
    }

    @Test
    void saveAsJsonButtonClickedTest() {
    }



}