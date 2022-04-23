class MissingDateTimeComponentsException internal constructor(
    message: String? = null,
    cause: Throwable? = null
) : IllegalArgumentException(message, cause)

class DateTimeComponentOutOfBoundsException internal constructor(
    message: String? = null,
    cause: Throwable? = null
) : RuntimeException(message, cause)

class IllegalTimeUnitException internal constructor(
    message: String? = null,
    cause: Throwable? = null
) : IllegalArgumentException(message, cause)
