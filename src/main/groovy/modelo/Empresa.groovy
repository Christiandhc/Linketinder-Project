package modelo

class Empresa extends Pessoa {
    String cnpj
    String pais
    List<Competencia> competencias

    Empresa(String nome, String email, String cnpj, String pais, String estado, String cep, String descricao, List<Competencia> competencias) {
        super(nome, email, estado, cep, descricao)
        this.cnpj = cnpj
        this.pais = pais
        this.competencias = competencias
    }

    @Override
    String toString() {
        return super.toString() + ", CNPJ: $cnpj, País: $pais, Competências: ${competencias.join(', ')}"
    }
}
