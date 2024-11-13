package dao

import modelo.Candidato
import database.DatabaseConnection
import java.sql.Connection
import java.sql.SQLException
import java.util.logging.Logger

class CandidatoDAO {

    private static final Logger logger = Logger.getLogger(CandidatoDAO.class.name)

    static void inserirCandidato(Candidato candidato) {
        Connection connection = DatabaseConnection.getConnection()
        String sql = "INSERT INTO candidato (nome, sobrenome, data_nascimento, email, cpf, pais, cep, descricao_pessoal, senha) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"

        try {
            def stmt = connection.prepareStatement(sql)
            stmt.setString(1, candidato.nome)
            stmt.setString(2, candidato.sobrenome)
            stmt.setDate(3, java.sql.Date.valueOf(candidato.dataNascimento.toString()))
            stmt.setString(4, candidato.email)
            stmt.setString(5, candidato.cpf)
            stmt.setString(6, candidato.pais)
            stmt.setString(7, candidato.cep)
            stmt.setString(8, candidato.descricaoPessoal)
            stmt.setString(9, candidato.senha)

            stmt.executeUpdate()
        } catch (SQLException e) {
            logger.log(java.util.logging.Level.SEVERE, "Erro ao inserir candidato", e)
        } finally {
            connection.close()
        }
    }

    static List<Candidato> listarCandidatos() {
        Connection connection = DatabaseConnection.getConnection()
        String sql = "SELECT * FROM candidato"
        List<Candidato> listaCandidatos = []

        try {
            def stmt = connection.createStatement()
            def rs = stmt.executeQuery(sql)

            while (rs.next()) {
                Candidato candidato = new Candidato(
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
                listaCandidatos.add(candidato)
            }
        } catch (SQLException e) {
            logger.log(java.util.logging.Level.SEVERE, "Erro ao listar candidatos", e)
        } finally {
            connection.close()
        }

        return listaCandidatos
    }
}
