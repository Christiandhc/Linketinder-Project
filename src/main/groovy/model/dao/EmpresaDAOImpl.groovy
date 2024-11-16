package model.dao

import model.Empresa
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException
import java.util.logging.Logger

class EmpresaDAOImpl extends BaseDAO implements EmpresaDAO {

    private static final Logger logger = Logger.getLogger(EmpresaDAOImpl.class.name)

    EmpresaDAOImpl(Connection connection) {
        super(connection)
    }

    @Override
    void inserirEmpresa(Empresa empresa) {
        String sql = "INSERT INTO empresa (nome, email, cnpj, pais, cep, descricao) VALUES (?, ?, ?, ?, ?, ?)"

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preencherCamposEmpresa(empresa, preparedStatement)
            preparedStatement.executeUpdate()
        } catch (SQLException e) {
            logger.log(java.util.logging.Level.SEVERE, "Erro ao inserir empresa", e)
            throw new DatabaseException("Erro ao inserir empresa", e)
        }
    }

    @Override
    List<Empresa> listarEmpresas() {
        String sql = "SELECT * FROM empresa"
        List<Empresa> listaEmpresas = []

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                listaEmpresas.add(mapearEmpresa(rs))
            }
        } catch (SQLException e) {
            logger.log(java.util.logging.Level.SEVERE, "Erro ao listar empresas", e)
            throw new DatabaseException("Erro ao listar empresas", e)
        }

        return listaEmpresas
    }

    private void preencherCamposEmpresa(Empresa empresa, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, empresa.nome)
        preparedStatement.setString(2, empresa.emailCorporativo)
        preparedStatement.setString(3, empresa.cnpj)
        preparedStatement.setString(4, empresa.pais)
        preparedStatement.setString(5, empresa.cep)
        preparedStatement.setString(6, empresa.descricao)
    }

    private Empresa mapearEmpresa(ResultSet rs) throws SQLException {
        return new Empresa(
                rs.getInt("id_empresa"),
                rs.getString("nome"),
                rs.getString("email"),
                rs.getString("cnpj"),
                rs.getString("pais"),
                rs.getString("cep"),
                rs.getString("descricao"),
                []
        )
    }
}
