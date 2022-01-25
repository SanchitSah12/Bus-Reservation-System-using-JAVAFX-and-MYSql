package com.example.jdbcc;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import Connectivity.connectionClass;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AddBus {
    @FXML
    public TextField busName;
    public TextField seats;
    public TextField price;
    public TextField busType;
    public TextField from;
    public TextField to;
    public TextField time;
    public DatePicker date;
    public Label success;


    @FXML
    protected void addBus() throws SQLException {
        connectionClass connectionClass = new connectionClass();
        Connection connection = connectionClass.getConnection();

        Statement statement = connection.createStatement();
//
        System.out.println(date.getValue());
//
        String busname = busName.getText();
        String Type = busType.getText();
        String Busfrom = from.getText();
        String busTo = to.getText();
        String times = time.getText();
        String dates = String.valueOf(date.getValue());
        int seat = Integer.parseInt(seats.getText());
        int prices = Integer.parseInt(price.getText());


        String sql =  "INSERT INTO buses values(NULL, '"+busname+"', '"+Type+"', '"+Busfrom+"', '"+busTo+"', '"+dates+"', '"+times+"',"+seat+","+prices+");";
        statement.executeUpdate(sql);
        success.setText("Bus has been added");
//
    }
    @FXML
    protected void goBack() throws SQLException, IOException {
        HelloApplication.setRoot("admin-view");
    }
}


