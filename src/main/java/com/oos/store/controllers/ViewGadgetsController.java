/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oos.store.controllers;

import com.jfoenix.controls.JFXTextField;
import com.oos.store.entities.Goods;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Oluwaseun_Smart
 */
public class ViewGadgetsController implements Initializable {

    @FXML
    private TableView<Goods> gadgetViews;

    @FXML
    private TableColumn<Goods, String> nameCol;

    @FXML
    private TableColumn<Goods, String> modelCol;

    @FXML
    private TableColumn<Goods, Integer> quatityCol;

    @FXML
    private TableColumn<Goods, Double> priceCol;

    @FXML
    private TableColumn<Goods, Boolean> visibilityCol;

    @FXML
    private TableColumn<Goods, String> dateTimeCol;

    private ObservableList<Goods> listGoods;

    private Stage stage;

    @FXML
    private JFXTextField filtered;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        populateGoodsTable();
        getGoods();
    }

    public void populateGoodsTable() {
        listGoods = FXCollections.observableArrayList();
        List<Goods> allGoods = QueryManager.getAllGoods();

        listGoods.addAll(allGoods);

        nameCol.setCellValueFactory(new PropertyValueFactory("gadgetName"));
        modelCol.setCellValueFactory(new PropertyValueFactory("model"));
        quatityCol.setCellValueFactory(new PropertyValueFactory("quatity"));
        priceCol.setCellValueFactory(new PropertyValueFactory("price"));
        visibilityCol.setCellValueFactory(new PropertyValueFactory("showGoods"));
        dateTimeCol.setCellValueFactory(new PropertyValueFactory("curdate"));

        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Goods> filteredData = new FilteredList<>(listGoods, p -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        filtered.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(goods -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (goods.getGadgetName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (goods.getModel().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Goods> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(gadgetViews.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        gadgetViews.setItems(sortedData);

    }

    public void getGoods() {
        gadgetViews.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Goods> observable, Goods oldValue, Goods newValue) -> {
            showGoodsDeatails(newValue);
        });
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void showGoodsDeatails(Goods goods) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/EditAndDeleteGoods.fxml"));
            AnchorPane load = (AnchorPane) loader.load();

            Stage dialogSatage = new Stage();
            dialogSatage.setTitle("Edit Or Delete Goods");
            dialogSatage.initModality(Modality.WINDOW_MODAL);
            dialogSatage.initOwner(getStage());
            dialogSatage.setResizable(false);
            Scene scene = new Scene(load);
            dialogSatage.setScene(scene);

            EditAndDeleteGoodsController controller = (EditAndDeleteGoodsController) loader.getController();
            controller.setStage(dialogSatage);
            controller.setGoods(goods);

            dialogSatage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(ViewGadgetsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
