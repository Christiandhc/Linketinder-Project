package servico

import modelo.Competencia
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Scanner

class EntradaDados {
    static String obterInput(String campo, Scanner scanner) {
        print "$campo (ou digite /cancelar para desistir): "
        def input = scanner.nextLine()
        if (input.equalsIgnoreCase("/cancelar")) throw new IllegalArgumentException("Operação cancelada pelo usuário.")
        return input
    }

    static LocalDate obterDataDeNascimento(Scanner scanner) {
        while (true) {
            String dataStr = obterInput("Data de Nascimento (YYYY-MM-DD)", scanner)
            try {
                return LocalDate.parse(dataStr, DateTimeFormatter.ISO_LOCAL_DATE)
            } catch (Exception e) {
                println "Data inválida. Por favor, use o formato YYYY-MM-DD."
            }
        }
    }

    static int obterIdEmpresa(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(obterInput("ID da Empresa", scanner))
            } catch (NumberFormatException e) {
                println "ID inválido. Por favor, insira um número."
            }
        }
    }

    static List<Competencia> obterCompetencias(Scanner scanner) {
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
