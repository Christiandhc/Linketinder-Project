package dao

import modelo.Candidato
import database.DatabaseConnection
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException
import java.util.logging.Logger

class CandidatoDAOImpl implements CandidatoDAO {

    private static final Logger logger = Logger.getLogger(CandidatoDAOImpl.class.name)

    @Override
    void inserirCandidato(Candidato candidato) {
        String sqlInsert = """
            INSERT INTO candidato (nome, sobrenome, data_nascimento, email, cpf, pais, cep, descricao_pessoal, senha) 
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
        """
        executeInsertCandidato(candidato, sqlInsert)
    }

    private void executeInsertCandidato(Candidato candidato, String sqlInsert) {
        Connection connection = DatabaseConnection.getConnection()

        try {
            PreparedStatement stmt = connection.prepareStatement(sqlInsert)
            preencherParametrosInsercao(stmt, candidato)
            stmt.executeUpdate()
        } catch (SQLException e) {
            logger.log(java.util.logging.Level.SEVERE, "Erro ao inserir candidato", e)
        } finally {
            connection?.close()
        }
    }

    private void preencherParametrosInsercao(PreparedStatement stmt, Candidato candidato) {
        stmt.setString(1, candidato.nome)
        stmt.setString(2, candidato.sobrenome)
        stmt.setDate(3, java.sql.Date.valueOf(candidato.dataNascimento))
        stmt.setString(4, candidato.email)
        stmt.setString(5, candidato.cpf)
        stmt.setString(6, candidato.pais)
        stmt.setString(7, candidato.cep)
        stmt.setString(8, candidato.descricao)
        stmt.setString(9, candidato.senha)
    }

    @Override
    List<Candidato> listarCandidatos() {
        String sqlSelect = "SELECT * FROM candidato"
        executeListarCandidatos(sqlSelect)
    }

    private List<Candidato> executeListarCandidatos(String sqlSelect) {
        Connection connection = DatabaseConnection.getConnection()
        List<Candidato> listaCandidatos = []

        try {
            PreparedStatement stmt = connection.createStatement()
            ResultSet rs = stmt.executeQuery(sqlSelect)

            while (rs.next()) {
                listaCandidatos.add(criarCandidato(rs))
            }
        } catch (SQLException e) {
            logger.log(java.util.logging.Level.SEVERE, "Erro ao listar candidatos", e)
        } finally {
            connection?.close()
        }

        listaCandidatos
    }

    private Candidato criarCandidato(ResultSet rs) {
        new Candidato(
                rs.getString("nome"),
                rs.getString("sobrenome"),
                rs.getDate("data_nascimento").toLocalDate(),
                rs.getString("email"),
                rs.getString("cpf"),
                rs.getString("pais"),
                rs.getString("cep"),
                rs.getString("descricao_pessoal"),
                "",
                []
        )
    }
}
