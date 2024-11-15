package modelo

class Empresa extends Pessoa {
    int idEmpresa
    String cnpj
    String emailCorporativo
    List<Competencia> competencias

    Empresa(int idEmpresa, String nome, String emailCorporativo, String cnpj, String pais, String cep, String descricao, List<Competencia> competencias) {
        super(nome, emailCorporativo, pais, cep, descricao)
        this.idEmpresa = idEmpresa
        this.cnpj = cnpj
        this.competencias = competencias
    }

    @Override
    String toString() {
        return super.toString() + ", ID: $idEmpresa, CNPJ: $cnpj, Competências: ${competencias?.join(', ') ?: 'Nenhuma competência'}"
    }
}


