/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oos.store.utils;

import com.oos.store.entities.Cashier;
import com.oos.store.entities.DateChecker;
import com.oos.store.entities.Goods;
import com.oos.store.entities.MonAndYeaChecker;
import com.oos.store.entities.MonthChecker;
import com.oos.store.entities.Sales;
import com.oos.store.entities.YearChecker;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Oluwaseun_Smart
 */
public class QueryManager {

    public static List<Goods> getAllGoods() {
        EntityManager entityManager = DBManager.entityManager();
//        entityManager.getTransaction().begin();
        TypedQuery<Goods> query = entityManager.createQuery("SELECT g FROM Goods g", Goods.class);
        List<Goods> resultList = query.getResultList();
//        entityManager.getTransaction().commit();
        return resultList;
    }

    public static List<Goods> getAllWithVisibleAndHighStock() {
        EntityManager entityManager = DBManager.entityManager();
//        entityManager.getTransaction().begin();
        TypedQuery<Goods> query = entityManager.createQuery("SELECT g FROM Goods g WHERE g.showGoods = true AND g.quatity > 0", Goods.class);
        List<Goods> resultList = query.getResultList();
//        entityManager.getTransaction().commit();
        return resultList;
    }

    public static void updateGoods(String gadgetName, String model, String desc, double price, int quantity, boolean visibility, long id) {
        EntityManager entityManager = DBManager.entityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("UPDATE Goods g SET g.gadgetName =:gadget, g.model =:model, g.description =:desc,g.price =:price, g.quatity =:quantity,g.showGoods =:visibility WHERE g.id =:id");
        query.setParameter("gadget", gadgetName);
        query.setParameter("model", model);
        query.setParameter("desc", desc);
        query.setParameter("price", price);
        query.setParameter("quantity", quantity);
        query.setParameter("visibility", visibility);
        query.setParameter("id", id);

        int executeUpdate = query.executeUpdate();
        entityManager.getTransaction().commit();
    }

    public static void updateGoodsQuantity(int quantity, long id) {
        EntityManager entityManager = DBManager.entityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("UPDATE Goods g SET g.quatity =:quantity WHERE g.id =:id AND g.quatity > 0");
        query.setParameter("quantity", quantity);
        query.setParameter("id", id);
        int executeUpdate = query.executeUpdate();
        entityManager.getTransaction().commit();
    }

    public static void updateCashierPassword(String username, String password) {
        EntityManager entityManager = DBManager.entityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("UPDATE Cashier c SET c.password =:password WHERE c.username =:username");
        query.setParameter("username", username);
        query.setParameter("password", password);
        int executeUpdate = query.executeUpdate();
        entityManager.getTransaction().commit();
    }

    public static void delectGoods(long id) {
        EntityManager entityManager = DBManager.entityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("DELETE FROM Goods g WHERE g.id =:id");
        query.setParameter("id", id);
        int row = query.executeUpdate();
        entityManager.getTransaction().commit();
    }

    public static void sackCashier(long id) {
        EntityManager entityManager = DBManager.entityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("DELETE FROM Cashier c WHERE c.id =:id");
        query.setParameter("id", id);
        int row = query.executeUpdate();
        entityManager.getTransaction().commit();
    }

    public static List<Cashier> getAllCashier() {
        EntityManager entityManager = DBManager.entityManager();
//        entityManager.getTransaction().begin();
        TypedQuery<Cashier> query = entityManager.createQuery("SELECT c FROM Cashier c", Cashier.class);
        List<Cashier> resultList = query.getResultList();
//        entityManager.getTransaction().commit();
        return resultList;
    }

    public static List<Sales> getAllCashierSales(String username) {
        EntityManager entityManager = DBManager.entityManager();
//        entityManager.getTransaction().begin();
        TypedQuery<Sales> query = entityManager.createQuery("SELECT s FROM Sales s WHERE s.seller =:username", Sales.class);
        query.setParameter("username", username);
        List<Sales> resultList = query.getResultList();
//        entityManager.getTransaction().commit();
        return resultList;
    }

    public static List<Sales> getAllSales(String selectedItem) {
        EntityManager entityManager = DBManager.entityManager();
//        entityManager.getTransaction().begin();
        TypedQuery<Sales> query = entityManager.createQuery("SELECT s FROM Sales s WHERE s.seller =:item OR s.monAndYea =:item OR s.monOfSell =:item OR s.yeaOfSells =:item OR s.dateOfSells =:item", Sales.class);
        query.setParameter("item", selectedItem);
        List<Sales> resultList = query.getResultList();
//        entityManager.getTransaction().commit();
        return resultList;
    }

