package servico

import modelo.Candidato
import modelo.Empresa
import modelo.Competencia

class GerenciadorDeRecrutamento {
    List<Candidato> candidatos = []
    List<Empresa> empresas = []

    void adicionarCandidato(String nome, String email, String cpf, int idade, String estado, String cep, String descricao, List<Competencia> competencias) {
        candidatos.add(new Candidato(nome, email, cpf, idade, estado, cep, descricao, competencias))
    }

    void adicionarEmpresa(String nome, String email, String cnpj, String pais, String estado, String cep, String descricao, List<Competencia> competencias) {
        empresas.add(new Empresa(nome, email, cnpj, pais, estado, cep, descricao, competencias))
    }

    void cadastrarNovoCandidato(Scanner scanner) {
        try {
            def nome = solicitarInput("Nome", scanner)
            if (nome == '/cancelar') return

            def email = solicitarInput("Email", scanner)
            if (email == '/cancelar') return

            def cpf = solicitarInput("CPF", scanner)
            if (cpf == '/cancelar') return

            def idade = null
            while (idade == null) {
                def idadeStr = solicitarInput("Idade", scanner)
                if (idadeStr == '/cancelar') return
                try {
                    idade = idadeStr.toInteger()
                } catch (NumberFormatException ignored) {
                    println "Entrada inválida para a idade. Por favor, insira um número válido."
                }
            }

            def estado = solicitarInput("Estado", scanner)
            if (estado == '/cancelar') return

            def cep = solicitarInput("CEP", scanner)
            if (cep == '/cancelar') return

            def descricao = solicitarInput("Descrição", scanner)
            if (descricao == '/cancelar') return

            def competencias = solicitarCompetencias(scanner)
            if (competencias == null) return

            adicionarCandidato(nome, email, cpf, idade, estado, cep, descricao, competencias)
            println "Candidato adicionado com sucesso."
        } catch (Exception e) {
            println "Erro ao adicionar candidato: ${e.message}"
        }
    }


    void cadastrarNovaEmpresa(Scanner scanner) {
        try {
            def nome = solicitarInput("Nome", scanner)
            if (nome == '/cancelar') return

            def email = solicitarInput("Email", scanner)
            if (email == '/cancelar') return

            def cnpj = solicitarInput("CNPJ", scanner)
            if (cnpj == '/cancelar') return

            def pais = solicitarInput("País", scanner)
            if (pais == '/cancelar') return

            def estado = solicitarInput("Estado", scanner)
            if (estado == '/cancelar') return

            def cep = solicitarInput("CEP", scanner)
            if (cep == '/cancelar') return

            def descricao = solicitarInput("Descrição", scanner)
            if (descricao == '/cancelar') return

            def competencias = solicitarCompetencias(scanner)
            if (competencias == null) return

            adicionarEmpresa(nome, email, cnpj, pais, estado, cep, descricao, competencias)
            println "Empresa adicionada com sucesso."
        } catch (Exception e) {
            println "Erro ao adicionar empresa: ${e.message}"
        }
    }

    List<Candidato> listarCandidatos() {
        return candidatos
    }

    List<Empresa> listarEmpresas() {
        return empresas
    }

    private static String solicitarInput(String campo, Scanner scanner) {
        print "$campo (ou digite /cancelar para desistir): "
        return scanner.nextLine()
    }

    private static List<Competencia> solicitarCompetencias(Scanner scanner) {
        List<Competencia> competencias = []
        while (true) {
            def competencia = solicitarInput("Competência (ou digite /fim para finalizar)", scanner)
            if (competencia == '/fim') break
            if (competencia == '/cancelar') return null
            competencias.add(new Competencia(competencia))
        }
        return competencias
    }
}
