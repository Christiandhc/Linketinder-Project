package modelo

class Empresa extends Pessoa {
    int idEmpresa
    String cnpj
    String emailCorporativo
    String pais
    List<Competencia> competencias // Lista de competências relacionadas à empresa

    Empresa(int idEmpresa, String nome, String emailCorporativo, String cnpj, String pais, String cep, String descricao, List<Competencia> competencias) {
        super(nome, emailCorporativo, pais, cep, descricao)
        this.idEmpresa = idEmpresa
        this.cnpj = cnpj
        this.pais = pais
        this.competencias = competencias // Inicializando a lista de competências
    }

    @Override
    String toString() {
        return super.toString() + ", ID: $idEmpresa, CNPJ: $cnpj, País: $pais, Competências: ${competencias.join(', ')}"
    }
}
