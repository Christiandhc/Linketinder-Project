package service


import java.time.LocalDate
import model.Competencia

class GerenciadorDeRecrutamento {
    private final RecrutamentoService recrutamentoService

    GerenciadorDeRecrutamento(RecrutamentoService recrutamentoService) {
        this.recrutamentoService = recrutamentoService
    }

    void cadastrarNovoCandidato(Scanner scanner) {
        try {
            String nome = EntradaDados.obterInput("Nome", scanner)
            String sobrenome = EntradaDados.obterInput("Sobrenome", scanner)
            LocalDate dataNascimento = EntradaDados.obterDataDeNascimento(scanner)
            String email = EntradaDados.obterInput("Email", scanner)
            String cpf = EntradaDados.obterInput("CPF", scanner)
            String pais = EntradaDados.obterInput("País", scanner)
            String cep = EntradaDados.obterInput("CEP", scanner)
            String descricao = EntradaDados.obterInput("Descrição", scanner)
            String senha = EntradaDados.obterInput("Senha", scanner)
            List<Competencia> competencias = EntradaDados.obterCompetencias(scanner)

            recrutamentoService.adicionarCandidato(nome, sobrenome, dataNascimento, email, cpf, pais, cep, descricao, senha, competencias)
            println "Candidato adicionado com sucesso."
        } catch (Exception e) {
            println "Erro ao adicionar candidato: ${e.message}"
        }
    }

    void cadastrarNovaEmpresa(Scanner scanner) {
        try {
            int idEmpresa = EntradaDados.obterIdEmpresa(scanner)
            String nome = EntradaDados.obterInput("Nome", scanner)
            String emailCorporativo = EntradaDados.obterInput("Email Corporativo", scanner)
            String cnpj = EntradaDados.obterInput("CNPJ", scanner)
            String pais = EntradaDados.obterInput("País", scanner)
            String cep = EntradaDados.obterInput("CEP", scanner)
            String descricao = EntradaDados.obterInput("Descrição", scanner)
            List<Competencia> competencias = EntradaDados.obterCompetencias(scanner)

            recrutamentoService.adicionarEmpresa(idEmpresa, nome, emailCorporativo, cnpj, pais, cep, descricao, competencias)
            println "Empresa adicionada com sucesso."
        } catch (Exception e) {
            println "Erro ao adicionar empresa: ${e.message}"
        }
    }

    List listarCandidatos() {
        return recrutamentoService.listarCandidatos()
    }

    List listarEmpresas() {
        return recrutamentoService.listarEmpresas()
    }
}
