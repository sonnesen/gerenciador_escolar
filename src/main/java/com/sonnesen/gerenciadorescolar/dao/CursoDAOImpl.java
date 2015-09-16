/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sonnesen.gerenciadorescolar.dao;

import com.sonnesen.gerenciadorescolar.model.Curso;
import com.sonnesen.gerenciadorescolar.util.JPAUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author winston.sonnesen
 */
public class CursoDAOImpl implements CursoDAO<Curso> {

    private final EntityManager entityManager;

    public CursoDAOImpl() {
        entityManager = JPAUtil.getEntityManager();
    }

    @Override
    public Curso save(Curso curso) {
        entityManager.getTransaction().begin();
        Curso merged = entityManager.merge(curso);
        entityManager.getTransaction().commit();
        entityManager.close();
        return merged;
    }

    @Override
    public void remove(Curso curso) {
        entityManager.getTransaction().begin();
        entityManager.remove(curso);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public Curso findByID(Integer codigo) {
        return entityManager.find(Curso.class, codigo);
    }

    @Override
    public List<Curso> findAll() {
        TypedQuery<Curso> query = entityManager.createQuery("from Curso", Curso.class);
        List<Curso> cursos = query.getResultList();
        return cursos;
    }

}
