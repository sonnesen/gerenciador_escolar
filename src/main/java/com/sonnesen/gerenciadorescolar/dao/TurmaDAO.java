/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sonnesen.gerenciadorescolar.dao;

import com.sonnesen.gerenciadorescolar.exception.BusinessException;
import com.sonnesen.gerenciadorescolar.entity.Turma;
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
public class TurmaDAO implements IDAO<Turma> {

    private final EntityManager entityManager;

    public TurmaDAO() {
        entityManager = JPAUtil.getEntityManager();
    }

    @Override
    public Turma save(Turma turma) throws BusinessException {
        Turma merged = null;
        try {
            entityManager.getTransaction().begin();
            merged = entityManager.merge(turma);
            entityManager.getTransaction().commit();
        } catch (PersistenceException ex) {
            try {
                entityManager.getTransaction().rollback();
            } catch (PersistenceException pex) {
                Logger.getLogger(TurmaDAO.class.getName()).log(Level.SEVERE, null, pex);
            }
            Logger.getLogger(TurmaDAO.class.getName()).log(Level.SEVERE, "Erro ao salvar turma: " + turma, ex);
            throw new BusinessException("Erro ao salvar turma: " + turma, ex);
        }
        JPAUtil.closeEntityManager(entityManager);
        return merged;
    }

    @Override
    public void remove(Turma turma) throws BusinessException {
        try {
            entityManager.getTransaction().begin();
            Turma u = entityManager.merge(turma);
            entityManager.remove(u);
            entityManager.getTransaction().commit();
        } catch (PersistenceException ex) {
            try {
                entityManager.getTransaction().rollback();
            } catch (PersistenceException pex) {
                Logger.getLogger(TurmaDAO.class.getName()).log(Level.SEVERE, null, pex);
            }
            Logger.getLogger(TurmaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new BusinessException("Erro ao remover turma: " + turma, ex);
        }
        JPAUtil.closeEntityManager(entityManager);
    }

    @Override
    public Turma findByID(Long codigo) {
        Turma turma = null;
        try {
            entityManager.find(Turma.class, codigo);
        } catch (PersistenceException ex) {
            Logger.getLogger(Turma.class.getName()).log(Level.SEVERE, null, ex);
        }
        JPAUtil.closeEntityManager(entityManager);
        return turma;
    }

    @Override
    public List<Turma> findAll() {
        List<Turma> turmas = null;
        try {
            TypedQuery<Turma> query = entityManager.createNamedQuery("Turma.findAll", Turma.class);
            turmas = query.getResultList();
        } catch (PersistenceException ex) {
            Logger.getLogger(TurmaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        JPAUtil.closeEntityManager(entityManager);
        return turmas;
    }

}
