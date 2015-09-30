/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sonnesen.gerenciadorescolar.dao;

import com.sonnesen.gerenciadorescolar.exception.BusinessException;
import com.sonnesen.gerenciadorescolar.entity.Curso;
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
public class CursoDAO implements IDAO<Curso> {

    private final EntityManager entityManager;

    public CursoDAO() {
        entityManager = JPAUtil.getEntityManager();
    }

    @Override
    public Curso save(Curso curso) throws BusinessException {
        Curso merged = null;
        try {
            entityManager.getTransaction().begin();
            merged = entityManager.merge(curso);
            entityManager.getTransaction().commit();
        } catch (PersistenceException ex) {
            try {
                entityManager.getTransaction().rollback();
            } catch (PersistenceException pex) {
                Logger.getLogger(CursoDAO.class.getName()).log(Level.SEVERE, null, pex);
            }
            Logger.getLogger(CursoDAO.class.getName()).log(Level.SEVERE, "Erro ao salvar curso: " + curso, ex);
            throw new BusinessException("Erro ao salvar curso: " + curso, ex);
        }
        JPAUtil.closeEntityManager(entityManager);
        return merged;
    }

    @Override
    public void remove(Curso curso) throws BusinessException {
        try {
            entityManager.getTransaction().begin();
            Curso u = entityManager.merge(curso);
            entityManager.remove(u);
            entityManager.getTransaction().commit();
        } catch (PersistenceException ex) {
            try {
                entityManager.getTransaction().rollback();
            } catch (PersistenceException pex) {
                Logger.getLogger(CursoDAO.class.getName()).log(Level.SEVERE, null, pex);
            }
            Logger.getLogger(CursoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new BusinessException("Erro ao remover curso: " + curso, ex);
        }
        JPAUtil.closeEntityManager(entityManager);
    }

    @Override
    public Curso findByID(Long codigo) {
        Curso curso = null;
        try {
            entityManager.find(Curso.class, codigo);
        } catch (PersistenceException ex) {
            Logger.getLogger(Curso.class.getName()).log(Level.SEVERE, null, ex);
        }
        JPAUtil.closeEntityManager(entityManager);
        return curso;
    }

    @Override
    public List<Curso> findAll() {
        List<Curso> cursos = null;
        try {
            TypedQuery<Curso> query = entityManager.createNamedQuery("Curso.findAll", Curso.class);
            cursos = query.getResultList();
        } catch (PersistenceException ex) {
            Logger.getLogger(CursoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        JPAUtil.closeEntityManager(entityManager);
        return cursos;
    }

}
