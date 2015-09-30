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
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author winston
 */
@Entity
@Table(name = "turma")
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
    @Column(name = "codigo")
    private Long codigo;
    @Basic(optional = false)
    @Column(name = "dataInicio")
    @Temporal(TemporalType.DATE)
    private Date dataInicio;
    @Basic(optional = false)
    @Column(name = "dataFim")
    @Temporal(TemporalType.DATE)
    private Date dataFim;
    @Enumerated(EnumType.ORDINAL)    
    @Basic(optional = false)
    @Column(name = "periodo")
    private PeriodoEnum periodo;
    @Basic(optional = false)
    @Column(name = "vagas")
    private short vagas;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "turma")
    private List<Matricula> matriculaList;
    @JoinColumn(name = "codigoCurso", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Curso curso;

    public Turma() {
    }

    public Turma(Long codigo) {
        this.codigo = codigo;
    }

    public Turma(Long codigo, Date dataInicio, Date dataFim, PeriodoEnum periodo, short vagas) {
        this.codigo = codigo;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.periodo = periodo;
        this.vagas = vagas;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        Long oldCodigo = this.codigo;
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

    public short getVagas() {
        return vagas;
    }

    public void setVagas(short vagas) {
        short oldVagas = this.vagas;
        this.vagas = vagas;
        changeSupport.firePropertyChange("vagas", oldVagas, vagas);
    }

    public List<Matricula> getMatriculaList() {
        return matriculaList;
    }

    public void setMatriculaList(List<Matricula> matriculaList) {
        List<Matricula> oldMatriculaList = this.matriculaList;
        this.matriculaList = matriculaList;
        changeSupport.firePropertyChange("matriculaList", oldMatriculaList, matriculaList);
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
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
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
