package service

import java.time.LocalDate
import model.dao.CandidatoDAO
import model.dao.EmpresaDAO
import model.Candidato
import model.Empresa
import model.Competencia

class RecrutamentoService {
    private final CandidatoDAO candidatoDAO
    private final EmpresaDAO empresaDAO

    RecrutamentoService(CandidatoDAO candidatoDAO, EmpresaDAO empresaDAO) {
        this.candidatoDAO = candidatoDAO
        this.empresaDAO = empresaDAO
    }

    void adicionarCandidato(String nome, String sobrenome, LocalDate dataNascimento, String email, String cpf, String pais, String cep, String descricao, String senha, List<Competencia> competencias) {
        Candidato candidato = new Candidato(nome, sobrenome, dataNascimento, email, cpf, pais, cep, descricao, senha, competencias)
        candidatoDAO.inserirCandidato(candidato)
    }

    void adicionarEmpresa(int idEmpresa, String nome, String emailCorporativo, String cnpj, String pais, String cep, String descricao, List<Competencia> competencias) {
        Empresa empresa = new Empresa(idEmpresa, nome, emailCorporativo, cnpj, pais, cep, descricao, competencias)
        empresaDAO.inserirEmpresa(empresa)
    }

    List<Candidato> listarCandidatos() {
        return candidatoDAO.listarCandidatos()
    }

    List<Empresa> listarEmpresas() {
        return empresaDAO.listarEmpresas()
    }
}

