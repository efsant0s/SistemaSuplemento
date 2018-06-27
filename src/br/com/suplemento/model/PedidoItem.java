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
public class PedidoItem {
    
    
    private Long id;
    private Integer ordem;
    private Suplemento suplemento;
    private Double quantidade;
    private Double valorUnitario;
    private Double valorTotal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
    }

    public Suplemento getSuplemento() {
        return suplemento;
    }

    public void setSuplemento(Suplemento suplemento) {
        this.suplemento = suplemento;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.ordem);
        hash = 53 * hash + Objects.hashCode(this.suplemento);
        hash = 53 * hash + Objects.hashCode(this.quantidade);
        hash = 53 * hash + Objects.hashCode(this.valorUnitario);
        hash = 53 * hash + Objects.hashCode(this.valorTotal);
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
        final PedidoItem other = (PedidoItem) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.ordem, other.ordem)) {
            return false;
        }
        if (!Objects.equals(this.suplemento, other.suplemento)) {
            return false;
        }
        if (!Objects.equals(this.quantidade, other.quantidade)) {
            return false;
        }
        if (!Objects.equals(this.valorUnitario, other.valorUnitario)) {
            return false;
        }
        if (!Objects.equals(this.valorTotal, other.valorTotal)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PedidoItem{" + "id=" + id + ", ordem=" + ordem + ", suplemento=" + suplemento + ", quantidade=" + quantidade + ", valorUnitario=" + valorUnitario + ", valorTotal=" + valorTotal + '}';
    }

   
    
}
