package servico

import aplicacao.InterfaceUsuario
import dao.CandidatoDAOImpl
import dao.EmpresaDAOImpl
import database.DatabaseConnection

class Main {
    static void main(String[] args) {
        def connection = DatabaseConnection.getConnection()

        def candidatoDAO = new CandidatoDAOImpl()
        def empresaDAO = new EmpresaDAOImpl(connection)

        def recrutamentoService = new RecrutamentoService(candidatoDAO, empresaDAO)

        def sistemaRecrutamento = new GerenciadorDeRecrutamento(recrutamentoService)

        def interfaceUsuario = new InterfaceUsuario(sistemaRecrutamento)
        interfaceUsuario.iniciar()
    }
}

