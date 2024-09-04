package modelo

class Competencia {
    String nome

    Competencia(String nome) {
        this.nome = nome
    }

    @Override
    String toString() {
        return nome
    }
}
