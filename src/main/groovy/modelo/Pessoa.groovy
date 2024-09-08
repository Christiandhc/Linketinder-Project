package modelo

abstract class Pessoa {
    String nome
    String email
    String estado
    String cep
    String descricao

    Pessoa(String nome, String email, String estado, String cep, String descricao) {
        this.nome = nome
        this.email = email
        this.estado = estado
        this.cep = cep
        this.descricao = descricao
    }

    @Override
    String toString() {
        return "Nome: ${nome}, Email: ${email}, Estado: ${estado}, CEP: ${cep}, Descrição: ${descricao}"
    }
}
