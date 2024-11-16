package model.dao

import java.sql.Connection
import java.sql.SQLException
import java.util.logging.Logger

abstract class BaseDAO {
    protected final Connection connection
    private static final Logger logger = Logger.getLogger(BaseDAO.class.name)

    BaseDAO(Connection connection) {
        this.connection = connection
    }

    protected void closeConnection() {
        try {
            connection?.close()
        } catch (SQLException e) {
            logger.severe("Erro ao fechar a conexão: ${e.message}")
        }
    }
}