    public static Cashier validateCashierUsernameAndPassword(String username, String password) {
        EntityManager entityManager = DBManager.entityManager();
//        entityManager.getTransaction().begin();
        TypedQuery<Cashier> query = entityManager.createQuery("SELECT c FROM Cashier c WHERE c.username =:username AND c.password =:password", Cashier.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        Cashier singleResult = query.getSingleResult();
//        entityManager.getTransaction().commit();
        return singleResult;
    }

    public static Cashier validateUsername(String username) {
        EntityManager entityManager = DBManager.entityManager();
//        entityManager.getTransaction().begin();
        TypedQuery<Cashier> query = entityManager.createQuery("SELECT c FROM Cashier c WHERE c.username =:username", Cashier.class);
        query.setParameter("username", username);
        Cashier singleResult = query.getSingleResult();
//        entityManager.getTransaction().commit();
        return singleResult;
    }

    public static List<YearChecker> checkYear(String year) {
        EntityManager entityManager = DBManager.entityManager();
//        entityManager.getTransaction().begin();
        TypedQuery<YearChecker> query = entityManager.createQuery("SELECT y FROM YearChecker y WHERE y.yeaOfSell =:yea ", YearChecker.class);
        query.setParameter("yea", year);
        List<YearChecker> resultList = query.getResultList();
//        entityManager.getTransaction().commit();
        return resultList;
    }

    public static List<MonthChecker> checkMonth(String month) {
        EntityManager entityManager = DBManager.entityManager();
//        entityManager.getTransaction().begin();
        TypedQuery<MonthChecker> query = entityManager.createQuery("SELECT m FROM MonthChecker m WHERE m.monOfSell =:mon ", MonthChecker.class);
        query.setParameter("mon", month);
        List<MonthChecker> resultList = query.getResultList();
//        entityManager.getTransaction().commit();
        return resultList;
    }

    public static List<DateChecker> checkDateOfSell(String dat) {
        EntityManager entityManager = DBManager.entityManager();
//        entityManager.getTransaction().begin();
        TypedQuery<DateChecker> query = entityManager.createQuery("SELECT d FROM DateChecker d WHERE d.dateOfSell =:dat ", DateChecker.class);
        query.setParameter("dat", dat);
        List<DateChecker> resultList = query.getResultList();
//        entityManager.getTransaction().commit();
        return resultList;
    }

    public static List<MonAndYeaChecker> checkMonAndYear(String yeaAndMon) {
        EntityManager entityManager = DBManager.entityManager();
//        entityManager.getTransaction().begin();
        TypedQuery<MonAndYeaChecker> query = entityManager.createQuery("SELECT m FROM MonAndYeaChecker m WHERE m.monAndYeaOfSell =:yea ", MonAndYeaChecker.class);
        query.setParameter("yea", yeaAndMon);
        List<MonAndYeaChecker> resultList = query.getResultList();
//        entityManager.getTransaction().commit();
        return resultList;
    }

    public static List<YearChecker> listAllYears() {
        EntityManager entityManager = DBManager.entityManager();
//        entityManager.getTransaction().begin();
        TypedQuery<YearChecker> query = entityManager.createQuery("SELECT y FROM YearChecker y ", YearChecker.class);
        List<YearChecker> resultList = query.getResultList();
//        entityManager.getTransaction().commit();
        return resultList;
    }

    public static List<MonthChecker> listAllMonth() {
        EntityManager entityManager = DBManager.entityManager();
//        entityManager.getTransaction().begin();
        TypedQuery<MonthChecker> query = entityManager.createQuery("SELECT m FROM MonthChecker m", MonthChecker.class);
        List<MonthChecker> resultList = query.getResultList();
//        entityManager.getTransaction().commit();
        return resultList;
    }

    public static List<DateChecker> listAllDateOfSell() {
        EntityManager entityManager = DBManager.entityManager();
//        entityManager.getTransaction().begin();
        TypedQuery<DateChecker> query = entityManager.createQuery("SELECT d FROM DateChecker d", DateChecker.class);
        List<DateChecker> resultList = query.getResultList();
//        entityManager.getTransaction().commit();
        return resultList;
    }

    public static List<MonAndYeaChecker> listAllMonAndYear() {
        EntityManager entityManager = DBManager.entityManager();
//        entityManager.getTransaction().begin();
        TypedQuery<MonAndYeaChecker> query = entityManager.createQuery("SELECT m FROM MonAndYeaChecker m", MonAndYeaChecker.class);
        List<MonAndYeaChecker> resultList = query.getResultList();
//        entityManager.getTransaction().commit();
        return resultList;
    }

}
