package pousada.sistemareserva.entity;

import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String senha;
    @SuppressWarnings("unused")
    private String tokenResetSenha;
    private Date dataExpiracaoTokenResetSenha;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    @Override
    public String toString() {
        return "usuario{" + "id=" + id 
        + ", nome="  + nome 
        + ", email=" + email 
        + ", senha=" + senha 
        + '}';
    }
    
    public void setTokenResetSenha(String tokenResetSenha) {
        this.tokenResetSenha = tokenResetSenha;
    }

    public void setDataExpiracaoTokenResetSenha(Date dataExpiracaoTokenResetSenha) {
        this.dataExpiracaoTokenResetSenha = dataExpiracaoTokenResetSenha;
    }

    public boolean isTokenExpirado() {
        if (dataExpiracaoTokenResetSenha != null) {
            return new Date().after(dataExpiracaoTokenResetSenha);
        }
        return false; // Se a data de expiração não estiver definida, o token não está expirado
    }
}
