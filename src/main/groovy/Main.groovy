import aplicacao.InterfaceUsuario
import dao.CandidatoDAOImpl
import dao.EmpresaDAOImpl
import database.DatabaseConnection
import servico.GerenciadorDeRecrutamento
import servico.RecrutamentoService

class Main {
    static void main(String[] args) {
        def connection = DatabaseConnection.getInstance().getConnection()

        def candidatoDAO = new CandidatoDAOImpl(connection)
        def empresaDAO = new EmpresaDAOImpl(connection)

        def recrutamentoService = new RecrutamentoService(candidatoDAO, empresaDAO)
        def sistemaRecrutamento = new GerenciadorDeRecrutamento(recrutamentoService)

        def interfaceUsuario = new InterfaceUsuario(sistemaRecrutamento)
        interfaceUsuario.iniciar()
    }
}
