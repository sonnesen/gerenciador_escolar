/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sonnesen.gerenciadorescolar.dao;

import com.sonnesen.gerenciadorescolar.model.Usuario;
import com.sonnesen.gerenciadorescolar.util.JPAUtil;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author winston.sonnesen
 */
public class UsuarioDAOImpl implements UsuarioDAO<Usuario> {

    private final EntityManager entityManager;

    public UsuarioDAOImpl() {
        entityManager = JPAUtil.getEntityManager();
    }

    @Override
    public Usuario save(Usuario usuario) {
        entityManager.getTransaction().begin();
        Usuario merged = entityManager.merge(usuario);
        entityManager.getTransaction().commit();
        entityManager.close();
        return merged;
    }

    @Override
    public void remove(Usuario usuario) {
        entityManager.getTransaction().begin();
        Usuario u = entityManager.merge(usuario);
        entityManager.remove(u);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public Usuario findByID(Integer codigo) {
        return entityManager.find(Usuario.class, codigo);
    }

    @Override
    public Usuario findByLoginSenha(String login, String senha) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = builder.createQuery(Usuario.class);
        
        Root<Usuario> usuario = criteriaQuery.from(Usuario.class);
        criteriaQuery.select(usuario);
        
        List<Predicate> predicates = new ArrayList<>();
        
        ParameterExpression<String> paramLogin = builder.parameter(String.class,"login");
        ParameterExpression<String> paramSenha = builder.parameter(String.class,"senha");
        
        predicates.add(builder.equal(usuario.get("login"), paramLogin));
        predicates.add(builder.equal(usuario.get("senha"), paramSenha));
        
        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        
        TypedQuery<Usuario> query = entityManager.createQuery(criteriaQuery);
        query.setParameter("login", login);
        query.setParameter("senha", senha);
        
        Usuario result = null;
        
        try {
            result = query.getSingleResult();
        } catch (Exception e) {
            
        }
            
        return result;

//        Query query = 
//                entityManager.createQuery(
//                        "from Usuario u where u.login = " +
//                        ":login and u.senha = :senha");
//        query.setParameter("login", login);
//        query.setParameter("senha", senha);
//        Usuario result = null;
//        try {
//            result = (Usuario) query.getSingleResult();
//        } catch (Exception e) {}
//        
//        return result;
    }

    @Override
    public List<Usuario> findAll() {
        TypedQuery<Usuario> query = entityManager.createQuery("from Usuario", Usuario.class);
        List<Usuario> usuarios = query.getResultList();
        return usuarios;
    }

}
