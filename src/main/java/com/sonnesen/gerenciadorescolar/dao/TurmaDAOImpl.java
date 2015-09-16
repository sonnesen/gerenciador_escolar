/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sonnesen.gerenciadorescolar.dao;

import com.sonnesen.gerenciadorescolar.model.Turma;
import com.sonnesen.gerenciadorescolar.util.JPAUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author winston.sonnesen
 */
public class TurmaDAOImpl implements TurmaDAO<Turma> {

    private final EntityManager entityManager;

    public TurmaDAOImpl() {
        entityManager = JPAUtil.getEntityManager();
    }

    @Override
    public Turma save(Turma turma) {
        entityManager.getTransaction().begin();
        Turma merged = entityManager.merge(turma);
        entityManager.getTransaction().commit();
        entityManager.close();
        return merged;
    }

    @Override
    public void remove(Turma turma) {
        entityManager.getTransaction().begin();
        entityManager.remove(turma);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public Turma findByID(Integer codigo) {
        return entityManager.find(Turma.class, codigo);
    }

    @Override
    public List<Turma> findAll() {
        TypedQuery<Turma> query = entityManager.createQuery("from Turma", Turma.class);
        List<Turma> turmas = query.getResultList();
        return turmas;
    }

}
