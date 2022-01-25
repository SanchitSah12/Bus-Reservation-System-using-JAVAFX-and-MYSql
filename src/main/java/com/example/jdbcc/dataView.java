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

public class dataView implements Initializable  {
    @FXML
    private TableView<buses> table;

    @FXML
    private TableColumn<buses,String> id,busName,busType,from,to,date,time;
    @FXML
    private TableColumn<buses,Integer> seats,price;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<buses> items = FXCollections.observableArrayList();

        connectionClass connectionClass = new connectionClass();
        Connection connection;
        try {
            connection = connectionClass.getConnection();
            Statement statement = connection.createStatement();

            String sql2 =  "SELECT * FROM BUSES;";
            ResultSet result = statement.executeQuery(sql2);
            while (result.next()){
                String id = result.getString("id");
                String Bname = result.getString("busName");
                String Btype = result.getString("busType");
                String from = result.getString("from");
                String to = result.getString("to");
                String date = result.getString("date");
                String time = result.getString("time");
                int seats = Integer.parseInt(result.getString("seats"));
                int price = Integer.parseInt(result.getString("price"));
                System.out.println(id+" "+Bname+" "+Btype+" "+from+" "+to+" "+date+" "+time);
                System.out.println(seats);
                System.out.println(price);

                items.add(new buses(id,Bname,Btype,from,to,date,time,seats,price));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }



        id.setCellValueFactory(new PropertyValueFactory<buses,String>("id"));
        busName.setCellValueFactory(new PropertyValueFactory<buses,String>("busName"));
        busType.setCellValueFactory(new PropertyValueFactory<buses,String>("busType"));
        from.setCellValueFactory(new PropertyValueFactory<buses,String>("from"));
        to.setCellValueFactory(new PropertyValueFactory<buses,String>("to"));
        date.setCellValueFactory(new PropertyValueFactory<buses,String>("date"));
        time.setCellValueFactory(new PropertyValueFactory<buses,String>("time"));
        seats.setCellValueFactory(new PropertyValueFactory<buses, Integer>("seats"));
        price.setCellValueFactory(new PropertyValueFactory<buses,Integer>("price"));

        table.setItems(items);

    }
    @FXML
    protected void showBookingData() throws SQLException, IOException {
        HelloApplication.setRoot("showBookings");
    }

    @FXML
    protected void goBack() throws SQLException, IOException {

        HelloApplication.setRoot("admin-view");

    }
}


