/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sonnesen.gerenciadorescolar.entity;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author winston
 */
@Entity
@Table(name = "matricula")
@NamedQueries({
    @NamedQuery(name = "Matricula.findAll", query = "SELECT m FROM Matricula m"),
    @NamedQuery(name = "Matricula.findByCodigo", query = "SELECT m FROM Matricula m WHERE m.codigo = :codigo"),
    @NamedQuery(name = "Matricula.findByDataMatricula", query = "SELECT m FROM Matricula m WHERE m.dataMatricula = :dataMatricula")})
public class Matricula implements Serializable {
    
    @Transient
    private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codigo")
    private Long codigo;
    @Basic(optional = false)
    @Column(name = "dataMatricula")
    @Temporal(TemporalType.DATE)
    private Date dataMatricula;
    @JoinColumn(name = "codigoAluno", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Aluno aluno;
    @JoinColumn(name = "codigoTurma", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Turma turma;

    public Matricula() {
    }

    public Matricula(Long codigo) {
        this.codigo = codigo;
    }

    public Matricula(Long codigo, Date dataMatricula) {
        this.codigo = codigo;
        this.dataMatricula = dataMatricula;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        Long oldCodigo = this.codigo;
        this.codigo = codigo;
        changeSupport.firePropertyChange("codigo", oldCodigo, codigo);
    }

    public Date getDataMatricula() {
        return dataMatricula;
    }

    public void setDataMatricula(Date dataMatricula) {
        Date oldDataMatricula = this.dataMatricula;
        this.dataMatricula = dataMatricula;
        changeSupport.firePropertyChange("dataMatricula", oldDataMatricula, dataMatricula);
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        Aluno oldAluno = this.aluno;
        this.aluno = aluno;
        changeSupport.firePropertyChange("aluno", oldAluno, aluno);
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        Turma oldTurma = this.turma;
        this.turma = turma;
        changeSupport.firePropertyChange("turma", oldTurma, turma);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Matricula)) {
            return false;
        }
        Matricula other = (Matricula) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sonnesen.gerenciadorescolar.model.Matricula[ codigo=" + codigo + " ]";
    }
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
