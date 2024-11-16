package model.dao

import model.Vaga

interface VagaDAO {
    void inserirVaga(Vaga vaga)
    List<Vaga> listarVagas()
}
