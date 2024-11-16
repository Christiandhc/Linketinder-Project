package model.dao

import model.Candidato
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException
import java.util.logging.Logger

class CandidatoDAOImpl extends BaseDAO implements CandidatoDAO {
    private static final Logger logger = Logger.getLogger(CandidatoDAOImpl.class.name)

    CandidatoDAOImpl(Connection connection) {
        super(connection)
    }

    @Override
    void inserirCandidato(Candidato candidato) {
        String sqlInsert = """
            INSERT INTO candidato (nome, sobrenome, data_nascimento, email, cpf, pais, cep, descricao_pessoal, senha) 
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
        """
        try (PreparedStatement stmt = connection.prepareStatement(sqlInsert)) {
            preencherParametrosInsercao(stmt, candidato)
            stmt.executeUpdate()
        } catch (SQLException e) {
            logger.severe("Erro ao inserir candidato: ${e.message}")
        }
    }

    @Override
    List<Candidato> listarCandidatos() {
        String sqlSelect = "SELECT * FROM candidato"
        List<Candidato> listaCandidatos = []
        try (PreparedStatement stmt = connection.prepareStatement(sqlSelect);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                listaCandidatos.add(criarCandidato(rs))
            }
        } catch (SQLException e) {
            logger.severe("Erro ao listar candidatos: ${e.message}")
        }
        return listaCandidatos
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

    private Candidato criarCandidato(ResultSet rs) {
        return new Candidato(
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

