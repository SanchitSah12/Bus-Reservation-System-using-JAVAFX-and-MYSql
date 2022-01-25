package com.example.jdbcc;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import Connectivity.connectionClass;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminView {


    @FXML
    protected void addBus() throws SQLException, IOException {
        HelloApplication.setRoot("add-bus");
    }
    @FXML
    protected void delBus() throws SQLException, IOException {
        HelloApplication.setRoot("delBus");
    }
    @FXML
    protected void showData() throws SQLException, IOException {
        HelloApplication.setRoot("showData");
    }
    @FXML
    protected void bookingdata() throws SQLException, IOException {
        HelloApplication.setRoot("showBookings");
    }
    @FXML
    protected void goHome() throws SQLException, IOException {
        HelloApplication.setRoot("page-1");
    }



}


