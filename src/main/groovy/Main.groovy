import servico.GerenciadorDeRecrutamento
import java.util.Scanner

class Main {
    static void main(String[] args) {
        def sistemaRecrutamento = new GerenciadorDeRecrutamento()
        iniciarSistema(sistemaRecrutamento)
    }

    static void iniciarSistema(GerenciadorDeRecrutamento sistemaRecrutamento) {
        def scanner = new Scanner(System.in)
        boolean continuar = true

        while (continuar) {
            exibirMenu()
            continuar = processarOpcao(scanner, sistemaRecrutamento)
        }
    }

    static void exibirMenu() {
        println '''
            === Menu Principal ===
            1. Listar Candidatos
            2. Listar Empresas
            3. Adicionar Candidato
            4. Adicionar Empresa
            5. Sair
            '''
        print "Escolha uma opção: "
    }

    static boolean processarOpcao(Scanner scanner, GerenciadorDeRecrutamento sistemaRecrutamento) {
        try {
            int opcao = obterOpcao(scanner)
            return executarOpcao(opcao, sistemaRecrutamento, scanner)
        } catch (InputMismatchException e) {
            println "Entrada inválida. Por favor, insira um número válido."
            scanner.nextLine()
            return true
        }
    }

    static int obterOpcao(Scanner scanner) {
        def opcao = scanner.nextInt()
        scanner.nextLine()
        return opcao
    }

    static boolean executarOpcao(int opcao, GerenciadorDeRecrutamento sistemaRecrutamento, Scanner scanner) {
        switch (opcao) {
            case 1:
                listarCandidatos(sistemaRecrutamento)
                break
            case 2:
                listarEmpresas(sistemaRecrutamento)
                break
            case 3:
                adicionarCandidato(sistemaRecrutamento, scanner)
                break
            case 4:
                adicionarEmpresa(sistemaRecrutamento, scanner)
                break
            case 5:
                println 'Saindo...'
                return false
            default:
                println 'Opção inválida, tente novamente.'
        }
        return true
    }

    static void listarCandidatos(GerenciadorDeRecrutamento sistemaRecrutamento) {
        println '=== Lista de Candidatos ==='
        sistemaRecrutamento.listarCandidatos().each { println it }
    }

    static void listarEmpresas(GerenciadorDeRecrutamento sistemaRecrutamento) {
        println '=== Lista de Empresas ==='
        sistemaRecrutamento.listarEmpresas().each { println it }
    }

    static void adicionarCandidato(GerenciadorDeRecrutamento sistemaRecrutamento, Scanner scanner) {
        println '=== Cadastro de Novo Candidato ==='
        sistemaRecrutamento.cadastrarNovoCandidato(scanner)
    }

    static void adicionarEmpresa(GerenciadorDeRecrutamento sistemaRecrutamento, Scanner scanner) {
        println '=== Cadastro de Nova Empresa ==='
        sistemaRecrutamento.cadastrarNovaEmpresa(scanner)
    }
}


