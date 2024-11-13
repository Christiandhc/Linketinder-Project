package modelo

import spock.lang.Specification
import java.time.LocalDate

class CandidatoSpec extends Specification {

    def "Deve adicionar um novo candidato à lista de candidatos"() {
        given: "Uma lista vazia de candidatos e um novo candidato"
        List<Candidato> listaCandidatos = criarListaVaziaDeCandidatos()
        Candidato novoCandidato = criarNovoCandidato()

        when: "O novo candidato é adicionado à lista"
        listaCandidatos.add(novoCandidato)

        then: "O candidato deve ser adicionado à lista"
        listaCandidatos.size() == 1
        listaCandidatos[0] == novoCandidato
    }

    private List<Candidato> criarListaVaziaDeCandidatos() {
        return []
    }

    private Candidato criarNovoCandidato() {
        return new Candidato(
                "Arya Stark", "Sobrenome", LocalDate.of(2000, 1, 1),
                "arya.stark@example.com", "123.456.789-00", "Winterfell",
                "12345-678", "Desenvolvedora Java", "senhaSegura",
                [new Competencia(1, "Java")]
        )
    }
}
