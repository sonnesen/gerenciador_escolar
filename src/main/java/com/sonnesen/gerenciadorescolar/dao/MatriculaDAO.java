/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sonnesen.gerenciadorescolar.dao;

import java.util.List;

/**
 *
 * @author winston.sonnesen
 * @param <Matricula>
 */
public interface MatriculaDAO<Matricula> {

    Matricula save(Matricula aluno);

    void remove(Matricula aluno);

    Matricula findByID(Integer codigo);

    List<Matricula> findAll();
}
