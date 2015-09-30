/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sonnesen.gerenciadorescolar.dao;

import com.sonnesen.gerenciadorescolar.exception.BusinessException;
import java.util.List;

/**
 *
 * @author winston
 */
public interface IDAO <E>{
    
    E findByID(Long id);
    List<E> findAll();
    E save(E entity) throws BusinessException;
    void remove(E entity) throws BusinessException;
}
