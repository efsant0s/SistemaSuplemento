/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sistemasuplemento.model;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author eduardo_fs1
 */
public class Pedido {   
    
   
    private Cliente cliente;
    private double total;
    private long idPedido;
    private long numero;
    private Date emissao;
    private Date aprovacao;
    private FormaPagto formaPagto;
    private CondicaoPagto condicaoPagto;
    private double valorTotal;
    private List<PedidoItem> itens;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(long idPedido) {
        this.idPedido = idPedido;
    }

    public long getNumero() {
        return numero;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }

    public Date getEmissao() {
        return emissao;
    }

    public void setEmissao(Date emissao) {
        this.emissao = emissao;
    }

    public Date getAprovacao() {
        return aprovacao;
    }

    public void setAprovacao(Date aprovacao) {
        this.aprovacao = aprovacao;
    }

    public FormaPagto getFormaPagto() {
        return formaPagto;
    }

    public void setFormaPagto(FormaPagto formaPagto) {
        this.formaPagto = formaPagto;
    }

    public CondicaoPagto getCondicaoPagto() {
        return condicaoPagto;
    }

    public void setCondicaoPagto(CondicaoPagto condicaoPagto) {
        this.condicaoPagto = condicaoPagto;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<PedidoItem> getItens() {
        return itens;
    }

    public void setItens(List<PedidoItem> itens) {
        this.itens = itens;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.cliente);
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.total) ^ (Double.doubleToLongBits(this.total) >>> 32));
        hash = 97 * hash + (int) (this.idPedido ^ (this.idPedido >>> 32));
        hash = 97 * hash + (int) (this.numero ^ (this.numero >>> 32));
        hash = 97 * hash + Objects.hashCode(this.emissao);
        hash = 97 * hash + Objects.hashCode(this.aprovacao);
        hash = 97 * hash + Objects.hashCode(this.formaPagto);
        hash = 97 * hash + Objects.hashCode(this.condicaoPagto);
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.valorTotal) ^ (Double.doubleToLongBits(this.valorTotal) >>> 32));
        hash = 97 * hash + Objects.hashCode(this.itens);
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
        final Pedido other = (Pedido) obj;
        if (!Objects.equals(this.cliente, other.cliente)) {
            return false;
        }
        if (Double.doubleToLongBits(this.total) != Double.doubleToLongBits(other.total)) {
            return false;
        }
        if (this.idPedido != other.idPedido) {
            return false;
        }
        if (this.numero != other.numero) {
            return false;
        }
        if (!Objects.equals(this.emissao, other.emissao)) {
            return false;
        }
        if (!Objects.equals(this.aprovacao, other.aprovacao)) {
            return false;
        }
        if (!Objects.equals(this.formaPagto, other.formaPagto)) {
            return false;
        }
        if (!Objects.equals(this.condicaoPagto, other.condicaoPagto)) {
            return false;
        }
        if (Double.doubleToLongBits(this.valorTotal) != Double.doubleToLongBits(other.valorTotal)) {
            return false;
        }
        if (!Objects.equals(this.itens, other.itens)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Pedido{" + "cliente=" + cliente + ", total=" + total + ", idPedido=" + idPedido + ", numero=" + numero + ", emissao=" + emissao + ", aprovacao=" + aprovacao + ", formaPagto=" + formaPagto + ", condicaoPagto=" + condicaoPagto + ", valorTotal=" + valorTotal + ", itens=" + itens + '}';
    }

 

    
}
