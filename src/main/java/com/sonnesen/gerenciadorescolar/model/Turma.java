/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sonnesen.gerenciadorescolar.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author winston.sonnesen
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Turma.findAll", query = "SELECT t FROM Turma t"),
    @NamedQuery(name = "Turma.findByCodigo", query = "SELECT t FROM Turma t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "Turma.findByDataInicio", query = "SELECT t FROM Turma t WHERE t.dataInicio = :dataInicio"),
    @NamedQuery(name = "Turma.findByDataFim", query = "SELECT t FROM Turma t WHERE t.dataFim = :dataFim"),
    @NamedQuery(name = "Turma.findByPeriodo", query = "SELECT t FROM Turma t WHERE t.periodo = :periodo"),
    @NamedQuery(name = "Turma.findByVagas", query = "SELECT t FROM Turma t WHERE t.vagas = :vagas")})
public class Turma implements Serializable {
    
    @Transient
    private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer codigo;
    
    @Column(name = "dataInicio", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataInicio;
    
    @Column(name = "dataFim", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataFim;
    
    @Column(name = "periodo", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private PeriodoEnum periodo;
    
    @Column(name = "vagas", nullable = false)
    private Short vagas;
    
    @JoinColumn(name = "codigoCurso", referencedColumnName = "codigo", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Curso curso;

    public Turma() {
    }

    public Turma(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        Integer oldCodigo = this.codigo;
        this.codigo = codigo;
        changeSupport.firePropertyChange("codigo", oldCodigo, codigo);
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        Date oldDataInicio = this.dataInicio;
        this.dataInicio = dataInicio;
        changeSupport.firePropertyChange("dataInicio", oldDataInicio, dataInicio);
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        Date oldDataFim = this.dataFim;
        this.dataFim = dataFim;
        changeSupport.firePropertyChange("dataFim", oldDataFim, dataFim);
    }

    public PeriodoEnum getPeriodo() {
        return periodo;
    }

    public void setPeriodo(PeriodoEnum periodo) {
        PeriodoEnum oldPeriodo = this.periodo;
        this.periodo = periodo;
        changeSupport.firePropertyChange("periodo", oldPeriodo, periodo);
    }

    public Short getVagas() {
        return vagas;
    }

    public void setVagas(Short vagas) {
        Short oldVagas = this.vagas;
        this.vagas = vagas;
        changeSupport.firePropertyChange("vagas", oldVagas, vagas);
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        Curso oldCurso = this.curso;
        this.curso = curso;
        changeSupport.firePropertyChange("curso", oldCurso, curso);
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
        if (!(object instanceof Turma)) {
            return false;
        }
        Turma other = (Turma) object;
        return !((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo)));
    }

    @Override
    public String toString() {
        return "com.sonnesen.gerenciadorescolar.model.Turma[ codigo=" + codigo + " ]";
    }
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
}
