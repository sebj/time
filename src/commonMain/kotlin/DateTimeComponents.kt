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
private inline fun <T> requireDateTimeComponents(vararg components: KProperty0<T?>) {
    val missingComponents = components.filter { it.get() == null }.map { it.name }
    if (missingComponents.isNotEmpty()) {
        throw MissingDateTimeComponentsException("Missing components ${missingComponents.joinToString()}")
    }
}

@Throws(MissingDateTimeComponentsException::class)
internal fun DateTimeComponents.requireAndRestrict(unit: TimeUnit) = when (unit) {
    is Nanosecond -> {
        requireDateTimeComponents(::nanosecond, ::second, ::minute, ::hour, ::dayOfMonth, ::month, ::year)
        this
    }
    is Second -> {
        requireDateTimeComponents(::second, ::minute, ::hour, ::dayOfMonth, ::month, ::year)
        copy(nanosecond = null)
    }
    is Minute -> {
        requireDateTimeComponents(::minute, ::hour, ::dayOfMonth, ::month, ::year)
        copy(second = null, nanosecond = null)
    }
    is Hour -> {
        requireDateTimeComponents(::hour, ::dayOfMonth, ::month, ::year)
        copy(minute = null, second = null, nanosecond = null)
    }
    is Day -> {
        requireDateTimeComponents(::dayOfMonth, ::month, ::year)
        copy(hour = null, minute = null, second = null, nanosecond = null)
    }
    is Month -> {
        requireDateTimeComponents(::month, ::year)
        copy(dayOfMonth = null, hour = null, minute = null, second = null, nanosecond = null)
    }
    else -> {
        requireDateTimeComponents(::year)
        copy(month = null, dayOfMonth = null, hour = null, minute = null, second = null, nanosecond = null)
    }
}
