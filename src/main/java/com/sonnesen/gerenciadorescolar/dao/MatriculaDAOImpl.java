/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sonnesen.gerenciadorescolar.dao;

import com.sonnesen.gerenciadorescolar.model.Matricula;
import com.sonnesen.gerenciadorescolar.util.JPAUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author winston.sonnesen
 */
public class MatriculaDAOImpl implements MatriculaDAO<Matricula> {

    private final EntityManager entityManager;

    public MatriculaDAOImpl() {
        entityManager = JPAUtil.getEntityManager();
    }

    @Override
    public Matricula save(Matricula matricula) {
        entityManager.getTransaction().begin();
        Matricula merged = entityManager.merge(matricula);
        entityManager.getTransaction().commit();
        entityManager.close();
        return merged;
    }

    @Override
    public void remove(Matricula matricula) {
        entityManager.getTransaction().begin();
        entityManager.remove(matricula);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public Matricula findByID(Integer codigo) {
        return entityManager.find(Matricula.class, codigo);
    }

    @Override
    public List<Matricula> findAll() {
        TypedQuery<Matricula> query = entityManager.createQuery("from Matricula", Matricula.class);
        List<Matricula> matriculas = query.getResultList();
        return matriculas;
    }

}
