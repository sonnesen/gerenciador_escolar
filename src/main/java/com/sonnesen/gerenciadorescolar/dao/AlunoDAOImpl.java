/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sonnesen.gerenciadorescolar.dao;

import com.sonnesen.gerenciadorescolar.model.Aluno;
import com.sonnesen.gerenciadorescolar.util.JPAUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author winston.sonnesen
 */
public class AlunoDAOImpl implements AlunoDAO<Aluno> {

    private final EntityManager entityManager;

    public AlunoDAOImpl() {
        entityManager = JPAUtil.getEntityManager();
    }

    @Override
    public Aluno save(Aluno aluno) {
        entityManager.getTransaction().begin();
        Aluno merged = entityManager.merge(aluno);
        entityManager.getTransaction().commit();
        entityManager.close();
        return merged;
    }

    @Override
    public void remove(Aluno aluno) {
        entityManager.getTransaction().begin();
        entityManager.remove(aluno);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public Aluno findByID(Integer codigo) {
        return entityManager.find(Aluno.class, codigo);
    }

    @Override
    public List<Aluno> findAll() {
        TypedQuery<Aluno> query = entityManager.createQuery("from Aluno", Aluno.class);
        List<Aluno> alunos = query.getResultList();
        return alunos;
    }

}
