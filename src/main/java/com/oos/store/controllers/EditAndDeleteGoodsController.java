/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oos.store.controllers;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.oos.store.entities.Goods;
import com.oos.store.utils.QueryManager;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Oluwaseun_Smart
 */
public class EditAndDeleteGoodsController implements Initializable {

    @FXML
    private JFXTextField gadgetName;

    @FXML
    private JFXTextField model;

    @FXML
    private JFXTextField price;

    @FXML
    private JFXTextArea description;

    @FXML
    private JFXTextField quantity;

    @FXML
    private JFXTextField visibility;

    private Stage stage;

    private Goods goods;

    DecimalFormat df;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setGoods(Goods goods) {

        this.goods = goods;

        df = new DecimalFormat("0.00");

        gadgetName.setText(goods.getGadgetName());
        model.setText(goods.getModel());
        price.setText(df.format(goods.getPrice()));
        description.setText(goods.getDescription());
        quantity.setText(goods.getQuatity() + "");
        visibility.setText(goods.isShowGoods() + "");
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    void handleDelete(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation ");
        alert.setHeaderText("Are You Sure");
        alert.setContentText("Are You sure, you want to Delete this Goods Record?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            QueryManager.delectGoods(getGoods().getId());
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

    @FXML
    void handleEdit(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation ");
        alert.setHeaderText("Are You Sure");
        alert.setContentText("Are You sure, you want to Updated?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {

            if (validateGoodsInput()) {
                QueryManager.updateGoods(gadgetName.getText(), model.getText(), description.getText(), Double.parseDouble(price.getText()), Integer.parseInt(quantity.getText()), Boolean.parseBoolean(visibility.getText()), goods.getId());
                Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
                alert3.setTitle("Successfull ");
                alert3.setHeaderText("Record successfully Updated");
                alert3.setContentText("Done!!!...");
                alert3.showAndWait();
                getStage().close();
            }

        } else {
            Alert alert3 = new Alert(Alert.AlertType.WARNING);
            alert3.setTitle("Abort ");
            alert3.setHeaderText("Look like something went wrong");
            alert3.setContentText("The operation has been cancel by user");
            alert3.showAndWait();
        }
    }

    public boolean validateGoodsInput() {
        String errorMessage = "";
        if (gadgetName.getText() == null || gadgetName.getText().length() == 0) {
            errorMessage += "No valid Gadget Name!\n";
        }
        if (model.getText() == null || model.getText().length() == 0) {
            errorMessage += "No valid Model Name!\n";
        }
        if (price.getText() == null || price.getText().length() == 0) {
            errorMessage += "No valid Price!\n";
        } else {
            try {
                Double.parseDouble(price.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid Price(Must be a Number)!\n";
            }
        }

        if (quantity.getText() == null || quantity.getText().length() == 0) {
            errorMessage += "No valid Quantity!\n";
        } else {
            try {
                Integer.parseInt(quantity.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid Quantity(Must be a Number)!\n";
            }
        }

        if (description.getText() == null || description.getText().length() == 0) {
            errorMessage += "No valid Description!\n";
        }

        if (visibility.getText() == null || visibility.getText().length() == 0) {
            errorMessage += "No valid visibility!\n";
        } else {
            try {
                Boolean.parseBoolean(visibility.getText());
            } catch (Exception e) {
                errorMessage += "No valid visibility!(Must be true or false)\n";
            }
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

    public Goods getGoods() {
        return goods;
    }

}
