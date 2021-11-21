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
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

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
        item = new Item(nameInput.getText(), serialNumberInput.getText(),  Double.parseDouble(valueInput.getText()));

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
        if(  item.getValue() == -1.00){

            /**set the alert*/
            alert.setAlertType(Alert.AlertType.WARNING);
            /**set the alert's message*/
            alert.setContentText(valueExc.getMessage());
            /**show alert*/
            alert.show();
            throw valueExc;
        }
        /** Also, if item.getBarcode already exists I need to display an error message*/
        for(int i = 0; i < list.size(); i++){

            if( list.get(i).getSerialNumber().equals(item.getSerialNumber())){

                /**set the alert*/
                alert.setAlertType(Alert.AlertType.ERROR);
                /**set the alert's message*/
                alert.setContentText(serialNumExc2.getMessage());
                /**show alert*/
                alert.show();
                throw serialNumExc2;

            }

        }/**the barcode is not in the list*/

        list.add(item);

    }/**end add function*/

    /**editButton code*/
    public void editItem(ActionEvent ae){

        /**get index of item in the list*/
        int index = Inventory.getSelectionModel().getSelectedIndex();

        /**remove item*/
        list.remove(index);

        /**add item in the same index*/
        Item item = new Item(nameInput.getText(), serialNumberInput.getText(),  Double.parseDouble(valueInput.getText()));

        list.add(index, item);


    }/**end edit function*/

    /**removeButton code*/
    public void removeItem(ActionEvent ae){

        list.remove(Inventory.getSelectionModel().getSelectedIndex());
    }

    /**removeAllButton code*/
    public void removeAllItems(ActionEvent ae){

        list.remove(0, list.size());
    }

    /**helper function to save files*/
    public void save(File file, String content) {

        try{
            FileWriter writer = new FileWriter(file);
            writer.write(content);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**saveFile code*/
    public void saveFileTSV(ActionEvent ae){

        File file = fileChooser.showSaveDialog(new Stage());

        if(file != null){

            String content = "Serial Number\t\tName\t\tValue\n";

            /**print items*/
            for(int i = 0; i < list.size(); i++){

                content = content + list.get(i).getSerialNumber() + "\t\t" + list.get(i).getName() + "\t\t" +
                        list.get(i).getValue() + "\n";
            }

            save(file, content);
        }
    }

    /**save as html*/

    /**save as json*/

    /**load File*/
    public void loadFile(ActionEvent ae){

        File file = fileChooser.showOpenDialog(new Stage());

        try {
            Scanner input = new Scanner(file);

            String name;
            String serialNumber;
            double value;

            /**skipping the title of the file*/
            input.nextLine();

            while(input.hasNext()){

                serialNumber = input.next();
                name = input.next();
                value = Double.parseDouble(input.next());

                Item item = new Item(name, serialNumber , value);

                list.add(item);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }



}