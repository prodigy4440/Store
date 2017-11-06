package com.oos.store.controllers;

import com.jfoenix.controls.JFXPasswordField;
import com.oos.store.utils.CashierUtils;
import com.oos.store.utils.QueryManager;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class CashierChangePasswordController implements Initializable {

    @FXML
    private JFXPasswordField currentPassword;

    @FXML
    private JFXPasswordField newPAssword;

    @FXML
    private JFXPasswordField confirmPassword;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    void handleChangePassword(ActionEvent event) {
        changePassword();
    }

    public void changePassword() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation ");
        alert.setHeaderText("Are You Sure");
        alert.setContentText("Are You sure,you want to change your password!");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            if (validatePasswordInput()) {
                QueryManager.updateCashierPassword(CashierUtils.cashier.getUsername(), newPAssword.getText());
                Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
                alert3.setTitle("Successfull ");
                alert3.setHeaderText("Password Changed");
                alert3.setContentText("Done!!!...");
                alert3.showAndWait();

            }
        }
    }

    public boolean validatePasswordInput() {
        String errorMessage = "";

        if (!currentPassword.getText().equals(CashierUtils.cashier.getPassword())) {
            errorMessage += "Incorrect Password!\n";
        }

        if (currentPassword.getText() == null || currentPassword.getText().length() == 0) {
            errorMessage += "Invalid Current Password!\n";
        }

        if (newPAssword.getText() == null || newPAssword.getText().length() == 0) {
            errorMessage += "Invalid new Password!\n";
        }

        if (confirmPassword.getText() == null || confirmPassword.getText().length() == 0) {
            errorMessage += "Invalid Confirm Password!\n";
        }

        if (!newPAssword.getText().equals(confirmPassword.getText())) {
            errorMessage += "Password does not match!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert3 = new Alert(Alert.AlertType.WARNING);
            alert3.setTitle("Inpur Error ");
            alert3.setHeaderText("Look like something went wrong");
            alert3.setContentText(errorMessage);
            alert3.showAndWait();
            return false;

        }
    }

}
