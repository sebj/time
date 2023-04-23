public class MissingDateTimeComponentsException internal constructor(
    message: String? = null,
    cause: Throwable? = null
) : IllegalArgumentException(message, cause)

public class DateTimeComponentOutOfBoundsException internal constructor(
    message: String? = null,
    cause: Throwable? = null
) : RuntimeException(message, cause)

public class IllegalTimeUnitException internal constructor(
    message: String? = null,
    cause: Throwable? = null
) : IllegalArgumentException(message, cause)
