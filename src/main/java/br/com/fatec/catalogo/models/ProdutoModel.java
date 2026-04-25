package br.com.fatec.catalogo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "produtos")
public class ProdutoModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idProduto;

    @NotBlank(message = "Nome é obrigatório.")
    @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres.")
    private String nome;

    @NotNull(message = "Valor é obrigatório.")
    @Positive(message = "Valor deve ser um número positivo.")
    private BigDecimal valor;

    @Column(updatable = false)
    private LocalDateTime dataCadastro;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private CategoriaModel categoria;

    // Construtores, Getters e Setters

    public ProdutoModel() {}

    @PrePersist
    public void prePersist() {
        this.dataCadastro = LocalDateTime.now();
    }

    public long getIdProduto() {return idProduto;}
    public void setIdProduto(long idProduto) {this.idProduto = idProduto;}
    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}
    public BigDecimal getValor() {return valor;}
    public void setValor(BigDecimal valor) {this.valor = valor;}
    public LocalDateTime getDataCadastro() {return dataCadastro;}
    public CategoriaModel getCategoria() {return categoria;}
    public void setCategoria(CategoriaModel categoria) {this.categoria = categoria;}
}
