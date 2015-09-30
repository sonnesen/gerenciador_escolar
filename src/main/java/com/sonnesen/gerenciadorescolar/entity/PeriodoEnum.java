/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sonnesen.gerenciadorescolar.entity;

/**
 *
 * @author winston
 */
public  enum PeriodoEnum {
    
    MATUTINO(0,"Matutino"), VESPERTINO(1,"Vespertino"), INTEGRAL(2,"Integral"), NOTURNO(3,"Noturno");
    
    private int valor;
    private String descricao;
    
    PeriodoEnum(int valor, String descricao) {
        this.valor = valor;
        this.descricao = descricao;
    }
    
    public int getValor() {
        return valor;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
}
