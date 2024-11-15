package modelo

import spock.lang.Specification

class EmpresaSpec extends Specification {

    def "Deve criar uma empresa corretamente"() {
        given: "As informações necessárias para a criação de uma empresa"
        Empresa empresa = Mock(Empresa)

        when: "A empresa é criada"
        empresa.nome >> "Stark Industries"
        empresa.email >> "contato@starkindustries.com"
        empresa.cnpj >> "12345678000100"
        empresa.pais >> "Westeros"
        empresa.cep >> "54321-876"
        empresa.descricao >> "Empresa de tecnologia"
        empresa.competencias >> [new Competencia(1, "DevOps"), new Competencia(2, "CyberSegurança")]

        then: "A empresa deve ser criada com os atributos corretos"
        empresa.nome == "Stark Industries"
        empresa.email == "contato@starkindustries.com"
        empresa.cnpj == "12345678000100"
        empresa.pais == "Westeros"
        empresa.cep == "54321-876"
        empresa.descricao == "Empresa de tecnologia"
        empresa.competencias*.nomeCompetencia.containsAll(["DevOps", "CyberSegurança"])
    }
}

