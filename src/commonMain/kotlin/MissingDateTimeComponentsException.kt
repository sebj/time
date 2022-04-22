class MissingDateTimeComponentsException(
    message: String? = null,
    cause: Throwable? = null
) : IllegalArgumentException(message, cause) {
    @Suppress("unused")
    constructor(cause: Throwable) : this(null, cause)
}
