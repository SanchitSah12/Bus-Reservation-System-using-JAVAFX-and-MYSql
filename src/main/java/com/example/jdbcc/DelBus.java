package com.example.jdbcc;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import Connectivity.connectionClass;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DelBus{
    @FXML
    private ComboBox selector;


    @FXML
    protected void populate() throws SQLException {
        System.out.println("populated");
        connectionClass connectionClass = new connectionClass();
        Connection connection = connectionClass.getConnection();

        Statement statement = connection.createStatement();

        String sql2 = "SELECT * FROM BUSES;";
        ResultSet result = statement.executeQuery(sql2);

        while (result.next()) {
            selector.getItems().add(result.getString("busName"));
        }
    }


    @FXML
    protected void delBus() throws SQLException {

        System.out.println(selector.getValue());
        connectionClass connectionClass = new connectionClass();
        Connection connection = connectionClass.getConnection();

        Statement statement = connection.createStatement();
        String sql = "Delete FROM BUSES WHERE BUSNAME='"+selector.getValue()+"';";

        statement.executeUpdate(sql);
        String sql2 = "Delete FROM TICKET WHERE BUSNAME='"+selector.getValue()+"';";
        statement.executeUpdate(sql2);

    }
    @FXML
    protected void goBack() throws SQLException, IOException {

        HelloApplication.setRoot("admin-view");

    }
}


