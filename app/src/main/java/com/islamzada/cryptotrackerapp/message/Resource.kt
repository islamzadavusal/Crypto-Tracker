package com.islamzada.cryptotrackerapp.message

data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
    // The Resource class encapsulates the result of an operation, such as API calls or database queries.
    // It contains the status of the operation, the data returned (if any), and an optional message.

    companion object {
        // Provides factory methods to create instances of the Resource class.

        // Creates a success instance of the Resource class.
        // It takes the data as a parameter and sets the status to Status.SUCCESS.
        // The message is set to null.

        fun <T> success(data: T?): Resource<T & Any> {
            return Resource(Status.SUCCESS, data, null)
        }

        // Creates an error instance of the Resource class.
        // It takes the error message and data as parameters and sets the status to Status.ERROR.

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

        // Creates a loading instance of the Resource class.
        // It takes the data as a parameter and sets the status to Status.LOADING.
        // The message is set to null.

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null)

        }
    }
}