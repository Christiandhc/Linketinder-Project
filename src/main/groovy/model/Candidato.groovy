package model

import java.time.LocalDate

class Candidato extends Pessoa {
    String cpf
    String sobrenome
    LocalDate dataNascimento
    String senha
    List<Competencia> competencias

    Candidato(String nome, String sobrenome, LocalDate dataNascimento, String email, String cpf, String pais, String cep, String descricaoPessoal, String senha, List<Competencia> competencias) {
        super(nome, email, pais, cep, descricaoPessoal)
        this.sobrenome = sobrenome
        this.dataNascimento = dataNascimento
        this.cpf = cpf
        this.senha = senha
        this.competencias = competencias
    }

    @Override
    String toString() {
        return super.toString() + ", Sobrenome: $sobrenome, CPF: $cpf, Data de Nascimento: $dataNascimento, Competências: ${competencias?.join(', ') ?: 'Nenhuma competência'}"
    }
}


