package model

class Competencia {
    private int idCompetencia
    private String nomeCompetencia

    Competencia(int idCompetencia, String nomeCompetencia) {
        this.idCompetencia = idCompetencia
        this.nomeCompetencia = nomeCompetencia
    }


    int getIdCompetencia() {
        return idCompetencia
    }

    String getNomeCompetencia() {
        return nomeCompetencia
    }

    void setNomeCompetencia(String nomeCompetencia) {
        this.nomeCompetencia = nomeCompetencia
    }

    @Override
    String toString() {
        return nomeCompetencia
    }
}


