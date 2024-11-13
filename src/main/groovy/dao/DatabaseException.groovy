package dao

class DatabaseException extends Exception {

    DatabaseException(String message) {
        super(message)
    }

    DatabaseException(String message, Throwable cause) {
        super(message, cause)
    }
}
