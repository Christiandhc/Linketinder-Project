package modelo

import spock.lang.Specification
import java.time.LocalDate // Adicione esta linha

class CandidatoSpec extends Specification {

    def "Deve adicionar um novo candidato à lista de candidatos"() {
        given: "Uma lista vazia de candidatos e um novo candidato"
        List<Candidato> listaCandidatos = []
        Candidato novoCandidato = new Candidato("Arya Stark", "Sobrenome", LocalDate.of(2000, 1, 1), "arya.stark@example.com", "123.456.789-00", "Winterfell", "12345-678", "Desenvolvedora Java", "senhaSegura", [new Competencia(1, "Java")])

        println "Lista de candidatos antes da adição: $listaCandidatos"

        when: "O novo candidato é adicionado à lista"
        listaCandidatos.add(novoCandidato)

        println "Novo candidato adicionado: ${novoCandidato.nome}"

        then: "O candidato deve ser adicionado à lista"
        listaCandidatos.size() == 1
        listaCandidatos[0] == novoCandidato
    }
}
