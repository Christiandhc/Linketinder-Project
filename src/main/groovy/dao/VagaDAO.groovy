package dao

import modelo.Vaga
import database.DatabaseConnection
import java.sql.SQLException

class VagaDAO {

    static void inserirVaga(Vaga vaga) {
        def connection = DatabaseConnection.getConnection()
        def sql = "INSERT INTO vaga (nome_vaga, descricao_vaga, local_vaga, id_empresa) VALUES (?, ?, ?, ?)"  // Corrigido para refletir os nomes corretos das colunas

        try {
            def stmt = connection.prepareStatement(sql)
            stmt.setString(1, vaga.nomeVaga)         // Usando nomeVaga
            stmt.setString(2, vaga.descricaoVaga)    // Usando descricaoVaga
            stmt.setString(3, vaga.localVaga)        // Usando localVaga
            stmt.setInt(4, vaga.idEmpresa)           // Usando idEmpresa

            stmt.executeUpdate()
        } catch (SQLException e) {
            println "Erro ao inserir vaga: ${e.message}"
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
                        rs.getInt("id_vaga"),           // ID da vaga
                        rs.getString("nome_vaga"),      // Nome da vaga
                        rs.getString("descricao_vaga"), // Descrição da vaga
                        rs.getString("local_vaga"),     // Local da vaga
                        rs.getInt("id_empresa")         // ID da empresa
                )
                listaVagas.add(vaga)
            }
        } catch (SQLException e) {
            println "Erro ao listar vagas: ${e.message}"
        } finally {
            connection.close()
        }

        return listaVagas
    }
}
