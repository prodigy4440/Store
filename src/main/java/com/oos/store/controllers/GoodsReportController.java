package com.oos.store.controllers;

import com.jfoenix.controls.JFXTextField;
import com.oos.store.entities.Sales;
import com.oos.store.utils.QueryManager;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Oluwaseun_Smart
 */
public class GoodsReportController implements Initializable {

    private Stage stage;

    @FXML
    private TableView<Sales> reportView;

    @FXML
    private TableColumn<Sales, String> gadgetNameCol;

    @FXML
    private TableColumn<Sales, String> modelCol;

    @FXML
    private TableColumn<Sales, Double> priceCol;

    @FXML
    private TableColumn<Sales, Double> priceSoldCol;

    @FXML
    private TableColumn<Sales, Double> ProfitCol;

    @FXML
    private TableColumn<Sales, Double> lossCol;

    @FXML
    private TableColumn<Sales, String> CustomerNameCol;

    @FXML
    private TableColumn<Sales, String> cashierNameCol;

    @FXML
    private TableColumn<Sales, String> dateAndTimeCol;

    private List<Sales> allSales;

    @FXML
    private JFXTextField filtered;

    @FXML
    private JFXTextField profitTxt;

    @FXML
    private JFXTextField lossTxt;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setSales() {

    }

    @FXML
    void handleCancel(ActionEvent event) {
        getStage().close();
    }

    public void populateReportView(String selectedItem) {

        DecimalFormat df = new DecimalFormat("0.00");

        if (Objects.nonNull(selectedItem)) {
            allSales = QueryManager.getAllSales(selectedItem);
            ObservableList<Sales> listAllSales = FXCollections.observableArrayList();

            listAllSales.addAll(allSales);

            gadgetNameCol.setCellValueFactory(new PropertyValueFactory("gadgetName"));
            modelCol.setCellValueFactory(new PropertyValueFactory("model"));
            priceCol.setCellValueFactory(new PropertyValueFactory("price"));
            priceSoldCol.setCellValueFactory(new PropertyValueFactory("priceSold"));
            ProfitCol.setCellValueFactory(new PropertyValueFactory("profit"));
            lossCol.setCellValueFactory(new PropertyValueFactory("loss"));
            CustomerNameCol.setCellValueFactory(new PropertyValueFactory("customerName"));
            cashierNameCol.setCellValueFactory(new PropertyValueFactory("seller"));
            dateAndTimeCol.setCellValueFactory(new PropertyValueFactory("cur"));

            // Formatting Loss Cell
            lossCol.setCellFactory(new Callback<TableColumn<Sales, Double>, TableCell<Sales, Double>>() {
                @Override
                public TableCell<Sales, Double> call(TableColumn<Sales, Double> param) {
                    return new TableCell<Sales, Double>() {

                        @Override
                        protected void updateItem(Double item, boolean empty) {
                            super.updateItem(item, empty);

                            if (!empty) {
                                // Use a SimpleDateFormat or similar in the format method
                                setText(df.format(item));

                                if (item < 0) {
                                    setTextFill(Color.RED);
                                    setStyle("-fx-background-color : Yellow");
                                } else {
                                    setTextFill(Color.GREEN);
                                }

                            } else {
                                setText(null);
                            }
                        }

                    };
                }
            });

            // Formatting Profit Cell
            ProfitCol.setCellFactory(new Callback<TableColumn<Sales, Double>, TableCell<Sales, Double>>() {
                @Override
                public TableCell<Sales, Double> call(TableColumn<Sales, Double> param) {
                    return new TableCell<Sales, Double>() {

                        @Override
                        protected void updateItem(Double item, boolean empty) {
                            super.updateItem(item, empty);

                            if (!empty) {
                                // Use a SimpleDateFormat or similar in the format method
                                setText(df.format(item));

                                if (item > 0) {
                                    setTextFill(Color.GREEN);
                                    setStyle("-fx-background-color : Black");
                                } else {
                                    setTextFill(Color.RED);
                                }

                            } else {
                                setText(null);
                            }
                        }

                    };
                }
            });

            // 1. Wrap the ObservableList in a FilteredList (initially display all data).
            FilteredList<Sales> filteredData = new FilteredList<>(listAllSales, s -> true);

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
                    } else if (sales.getSeller().toLowerCase().contains(lowerCaseFilter)) {
                        return true; // Filter matches last name.
                    }
                    return false; // Does not match.
                });
            });

            // 3. Wrap the FilteredList in a SortedList. 
            SortedList<Sales> sortedData = new SortedList<>(filteredData);

            // 4. Bind the SortedList comparator to the TableView comparator.
            sortedData.comparatorProperty().bind(reportView.comparatorProperty());

            // 5. Add sorted (and filtered) data to the table.
            reportView.setItems(sortedData);

            int loss = 0;
            int gain = 0;

            for (Sales allsales : allSales) {
                gain += allsales.getProfit();
                loss += allsales.getLoss();
            }
            profitTxt.setText(gain + "");
            lossTxt.setText(loss + "");
        }

    }

    @FXML
    void generatePdfReport(ActionEvent event) {
//        try {
//            JasperReport compileReport = JasperCompileManager.compileReport("/reports/SalesReport.jrxml");
//            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(allSales);
//            HashMap<String, Object> map = new HashMap<>();
//            JasperPrint fillReport = JasperFillManager.fillReport(compileReport, map, dataSource);
//            
//            if(Objects.nonNull(fillReport)){
//                JasperViewer.viewReport(fillReport);
//            }
//        } catch (JRException ex) {
//            Logger.getLogger(GoodsReportController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

}
