package modelo

import spock.lang.Specification

class CandidatoSpec extends Specification {

    def "Deve adicionar um novo candidato à lista de candidatos"() {
        given: "Uma lista vazia de candidatos e um novo candidato"
        List<Candidato> listaCandidatos = []
        Candidato novoCandidato = new Candidato("Arya Stark", "arya.stark@example.com", "123.456.789-00", 18, "Winterfell", "12345-678", "Desenvolvedora Java", [new Competencia("Java")])

        println "Lista de candidatos antes da adição: $listaCandidatos"

        when: "O novo candidato é adicionado à lista"
        listaCandidatos.add(novoCandidato)

        println "Novo candidato adicionado: ${novoCandidato.nome}"

        then: "O candidato deve ser adicionado à lista"
        println "Lista de candidatos antes: " + listaDeCandidatos
        listaDeCandidatos.size() == 1
        listaDeCandidatos[0] == candidato
        println "Lista de candidatos após adicionar: " + listaDeCandidatos

    }
}
