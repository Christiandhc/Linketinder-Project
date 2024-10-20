import servico.GerenciadorDeRecrutamento
import modelo.Competencia
import dao.CandidatoDAO
import modelo.Candidato
import java.util.Scanner

class Main {
    static void main(String[] args) {
        def sistema = new GerenciadorDeRecrutamento()
        exibirMenu(sistema)
    }

    static void exibirMenu(GerenciadorDeRecrutamento sistema) {
        def scanner = new Scanner(System.in)
        boolean sair = false

        while (!sair) {
            println '''
            Menu Principal:
            1. Listar Candidatos
            2. Listar Empresas
            3. Adicionar Candidato
            4. Adicionar Empresa
            5. Sair
            '''
            print "Escolha uma opção: "
            try {
                def opcao = scanner.nextInt()
                scanner.nextLine()

                switch (opcao) {
                    case 1:
                        println 'Candidatos:'
                        sistema.listarCandidatos().each { println it }
                        break
                    case 2:
                        println 'Empresas:'
                        sistema.listarEmpresas().each { println it }
                        break
                    case 3:
                        sistema.cadastrarNovoCandidato(scanner)
                        break
                    case 4:
                        sistema.cadastrarNovaEmpresa(scanner)
                        break
                    case 5:
                        println 'Saindo...'
                        sair = true
                        break
                    default:
                        println 'Opção inválida, tente novamente.'
                }
            } catch (InputMismatchException ignored) {
                println "Entrada inválida. Por favor, insira um número válido."
                scanner.nextLine()
            }
        }
    }
}

