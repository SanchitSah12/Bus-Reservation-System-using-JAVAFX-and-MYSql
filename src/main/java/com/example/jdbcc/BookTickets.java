package com.example.jdbcc;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import Connectivity.connectionClass;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.fxml.Initializable;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class BookTickets implements Initializable {
    @FXML
    private TableView<buses> table;
    @FXML
    private ComboBox selector;

    @FXML
    private TextField ss;

    @FXML
    private TableColumn<buses, String> name, type, from, to, date;
    @FXML
    private TableColumn<buses, Integer> price;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<buses> items = FXCollections.observableArrayList();

        connectionClass connectionClass = new connectionClass();
        Connection connection;


        try {
            connection = connectionClass.getConnection();
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM BUSES;";
            ResultSet result1 = statement.executeQuery(sql);

            while (result1.next()) {
                selector.getItems().add(result1.getString("busName"));
            }


            String sql2 = "SELECT * FROM BUSES;";
            ResultSet result = statement.executeQuery(sql2);
            while (result.next()) {
                String id = result.getString("id");
                String Bname = result.getString("busName");
                String Btype = result.getString("busType");
                String from = result.getString("from");
                String to = result.getString("to");
                String date = result.getString("date") + "  " + result.getString("time");
                String time = result.getString("time");
                int seats = Integer.parseInt(result.getString("seats"));
                int price = Integer.parseInt(result.getString("price"));
                System.out.println(id + " " + Bname + " " + Btype + " " + from + " " + to + " " + date + " " + time);
                System.out.println(seats);
                System.out.println(price);

                items.add(new buses(id, Bname, Btype, from, to, date, time, seats, price));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        name.setCellValueFactory(new PropertyValueFactory<buses, String>("busName"));
        type.setCellValueFactory(new PropertyValueFactory<buses, String>("busType"));
        from.setCellValueFactory(new PropertyValueFactory<buses, String>("from"));
        to.setCellValueFactory(new PropertyValueFactory<buses, String>("to"));
        date.setCellValueFactory(new PropertyValueFactory<buses, String>("date"));
        price.setCellValueFactory(new PropertyValueFactory<buses, Integer>("price"));

        table.setItems(items);

    }

    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    @FXML
    protected void book() throws SQLException, IOException {
        Window owner = selector.getScene().getWindow();

        System.out.println(selector.getValue());
        System.out.println(ss.getText());
        if (!isNumeric(ss.getText())) {
            showAlert(Alert.AlertType.ERROR, owner, "Invalid!",
                    "Enter Valid number of Seats");
            return;
        } else if (ss.getText().equals("0")) {
            showAlert(Alert.AlertType.ERROR, owner, "O seats FR???!",
                    "Book some seats");
            return;
        }
        else {


            String user = "";
            int seats = 0;
//        System.out.println(user);
            String sql2 = "SELECT * FROM TICKET WHERE BUSNAME IS NULL;";
            connectionClass connectionClass = new connectionClass();
            Connection connection = connectionClass.getConnection();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql2);
            while (result.next()) {
                user = result.getString("Username");
            }
            System.out.println(user);


            String sql22 = "SELECT * FROM BUSES WHERE BUSNAME='" + selector.getValue() + "';";
            ResultSet result1 = statement.executeQuery(sql22);
            while (result1.next()) {
                seats = Integer.parseInt(result1.getString("seats"));
            }

            int newSeat = seats - Integer.parseInt(ss.getText());
            System.out.println(newSeat);
            if (newSeat >= 0) {
                String sql = "UPDATE ticket SET BUSNAME = '" + selector.getValue() + "', no_of_tickets= '" + ss.getText() + "' WHERE BUSNAME IS NULL;";
                statement.executeUpdate(sql);
                String sql3 = "UPDATE buses SET seats = '" + newSeat + "' WHERE BUSNAME='" + selector.getValue() + "';";
                statement.executeUpdate(sql3);
                HelloApplication.setRoot("thankYou");


            } else {
                showAlert(Alert.AlertType.ERROR, owner, "No Seats left!",
                        "Not enough Seats: Seats left " + seats + ".");
                return;
            }


        }

    }

    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    @FXML
    protected void goBack() throws SQLException, IOException {

        HelloApplication.setRoot("customerView");

    }


}


