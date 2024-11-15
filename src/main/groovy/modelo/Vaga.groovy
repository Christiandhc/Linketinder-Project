package modelo

class Vaga {
    private int idVaga
    private String nomeVaga
    private String descricaoVaga
    private String localVaga
    private int idEmpresa

    Vaga(int idVaga, String nomeVaga, String descricaoVaga, String localVaga, int idEmpresa) {
        this.idVaga = idVaga
        this.nomeVaga = nomeVaga
        this.descricaoVaga = descricaoVaga
        this.localVaga = localVaga
        this.idEmpresa = idEmpresa
    }


    int getIdVaga() {
        return idVaga
    }

    String getNomeVaga() {
        return nomeVaga
    }

    String getDescricaoVaga() {
        return descricaoVaga
    }

    String getLocalVaga() {
        return localVaga
    }

    int getIdEmpresa() {
        return idEmpresa
    }


    void setNomeVaga(String nomeVaga) {
        this.nomeVaga = nomeVaga
    }

    void setDescricaoVaga(String descricaoVaga) {
        this.descricaoVaga = descricaoVaga
    }

    void setLocalVaga(String localVaga) {
        this.localVaga = localVaga
    }

    void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa
    }

    @Override
    String toString() {
        return "Vaga(idVaga=$idVaga, nomeVaga='$nomeVaga', descricaoVaga='$descricaoVaga', localVaga='$localVaga', idEmpresa=$idEmpresa)"
    }
}
