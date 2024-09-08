package modelo

import spock.lang.Specification

class EmpresaSpec extends Specification {

    def "Deve criar uma empresa com nome, email, cnpj, estado, cep, pais, descricao e competencias"() {
        given: "Um conjunto de informações para a empresa"
        String nome = "Stark Industries"
        String email = "contato@starkindustries.com"
        String cnpj = "12.345.678/0001-00"
        String estado = "Winterfell"
        String cep = "54321-876"
        String pais = "Westeros"
        String descricao = "Empresa de tecnologia"
        List<Competencia> competencias = [new Competencia("DevOps"), new Competencia("CyberSegurança")]

        when: "A empresa é criada"
        Empresa empresa = new Empresa(nome, email, cnpj, estado, cep, pais, descricao, competencias)

        then: "Os dados da empresa devem ser corretamente atribuídos"
        empresa.nome == nome
        empresa.email == email
        empresa.cnpj == cnpj
        empresa.estado == estado
        empresa.cep == cep
        empresa.pais == pais
        empresa.descricao == descricao
        empresa.competencias == competencias
    }
}
