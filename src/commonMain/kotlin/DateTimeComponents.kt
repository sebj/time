import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlin.reflect.KProperty0

/**
 * A date or time specified in terms of units.
 */
internal data class DateTimeComponents(
    val year: Int,
    val month: kotlinx.datetime.Month? = null,
    val dayOfMonth: Int? = null,
    val hour: Int? = null,
    val minute: Int? = null,
    val second: Int? = null,
    val nanosecond: Int? = null
) : Comparable<DateTimeComponents> {

    @Throws(DateTimeComponentOutOfBoundsException::class)
    internal fun validate() {
        dayOfMonth?.also {
            if (it < 1 || it > 31) {
                throw DateTimeComponentOutOfBoundsException("Day not in valid range: $it is not in the range 1 to 31")
            }
        }

        hour?.also {
            if (it < 0 || it > 23) {
                throw DateTimeComponentOutOfBoundsException("Hour not in valid range: $it is not in the range 0 to 23")
            }
        }

        minute?.also {
            if (it < 0 || it > 59) {
                throw DateTimeComponentOutOfBoundsException("Minute not in valid range: $it is not in the range 0 to 59")
            }
        }

        second?.also {
            if (it < 0 || it > 59) {
                throw DateTimeComponentOutOfBoundsException("Second not in valid range: $it is not in the range 0 to 59")
            }
        }

        nanosecond?.also {
            if (it < 0 || it > 1_000_000_000) {
                throw DateTimeComponentOutOfBoundsException("Nanosecond not in valid range: $it is not in the range 0 to 1,000,000,000 (1e+9)")
            }
        }
    }

    override fun compareTo(other: DateTimeComponents) = compareValuesBy(
        this,
        other,
        { it.year },
        { it.month },
        { it.dayOfMonth },
        { it.hour },
        { it.minute },
        { it.second },
        { it.nanosecond }
    )

    override fun toString() = buildString {
        append(DateTimeComponents::class.simpleName)
        append("(")

        val components = buildList {
            add("year=$year")
            month?.let { add("month=$it") }
            dayOfMonth?.let { add("dayOfMonth=$it") }
            hour?.let { add("hour=$it") }
            minute?.let { add("minute=$it") }
            second?.let { add("second=$it") }
            nanosecond?.let { add("nanosecond=$it") }
        }.joinToString(separator = ", ")

        append(components)
        append(")")
    }
}

@Throws(MissingDateTimeComponentsException::class)
internal fun DateTimeComponents.toLocalDate(): LocalDate {
    if (month != null && dayOfMonth != null) {
        return LocalDate(
            year = year,
            month = month,
            dayOfMonth = dayOfMonth
        )
    }

    throw MissingDateTimeComponentsException("Missing month and/or day components")
}

@Throws(MissingDateTimeComponentsException::class)
internal fun DateTimeComponents.toLocalDateTime(): LocalDateTime {
    if (month != null && dayOfMonth != null && hour != null) {
        return LocalDateTime(
            year = year,
            month = month,
            dayOfMonth = dayOfMonth,
            hour = hour,
            minute = minute ?: 0,
            second = second ?: 0,
            nanosecond = nanosecond ?: 0
        )
    }

    throw MissingDateTimeComponentsException("Missing month, day, and/or hour components")
}

@Throws(MissingDateTimeComponentsException::class)
private fun <T> requireComponents(vararg components: KProperty0<T?>) {
    val missingComponents = components.filter { it.get() == null }.map { it.name }
    if (missingComponents.isNotEmpty()) {
        throw MissingDateTimeComponentsException("Missing components ${missingComponents.joinToString()}")
    }
}

@Throws(MissingDateTimeComponentsException::class)
internal fun DateTimeComponents.requireAndRestrict(unit: TimeUnit) = when (unit) {
    is TimeUnit.Nanosecond -> {
        requireComponents(::nanosecond, ::second, ::minute, ::hour, ::dayOfMonth, ::month, ::year)
        validate()
        this
    }
    is TimeUnit.Second -> {
        requireComponents(::second, ::minute, ::hour, ::dayOfMonth, ::month, ::year)
        validate()
        copy(nanosecond = null)
    }
    is TimeUnit.Minute -> {
        requireComponents(::minute, ::hour, ::dayOfMonth, ::month, ::year)
        validate()
        copy(second = null, nanosecond = null)
    }
    is TimeUnit.Hour -> {
        requireComponents(::hour, ::dayOfMonth, ::month, ::year)
        validate()
        copy(minute = null, second = null, nanosecond = null)
    }
    is TimeUnit.Day -> {
        requireComponents(::dayOfMonth, ::month, ::year)
        validate()
        copy(hour = null, minute = null, second = null, nanosecond = null)
    }
    is TimeUnit.Month -> {
        requireComponents(::month, ::year)
        validate()
        copy(dayOfMonth = null, hour = null, minute = null, second = null, nanosecond = null)
    }
    is TimeUnit.Year -> {
        requireComponents(::year)
        validate()
        copy(month = null, dayOfMonth = null, hour = null, minute = null, second = null, nanosecond = null)
    }
}
