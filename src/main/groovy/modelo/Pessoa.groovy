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

    @Override
    String toString() {
        return formatarToString()
    }

    private String formatarToString() {
        return "Nome: $nome, Email: $email, Pais: $pais, CEP: $cep, Descrição: $descricao"
    }
}

