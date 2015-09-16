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
 * @param <Curso>
 */
public interface CursoDAO<Curso> {

    Curso save(Curso curso);

    void remove(Curso curso);

    Curso findByID(Integer codigo);

    List<Curso> findAll();
}
