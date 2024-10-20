package dao

import modelo.Empresa
import database.DatabaseConnection
import java.sql.SQLException

class EmpresaDAO {

    static void inserirEmpresa(Empresa empresa) {
        def connection = DatabaseConnection.getConnection()
        def sql = "INSERT INTO empresa (nome, email, cnpj, pais, estado, cep, descricao) VALUES (?, ?, ?, ?, ?, ?, ?)"

        try {
            def stmt = connection.prepareStatement(sql)
            stmt.setString(1, empresa.nome)
            stmt.setString(2, empresa.emailCorporativo) // Corrigido para usar emailCorporativo
            stmt.setString(3, empresa.cnpj)
            stmt.setString(4, empresa.pais)
            stmt.setString(5, empresa.estado)
            stmt.setString(6, empresa.cep)
            stmt.setString(7, empresa.descricao)

            stmt.executeUpdate()
        } catch (SQLException e) {
            println "Erro ao inserir empresa: ${e.message}"
        } finally {
            connection.close()
        }
    }

    static List<Empresa> listarEmpresas() {
        def connection = DatabaseConnection.getConnection()
        def sql = "SELECT * FROM empresa"
        def listaEmpresas = []

        try {
            def stmt = connection.createStatement()
            def rs = stmt.executeQuery(sql)

            while (rs.next()) {
                def empresa = new Empresa(
                        rs.getInt("id_empresa"), // Supondo que você tenha um campo id_empresa no banco de dados
                        rs.getString("nome"),
                        rs.getString("email"), // Corrigido para usar emailCorporativo
                        rs.getString("cnpj"),
                        rs.getString("pais"),
                        rs.getString("estado"),
                        rs.getString("cep"),
                        rs.getString("descricao"),
                        [] // Passando uma lista vazia
                )
                listaEmpresas.add(empresa)
            }
        } catch (SQLException e) {
            println "Erro ao listar empresas: ${e.message}"
        } finally {
            connection.close()
        }

        return listaEmpresas
    }
}
