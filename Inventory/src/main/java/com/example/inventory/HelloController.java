/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Juan Guarnizo
 */
package com.example.inventory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private TextField search = new TextField();

    @FXML
    private TextField serialNumberInput = new TextField();

    @FXML
    private TextField nameInput = new TextField();

    @FXML
    private TextField valueInput = new TextField();

    @FXML
    private TableColumn< Item, String> Serial_Number = new TableColumn<>();

    @FXML
    private TableColumn< Item, String> Name = new TableColumn<>();

    @FXML
    private TableColumn< Item, String> Value = new TableColumn<>();

    @FXML
    private TableView< Item > Inventory = new TableView<>();

    ObservableList<Item> list = FXCollections.observableArrayList();

    @FXML
    private FileChooser fileChooser = new FileChooser();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Name.setCellValueFactory( new PropertyValueFactory<>("name"));

        Serial_Number.setCellValueFactory( new PropertyValueFactory<>("serialNumber"));

        Value.setCellValueFactory( new PropertyValueFactory<>("value"));

        Inventory.setItems(list);

        fileChooser.setInitialDirectory(new File("src\\saved files"));
    }


    /**AddButton code*/
    public void AddItem(ActionEvent ae) {

        IllegalArgumentException valueExc = new IllegalArgumentException("Value has to be a number greater than zero");

        IllegalArgumentException nameExc = new IllegalArgumentException("Name has to be between 2 and 256 characters long (inclusive)");

        IllegalArgumentException serialNumExc1 = new IllegalArgumentException("Serial Number has incorrect format");

        IllegalArgumentException serialNumExc2 = new IllegalArgumentException("Serial number already exists");

        Alert alert = new Alert(Alert.AlertType.NONE);

        Item item;
        try {
        item = new Item(nameInput.getText(), serialNumberInput.getText(), Double.parseDouble(valueInput.getText()));
        } catch (IllegalArgumentException e) {

         /**set the alert*/
         alert.setAlertType(Alert.AlertType.ERROR);
         /**set the alert's message*/
         alert.setContentText(valueExc.getMessage());
         /**show alert*/
         alert.show();
         throw valueExc;
         }
        if (item.getName().equals("Invalid name") ) {

            /**set the alert*/
            alert.setAlertType(Alert.AlertType.WARNING);
            /**set the alert's message*/
            alert.setContentText(nameExc.getMessage());
            /**show alert*/
            alert.show();
            throw nameExc;
        }
        if(item.getSerialNumber().equals("Invalid Serial Number !!")){

            /**set the alert*/
            alert.setAlertType(Alert.AlertType.ERROR);
            /**set the alert's message*/
            alert.setContentText(serialNumExc1.getMessage());
            /**show alert*/
            alert.show();
            throw serialNumExc1;
        }
        /**if(item.getBarcode already exists){
         throw serialNumExc2;
         //set the alert
         alert.setAlertType(Alert.AlertType.ERROR);
         //set the alert's message
         alert.setContentText(serialNumExc2.getMessage());
         //show alert
         alert.show();
         }*/
        if( item.getValue() == -1.0){

            /**set the alert*/
            alert.setAlertType(Alert.AlertType.WARNING);
            /**set the alert's message*/
            alert.setContentText(valueExc.getMessage());
            /**show alert*/
            alert.show();
            throw valueExc;
        }
        /**
         *  what do I do here in case that the user types a non-numerical symbol on the value textfield?
         * Also, if item.getName == "Invalid name" I need to display an error message
         * Also, if item.getBarcode == "Invalid Serial Number !!" I need to display an error message)
         * Also, if item.getBarcode already exists I need to display an error message)
         * Also, if item.getValue == -1.0 I need to display an error message*/

        list.add(item);
    }

}