package com.oos.store.controllers;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.oos.store.entities.Cashier;
import com.oos.store.utils.ImageUtil;
import com.oos.store.utils.QueryManager;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;

public class ShowCashierDeatailsController implements Initializable {

    @FXML
    private JFXTextField fullname;

    @FXML
    private JFXTextField phone;

    @FXML
    private JFXTextField username;

    @FXML
    private JFXTextArea address;

    @FXML
    private ImageView passport;

    private Stage stage;

    private Cashier cashier;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    void handleClose(ActionEvent event) {
        getStage().close();
    }

    public void setCashierDetails(Cashier cashier) {
        if (Objects.nonNull(cashier)) {
            this.cashier = cashier;
            BufferedImage cashierImage = ImageUtil.byteArrayToBufferedImage(cashier.getPassport());
            fullname.setText(cashier.getFullName());
            address.setText(cashier.getAddress());
            phone.setText(cashier.getPhone());
            username.setText(cashier.getUsername());
            WritableImage toFXImage = SwingFXUtils.toFXImage(cashierImage, null);
            passport.setImage(toFXImage);
        }

    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    void handleSack(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation ");
        alert.setHeaderText("Are You Sure");
        alert.setContentText("Are You sure, this cashier has been sack?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            QueryManager.sackCashier(getCashier().getId());
            Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
            alert3.setTitle("Successfull ");
            alert3.setHeaderText("Record successfully Deleted");
            alert3.setContentText("Done!!!...");
            alert3.showAndWait();
            getStage().close();

        } else {
            Alert alert3 = new Alert(Alert.AlertType.WARNING);
            alert3.setTitle("Abort ");
            alert3.setHeaderText("Look like something went wrong");
            alert3.setContentText("The operation has been cancel by user");
            alert3.showAndWait();
        }
    }

    public Cashier getCashier() {
        return cashier;
    }

}
