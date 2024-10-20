package modelo

class Competencia {
    int idCompetencia
    String nomeCompetencia

    Competencia(int idCompetencia, String nomeCompetencia) {
        this.idCompetencia = idCompetencia
        this.nomeCompetencia = nomeCompetencia
    }

    String getNome() {
        return nomeCompetencia
    }

    @Override
    String toString() {
        return nomeCompetencia
    }
}
