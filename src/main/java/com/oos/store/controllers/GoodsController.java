package com.oos.store.controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.oos.store.entities.Goods;
import com.oos.store.utils.DBManager;
import com.oos.store.utils.DateUtil;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javax.persistence.EntityManager;

/**
 * FXML Controller class
 *
 * @author Oluwaseun_Smart
 */
public class GoodsController implements Initializable {

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    @FXML
    void handleSaveGadget(ActionEvent event) {

        String currentDateTime = DateUtil.getCurrentDateTime();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation ");
        alert.setHeaderText("Are You Sure");
        alert.setContentText("Are You sure, you want to submit?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {

            if (validateGoodsInput()) {
                Goods goods = new Goods();
                goods.setDescription(description.getText());
                goods.setGadgetName(gadgetName.getText());
                goods.setModel(model.getText());
                goods.setPrice(Double.parseDouble(price.getText()));
                goods.setQuatity(Integer.parseInt(quantity.getText()));
                goods.setCurdate(currentDateTime);
                goods.setShowGoods(false);

                EntityManager em = DBManager.entityManager();
                em.getTransaction().begin();
                em.persist(goods);
                em.getTransaction().commit();

                Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
                alert3.setTitle("Successfull ");
                alert3.setHeaderText("Record successfully added");
                alert3.setContentText("Done!!!...");
                alert3.showAndWait();
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
