package dao

import modelo.Vaga

interface VagaDAO {
    void inserirVaga(Vaga vaga)
    List<Vaga> listarVagas()
}
