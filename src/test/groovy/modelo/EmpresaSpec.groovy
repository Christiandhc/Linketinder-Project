package modelo

import spock.lang.Specification

class EmpresaSpec extends Specification {

    def "Deve criar uma empresa corretamente"() {
        given:
        Empresa empresa

        when:
        empresa = new Empresa("Stark Industries", "contato@starkindustries.com", "12.345.678/0001-00", "Westeros", "Winterfell", "54321-876", "Empresa de tecnologia", [new Competencia("DevOps"), new Competencia("CyberSegurança")])

        then:
        empresa.nome == "Stark Industries"
        empresa.email == "contato@starkindustries.com"
        empresa.cnpj == "12.345.678/0001-00"
        empresa.pais == "Westeros"
        empresa.estado == "Winterfell"
        empresa.cep == "54321-876"
        empresa.descricao == "Empresa de tecnologia"
        empresa.competencias*.nome.containsAll(["DevOps", "CyberSegurança"])
    }
}
