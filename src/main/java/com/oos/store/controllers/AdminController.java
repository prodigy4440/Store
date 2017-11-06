package com.oos.store.controllers;

import com.oos.store.entities.DateChecker;
import com.oos.store.entities.MonAndYeaChecker;
import com.oos.store.entities.MonthChecker;
import com.oos.store.entities.YearChecker;
import com.oos.store.utils.CashierUtils;
import com.oos.store.utils.DBManager;
import com.oos.store.utils.DateUtil;
import com.oos.store.utils.QueryManager;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

public class AdminController implements Initializable {

    @FXML
    private ListView<String> salesList;

    @FXML
    private TabPane tabHolder;

    @FXML
    private ListView<String> goodsList;

    @FXML
    private ListView<String> cashiersList;

    @FXML
    private ListView<String> reportList;

    @FXML
    private ListView<String> listAccountSettings;

    private Stage stage;

    private Stage loginStage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        populateSalesList();
        populateGoodsList();
        populateCashierList();
        populateReportList();
        populateAccountList();
        getSelectedSalesMenu();
        getSelectedGoodsMenu();
        getSelectedCashierMenu();
        getSelectedReportMenu();
        getSelectedAccounntOption();

    }

    private void populateSalesList() {
        ObservableList<String> salesListData = FXCollections.observableArrayList();
        salesListData.add("Sell Gadget Now");
        salesListData.add("View My Sales");
        salesList.setItems(salesListData);
    }

    private void populateGoodsList() {
        ObservableList<String> goodsListData = FXCollections.observableArrayList();
        goodsListData.add("Add Goods");
        goodsListData.add("Add Price");
        goodsListData.add("View Gadget");
        goodsList.setItems(goodsListData);
    }

    private void populateCashierList() {
        ObservableList<String> cashierListData = FXCollections.observableArrayList();
        cashierListData.add("Add Cashier");
        cashierListData.add("View Cashier");
        cashiersList.setItems(cashierListData);
    }

    private void populateReportList() {
        ObservableList<String> reportListData = FXCollections.observableArrayList();
        reportListData.add("View Report");
        reportList.setItems(reportListData);
    }

    private void populateAccountList() {
        ObservableList<String> accountData = FXCollections.observableArrayList();
        accountData.add("Change::Password");
        listAccountSettings.setItems(accountData);
    }

    private void getSelectedSalesMenu() {
        salesList.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            int selectedIndex = salesList.getSelectionModel().getSelectedIndex();
            if (selectedIndex == 0) {
                try {
                    Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/fxml/Sales.fxml"));
                    Tab tab = new Tab("Sell");
                    tab.setContent(node);
                    tabHolder.getTabs().add(tab);
                    tabHolder.getSelectionModel().select(tab);
                } catch (IOException ex) {
                    Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (selectedIndex == 1) {
                try {
                    Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/fxml/CashierSalesReport.fxml"));
                    Tab tab = new Tab("Sales Report");
                    tab.setContent(node);
                    tabHolder.getTabs().add(tab);
                    tabHolder.getSelectionModel().select(tab);
                } catch (IOException ex) {
                    Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }

    public void getSelectedGoodsMenu() {
        goodsList.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            int selectedIndex = goodsList.getSelectionModel().getSelectedIndex();
            if (selectedIndex == 0) {
                try {
                    Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/fxml/Goods.fxml"));
                    Tab tab = new Tab("Add::Gadget");
                    tab.setContent(node);
                    tabHolder.getTabs().add(tab);
                    tabHolder.getSelectionModel().select(tab);
                } catch (IOException ex) {
                    Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (selectedIndex == 2) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ViewGadgets.fxml"));
                    Node node = (AnchorPane) loader.load();
                    ViewGadgetsController controller = (ViewGadgetsController) loader.getController();
                    controller.setStage(getStage());
                    Tab tab = new Tab("View::Gadgets");
                    tab.setContent(node);
                    tabHolder.getTabs().add(tab);
                    tabHolder.getSelectionModel().select(tab);
                } catch (IOException ex) {
                    Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public void getSelectedCashierMenu() {
        cashiersList.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            int selectedIndex = cashiersList.getSelectionModel().getSelectedIndex();
            if (selectedIndex == 0) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AddCashier.fxml"));
                    AnchorPane node = (AnchorPane) loader.load();

                    AddCashierController controller = (AddCashierController) loader.getController();
                    controller.setStage(getStage());

                    Tab tab = new Tab("Add::Cashier");
                    tab.setContent(node);
                    tabHolder.getTabs().add(tab);
                    tabHolder.getSelectionModel().select(tab);
                } catch (IOException ex) {
                    Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (selectedIndex == 1) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ViewCashiers.fxml"));
                    AnchorPane node = (AnchorPane) loader.load();

                    ViewCashiersController controller = (ViewCashiersController) loader.getController();
                    controller.setStage(getStage());

                    Tab tab = new Tab("View::Cashiers");
                    tab.setContent(node);
                    tabHolder.getTabs().add(tab);
                    tabHolder.getSelectionModel().select(tab);
                } catch (IOException ex) {
                    Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public void getSelectedReportMenu() {
        reportList.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            int selectedIndex = reportList.getSelectionModel().getSelectedIndex();
            if (selectedIndex == 0) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/SpecifyReport.fxml"));
                    AnchorPane node = (AnchorPane) loader.load();

                    SpecifyReportController controller = (SpecifyReportController) loader.getController();
                    controller.setStage(getStage());

                    Tab tab = new Tab("Reporting...");
                    tab.setContent(node);
                    tabHolder.getTabs().add(tab);
                    tabHolder.getSelectionModel().select(tab);

                } catch (IOException ex) {
                    Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public void getSelectedAccounntOption() {
        listAccountSettings.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            int selectedIndex = listAccountSettings.getSelectionModel().getSelectedIndex();
            if (selectedIndex == 0) {

                try {
                    Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/fxml/CashierChangePassword.fxml"));
                    Tab tab = new Tab("Change::Password");
                    tab.setContent(node);
                    tabHolder.getTabs().add(tab);
                    tabHolder.getSelectionModel().select(tab);
                } catch (IOException ex) {
                    Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    void handleClose(ActionEvent event) {
        Platform.exit();
        System.exit(0);

    }

    @FXML
    void handleLogout(ActionEvent event) {
        CashierUtils.cashier = null;

        if (Objects.nonNull(CashierUtils.listGoods)) {
            CashierUtils.listGoods.clear();
        }
        if (Objects.nonNull(CashierUtils.listSales)) {
            CashierUtils.listSales.clear();
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GoogleLogin.fxml"));
            AnchorPane load = (AnchorPane) loader.load();
            GoogleLoginController controller = (GoogleLoginController) loader.getController();
            controller.setStage(getLoginStage());
            Scene scene = new Scene(load);
            getLoginStage().setTitle("Login");
            getLoginStage().setResizable(false);
            getLoginStage().setScene(scene);
            getLoginStage().show();
            getStage().close();
        } catch (IOException ex) {
            Logger.getLogger(CashierController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void handlePrintInvoice(ActionEvent event) {

        String currentSellDate = DateUtil.getCurrentDate();
        String currentMonOfSell = DateUtil.getCurrentMonOfSell();
        String currentYearOfSell = DateUtil.getCurrentYearOfSell();
        String currentYearAndMonOfSell = DateUtil.getCurrentYearAndMonOfSell();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation ");
        alert.setHeaderText("Are You Sure");
        alert.setContentText("Are You sure, you want to Purchase? Note there is no returnable gadget");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {

            try {
                List<MonAndYeaChecker> checkMonAndYear = QueryManager.checkMonAndYear(currentYearAndMonOfSell);
                if (checkMonAndYear.isEmpty()) {
                    MonAndYeaChecker checker = new MonAndYeaChecker();
                    checker.setMonAndYeaOfSell(currentYearAndMonOfSell);
                    EntityManager em = DBManager.entityManager();
                    em.getTransaction().begin();
                    em.persist(checker);
                    em.getTransaction().commit();
                }
            } catch (NoResultException e) {

            }

            try {
                List<YearChecker> checkYear = QueryManager.checkYear(currentYearOfSell);
                if (checkYear.isEmpty()) {
                    YearChecker yearChecker = new YearChecker();
                    yearChecker.setYeaOfSell(currentYearOfSell);
                    EntityManager em = DBManager.entityManager();
                    em.getTransaction().begin();
                    em.persist(yearChecker);
                    em.getTransaction().commit();
                }
            } catch (NoResultException e) {

            }

            try {
                List<MonthChecker> checkMonth = QueryManager.checkMonth(currentMonOfSell);
                if (checkMonth.isEmpty()) {
                    MonthChecker checker = new MonthChecker();
                    checker.setMonOfSell(currentMonOfSell);
                    EntityManager em = DBManager.entityManager();
                    em.getTransaction().begin();
                    em.persist(checker);
                    em.getTransaction().commit();
                }
            } catch (NoResultException e) {

            }

            try {
                List<DateChecker> checkDateOfSell = QueryManager.checkDateOfSell(currentSellDate);
                if (checkDateOfSell.isEmpty()) {
                    DateChecker checker = new DateChecker();
                    checker.setDateOfSell(currentSellDate);
                    EntityManager em = DBManager.entityManager();
                    em.getTransaction().begin();
                    em.persist(checker);
                    em.getTransaction().commit();
                }
            } catch (NoResultException e) {

            }

            EntityManager em = DBManager.entityManager();
            em.getTransaction().begin();
            CashierUtils.listSales.stream().forEach((col) -> {
                em.persist(col);
            });
            em.getTransaction().commit();

            CashierUtils.listGoods.stream().forEach((goods) -> {
                int quantity = goods.getQuatity() - 1;
                QueryManager.updateGoodsQuantity(quantity, goods.getId());
            });

            try {
                Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/fxml/Sales.fxml"));
                Tab tab = new Tab("Sell");
                tab.setContent(node);
                tabHolder.getTabs().add(tab);
                tabHolder.getSelectionModel().select(tab);
            } catch (IOException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
        alert3.setTitle("Successfull ");
        alert3.setHeaderText("Purchase Successfull");
        alert3.setContentText("Done!!!...");
        alert3.showAndWait();
    }

    public Stage getLoginStage() {
        return loginStage;
    }

    public void setLoginStage(Stage loginStage) {
        this.loginStage = loginStage;
    }

}
