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
    private final CandidatoDAO candidatoDAO = new CandidatoDAO()
    private final EmpresaDAO empresaDAO = new EmpresaDAO()

    void adicionarCandidato(String nome, String sobrenome, LocalDate dataNascimento, String email, String cpf, String pais, String cep, String descricao, String senha, List<Competencia> competencias) {
        Candidato candidato = new Candidato(nome, sobrenome, dataNascimento, email, cpf, pais, cep, descricao, senha, competencias)
        candidatoDAO.inserirCandidato(candidato)
    }

    void adicionarEmpresa(int idEmpresa, String nome, String emailCorporativo, String cnpj, String pais, String cep, String descricao, List<Competencia> competencias) {
        Empresa empresa = new Empresa(idEmpresa, nome, emailCorporativo, cnpj, pais, cep, descricao, competencias)
        empresaDAO.inserirEmpresa(empresa)
    }

    void cadastrarNovoCandidato(Scanner scanner) {
        try {
            String nome = obterInput("Nome", scanner)
            String sobrenome = obterInput("Sobrenome", scanner)
            LocalDate dataNascimento = obterDataDeNascimento(scanner)
            String email = obterInput("Email", scanner)
            String cpf = obterInput("CPF", scanner)
            String pais = obterInput("País", scanner)
            String cep = obterInput("CEP", scanner)
            String descricao = obterInput("Descrição", scanner)
            String senha = obterInput("Senha", scanner)
            List<Competencia> competencias = obterCompetencias(scanner)

            adicionarCandidato(nome, sobrenome, dataNascimento, email, cpf, pais, cep, descricao, senha, competencias)
            println "Candidato adicionado com sucesso."
        } catch (Exception e) {
            println "Erro ao adicionar candidato: ${e.message}"
        }
    }

    void cadastrarNovaEmpresa(Scanner scanner) {
        try {
            int idEmpresa = obterIdEmpresa(scanner)
            String nome = obterInput("Nome", scanner)
            String emailCorporativo = obterInput("Email Corporativo", scanner)
            String cnpj = obterInput("CNPJ", scanner)
            String pais = obterInput("País", scanner)
            String cep = obterInput("CEP", scanner)
            String descricao = obterInput("Descrição", scanner)
            List<Competencia> competencias = obterCompetencias(scanner)

            adicionarEmpresa(idEmpresa, nome, emailCorporativo, cnpj, pais, cep, descricao, competencias)
            println "Empresa adicionada com sucesso."
        } catch (Exception e) {
            println "Erro ao adicionar empresa: ${e.message}"
        }
    }

    List<Candidato> listarCandidatos() {
        return candidatoDAO.listarCandidatos()

    }

    List<Empresa> listarEmpresas() {
        return empresaDAO.listarEmpresas()
    }

    private static String obterInput(String campo, Scanner scanner) {
        print "$campo (ou digite /cancelar para desistir): "
        def input = scanner.nextLine()
        if (input.equalsIgnoreCase("/cancelar")) throw new IllegalArgumentException("Operação cancelada pelo usuário.")
        return input
    }

    private static LocalDate obterDataDeNascimento(Scanner scanner) {
        while (true) {
            String dataStr = obterInput("Data de Nascimento (YYYY-MM-DD)", scanner)
            try {
                return LocalDate.parse(dataStr, DateTimeFormatter.ISO_LOCAL_DATE)
            } catch (Exception e) {
                println "Data inválida. Por favor, use o formato YYYY-MM-DD."
            }
        }
    }

    private static int obterIdEmpresa(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(obterInput("ID da Empresa", scanner))
            } catch (NumberFormatException e) {
                println "ID inválido. Por favor, insira um número."
            }
        }
    }

    private static List<Competencia> obterCompetencias(Scanner scanner) {
        List<Competencia> competencias = []
        int contador = 1
        while (true) {
            String nomeCompetencia = obterInput("Competência (ou digite /fim para finalizar)", scanner)
            if (nomeCompetencia.equalsIgnoreCase("/fim")) break
            competencias.add(new Competencia(contador++, nomeCompetencia))
        }
        return competencias
    }
}

