package com.oos.store.controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.oos.store.entities.Goods;
import com.oos.store.entities.Sales;
import com.oos.store.utils.CashierUtils;
import com.oos.store.utils.DateUtil;
import com.oos.store.utils.QueryManager;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;

public class SalesController implements Initializable {

    @FXML
    private JFXComboBox<Goods> gadgetCombo;

    private ObservableList<Goods> listGadgets;

    private ObservableList<Sales> listSales;

    private DecimalFormat df;

    @FXML
    private JFXTextField priceSold;

    @FXML
    private JFXTextField customerName;

    @FXML
    private JFXTextField imeiNumber;

    @FXML
    private JFXTextField customerAddress;

    @FXML
    private JFXTextField price;

    @FXML
    private TableView<Sales> salesView;

    @FXML
    private TableColumn<Sales, String> gadgetNameCol;

    @FXML
    private TableColumn<Sales, String> gadgetModelCol;

    @FXML
    private TableColumn<Sales, String> ImeiNumberCol;

    @FXML
    private TableColumn<Sales, Double> amountCol;

    @FXML
    private TableColumn<Sales, String> curCol;

    private Goods goods;

    private List<Goods> listGoodsMock;

    private List<Sales> listSalesMock;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listSales = FXCollections.observableArrayList();

        df = new DecimalFormat("0.00");

        listGoodsMock = new LinkedList<>();

        listSalesMock = new LinkedList<>();

