/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.suplemento.model;

import java.util.Objects;

/**
 *
 * @author eduardo_fs1
 */
public class Suplemento {
    private long idProduto;
    private String codigo;
    private String nome;
    private double preco;
    private double quantidade;
    private MarcaSuplemento marcaSuplemento;
    private TipoSuplemento TipoSuplemento;

    public long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(long idProduto) {
        this.idProduto = idProduto;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public MarcaSuplemento getMarcaSuplemento() {
        return marcaSuplemento;
    }

    public void setMarcaSuplemento(MarcaSuplemento marcaSuplemento) {
        this.marcaSuplemento = marcaSuplemento;
    }

    public TipoSuplemento getTipoSuplemento() {
        return TipoSuplemento;
    }

    public void setTipoSuplemento(TipoSuplemento TipoSuplemento) {
        this.TipoSuplemento = TipoSuplemento;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (int) (this.idProduto ^ (this.idProduto >>> 32));
        hash = 59 * hash + Objects.hashCode(this.codigo);
        hash = 59 * hash + Objects.hashCode(this.nome);
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.preco) ^ (Double.doubleToLongBits(this.preco) >>> 32));
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.quantidade) ^ (Double.doubleToLongBits(this.quantidade) >>> 32));
        hash = 59 * hash + Objects.hashCode(this.marcaSuplemento);
        hash = 59 * hash + Objects.hashCode(this.TipoSuplemento);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Suplemento other = (Suplemento) obj;
        if (this.idProduto != other.idProduto) {
            return false;
        }
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (Double.doubleToLongBits(this.preco) != Double.doubleToLongBits(other.preco)) {
            return false;
        }
        if (Double.doubleToLongBits(this.quantidade) != Double.doubleToLongBits(other.quantidade)) {
            return false;
        }
        if (!Objects.equals(this.marcaSuplemento, other.marcaSuplemento)) {
            return false;
        }
        if (!Objects.equals(this.TipoSuplemento, other.TipoSuplemento)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Nome: "+nome+ " Tipo: " +TipoSuplemento;
    }
    
    
    
    

    
}
