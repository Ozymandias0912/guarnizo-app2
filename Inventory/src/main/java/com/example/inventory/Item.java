/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Juan Guarnizo
 */

package com.example.inventory;

public class Item {

    /**private name*/
    private String name;

    /**private serialNumber*/
    private String serialNumber;

    /**private value*/
    private double value;



    /**constructors:
     Item()*/
    public Item(String name, String serialNumber, double value) {
        this.name = validateName(name);
        this.serialNumber = validateSerialNumber(serialNumber);
        this.value = validateValue(value);
    }

    public Item() {
        this.name = "Xbox";

        this.serialNumber = "A-Xbo-x4l-ife";

        this.value = 500.00;
    }

    /**setters & getters*/
    /**getName()*/
    public String getName() {
        return name;
    }
    /**setName()*/
    public void setName(String name) {
        this.name = validateName(name);
    }
    /**getSerialNumber()*/
    public String getSerialNumber() {
        return serialNumber;
    }
    /**setSerialNumber()*/
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = validateSerialNumber(serialNumber);
    }
    /**getValue()*/
    public double getValue() {
        return value;
    }
    /**setValue()*/
    public void setValue(double value) {
        this.value = validateValue(value);
    }
    /**validateName()*/
    public String validateName(String name){

        if( name.length() >= 2 && name.length() <= 256){
            return name;
        }

        return "Invalid name";
    }

    /**validateSerialNumber()*/
    public String validateSerialNumber(String SerialNumber){
        boolean check = true;

        if( SerialNumber.length() == 13  &&  SerialNumber.charAt(0) == 'A'  &&  SerialNumber.charAt(1) == '-'  &&
                SerialNumber.charAt(5) == '-'  &&  SerialNumber.charAt(9) == '-'){
            int i = 0;
            /**checking indexes 2 through 4*/
            for(i = 2; i < 5; i++){

                if( (!isDigit(SerialNumber.charAt(i))  &&  !isLetter(SerialNumber.charAt(i)))){
                    check = false;
                }
            }
            /**checking indexes 6 through 8*/
            for(i = 6; i < 9; i++){

                if( (!isDigit(SerialNumber.charAt(i))  &&  !isLetter(SerialNumber.charAt(i)))){
                    check = false;
                }
            }
            /**checking indexes 10 through 12*/
            for(i = 10; i < 13; i++){

                if( (!isDigit(SerialNumber.charAt(i))  &&  !isLetter(SerialNumber.charAt(i)))){
                    check = false;
                }
            }
            if(check){
                return SerialNumber;
            }
            else{
                return "Invalid Serial Number !!";
            }
        }
        else{
            return "Invalid Serial Number !!";
        }
    }
    /**isDigits()*/
    /**This function will be used by validateSerialNumber()*/
    public boolean isDigit(char character){

        if( character >= '0' && character <= '9'){
            return true;
        }
        else{
            return false;
        }
    }
    /**isLetter()*/
    /**This function will be used by validateSerialNumber()*/
    public boolean isLetter(char character){

        if( (character >= 'A' && character <= 'Z')  ||  (character >= 'a' && character <= 'z')) {
            return true;
        }
        else{
            return false;
        }
    }

    /**validateValue()*/
    public double validateValue(double value){

        if( value >= 0.00 ){

            return value;
        }
        else{
            return -1.00;
        }
    }
}
