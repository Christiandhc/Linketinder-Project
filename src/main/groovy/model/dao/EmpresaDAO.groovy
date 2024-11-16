package model.dao

import model.Empresa

interface EmpresaDAO {
    void inserirEmpresa(Empresa empresa)
    List<Empresa> listarEmpresas()
}