        renderComboBox();

    }

    public void renderComboBox() {

        List<Goods> allWithVisibleAndHighStock = QueryManager.getAllWithVisibleAndHighStock();

        listGadgets = FXCollections.observableArrayList();

        listGadgets.addAll(allWithVisibleAndHighStock);

        gadgetCombo.setItems(listGadgets);

// Define rendering of the list of values in ComboBox drop down. 
        gadgetCombo.setCellFactory((comboBox) -> {
            return new ListCell<Goods>() {
                @Override
                protected void updateItem(Goods goods, boolean empty) {
                    super.updateItem(goods, empty);

                    if (goods == null || empty) {
                        setText(null);
                    } else {
                        setText(goods.getGadgetName() + " " + goods.getModel());
                    }
                }
            };
        });

// Define rendering of selected value shown in ComboBox.
        gadgetCombo.setConverter(new StringConverter<Goods>() {
            @Override
            public String toString(Goods goods) {
                if (goods == null) {
                    return null;
                } else {
                    return goods.getGadgetName() + " - " + goods.getModel();
                }
            }

            @Override
            public Goods fromString(String personString) {
                return null; // No conversion fromString needed.
            }
        });

    }

    @FXML
    void handleGadgetSelection(ActionEvent event) {
        goods = gadgetCombo.getSelectionModel().getSelectedItem();
        setGadget(goods);
    }

    public void setGadget(Goods goods) {
        if (Objects.nonNull(goods)) {
            price.setText(df.format(goods.getPrice()));
        } else {
            price.setText("");
        }
    }

    @FXML
    void handleBuy(ActionEvent event) {

        String currentDateTime = DateUtil.getCurrentDateTime();
        String currentMonOfSell = DateUtil.getCurrentMonOfSell();
        String currentYearOfSell = DateUtil.getCurrentYearOfSell();
        String currentYearAndMonOfSell = DateUtil.getCurrentYearAndMonOfSell();
        String dateOfSells = DateUtil.getCurrentDate();


        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation ");
        alert.setHeaderText("Are You Sure");
        alert.setContentText("Are You sure, you want to submit?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {

            if (validateGoodsInput()) {

                double gainOrLossValue = Double.parseDouble(priceSold.getText()) - goods.getPrice();

                if (gainOrLossValue < 0) {
                    Alert alert3 = new Alert(Alert.AlertType.WARNING);
                    alert3.setTitle("Alert!!! ");
                    alert3.setHeaderText("Check carefully");
                    alert3.setContentText("Please check, the selling price is too low!!!");
                    alert3.showAndWait();

                    Sales sales = new Sales();

                    sales.setProfit(0);
                    sales.setLoss(gainOrLossValue);

                    sales.setCur(currentDateTime);
                    sales.setMonAndYea(currentYearAndMonOfSell);
                    sales.setMonOfSell(currentMonOfSell);
                    sales.setYeaOfSells(currentYearOfSell);
                    sales.setDateOfSells(dateOfSells);
                    sales.setCustomerAddress(customerAddress.getText());
                    sales.setCustomerName(customerName.getText());
                    sales.setGadgetImei(imeiNumber.getText());
                    sales.setGadgetName(goods.getGadgetName());
                    sales.setGadgetId(goods.getId());
                    sales.setModel(goods.getModel());
                    sales.setPrice(goods.getPrice());
                    sales.setPriceSold(Double.parseDouble(priceSold.getText()));
                    sales.setSeller(CashierUtils.cashier.getUsername());

                    listSalesMock.add(sales);

                    listGoodsMock.add(goods);

                    CashierUtils.listGoods = listGoodsMock;

                    CashierUtils.listSales = listSalesMock;

                    renderComboBox();

                    addGadgetToCart(sales);

                    Alert alert4 = new Alert(Alert.AlertType.INFORMATION);
                    alert4.setTitle("Successfull ");
                    alert4.setHeaderText("Record successfully added");
                    alert4.setContentText("Done!!!...");
                    alert4.showAndWait();

                } else {

                    Sales sales = new Sales();

                    sales.setProfit(gainOrLossValue);
                    sales.setLoss(0);

                    sales.setCur(currentDateTime);
                    sales.setMonOfSell(currentMonOfSell);
                    sales.setYeaOfSells(currentYearOfSell);
                    sales.setMonAndYea(currentYearAndMonOfSell);
                    sales.setDateOfSells(dateOfSells);
                    sales.setCustomerAddress(customerAddress.getText());
                    sales.setCustomerName(customerName.getText());
                    sales.setGadgetImei(imeiNumber.getText());
                    sales.setGadgetName(goods.getGadgetName());
                    sales.setGadgetId(goods.getId());
                    sales.setModel(goods.getModel());
                    sales.setPrice(goods.getPrice());
                    sales.setPriceSold(Double.parseDouble(priceSold.getText()));
                    sales.setSeller(CashierUtils.cashier.getUsername());

                    listSalesMock.add(sales);

                    listGoodsMock.add(goods);

                    CashierUtils.listGoods = listGoodsMock;

                    CashierUtils.listSales = listSalesMock;

                    renderComboBox();

                    addGadgetToCart(sales);

                    Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
                    alert3.setTitle("Successfull ");
                    alert3.setHeaderText("Record successfully added");
                    alert3.setContentText("Done!!!...");
                    alert3.showAndWait();

                }
            }

        } else {
            Alert alert3 = new Alert(Alert.AlertType.WARNING);
            alert3.setTitle("Abort ");
            alert3.setHeaderText("Look like something went wrong");
            alert3.setContentText("The operation has been cancel by user");
            alert3.showAndWait();
        }

    }

    @FXML
    void handleClear(ActionEvent event) {
        goods = null;
        gadgetCombo.getSelectionModel().clearSelection();
        listSales.clear();

        listGadgets.clear();

        listGoodsMock.clear();
        listSalesMock.clear();
        price.setText("");
        priceSold.setText("");
        customerAddress.setText("");
        customerName.setText("");
        imeiNumber.setText("");
    }

    public void addGadgetToCart(Sales sales) {
        listSales.add(sales);

        gadgetNameCol.setCellValueFactory(new PropertyValueFactory("gadgetName"));
        gadgetModelCol.setCellValueFactory(new PropertyValueFactory("model"));
        ImeiNumberCol.setCellValueFactory(new PropertyValueFactory("gadgetImei"));
        amountCol.setCellValueFactory(new PropertyValueFactory("priceSold"));
        curCol.setCellValueFactory(new PropertyValueFactory("cur"));

        salesView.setItems(listSales);

    }

    public boolean validateGoodsInput() {
        String errorMessage = "";
        if (Objects.isNull(goods)) {
            errorMessage += "You must Select a gadget first!\n";
        }
        if (priceSold.getText() == null || priceSold.getText().length() == 0) {
            errorMessage += "Specify the selling Price!\n";
        } else {
            try {
                Double.parseDouble(priceSold.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid Price(Must be a Number)!\n";
            }
        }

        if (customerName.getText() == null || customerName.getText().length() == 0) {
            errorMessage += "No valid Customer name!\n";
        }

        if (customerAddress.getText() == null || customerAddress.getText().length() == 0) {
            errorMessage += "No valid Customer Address!\n";
        }
        if (imeiNumber.getText() == null || imeiNumber.getText().length() == 0) {
            errorMessage += "No valid Gadget Imei Number!\n";
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
