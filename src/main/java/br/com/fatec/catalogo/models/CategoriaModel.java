package br.com.fatec.catalogo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "categorias")
public class CategoriaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategoria;

    @NotBlank
    @Size(min = 3, max = 100, message = "O nome deve ter entre 2 e 100 caracteres.")
    private String nome;

    @OneToMany(mappedBy = "categoria")
    @JsonIgnore
    private List<ProdutoModel> produtos;

    // Get Set
    public long getIdCategoria() {return idCategoria;}
    public void setIdCategoria(long idCategoria) {this.idCategoria = idCategoria;}
    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}
    public List<ProdutoModel> getProdutos() {return produtos;}
    public void setProdutos(List<ProdutoModel> produtos) {this.produtos = produtos;}
}
