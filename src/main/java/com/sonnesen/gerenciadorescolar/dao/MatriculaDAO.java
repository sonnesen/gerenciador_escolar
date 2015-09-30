/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sonnesen.gerenciadorescolar.dao;

import com.sonnesen.gerenciadorescolar.exception.BusinessException;
import com.sonnesen.gerenciadorescolar.entity.Matricula;
import com.sonnesen.gerenciadorescolar.util.JPAUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

/**
 *
 * @author winston.sonnesen
 */
public class MatriculaDAO implements IDAO<Matricula> {

    private final EntityManager entityManager;

    public MatriculaDAO() {
        entityManager = JPAUtil.getEntityManager();
    }

    @Override
    public Matricula save(Matricula matricula) throws BusinessException {
        Matricula merged = null;
        try {
            entityManager.getTransaction().begin();
            merged = entityManager.merge(matricula);
            entityManager.getTransaction().commit();
        } catch (PersistenceException ex) {
            try {
                entityManager.getTransaction().rollback();
            } catch (PersistenceException pex) {
                Logger.getLogger(MatriculaDAO.class.getName()).log(Level.SEVERE, null, pex);
            }
            Logger.getLogger(MatriculaDAO.class.getName()).log(Level.SEVERE, "Erro ao salvar matricula: " + matricula, ex);
            throw new BusinessException("Erro ao salvar matricula: " + matricula, ex);
        }
        JPAUtil.closeEntityManager(entityManager);
        return merged;
    }

    @Override
    public void remove(Matricula matricula) throws BusinessException {
        try {
            entityManager.getTransaction().begin();
            Matricula u = entityManager.merge(matricula);
            entityManager.remove(u);
            entityManager.getTransaction().commit();
        } catch (PersistenceException ex) {
            try {
                entityManager.getTransaction().rollback();
            } catch (PersistenceException pex) {
                Logger.getLogger(MatriculaDAO.class.getName()).log(Level.SEVERE, null, pex);
            }
            Logger.getLogger(MatriculaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new BusinessException("Erro ao remover matricula: " + matricula, ex);
        }
        JPAUtil.closeEntityManager(entityManager);
    }

    @Override
    public Matricula findByID(Long codigo) {
        Matricula matricula = null;
        try {
            entityManager.find(Matricula.class, codigo);
        } catch (PersistenceException ex) {
            Logger.getLogger(Matricula.class.getName()).log(Level.SEVERE, null, ex);
        }
        JPAUtil.closeEntityManager(entityManager);
        return matricula;
    }

    @Override
    public List<Matricula> findAll() {
        List<Matricula> matriculas = null;
        try {
            TypedQuery<Matricula> query = entityManager.createNamedQuery("Matricula.findAll", Matricula.class);
            matriculas = query.getResultList();
        } catch (PersistenceException ex) {
            Logger.getLogger(MatriculaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        JPAUtil.closeEntityManager(entityManager);
        return matriculas;
    }

}
