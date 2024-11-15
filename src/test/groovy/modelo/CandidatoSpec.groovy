package modelo

import spock.lang.Specification
import java.time.LocalDate

class CandidatoSpec extends Specification {

    def "Deve adicionar um novo candidato à lista de candidatos"() {
        given: "Uma lista vazia de candidatos e um novo candidato"
        List<Candidato> listaCandidatos = criarListaVazia()
        Candidato novoCandidato = Mock(Candidato)

        when: "O novo candidato é adicionado à lista"
        listaCandidatos.add(novoCandidato)

        then: "O candidato deve ser adicionado à lista"
        listaCandidatos.size() == 1
        listaCandidatos[0] == novoCandidato
    }

    private List<Candidato> criarListaVazia() {
        return []
    }
}

