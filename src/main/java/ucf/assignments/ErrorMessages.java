/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Robert Uriarte
 */
package ucf.assignments;

//Set up imports
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import java.util.Optional;

public class ErrorMessages {
    public static void displayDuplicateError(){
        //Create alert
        //Set alert title
        //Set text
        //make alert show and wait

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Serial Number already exist, please try again");
        Optional<ButtonType> result = alert.showAndWait();
    }

    public static void displaySerialFormatError(){
        //Create alert
        //Set alert title
        //Set text
        //make alert show and wait

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Incorrect serial number format");
        alert.setContentText("Format: XXXXXXXXXX of only letters and numbers.");
        Optional<ButtonType> result = alert.showAndWait();
    }

    public static void displaySerialLengthError(){
        //Create alert
        //Set alert title
        //Set text
        //make alert show and wait

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Serial Number entered is not of the correct length.");
        alert.setContentText("Format: XXXXXXXXXX of only letters and numbers.");
        Optional<ButtonType> result = alert.showAndWait();
    }

    public static void displayMoneyError(){
        //Create alert
        //Set alert title
        //Set text
        //make alert show and wait

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Money value is entered in wrong format, please try again");
        alert.setContentText("Format: 10.00 , of type double.");
        Optional<ButtonType> result = alert.showAndWait();
    }

    public static void displayNameError(){
        //Create alert
        //Set alert title
        //Set text
        //make alert show and wait

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Name must be longer than 1 letter");
        Optional<ButtonType> result = alert.showAndWait();
    }

    public static void displayNameWarning(){
        //Create alert
        //Set alert title
        //Set text
        //make alert show and wait

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Warning");
        alert.setHeaderText("A name is only allowed a max length of 256");
        alert.setContentText("The name entered has been cut to 256 characters");
        Optional<ButtonType> result = alert.showAndWait();
    }

    public static void displayFileError(){
        //Create alert
        //Set alert title
        //Set text
        //make alert show and wait

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Error, file could not be saved");
        Optional<ButtonType> result = alert.showAndWait();
    }

    public static void displayFileNotFoundError(){
        //Create alert
        //Set alert title
        //Set text
        //make alert show and wait

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Error, file could not be loaded");
        alert.setContentText("Make sure file is of the correct type and correct format");
        Optional<ButtonType> result = alert.showAndWait();
    }
}
