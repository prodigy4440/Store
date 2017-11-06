package com.oos.store.controllers;

import com.oos.store.entities.Cashier;
import com.oos.store.entities.DateChecker;
import com.oos.store.entities.MonAndYeaChecker;
import com.oos.store.entities.MonthChecker;
import com.oos.store.entities.YearChecker;
import com.oos.store.utils.CashierUtils;
import com.oos.store.utils.DBManager;
import com.oos.store.utils.DateUtil;
import com.oos.store.utils.ImageUtil;
import com.oos.store.utils.QueryManager;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

/**
 * FXML Controller class
 *
 * @author Oluwaseun_Smart
 */
public class CashierController implements Initializable {
    
    @FXML
    private BorderPane borderPane;
    
    private Stage statge;
    
    private Cashier cashier;
    
    @FXML
    private ImageView passport;
    
    @FXML
    private Label welcome;
    
    private Stage loginSatge;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Sales.fxml"));
            Node node = (AnchorPane) loader.load();
            borderPane.setCenter(node);
        } catch (IOException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @FXML
    void handleNew(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Sales.fxml"));
            Node node = (AnchorPane) loader.load();
            borderPane.setCenter(node);
        } catch (IOException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    void handleAccount(ActionEvent event) {
        try {
            Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/fxml/CashierChangePassword.fxml"));
            borderPane.setCenter(node);
        } catch (IOException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    void handleSalesReport(ActionEvent event) {
        try {
            Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("/fxml/CashierSalesReport.fxml"));
            borderPane.setCenter(node);
        } catch (IOException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public BorderPane getBorderPane() {
        return borderPane;
    }
    
    public void setBorderPane(BorderPane borderPane) {
        this.borderPane = borderPane;
    }
    
    public Stage getStatge() {
        return statge;
    }
    
    public void setStatge(Stage statge) {
        this.statge = statge;
    }
    
    public void setCashierDeatils(Cashier cashier) {
        if (Objects.nonNull(cashier)) {
            BufferedImage byteArrayToBufferedImage = ImageUtil.byteArrayToBufferedImage(cashier.getPassport());
            welcome.setText("(ACTIVE) : " + cashier.getFullName());
            WritableImage toFXImage = SwingFXUtils.toFXImage(byteArrayToBufferedImage, null);
            passport.setImage(toFXImage);
        }
    }
    
    @FXML
    void checkOutGadget(ActionEvent event) {
        
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
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Sales.fxml"));
                Node node = (AnchorPane) loader.load();
                borderPane.setCenter(node);
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
            controller.setStage(getLoginSatge());
            Scene scene = new Scene(load);
            getLoginSatge().setTitle("Login");
            getLoginSatge().setResizable(false);
            getLoginSatge().setScene(scene);
            getLoginSatge().show();
            getStatge().close();
        } catch (IOException ex) {
            Logger.getLogger(CashierController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Stage getLoginSatge() {
        return loginSatge;
    }
    
    public void setLoginSatge(Stage loginSatge) {
        this.loginSatge = loginSatge;
    }
    
}
