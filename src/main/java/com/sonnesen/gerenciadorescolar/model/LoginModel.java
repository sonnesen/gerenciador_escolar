/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sonnesen.gerenciadorescolar.model;

import com.sonnesen.gerenciadorescolar.entity.Usuario;

public class LoginModel extends BindableModel {

    private Usuario usuario;

    public LoginModel() {
        this.usuario = new Usuario();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        Usuario oldValue = this.usuario;
        this.usuario = usuario;
        firePropertyChange("usuario", oldValue, usuario);
    }

}
