import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

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

internal fun DateTimeComponents.toLocalDate(): LocalDate {
    if (month != null && dayOfMonth != null) {
        return LocalDate(
            year = year,
            month = month,
            dayOfMonth = dayOfMonth
        )
    }

    throw IllegalStateException("Missing year, month and/or day components")
}

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

    throw IllegalStateException("Missing year, month, day and/or hour components")
}

internal fun DateTimeComponents.restrictComponents(unit: TimeUnit) = when (unit) {
    is Nanosecond -> this
    is Second -> copy(nanosecond = null)
    is Minute -> copy(second = null, nanosecond = null)
    is Hour -> copy(minute = null, second = null, nanosecond = null)
    is Day -> copy(hour = null, minute = null, second = null, nanosecond = null)
    is Month -> copy(dayOfMonth = null, hour = null, minute = null, second = null, nanosecond = null)
    else -> copy(month = null, dayOfMonth = null, hour = null, minute = null, second = null, nanosecond = null)
}
