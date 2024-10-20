package servico

import modelo.Candidato
import modelo.Empresa
import modelo.Competencia
import dao.CandidatoDAO
import dao.EmpresaDAO
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Scanner

class GerenciadorDeRecrutamento {
    CandidatoDAO candidatoDAO = new CandidatoDAO()
    EmpresaDAO empresaDAO = new EmpresaDAO()

    void adicionarCandidato(String nome, String sobrenome, LocalDate dataNascimento, String email, String cpf, String pais, String cep, String descricao, String senha, List<Competencia> competencias) {
        Candidato candidato = new Candidato(nome, sobrenome, dataNascimento, email, cpf, pais, cep, descricao, senha, competencias)
        candidatoDAO.adicionarCandidato(candidato)
    }

    void adicionarEmpresa(int idEmpresa, String nome, String emailCorporativo, String cnpj, String pais, String cep, String descricao, List<Competencia> competencias) {
        Empresa empresa = new Empresa(idEmpresa, nome, emailCorporativo, cnpj, pais, cep, descricao, competencias)
        empresaDAO.adicionarEmpresa(empresa)
    }

    void cadastrarNovoCandidato(Scanner scanner) {
        try {
            def nome = solicitarInput("Nome", scanner)
            if (nome == '/cancelar') return

            def sobrenome = solicitarInput("Sobrenome", scanner)
            if (sobrenome == '/cancelar') return

            LocalDate dataNascimento = null
            while (dataNascimento == null) {
                def dataStr = solicitarInput("Data de Nascimento (YYYY-MM-DD)", scanner)
                if (dataStr == '/cancelar') return
                try {
                    dataNascimento = LocalDate.parse(dataStr, DateTimeFormatter.ISO_LOCAL_DATE)
                } catch (Exception e) {
                    println "Data inválida. Por favor, use o formato YYYY-MM-DD."
                }
            }

            def email = solicitarInput("Email", scanner)
            if (email == '/cancelar') return

            def cpf = solicitarInput("CPF", scanner)
            if (cpf == '/cancelar') return

            def pais = solicitarInput("País", scanner)
            if (pais == '/cancelar') return

            def cep = solicitarInput("CEP", scanner)
            if (cep == '/cancelar') return

            def descricao = solicitarInput("Descrição", scanner)
            if (descricao == '/cancelar') return

            def senha = solicitarInput("Senha", scanner)
            if (senha == '/cancelar') return

            def competencias = solicitarCompetencias(scanner)
            if (competencias == null) return

            adicionarCandidato(nome, sobrenome, dataNascimento, email, cpf, pais, cep, descricao, senha, competencias)
            println "Candidato adicionado com sucesso."
        } catch (Exception e) {
            println "Erro ao adicionar candidato: ${e.message}"
        }
    }

    void cadastrarNovaEmpresa(Scanner scanner) {
        try {
            def idEmpresa = solicitarInput("ID da Empresa", scanner)
            if (idEmpresa == '/cancelar') return

            def nome = solicitarInput("Nome", scanner)
            if (nome == '/cancelar') return

            def emailCorporativo = solicitarInput("Email Corporativo", scanner)
            if (emailCorporativo == '/cancelar') return

            def cnpj = solicitarInput("CNPJ", scanner)
            if (cnpj == '/cancelar') return

            def pais = solicitarInput("País", scanner)
            if (pais == '/cancelar') return

            def cep = solicitarInput("CEP", scanner)
            if (cep == '/cancelar') return

            def descricao = solicitarInput("Descrição", scanner)
            if (descricao == '/cancelar') return

            def competencias = solicitarCompetencias(scanner)
            if (competencias == null) return

            adicionarEmpresa(idEmpresa.toInteger(), nome, emailCorporativo, cnpj, pais, cep, descricao, competencias)
            println "Empresa adicionada com sucesso."
        } catch (Exception e) {
            println "Erro ao adicionar empresa: ${e.message}"
        }
    }

    List<Candidato> listarCandidatos() {
        return candidatoDAO.listarTodos()
    }

    List<Empresa> listarEmpresas() {
        return empresaDAO.listarTodos()
    }

    private static String solicitarInput(String campo, Scanner scanner) {
        print "$campo (ou digite /cancelar para desistir): "
        return scanner.nextLine()
    }

    private static List<Competencia> solicitarCompetencias(Scanner scanner) {
        List<Competencia> competencias = []
        int contador = 1
        while (true) {
            def competenciaNome = solicitarInput("Competência (ou digite /fim para finalizar)", scanner)
            if (competenciaNome == '/fim') break
            if (competenciaNome == '/cancelar') return null

            competencias.add(new Competencia(contador++, competenciaNome))
        }
        return competencias
    }
}
