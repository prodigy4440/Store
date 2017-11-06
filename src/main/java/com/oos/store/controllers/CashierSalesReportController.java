package com.oos.store.controllers;

import com.jfoenix.controls.JFXTextField;
import com.oos.store.entities.Sales;
import com.oos.store.utils.CashierUtils;
import com.oos.store.utils.QueryManager;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CashierSalesReportController implements Initializable {

    @FXML
    private TableView<Sales> cashierReportView;

    @FXML
    private TableColumn<Sales, String> gadgetNameCol;

    @FXML
    private TableColumn<Sales, String> gadgetModelCol;

    @FXML
    private TableColumn<Sales, String> gadgetImeiCol;

    @FXML
    private TableColumn<Sales, Double> prcieCol;

    @FXML
    private TableColumn<Sales, String> sellerCol;

    @FXML
    private TableColumn<Sales, String> buyerCol;

    @FXML
    private TableColumn<Sales, String> dateCol;

    @FXML
    private TableColumn<Sales, Double> priceSoldCol;

    @FXML
    private JFXTextField filtered;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        populateReportView();
    }

    public void populateReportView() {
        List<Sales> allCashierSales = QueryManager.getAllCashierSales(CashierUtils.cashier.getUsername());
        ObservableList<Sales> getAllSalesPerCashier = FXCollections.observableArrayList();
        getAllSalesPerCashier.addAll(allCashierSales);

        gadgetNameCol.setCellValueFactory(new PropertyValueFactory("gadgetName"));
        gadgetModelCol.setCellValueFactory(new PropertyValueFactory("model"));
        gadgetImeiCol.setCellValueFactory(new PropertyValueFactory("gadgetImei"));
        prcieCol.setCellValueFactory(new PropertyValueFactory("price"));
        sellerCol.setCellValueFactory(new PropertyValueFactory("seller"));
        buyerCol.setCellValueFactory(new PropertyValueFactory("customerName"));
        dateCol.setCellValueFactory(new PropertyValueFactory("cur"));
        priceSoldCol.setCellValueFactory(new PropertyValueFactory("priceSold"));

        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Sales> filteredData = new FilteredList<>(getAllSalesPerCashier, s -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        filtered.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(sales -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (sales.getGadgetName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (sales.getCustomerName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (sales.getCur().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Sales> sortedData = new SortedList<>(filteredData);
        

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(cashierReportView.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        cashierReportView.setItems(sortedData);

    }

}
