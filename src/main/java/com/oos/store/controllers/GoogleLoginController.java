/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oos.store.controllers;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.oos.store.entities.Cashier;
import com.oos.store.utils.CashierUtils;
import com.oos.store.utils.QueryManager;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.persistence.NoResultException;

/**
 * FXML Controller class
 *
 * @author Oluwaseun_Smart
 */
public class GoogleLoginController implements Initializable {

    private Stage stage;

    @FXML
    private JFXTextField username;

    @FXML
    private JFXPasswordField password;


    @FXML
    private Label errorMsg;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    void handleLogin(ActionEvent event) throws IOException {

        try {
            Cashier validateCashier = QueryManager.validateCashierUsernameAndPassword(username.getText(), password.getText());
            CashierUtils.cashier = validateCashier;
            if (validateCashier.getRol().equals("admin")) {
                Stage dialog = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Admin.fxml"));
                AnchorPane load = (AnchorPane) loader.load();
                Scene scene = new Scene(load);
                AdminController controller = (AdminController) loader.getController();
                controller.setStage(dialog);
                controller.setLoginStage(getStage());

                dialog.setTitle("Administrative Centre");
                dialog.setScene(scene);
                dialog.show();
                getStage().close();
            } else {
                Stage dialog = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Cashier.fxml"));
                BorderPane load = (BorderPane) loader.load();
                Scene scene = new Scene(load);
                CashierController controller = (CashierController) loader.getController();
                
                controller.setCashierDeatils(CashierUtils.cashier);

                controller.setStatge(dialog);
                controller.setLoginSatge(getStage());

                

                dialog.setTitle("Sales Centre");
                dialog.setScene(scene);
                dialog.show();
                getStage().close();
            }
        } catch (NoResultException e) {
            errorMsg.setText("***Username or password is incorrect...");
            errorMsg.setVisible(true);
            animateErrorMsg();
        }

    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private void animateErrorMsg() {
        FadeTransition ft = new FadeTransition(Duration.millis(3000), errorMsg);
        ft.setFromValue(0.0);
        ft.setToValue(1);
        ft.play();
    }

}
