/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sonnesen.gerenciadorescolar.util;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

/**
 *
 * @author winston.sonnesen
 */
public class JPAUtil {

    private static final String PERSISTENCE_UNIT_NAME = "GerenciadorEscolarPU";
    private static final EntityManagerFactory emf;

    static {
        emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    private JPAUtil() {

    }

    /**
     * Obtendo um gerenciador de entidades
     *
     * @return EntityManager
     */
    public static EntityManager getEntityManager() {
        EntityManager em = null;
        try {
            emf.createEntityManager();
        } catch (PersistenceException ex) {
            Logger.getLogger(JPAUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return em;
    }

    /**
     * Fechando o gerenciandor de entidades
     *
     * @param em Gerenciaodr de entidades
     */
    public static void closeEntityManager(EntityManager em) {
        try {
            em.close();
        } catch (PersistenceException ex) {
            Logger.getLogger(JPAUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Fechando as conexões com o banco de dados
     *
     */
    public static void closeEntityManagerFactory() {
        try {
            emf.close();
        } catch (PersistenceException ex) {
            Logger.getLogger(JPAUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
