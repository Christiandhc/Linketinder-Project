package model.dao

import model.Candidato

interface CandidatoDAO {
    void inserirCandidato(Candidato candidato)
    List<Candidato> listarCandidatos()
}


