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
import javafx.fxml.Initializable;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ShowBookings implements Initializable  {
    @FXML
    private TableView<tickets> table;

    @FXML
    private TableColumn<tickets,String> id,username,busname;
    @FXML
    private TableColumn<tickets,Integer> tickets;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<tickets> items = FXCollections.observableArrayList();

        connectionClass connectionClass = new connectionClass();
        Connection connection;
        try {
            connection = connectionClass.getConnection();
            Statement statement = connection.createStatement();
            String sql = "Delete FROM TICKET WHERE BUSNAME IS NULL;";
            statement.executeUpdate(sql);
            String sql2 =  "SELECT * FROM Ticket;";
            ResultSet result = statement.executeQuery(sql2);
            while (result.next()){
                String id = result.getString("id");
                String username = result.getString("username");
                String Bname = result.getString("busName");
                int seats = Integer.parseInt(result.getString("no_of_tickets"));
                items.add(new tickets(id,username,Bname,seats));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }



        id.setCellValueFactory(new PropertyValueFactory<tickets,String>("id"));
        username.setCellValueFactory(new PropertyValueFactory<tickets,String>("username"));
        busname.setCellValueFactory(new PropertyValueFactory<tickets,String>("busName"));
        tickets.setCellValueFactory(new PropertyValueFactory<tickets, Integer>("no_of_tickets"));


        table.setItems(items);

    }
    @FXML
    protected void goBack() throws SQLException, IOException {

        HelloApplication.setRoot("admin-view");

    }
}




