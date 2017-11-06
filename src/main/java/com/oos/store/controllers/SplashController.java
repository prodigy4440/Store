/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oos.store.controllers;

import com.oos.store.utils.QueryManager;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Oluwaseun_Smart
 */
public class SplashController implements Initializable {

    @FXML
    private Label label;

    @FXML
    private ProgressBar progressUpdater;

    private int maxTimeTaken;

    private Service<Void> progressUpdate;

    private List<String> msg;

    private int max;

    @FXML
    private Stage Constate;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        maxTimeTaken = 100000000;

        msg = new LinkedList<>();

        
        msg.add("Selecting Cashier");
        msg.add("Selecting Gadget...");
        msg.add("Setting System Look And feel...");
        msg.add("Connecting the Database...");
        msg.add("Configuring the System");
        msg.add("Checking System Specification...");

        maxTimeTaken = 100000000;

        max = 8;

        progressFun();

        msgFun();

    }

    public void progressFun() {
        progressUpdate = new Service<Void>() {

            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {

                    @Override
                    protected Void call() throws Exception {
                        for (int i = 0; i < maxTimeTaken; i++) {
                            updateProgress(i, maxTimeTaken);
                        }
                        return null;
                    }

                    @Override
                    protected void succeeded() {
                        loadLogin();
                    }

                    @Override
                    protected void running() {
                        QueryManager.getAllCashier();
                    }
                 
                    

                };
            }
        };
        progressUpdater.progressProperty().bind(progressUpdate.progressProperty());
        progressUpdate.restart();
    }

    public void msgFun() {
        progressUpdate = new Service<Void>() {

            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {

                    @Override
                    protected Void call() throws Exception {
                        for (int i = 0; i < max; i++) {
                            updateMessage(msg.get(i));
                            Thread.sleep(1000);
                        }
                        return null;
                    }

                    @Override
                    protected void succeeded() {
                        label.setText("Completed.");
                    }

                };
            }
        };
        label.textProperty().bind(progressUpdate.messageProperty());
        progressUpdate.restart();
    }

    public void loadLogin() {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GoogleLogin.fxml"));
            AnchorPane load = (AnchorPane) loader.load();
            GoogleLoginController controller = (GoogleLoginController) loader.getController();
            controller.setStage(stage);
            Scene scene = new Scene(load);
            stage.setTitle("Login");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
            getConstate().close();
        } catch (IOException ex) {
            Logger.getLogger(SplashController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Stage getConstate() {
        return Constate;
    }

    public void setConstate(Stage Constate) {
        this.Constate = Constate;
    }

}
