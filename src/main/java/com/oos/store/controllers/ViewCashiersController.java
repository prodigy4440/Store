package com.oos.store.controllers;

import com.jfoenix.controls.JFXTextField;
import com.oos.store.entities.Cashier;
import com.oos.store.utils.QueryManager;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ViewCashiersController implements Initializable {
    
    @FXML
    private TableView<Cashier> cashierView;
    
    @FXML
    private TableColumn<Cashier, String> fullnameCol;
    
    @FXML
    private TableColumn<Cashier, String> usernameCol;
    
    @FXML
    private TableColumn<Cashier, String> phone;
    
    @FXML
    private TableColumn<Cashier, String> address;
    
    private ObservableList<Cashier> listCashier;
    
    @FXML
    private JFXTextField filtered;
    
    private Stage stage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        populateCashierTable();
        getCashier();
    }
    
    public void populateCashierTable() {
        listCashier = FXCollections.observableArrayList();
        List<Cashier> allCashier = QueryManager.getAllCashier();
        listCashier.addAll(allCashier);
        
        fullnameCol.setCellValueFactory(new PropertyValueFactory("fullName"));
        usernameCol.setCellValueFactory(new PropertyValueFactory("username"));
        phone.setCellValueFactory(new PropertyValueFactory("phone"));
        address.setCellValueFactory(new PropertyValueFactory("address"));

        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Cashier> filteredData = new FilteredList<>(listCashier, c -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        filtered.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(cashiers -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (cashiers.getFullName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (cashiers.getUsername().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Cashier> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(cashierView.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        cashierView.setItems(sortedData);
    }
    
    public void showGoodsDeatails(Cashier cashier) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ShowCashierDeatails.fxml"));
            AnchorPane load = (AnchorPane) loader.load();
            
            Stage dialogSatage = new Stage();
            dialogSatage.setTitle("View Cashier Details");
            dialogSatage.initModality(Modality.WINDOW_MODAL);
            dialogSatage.initOwner(getStage());
            dialogSatage.setResizable(false);
            Scene scene = new Scene(load);
            dialogSatage.setScene(scene);
            
            ShowCashierDeatailsController controller = (ShowCashierDeatailsController) loader.getController();
            controller.setStage(dialogSatage);
            controller.setCashierDetails(cashier);
            
            dialogSatage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(ViewGadgetsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Stage getStage() {
        return stage;
    }
    
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    public void getCashier(){
        cashierView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Cashier>() {

            @Override
            public void changed(ObservableValue<? extends Cashier> observable, Cashier oldValue, Cashier newValue) {
                showGoodsDeatails(newValue);
            }
        });
    }
    
}
