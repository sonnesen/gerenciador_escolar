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
public class PeriodoUtil {

    public static String getDescricao(Integer valor) {
        String descricao = "";
        try {
            switch (valor) {
                case 0:
                    descricao = PeriodoEnum.MATUTINO.getDescricao();
                    break;
                case 1:
                    descricao = PeriodoEnum.VESPERTINO.getDescricao();
                    break;
                case 2:
                    descricao = PeriodoEnum.INTEGRAL.getDescricao();
                    break;
                case 3:
                    descricao = PeriodoEnum.NOTURNO.getDescricao();
                    break;

            }
        } catch (NullPointerException e) {

        }
        return descricao;
    }
}
