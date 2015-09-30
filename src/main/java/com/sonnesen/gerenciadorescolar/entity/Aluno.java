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
import javax.persistence.Id;
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
@Table(name = "aluno")
@NamedQueries({
    @NamedQuery(name = "Aluno.findAll", query = "SELECT a FROM Aluno a"),
    @NamedQuery(name = "Aluno.findByCodigo", query = "SELECT a FROM Aluno a WHERE a.codigo = :codigo"),
    @NamedQuery(name = "Aluno.findByNome", query = "SELECT a FROM Aluno a WHERE a.nome = :nome"),
    @NamedQuery(name = "Aluno.findByRg", query = "SELECT a FROM Aluno a WHERE a.rg = :rg"),
    @NamedQuery(name = "Aluno.findByCpf", query = "SELECT a FROM Aluno a WHERE a.cpf = :cpf"),
    @NamedQuery(name = "Aluno.findByNomeResponsavel", query = "SELECT a FROM Aluno a WHERE a.nomeResponsavel = :nomeResponsavel"),
    @NamedQuery(name = "Aluno.findByDataNascimento", query = "SELECT a FROM Aluno a WHERE a.dataNascimento = :dataNascimento"),
    @NamedQuery(name = "Aluno.findByEmail", query = "SELECT a FROM Aluno a WHERE a.email = :email"),
    @NamedQuery(name = "Aluno.findByTelefone1", query = "SELECT a FROM Aluno a WHERE a.telefone1 = :telefone1"),
    @NamedQuery(name = "Aluno.findByTelefone2", query = "SELECT a FROM Aluno a WHERE a.telefone2 = :telefone2"),
    @NamedQuery(name = "Aluno.findByEndereco", query = "SELECT a FROM Aluno a WHERE a.endereco = :endereco")})
public class Aluno implements Serializable {
    
    @Transient
    private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codigo")
    private Long codigo;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @Column(name = "rg")
    private String rg;
    @Basic(optional = false)
    @Column(name = "cpf")
    private String cpf;
    @Basic(optional = false)
    @Column(name = "nomeResponsavel")
    private String nomeResponsavel;
    @Basic(optional = false)
    @Column(name = "dataNascimento")
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "telefone1")
    private String telefone1;
    @Basic(optional = false)
    @Column(name = "telefone2")
    private String telefone2;
    @Basic(optional = false)
    @Column(name = "endereco")
    private String endereco;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "aluno")
    private List<Matricula> matriculaList;

    public Aluno() {
    }

    public Aluno(Long codigo) {
        this.codigo = codigo;
    }

    public Aluno(Long codigo, String nome, String rg, String cpf, String nomeResponsavel, Date dataNascimento, String email, String telefone1, String telefone2, String endereco) {
        this.codigo = codigo;
        this.nome = nome;
        this.rg = rg;
        this.cpf = cpf;
        this.nomeResponsavel = nomeResponsavel;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.telefone1 = telefone1;
        this.telefone2 = telefone2;
        this.endereco = endereco;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        Long oldCodigo = this.codigo;
        this.codigo = codigo;
        changeSupport.firePropertyChange("codigo", oldCodigo, codigo);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        String oldNome = this.nome;
        this.nome = nome;
        changeSupport.firePropertyChange("nome", oldNome, nome);
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        String oldRg = this.rg;
        this.rg = rg;
        changeSupport.firePropertyChange("rg", oldRg, rg);
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        String oldCpf = this.cpf;
        this.cpf = cpf;
        changeSupport.firePropertyChange("cpf", oldCpf, cpf);
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public void setNomeResponsavel(String nomeResponsavel) {
        String oldNomeResponsavel = this.nomeResponsavel;
        this.nomeResponsavel = nomeResponsavel;
        changeSupport.firePropertyChange("nomeResponsavel", oldNomeResponsavel, nomeResponsavel);
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        Date oldDataNascimento = this.dataNascimento;
        this.dataNascimento = dataNascimento;
        changeSupport.firePropertyChange("dataNascimento", oldDataNascimento, dataNascimento);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        String oldEmail = this.email;
        this.email = email;
        changeSupport.firePropertyChange("email", oldEmail, email);
    }

    public String getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(String telefone1) {
        String oldTelefone1 = this.telefone1;
        this.telefone1 = telefone1;
        changeSupport.firePropertyChange("telefone1", oldTelefone1, telefone1);
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        String oldTelefone2 = this.telefone2;
        this.telefone2 = telefone2;
        changeSupport.firePropertyChange("telefone2", oldTelefone2, telefone2);
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        String oldEndereco = this.endereco;
        this.endereco = endereco;
        changeSupport.firePropertyChange("endereco", oldEndereco, endereco);
    }

    public List<Matricula> getMatriculaList() {
        return matriculaList;
    }

    public void setMatriculaList(List<Matricula> matriculaList) {
        List<Matricula> oldMatriculaList = this.matriculaList;
        this.matriculaList = matriculaList;
        changeSupport.firePropertyChange("matriculaList", oldMatriculaList, matriculaList);
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
        if (!(object instanceof Aluno)) {
            return false;
        }
        Aluno other = (Aluno) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sonnesen.gerenciadorescolar.model.Aluno[ codigo=" + codigo + " ]";
    }
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
}
