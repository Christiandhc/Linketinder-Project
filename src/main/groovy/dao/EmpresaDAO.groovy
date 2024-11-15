package dao

import modelo.Empresa

interface EmpresaDAO {
    void inserirEmpresa(Empresa empresa)
    List<Empresa> listarEmpresas()
}

