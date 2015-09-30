/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sonnesen.gerenciadorescolar.entity;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author winston
 */
@Entity
@Table(name = "curso")
@NamedQueries({
    @NamedQuery(name = "Curso.findAll", query = "SELECT c FROM Curso c"),
    @NamedQuery(name = "Curso.findByCodigo", query = "SELECT c FROM Curso c WHERE c.codigo = :codigo"),
    @NamedQuery(name = "Curso.findByDescricao", query = "SELECT c FROM Curso c WHERE c.descricao = :descricao"),
    @NamedQuery(name = "Curso.findByCargaHoraria", query = "SELECT c FROM Curso c WHERE c.cargaHoraria = :cargaHoraria"),
    @NamedQuery(name = "Curso.findByAtivo", query = "SELECT c FROM Curso c WHERE c.ativo = :ativo"),
    @NamedQuery(name = "Curso.findByValor", query = "SELECT c FROM Curso c WHERE c.valor = :valor")})
public class Curso implements Serializable {
    
    @Transient
    private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Long codigo;
    @Basic(optional = false)
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @Column(name = "cargaHoraria")
    private short cargaHoraria;
    @Column(name = "ativo")
    private Boolean ativo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor")
    private Float valor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "curso")
    private List<Turma> turmaList;

    public Curso() {
    }

    public Curso(Long codigo) {
        this.codigo = codigo;
    }

    public Curso(Long codigo, String descricao, short cargaHoraria) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.cargaHoraria = cargaHoraria;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        Long oldCodigo = this.codigo;
        this.codigo = codigo;
        changeSupport.firePropertyChange("codigo", oldCodigo, codigo);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        String oldDescricao = this.descricao;
        this.descricao = descricao;
        changeSupport.firePropertyChange("descricao", oldDescricao, descricao);
    }

    public short getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(short cargaHoraria) {
        short oldCargaHoraria = this.cargaHoraria;                
        this.cargaHoraria = cargaHoraria;
        changeSupport.firePropertyChange("cargaHoraria", oldCargaHoraria, cargaHoraria);
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        Boolean oldAtivo = this.ativo;
        this.ativo = ativo;
        changeSupport.firePropertyChange("ativo", oldAtivo, ativo);
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        Float oldValor = this.valor;
        this.valor = valor;
        changeSupport.firePropertyChange("valor", oldValor, valor);
    }

    public List<Turma> getTurmaList() {
        return turmaList;
    }

    public void setTurmaList(List<Turma> turmaList) {
        List<Turma> oldTurmaList = this.turmaList;
        this.turmaList = turmaList;
        changeSupport.firePropertyChange("turmaList", oldTurmaList, turmaList);
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
        if (!(object instanceof Curso)) {
            return false;
        }
        Curso other = (Curso) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sonnesen.gerenciadorescolar.model.Curso[ codigo=" + codigo + " ]";
    }
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
