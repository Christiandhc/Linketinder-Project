package dao

import modelo.Empresa
import database.DatabaseConnection
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException
import java.util.logging.Logger

class EmpresaDAO {

    private static final Logger logger = Logger.getLogger(EmpresaDAO.class.name)

    static void inserirEmpresa(Empresa empresa) {
        Connection connection = DatabaseConnection.getConnection()
        String sql = "INSERT INTO empresa (nome, email, cnpj, pais, cep, descricao) VALUES (?, ?, ?, ?, ?, ?)"

        try {
            def preparedStatement = connection.prepareStatement(sql)
            preencherCamposEmpresa(empresa, preparedStatement)
            preparedStatement.executeUpdate()
        } catch (SQLException e) {
            logger.log(java.util.logging.Level.SEVERE, "Erro ao inserir empresa", e)
            throw new DatabaseException("Erro ao inserir empresa", e)
        } finally {
            connection.close()
        }
    }

    static List<Empresa> listarEmpresas() {
        Connection connection = DatabaseConnection.getConnection()
        String sql = "SELECT * FROM empresa"
        List<Empresa> listaEmpresas = []

        try {
            def preparedStatement = connection.createStatement()
            def rs = preparedStatement.executeQuery(sql)

            while (rs.next()) {
                listaEmpresas.add(mapearEmpresa(rs))
            }
        } catch (SQLException e) {
            logger.log(java.util.logging.Level.SEVERE, "Erro ao listar empresas", e)
            throw new DatabaseException("Erro ao listar empresas", e)
        } finally {
            connection.close()
        }

        return listaEmpresas
    }

    private static void preencherCamposEmpresa(Empresa empresa, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, empresa.nome)
        preparedStatement.setString(2, empresa.emailCorporativo)
        preparedStatement.setString(3, empresa.cnpj)
        preparedStatement.setString(4, empresa.pais)
        preparedStatement.setString(5, empresa.cep)
        preparedStatement.setString(6, empresa.descricao)
    }

    private static Empresa mapearEmpresa(ResultSet rs) throws SQLException {
        return new Empresa(
                rs.getInt("id_empresa"),
                rs.getString("nome"),
                rs.getString("email"),
                rs.getString("cnpj"),
                rs.getString("pais"),
                rs.getString("cep"),
                rs.getString("descricao"),
                []
        )
    }
}



