/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sonnesen.gerenciadorescolar.controller;

import com.sonnesen.gerenciadorescolar.dao.UsuarioDAO;
import com.sonnesen.gerenciadorescolar.entity.Usuario;
import com.sonnesen.gerenciadorescolar.exception.BusinessException;
import com.sonnesen.gerenciadorescolar.model.LoginModel;
import com.sonnesen.gerenciadorescolar.session.SessionManager;

/**
 *
 * @author winston
 */
public class LoginController {
 
    private final LoginModel model;
    
    public LoginController(LoginModel model) {
        this.model = model;
    }    
    
    public void doLogin(Usuario usuario) throws BusinessException {
        UsuarioDAO dao = new UsuarioDAO();
        Usuario usuarioLogado = dao.findByLoginSenha(usuario.getLogin(), usuario.getSenha());
        if (usuarioLogado == null) {
            throw new BusinessException("Usuário e/ou senha inválidos!");
        }
        SessionManager.setUsuarioLogado(usuarioLogado);        
    }
}
