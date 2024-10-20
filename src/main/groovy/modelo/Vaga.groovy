package modelo

class Vaga {
    int idVaga
    String nomeVaga
    String descricaoVaga
    String localVaga
    int idEmpresa

    Vaga(int idVaga, String nomeVaga, String descricaoVaga, String localVaga, int idEmpresa) {
        this.idVaga = idVaga
        this.nomeVaga = nomeVaga
        this.descricaoVaga = descricaoVaga
        this.localVaga = localVaga
        this.idEmpresa = idEmpresa
    }

    @Override
    String toString() {
        return "Vaga(idVaga=$idVaga, nomeVaga='$nomeVaga', descricaoVaga='$descricaoVaga', localVaga='$localVaga', idEmpresa=$idEmpresa)"
    }
}
