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
 * @param <Aluno>
 */
public interface AlunoDAO<Aluno> {

    Aluno save(Aluno aluno);

    void remove(Aluno aluno);

    Aluno findByID(Integer codigo);

    List<Aluno> findAll();
}
