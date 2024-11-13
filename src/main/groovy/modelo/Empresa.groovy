package modelo

class Empresa extends Pessoa {
    int idEmpresa
    String cnpj
    String emailCorporativo
    String pais
    List<Competencia> competencias

    Empresa(int idEmpresa, String nome, String emailCorporativo, String cnpj, String pais, String cep, String descricao, List<Competencia> competencias) {
        super(nome, emailCorporativo, pais, cep, descricao)
        this.idEmpresa = idEmpresa
        this.cnpj = cnpj
        this.pais = pais
        this.competencias = competencias
    }

    @Override
    String toString() {
        return formatarToString()
    }

    private String formatarToString() {
        return "${super.toString()}, ID: $idEmpresa, CNPJ: $cnpj, País: $pais, Competências: ${competencias.join(', ')}"
    }
}

