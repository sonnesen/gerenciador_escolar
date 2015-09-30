/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sonnesen.gerenciadorescolar.dao;

import com.sonnesen.gerenciadorescolar.exception.BusinessException;
import com.sonnesen.gerenciadorescolar.entity.Usuario;
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
public class UsuarioDAO implements IDAO<Usuario> {

    private final EntityManager entityManager;

    public UsuarioDAO() {
        entityManager = JPAUtil.getEntityManager();
    }

    @Override
    public Usuario save(Usuario usuario) throws BusinessException {
        Usuario merged = null;
        try {
            entityManager.getTransaction().begin();
            merged = entityManager.merge(usuario);
            entityManager.getTransaction().commit();
        } catch (PersistenceException ex) {
            try {
                entityManager.getTransaction().rollback();
            } catch (PersistenceException pex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, pex);
            }
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, "Erro ao salvar usuário: " + usuario, ex);
            throw new BusinessException("Erro ao salvar usuário: " + usuario, ex);
        }
        JPAUtil.closeEntityManager(entityManager);
        return merged;
    }

    @Override
    public void remove(Usuario usuario) throws BusinessException {
        try {
            entityManager.getTransaction().begin();
            Usuario u = entityManager.merge(usuario);
            entityManager.remove(u);
            entityManager.getTransaction().commit();
        } catch (PersistenceException ex) {
            try {
                entityManager.getTransaction().rollback();
            } catch (PersistenceException pex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, pex);
            }
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new BusinessException("Erro ao remover usuário: " + usuario, ex);
        }
        JPAUtil.closeEntityManager(entityManager);
    }

    @Override
    public Usuario findByID(Long codigo) {
        Usuario usuario = null;
        try {
            entityManager.find(Usuario.class, codigo);
        } catch (PersistenceException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        JPAUtil.closeEntityManager(entityManager);
        return usuario;
    }

    @Override
    public List<Usuario> findAll() {
        List<Usuario> usuarios = null;
        try {
            TypedQuery<Usuario> query = entityManager.createNamedQuery("Usuario.findAll", Usuario.class);
            usuarios = query.getResultList();
        } catch (PersistenceException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        JPAUtil.closeEntityManager(entityManager);
        return usuarios;
    }

    public Usuario findByLoginSenha(String login, String senha) {
        Usuario usuario = null;
        try {
            TypedQuery<Usuario> query = entityManager.createNamedQuery("Usuario.findByLoginSenha", Usuario.class)
                    .setParameter("login", login)
                    .setParameter("senha", senha);                    
            usuario = query.getSingleResult();
        } catch (PersistenceException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        JPAUtil.closeEntityManager(entityManager);
        return usuario;
    }

}
