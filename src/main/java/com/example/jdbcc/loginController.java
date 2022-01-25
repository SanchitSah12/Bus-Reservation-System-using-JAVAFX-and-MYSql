package com.example.jdbcc;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Window;
import javafx.scene.control.TextField;
import Connectivity.connectionClass;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class loginController {

    @FXML
    public PasswordField password;
    public TextField username;
    @FXML
    private Button loginbtn;



    @FXML
    protected void loginorsignup() throws SQLException, IOException {

        connectionClass connectionClass = new connectionClass();
        Connection connection = connectionClass.getConnection();
        Window owner = loginbtn.getScene().getWindow();
        String user = username.getText();
        String pass = password.getText();
        if (user.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Username error!",
                    "Please enter your username");
            return;
        }
        if (pass.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Password error!",
                    "Please enter a password");
            return;
        }
        if(user.equals("admin") && pass.equals("123456")){
            HelloApplication.setRoot("admin-view");
            return;
        }
        Statement statement = connection.createStatement();
////        statement.executeUpdate(sql);
        String sql2 =  "SELECT * FROM LOGIN WHERE USERNAME = '"+user+"';";

        ResultSet result = statement.executeQuery(sql2);
        if(result.next()){

                System.out.println(result.getString("password"));

                if(result.getString("password").equals(pass)){
                    //change Screen
                    String sql =  "INSERT INTO TICKET VALUES(NULL,'"+user+"',NULL,NULL)";
                    statement.executeUpdate(sql);
                    System.out.println("Logined");
                    HelloApplication.setRoot("customerView");
                }
                else {
                    showAlert(Alert.AlertType.ERROR, owner, "Password Error!",
                            "Wrong password");
                }
            }

        else {
            String sql =  "INSERT INTO LOGIN VALUES('"+user+"','"+pass+"')";
            statement.executeUpdate(sql);

            infoBox("As no user was Found We created your brand new Account with the same username and password you entered!", null, "New User");
            return;
        }




    }
    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    private static void showAlert(Alert.AlertType alertType, Window owner,String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
    }



