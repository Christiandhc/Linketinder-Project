package dao

import modelo.Candidato

interface CandidatoDAO {
    void inserirCandidato(Candidato candidato)
    List<Candidato> listarCandidatos()
}


