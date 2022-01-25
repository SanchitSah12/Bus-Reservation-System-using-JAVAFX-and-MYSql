package com.example.jdbcc;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import Connectivity.connectionClass;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class cancel implements Initializable {
    @FXML
    private ComboBox selector;
    @FXML
    private Label success;


    @FXML
    public void initialize(URL url, ResourceBundle rb) {

            String user="";
            int id = 0;
            System.out.println("populated");
            connectionClass connectionClass = new connectionClass();
            Connection connection;
            try {
                connection = connectionClass.getConnection();
                Statement statement = connection.createStatement();
                String sql2 =  "SELECT * FROM TICKET WHERE BUSNAME IS NULL;";
                ResultSet result1 = statement.executeQuery(sql2);
                while (result1.next()){
                    id = Integer.parseInt(result1.getString("ID"));
                    user = result1.getString("Username");
                }
                String sql1 = "Delete FROM TICKET WHERE BUSNAME IS NULL;";
                statement.executeUpdate(sql1);
                
                
                String sql = "SELECT * FROM TICKET WHERE USERNAME = '"+user+"';";

                ResultSet result = statement.executeQuery(sql);
                while (result.next()) {
                    selector.getItems().add(result.getString("ID") + " " + result.getString("BusName"));
                }


                String sql4 =  "INSERT INTO TICKET VALUES("+id+",'"+user+"',NULL,NULL)";
                statement.executeUpdate(sql4);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }



    @FXML
    protected void delBus() throws SQLException {
        int seats = 0,busseats = 0;
        String busname="";
        System.out.println(selector.getValue());
        connectionClass connectionClass = new connectionClass();
        Connection connection = connectionClass.getConnection();

        String selectorVal = (String) selector.getValue();

        int value = Integer.parseInt(selectorVal.replaceAll("[^0-9]", ""));
        Statement statement = connection.createStatement();




        String sql22 =  "SELECT * FROM TICKET WHERE ID='"+value+"';";
        ResultSet result1 = statement.executeQuery(sql22);
        while (result1.next()){
            seats = Integer.parseInt(result1.getString("no_of_tickets"));
            busname = result1.getString("BusName");
        }

        String sql2 =  "SELECT * FROM BUSES WHERE BUSNAME='"+busname+"';";
        ResultSet result = statement.executeQuery(sql2);
        while (result.next()){
            busseats = Integer.parseInt(result.getString("seats"));
        }


        int newSeat = seats + busseats;
        String sql3 = "UPDATE buses SET seats = '" + newSeat + "' WHERE BUSNAME='" + busname + "';";
        statement.executeUpdate(sql3);

        String sql = "Delete FROM TICKET WHERE ID='"+value+"';";
        statement.executeUpdate(sql);
        success.setText("Deleted");



    }
    @FXML
    protected void goBack() throws SQLException, IOException {

        HelloApplication.setRoot("customerView");

    }
}


