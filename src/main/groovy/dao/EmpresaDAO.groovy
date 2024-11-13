package dao

import modelo.Empresa
import database.DatabaseConnection
import java.sql.Connection
import java.sql.SQLException
import java.util.logging.Logger

class EmpresaDAO {

    private static final Logger logger = Logger.getLogger(EmpresaDAO.class.name)

    static void inserirEmpresa(Empresa empresa) {
        Connection connection = DatabaseConnection.getConnection()
        String sql = "INSERT INTO empresa (nome, email, cnpj, pais, cep, descricao) VALUES (?, ?, ?, ?, ?, ?)"

        try {
            def stmt = connection.prepareStatement(sql)
            stmt.setString(1, empresa.nome)
            stmt.setString(2, empresa.emailCorporativo)
            stmt.setString(3, empresa.cnpj)
            stmt.setString(4, empresa.pais)
            stmt.setString(5, empresa.cep)
            stmt.setString(6, empresa.descricao)

            stmt.executeUpdate()
        } catch (SQLException e) {
            logger.log(java.util.logging.Level.SEVERE, "Erro ao inserir empresa", e)
        } finally {
            connection.close()
        }
    }

    static List<Empresa> listarEmpresas() {
        Connection connection = DatabaseConnection.getConnection()
        String sql = "SELECT * FROM empresa"
        List<Empresa> listaEmpresas = []

        try {
            def stmt = connection.createStatement()
            def rs = stmt.executeQuery(sql)

            while (rs.next()) {
                Empresa empresa = new Empresa(
                        rs.getInt("id_empresa"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("cnpj"),
                        rs.getString("pais"),
                        rs.getString("cep"),
                        rs.getString("descricao"),
                        [] // Passando uma lista vazia
                )
                listaEmpresas.add(empresa)
            }
        } catch (SQLException e) {
            logger.log(java.util.logging.Level.SEVERE, "Erro ao listar empresas", e)
        } finally {
            connection.close()
        }

        return listaEmpresas
    }
}


