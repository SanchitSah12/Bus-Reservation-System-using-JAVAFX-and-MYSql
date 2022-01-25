package com.example.jdbcc;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;

import java.io.IOException;
import java.sql.SQLException;

public class CustomerView {



    @FXML
    private TableColumn<buses,String> id,busName,busType,from,to,date,time;

    @FXML
    protected void bookTickets() throws SQLException, IOException {
        HelloApplication.setRoot("bookTickets");
    }
    @FXML
    protected void cancelTickets() throws SQLException, IOException {

        HelloApplication.setRoot("cancelTicket");


    }

    @FXML
    protected void goHome() throws SQLException, IOException {
        HelloApplication.setRoot("page-1");
    }


}


