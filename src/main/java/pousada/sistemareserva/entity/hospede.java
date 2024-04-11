package pousada.sistemareserva.entity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class hospede {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "O nome completo do hóspede é obrigatório")
    private String nome;

    @NotBlank(message = "O CPF do hóspede é obrigatório")
    private String cpf;

    private String telefone;

    @Email(message = "O e-mail do hóspede deve ser válido")
    private String email;
    public hospede(){
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "hospede [id=" + id 
        + ", nome=" + nome 
        + ", cpf=" + cpf 
        + ", telefone=" + telefone 
        + ", email=" + email
        + "]";
    }
}
