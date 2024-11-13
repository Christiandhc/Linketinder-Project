package dao

import modelo.Vaga
import database.DatabaseConnection
import java.sql.SQLException
import java.util.logging.Logger

class VagaDAO {

    private static final Logger logger = Logger.getLogger(VagaDAO.class.name)

    static void inserirVaga(Vaga vaga) {
        def connection = DatabaseConnection.getConnection()
        def sql = "INSERT INTO vaga (nome_vaga, descricao_vaga, local_vaga, id_empresa) VALUES (?, ?, ?, ?)"

        try {
            def stmt = connection.prepareStatement(sql)
            stmt.setString(1, vaga.nomeVaga)
            stmt.setString(2, vaga.descricaoVaga)
            stmt.setString(3, vaga.localVaga)
            stmt.setInt(4, vaga.idEmpresa)

            stmt.executeUpdate()
        } catch (SQLException e) {
            logger.log(java.util.logging.Level.SEVERE, "Erro ao inserir vaga", e)
        } finally {
            connection.close()
        }
    }

    static List<Vaga> listarVagas() {
        def connection = DatabaseConnection.getConnection()
        def sql = "SELECT * FROM vaga"
        def listaVagas = []

        try {
            def stmt = connection.createStatement()
            def rs = stmt.executeQuery(sql)

            while (rs.next()) {
                def vaga = new Vaga(
                        rs.getInt("id_vaga"),
                        rs.getString("nome_vaga"),
                        rs.getString("descricao_vaga"),
                        rs.getString("local_vaga"),
                        rs.getInt("id_empresa")
                )
                listaVagas.add(vaga)
            }
        } catch (SQLException e) {
            logger.log(java.util.logging.Level.SEVERE, "Erro ao listar vagas", e)
        } finally {
            connection.close()
        }

        return listaVagas
    }
}
