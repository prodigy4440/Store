package com.oos.store.controllers;

import com.jfoenix.controls.JFXComboBox;
import com.oos.store.entities.Cashier;
import com.oos.store.entities.DateChecker;
import com.oos.store.entities.MonAndYeaChecker;
import com.oos.store.entities.MonthChecker;
import com.oos.store.entities.YearChecker;
import com.oos.store.utils.QueryManager;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Oluwaseun_Smart
 */
public class SpecifyReportController implements Initializable {

    private Stage stage;

    @FXML
    private JFXComboBox<String> category;

    @FXML
    private JFXComboBox<String> specify;

    private List<YearChecker> listAllYears;
    private List<MonthChecker> listAllMonths;
    private List<MonAndYeaChecker> monAndAllYears;
    private List<DateChecker> listAllDates;
    private List<Cashier> listAllCashier;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        listAllDates = QueryManager.listAllDateOfSell();
        monAndAllYears = QueryManager.listAllMonAndYear();
        listAllMonths = QueryManager.listAllMonth();
        listAllYears = QueryManager.listAllYears();
        listAllCashier = QueryManager.getAllCashier();

        populateCatCombo();
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    void handleReporting(ActionEvent event) {
        String selectedItem = specify.getSelectionModel().getSelectedItem();
        if (Objects.nonNull(selectedItem)) {
            showSalesReport(selectedItem);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error.... ");
            alert.setHeaderText("Invalid Choice");
            alert.setContentText("Please Specify your choice!!!...");
            alert.showAndWait();
        }

    }

    public void showSalesReport(String selectedItem) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GoodsReport.fxml"));
            AnchorPane load = (AnchorPane) loader.load();

            Stage dialogSatage = new Stage();
            dialogSatage.setTitle("Sales Reporting");
            dialogSatage.initModality(Modality.WINDOW_MODAL);
            dialogSatage.initOwner(getStage());
            dialogSatage.setResizable(false);
            Scene scene = new Scene(load);
            dialogSatage.setScene(scene);

            GoodsReportController controller = (GoodsReportController) loader.getController();
            controller.setStage(dialogSatage);
            controller.populateReportView(selectedItem);

            dialogSatage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(ViewGadgetsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void populateCatCombo() {
        ObservableList<String> listCat = FXCollections.observableArrayList();
        listCat.add("Year");
        listCat.add("Month");
        listCat.add("Month And Year");
        listCat.add("Date");
        listCat.add("Cashier");

        category.setItems(listCat);
    }

    @FXML
    void handleChooseCat(ActionEvent event) {
        String selectedItem = category.getSelectionModel().getSelectedItem();

        switch (selectedItem) {
            case "Year": {
                ObservableList<String> listSpecifyYear = FXCollections.observableArrayList();
                listAllYears.stream().forEach((checker) -> {
                    listSpecifyYear.add(checker.getYeaOfSell() + "");
                });
                specify.setItems(listSpecifyYear);
                break;
            }
            case "Month": {
                ObservableList<String> listSpecifyYear = FXCollections.observableArrayList();
                listAllMonths.stream().forEach((checker) -> {
                    listSpecifyYear.add(checker.getMonOfSell());
                });
                specify.setItems(listSpecifyYear);
                break;
            }
            case "Cashier": {
                ObservableList<String> listSpecifyYear = FXCollections.observableArrayList();
                listAllCashier.stream().forEach((checker) -> {
                    listSpecifyYear.add(checker.getUsername());
                });
                specify.setItems(listSpecifyYear);
                break;
            }
            case "Date": {
                ObservableList<String> listSpecifyYear = FXCollections.observableArrayList();
                listAllDates.stream().forEach((checker) -> {
                    listSpecifyYear.add(checker.getDateOfSell());
                });
                specify.setItems(listSpecifyYear);
                break;
            }
            case "Month And Year": {
                ObservableList<String> listSpecifyYear = FXCollections.observableArrayList();
                monAndAllYears.stream().forEach((checker) -> {
                    listSpecifyYear.add(checker.getMonAndYeaOfSell());
                });
                specify.setItems(listSpecifyYear);
                break;
            }
            default:
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error.... ");
                alert.setHeaderText("Invalid Choice");
                alert.setContentText("Choose a valid Choose!!!...");
                alert.showAndWait();
                break;
        }

    }

}
