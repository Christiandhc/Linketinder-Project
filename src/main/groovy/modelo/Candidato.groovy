package modelo

class Candidato extends Pessoa {
    String cpf
    int idade
    List<Competencia> competencias

    Candidato(String nome, String email, String cpf, int idade, String estado, String cep, String descricao, List<Competencia> competencias) {
        super(nome, email, estado, cep, descricao)
        this.cpf = cpf
        this.idade = idade
        this.competencias = competencias
    }

    @Override
    String toString() {
        return super.toString() + ", CPF: $cpf, Idade: $idade, Competências: ${competencias.join(', ')}"
    }
}
