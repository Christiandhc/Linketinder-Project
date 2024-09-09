

import servico.GerenciadorDeRecrutamento
import modelo.Competencia


class Main {
    static void main(String[] args) {
        def sistema = inicializarSistema()
        exibirMenu(sistema)
    }

    // Inicializa o sistema com dados fictícios de candidatos e empresas
    static GerenciadorDeRecrutamento inicializarSistema() {
        def sistema = new GerenciadorDeRecrutamento()

        def competenciasCandidato1 = [new Competencia('Java'), new Competencia('Spring')]
        def competenciasCandidato2 = [new Competencia('Python'), new Competencia('Django')]
        def competenciasCandidato3 = [new Competencia('Angular'), new Competencia('JavaScript')]
        def competenciasCandidato4 = [new Competencia('SQL'), new Competencia('NoSQL')]
        def competenciasCandidato5 = [new Competencia('Data Science'), new Competencia('Machine Learning')]

        sistema.adicionarCandidato('Harry Potter', 'harry.potter@example.com', '123.456.789-00', 30, 'Londres', '12345-678', 'Desenvolvedor Python e Django', competenciasCandidato1)
        sistema.adicionarCandidato('Frodo Baggins', 'frodo.baggins@example.com', '222.333.444-55', 33, 'Condado', '56789-012', 'Especialista em Java e Spring', competenciasCandidato2)
        sistema.adicionarCandidato('Katniss Everdeen', 'katniss.everdeen@example.com', '333.444.555-66', 27, 'Panem', '67890-123', 'Desenvolvedora Front-end Angular', competenciasCandidato3)
        sistema.adicionarCandidato('Daenerys Targaryen', 'daenerys.targaryen@example.com', '444.555.666-77', 32, 'Essos', '78901-234', 'Analista de Banco de Dados SQL', competenciasCandidato4)
        sistema.adicionarCandidato('Jon Snow', 'jon.snow@example.com', '555.666.777-88', 35, 'Winterfell', '89012-345', 'Desenvolvedor Full Stack JavaScript', competenciasCandidato5)

        def competenciasEmpresa1 = [new Competencia('Java'), new Competencia('Spring')]
        def competenciasEmpresa2 = [new Competencia('Python'), new Competencia('Data Science')]
        def competenciasEmpresa3 = [new Competencia('Angular'), new Competencia('JavaScript')]
        def competenciasEmpresa4 = [new Competencia('SQL'), new Competencia('DevOps')]
        def competenciasEmpresa5 = [new Competencia('Machine Learning'), new Competencia('Big Data')]

        sistema.adicionarEmpresa('Gringotts Bank', 'contact@gringotts.com', '01.234.567/0001-00', 'Reino Unido', 'Londres', '12345-678', 'Banco de Magia e Finanças', competenciasEmpresa1)
        sistema.adicionarEmpresa('Umbrella Corporation', 'contact@umbrella.com', '02.345.678/0001-11', 'EUA', 'Raccoon City', '23456-789', 'Corporation de Tecnologia e Bioengenharia', competenciasEmpresa2)
        sistema.adicionarEmpresa('Stark Industries', 'contact@starkindustries.com', '03.456.789/0001-22', 'EUA', 'Nova York', '34567-890', 'Tecnologia Avançada e Inovação', competenciasEmpresa3)
        sistema.adicionarEmpresa('WICKED', 'contact@wicked.com', '04.567.890/0001-33', 'EUA', 'Phoenix', '45678-901', 'Experimentos e Pesquisa', competenciasEmpresa4)
        sistema.adicionarEmpresa('Vought International', 'contact@vought.com', '05.678.901/0001-44', 'EUA', 'Washington DC', '56789-012', 'Gestão de Super-heróis e Tecnologia', competenciasEmpresa5)

        return sistema
    }

    // Exibe o menu principal e gerencia as interações do usuário
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

