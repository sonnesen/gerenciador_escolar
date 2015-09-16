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
 * @param <Turma>
 */
public interface TurmaDAO<Turma> {

    Turma save(Turma turma);

    void remove(Turma turma);

    Turma findByID(Integer codigo);

    List<Turma> findAll();
}
