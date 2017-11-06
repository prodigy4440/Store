/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oos.store.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Objects;

/**
 *
 * @author Oluwaseun_Smart
 */
public class DBManager {

    private static EntityManager em = null;
    private static EntityManagerFactory emf = null;


    public static EntityManager entityManager() {
        if(Objects.isNull(em)){
            emf = Persistence.createEntityManagerFactory("StorePU");
            em = emf.createEntityManager();
        }
        return em;
    }

    public static void closeDB() {
        em.close();
        emf.close();
    }
    
    

}
