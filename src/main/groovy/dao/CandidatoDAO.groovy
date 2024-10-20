package dao

import modelo.Candidato
import database.DatabaseConnection
import java.sql.SQLException

class CandidatoDAO {

    static void inserirCandidato(Candidato candidato) {
        def connection = DatabaseConnection.getConnection()
        def sql = "INSERT INTO candidato (nome, sobrenome, data_nascimento, email, cpf, pais, cep, descricao_pessoal, senha) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"

        try {
            def stmt = connection.prepareStatement(sql)
            stmt.setString(1, candidato.nome)
            stmt.setString(2, candidato.sobrenome)
            stmt.setDate(3, java.sql.Date.valueOf(candidato.dataNascimento.toString()))  // Conversão de LocalDate para java.sql.Date
            stmt.setString(4, candidato.email)
            stmt.setString(5, candidato.cpf)
            stmt.setString(6, candidato.pais) // Corrigido para usar 'pais'
            stmt.setString(7, candidato.cep)
            stmt.setString(8, candidato.descricaoPessoal) // Corrigido para usar 'descricaoPessoal'
            stmt.setString(9, candidato.senha)

            stmt.executeUpdate()
        } catch (SQLException e) {
            println "Erro ao inserir candidato: ${e.message}"
        } finally {
            connection.close()
        }
    }

    static List<Candidato> listarCandidatos() {
        def connection = DatabaseConnection.getConnection()
        def sql = "SELECT * FROM candidato"
        def listaCandidatos = []

        try {
            def stmt = connection.createStatement()
            def rs = stmt.executeQuery(sql)

            while (rs.next()) {
                def candidato = new Candidato(
                        rs.getString("nome"),
                        rs.getString("sobrenome"), // Corrigido para obter sobrenome
                        rs.getDate("data_nascimento").toLocalDate(), // Conversão para LocalDate
                        rs.getString("email"),
                        rs.getString("cpf"),
                        rs.getString("pais"), // Corrigido para obter país
                        rs.getString("cep"),
                        rs.getString("descricao_pessoal"),
                        "",
                        []
                )
                listaCandidatos.add(candidato)
            }
        } catch (SQLException e) {
            println "Erro ao listar candidatos: ${e.message}"
        } finally {
            connection.close()
        }

        return listaCandidatos
    }
}
