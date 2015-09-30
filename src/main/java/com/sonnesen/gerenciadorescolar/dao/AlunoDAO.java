/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sonnesen.gerenciadorescolar.dao;

import com.sonnesen.gerenciadorescolar.exception.BusinessException;
import com.sonnesen.gerenciadorescolar.entity.Aluno;
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
public class AlunoDAO implements IDAO<Aluno> {

    private final EntityManager entityManager;

    public AlunoDAO() {
        entityManager = JPAUtil.getEntityManager();
    }

    @Override
    public Aluno save(Aluno aluno) throws BusinessException {
        Aluno merged = null;
        try {
            entityManager.getTransaction().begin();
            merged = entityManager.merge(aluno);
            entityManager.getTransaction().commit();
        } catch (PersistenceException ex) {
            try {
                entityManager.getTransaction().rollback();
            } catch (PersistenceException pex) {
                Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, pex);
            }
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, "Erro ao salvar aluno: " + aluno, ex);
            throw new BusinessException("Erro ao salvar aluno: " + aluno, ex);
        }
        JPAUtil.closeEntityManager(entityManager);
        return merged;
    }

    @Override
    public void remove(Aluno aluno) throws BusinessException {
        try {
            entityManager.getTransaction().begin();
            Aluno u = entityManager.merge(aluno);
            entityManager.remove(u);
            entityManager.getTransaction().commit();
        } catch (PersistenceException ex) {
            try {
                entityManager.getTransaction().rollback();
            } catch (PersistenceException pex) {
                Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, pex);
            }
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new BusinessException("Erro ao remover aluno: " + aluno, ex);
        }
        JPAUtil.closeEntityManager(entityManager);
    }

    @Override
    public Aluno findByID(Long codigo) {
        Aluno aluno = null;
        try {
            entityManager.find(Aluno.class, codigo);
        } catch (PersistenceException ex) {
            Logger.getLogger(Aluno.class.getName()).log(Level.SEVERE, null, ex);
        }
        JPAUtil.closeEntityManager(entityManager);
        return aluno;
    }

    @Override
    public List<Aluno> findAll() {
        List<Aluno> alunos = null;
        try {
            TypedQuery<Aluno> query = entityManager.createNamedQuery("Aluno.findAll", Aluno.class);
            alunos = query.getResultList();
        } catch (PersistenceException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        JPAUtil.closeEntityManager(entityManager);
        return alunos;
    }

}
