package controller

import service.GerenciadorDeRecrutamento

class InterfaceUsuario {
    private final GerenciadorDeRecrutamento sistemaRecrutamento
    private final Scanner scanner = new Scanner(System.in)

    InterfaceUsuario(GerenciadorDeRecrutamento sistemaRecrutamento) {
        this.sistemaRecrutamento = sistemaRecrutamento
    }

    void iniciar() {
        boolean continuar = true

        while (continuar) {
            exibirMenu()
            continuar = processarOpcao()
        }
    }

    private void exibirMenu() {
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

    private boolean processarOpcao() {
        try {
            int opcao = obterOpcao()
            return executarOpcao(opcao)
        } catch (InputMismatchException e) {
            println "Entrada inválida. Por favor, insira um número válido."
            scanner.nextLine()
            return true
        }
    }

    private int obterOpcao() {
        def opcao = scanner.nextInt()
        scanner.nextLine()
        return opcao
    }

    private boolean executarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                listarCandidatos()
                break
            case 2:
                listarEmpresas()
                break
            case 3:
                adicionarCandidato()
                break
            case 4:
                adicionarEmpresa()
                break
            case 5:
                println 'Saindo...'
                return false
            default:
                println 'Opção inválida, tente novamente.'
        }
        return true
    }

    private void listarCandidatos() {
        println '=== Lista de Candidatos ==='
        sistemaRecrutamento.listarCandidatos().each { println it }
    }

    private void listarEmpresas() {
        println '=== Lista de Empresas ==='
        sistemaRecrutamento.listarEmpresas().each { println it }
    }

    private void adicionarCandidato() {
        println '=== Cadastro de Novo Candidato ==='
        sistemaRecrutamento.cadastrarNovoCandidato(scanner)
    }

    private void adicionarEmpresa() {
        println '=== Cadastro de Nova Empresa ==='
        sistemaRecrutamento.cadastrarNovaEmpresa(scanner)
    }
}
