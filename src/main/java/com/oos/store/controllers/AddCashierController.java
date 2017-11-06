package com.oos.store.controllers;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.oos.store.entities.Cashier;
import com.oos.store.entities.Goods;
import com.oos.store.utils.DBManager;
import com.oos.store.utils.DateUtil;
import com.oos.store.utils.ImageUtil;
import com.oos.store.utils.QueryManager;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

public class AddCashierController implements Initializable {

    @FXML
    private ImageView passport;

    private Stage stage;

    private BufferedImage image;

    @FXML
    private JFXTextField fullname;

    @FXML
    private JFXTextField phone;

    @FXML
    private JFXTextField username;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXTextArea address;

    @FXML
    private JFXPasswordField cPassword;

    private File file;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    void handleBrowse(ActionEvent event) {
        try {
            FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.jpeg");
            FileChooser fileChooser = new FileChooser();
            fileChooser
                    .getExtensionFilters().add(imageFilter);
            file = fileChooser.showOpenDialog(getStage());
            if (Objects.nonNull(file)) {
                image = ImageIO.read(file);
                WritableImage toFXImage = SwingFXUtils.toFXImage(image, null);
                passport.setImage(toFXImage);
            }
        } catch (IOException ex) {
            Logger.getLogger(AddCashierController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void handleSaveCashier(ActionEvent event) {

        String currentDateTime = DateUtil.getCurrentDateTime();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation ");
        alert.setHeaderText("Are You Sure");
        alert.setContentText("Are You sure, you want to submit?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {

            if (validateGoodsInput()) {
                byte[] imageToByteArray = ImageUtil.imageToByteArray(image);
                Cashier cashier = new Cashier();
                cashier.setFullName(fullname.getText());
                cashier.setAddress(address.getText());
                cashier.setPassword(password.getText());
                cashier.setPhone(phone.getText());
                cashier.setUsername(username.getText());
                cashier.setPassport(imageToByteArray);
                cashier.setCur(currentDateTime);
                cashier.setRol("cashier");

                EntityManager em = DBManager.entityManager();
                em.getTransaction().begin();
                em.persist(cashier);
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

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public boolean validateGoodsInput() {
        String errorMessage = "";
        Cashier validateUsername = null;
        try {
            validateUsername = QueryManager.validateUsername(username.getText());
        } catch (NoResultException e) {
        }

        if (fullname.getText() == null || fullname.getText().length() == 0) {
            errorMessage += "No valid Fullname!\n";
        }

        if (Objects.nonNull(validateUsername)) {
            errorMessage += "Username Already exists(Try another username)!\n";
        }
        if (phone.getText() == null || phone.getText().length() == 0) {
            errorMessage += "No valid Phone Number!\n";
        }
        if (username.getText() == null || username.getText().length() == 0) {
            errorMessage += "No valid Username!\n";
        }
        if (address.getText() == null || address.getText().length() == 0) {
            errorMessage += "No valid Address!\n";
        }
        if (password.getText() == null || password.getText().length() == 0) {
            errorMessage += "No valid Password!\n";

        }

        if (cPassword.getText() == null || cPassword.getText().length() == 0) {
            errorMessage += "No valid Confirm Password!\n";
        }

        if (!cPassword.getText().equals(password.getText())) {
            errorMessage += "Password do not match!\n";
        }
        if (image == null) {
            errorMessage += "No passport upload!\n";
        }
        if (file.length() / 1024 > 90) {
            errorMessage += "Image too big (maximum size is 80KB)!\n";
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
