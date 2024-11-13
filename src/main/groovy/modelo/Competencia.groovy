package modelo

class Competencia {
    int idCompetencia
    String nomeCompetencia

    Competencia(int idCompetencia, String nomeCompetencia) {
        this.idCompetencia = idCompetencia
        this.nomeCompetencia = nomeCompetencia
    }

    String getNomeCompetencia() {
        return nomeCompetencia
    }

    @Override
    String toString() {
        return formatarToString()
    }

    private String formatarToString() {
        return nomeCompetencia
    }
}

