package modelo

abstract class Pessoa {
    String nome
    String email
    String pais
    String cep
    String descricao

    Pessoa(String nome, String email, String pais, String cep, String descricao) {
        this.nome = nome
        this.email = email
        this.pais = pais
        this.cep = cep
        this.descricao = descricao
    }

    String getEmail() {
        return email
    }

    String getCep() {
        return cep
    }

    String getDescricao() {
        return descricao
    }

    @Override
    String toString() {
        return "Nome: $nome, Email: $email, País: $pais, CEP: $cep, Descrição: $descricao"
    }
}



