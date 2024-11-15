package dao

import modelo.Vaga
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException
import java.util.logging.Logger

class VagaDAOImpl extends BaseDAO implements VagaDAO {

    private static final Logger logger = Logger.getLogger(VagaDAOImpl.class.name)

    VagaDAOImpl(Connection connection) {
        super(connection)
    }

    @Override
    void inserirVaga(Vaga vaga) {
        String sql = "INSERT INTO vaga (nome_vaga, descricao_vaga, local_vaga, id_empresa) VALUES (?, ?, ?, ?)"

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            prepararStatement(vaga, preparedStatement)
            preparedStatement.executeUpdate()
        } catch (SQLException e) {
            logger.log(java.util.logging.Level.SEVERE, "Erro ao inserir vaga", e)
            throw new DatabaseException("Erro ao inserir vaga", e)
        }
    }

    @Override
    List<Vaga> listarVagas() {
        String sql = "SELECT * FROM vaga"
        List<Vaga> listaVagas = []

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                listaVagas.add(mapearVaga(rs))
            }
        } catch (SQLException e) {
            logger.log(java.util.logging.Level.SEVERE, "Erro ao listar vagas", e)
            throw new DatabaseException("Erro ao listar vagas", e)
        }

        return listaVagas
    }

    private void prepararStatement(Vaga vaga, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, vaga.nomeVaga)
        preparedStatement.setString(2, vaga.descricaoVaga)
        preparedStatement.setString(3, vaga.localVaga)
        preparedStatement.setInt(4, vaga.idEmpresa)
    }

    private Vaga mapearVaga(ResultSet rs) throws SQLException {
        return new Vaga(
                rs.getInt("id_vaga"),
                rs.getString("nome_vaga"),
                rs.getString("descricao_vaga"),
                rs.getString("local_vaga"),
                rs.getInt("id_empresa")
        )
    }
}
