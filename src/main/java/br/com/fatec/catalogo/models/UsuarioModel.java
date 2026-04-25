package br.com.fatec.catalogo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "usuarios")
public class UsuarioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idUsuario;

    @NotBlank(message = "Nome é obrigatório.")
    @Size(min = 3, max = 40, message = "O nome deve ter entre 3 e 40 caracteres.")
    private String nome;

    @NotBlank(message = "Senha é obrigatória.")
    @Size(min = 6, message = "A senha deve conter no mínimo 6 caracteres")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).+$", message = "A senha deve conter letra maiúscula, minúscula, número e caractere especial")
    private String senha;

    private String role;

    // getter e setters
    public long getIdUsuario() {return idUsuario;}
    public void setIdUsuario(long idUsuario) {this.idUsuario = idUsuario;}
    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}
    public String getSenha() {return senha;}
    public void setSenha(String senha) {this.senha = senha;}
    public String getRole() {return role;}
    public void setRole(String role) {this.role = role;}
}
