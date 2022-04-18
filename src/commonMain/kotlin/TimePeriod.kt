import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.UtcOffset
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlin.math.floor
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.nanoseconds
import kotlin.time.Duration.Companion.seconds

private fun <Unit : TimeUnit> Instant.dateTimeComponents(unit: Unit): DateTimeComponents {
    val dateTime = toLocalDateTime(TimeZone.UTC)
    return when (unit) {
        is Nanosecond -> {
            DateTimeComponents(
                year = dateTime.year,
                month = dateTime.month,
                dayOfMonth = dateTime.dayOfMonth,
                hour = dateTime.hour,
                minute = dateTime.minute,
                second = dateTime.second,
                nanosecond = dateTime.nanosecond
            )
        }
        is Second -> {
            DateTimeComponents(
                year = dateTime.year,
                month = dateTime.month,
                dayOfMonth = dateTime.dayOfMonth,
                hour = dateTime.hour,
                minute = dateTime.minute,
                second = dateTime.second
            )
        }
        is Minute -> {
            DateTimeComponents(
                year = dateTime.year,
                month = dateTime.month,
                dayOfMonth = dateTime.dayOfMonth,
                hour = dateTime.hour,
                minute = dateTime.minute
            )
        }
        is Hour -> {
            DateTimeComponents(
                year = dateTime.year,
                month = dateTime.month,
                dayOfMonth = dateTime.dayOfMonth,
                hour = dateTime.hour
            )
        }
        is Day -> {
            DateTimeComponents(
                year = dateTime.year,
                month = dateTime.month,
                dayOfMonth = dateTime.dayOfMonth
            )
        }
        is Month -> {
            DateTimeComponents(
                year = dateTime.year,
                month = dateTime.month
            )
        }
        else -> {
            DateTimeComponents(
                year = dateTime.year
            )
        }
    }
}

class TimePeriod<Unit : TimeUnit> internal constructor(
    internal val dateTimeComponents: DateTimeComponents,
    internal val unit: Unit
) : Comparable<TimePeriod<Unit>> {

    internal constructor(instant: Instant, unit: Unit) : this(instant.dateTimeComponents(unit), unit)

    companion object {

        fun nanosecond(instant: Instant) = TimePeriod(instant = instant, unit = Nanosecond)
        fun nanosecond(dateTimeComponents: DateTimeComponents) = TimePeriod(dateTimeComponents = dateTimeComponents, unit = Nanosecond)
        fun nanosecond(
            year: Int,
            month: kotlinx.datetime.Month,
            dayOfMonth: Int,
            hour: Int,
            minute: Int,
            second: Int,
            nanosecond: Int
        ) = TimePeriod(
            DateTimeComponents(
                year = year,
                month = month,
                dayOfMonth = dayOfMonth,
                hour = hour,
                minute = minute,
                second = second,
                nanosecond = nanosecond
            ),
            unit = Nanosecond
        )

        fun second(instant: Instant) = TimePeriod(instant = instant, unit = Second)
        fun second(dateTimeComponents: DateTimeComponents) = TimePeriod(dateTimeComponents = dateTimeComponents, unit = Second)
        fun second(
            year: Int,
            month: kotlinx.datetime.Month,
            dayOfMonth: Int,
            hour: Int,
            minute: Int,
            second: Int
        ) = TimePeriod(
            DateTimeComponents(
                year = year,
                month = month,
                dayOfMonth = dayOfMonth,
                hour = hour,
                minute = minute,
                second = second
            ),
            unit = Day
        )

        fun minute(instant: Instant) = TimePeriod(instant = instant, unit = Minute)
        fun minute(dateTimeComponents: DateTimeComponents) = TimePeriod(dateTimeComponents = dateTimeComponents, unit = Minute)
        fun minute(
            year: Int,
            month: kotlinx.datetime.Month,
            dayOfMonth: Int,
            hour: Int,
            minute: Int
        ) = TimePeriod(
            DateTimeComponents(
                year = year,
                month = month,
                dayOfMonth = dayOfMonth,
                hour = hour,
                minute = minute
            ),
            unit = Day
        )

        fun hour(instant: Instant) = TimePeriod(instant = instant, unit = Hour)
        fun hour(dateTimeComponents: DateTimeComponents) = TimePeriod(dateTimeComponents = dateTimeComponents, unit = Hour)
        fun hour(year: Int, month: kotlinx.datetime.Month, dayOfMonth: Int, hour: Int) = TimePeriod(
            DateTimeComponents(year = year, month = month, dayOfMonth = dayOfMonth, hour = hour),
            unit = Day
        )

        fun day(instant: Instant) = TimePeriod(instant = instant, unit = Day)
        fun day(dateTimeComponents: DateTimeComponents) = TimePeriod(dateTimeComponents = dateTimeComponents, unit = Day)
        fun day(year: Int, month: kotlinx.datetime.Month, dayOfMonth: Int) = TimePeriod(
            DateTimeComponents(year = year, month = month, dayOfMonth = dayOfMonth),
            unit = Day
        )

        fun month(instant: Instant) = TimePeriod(instant = instant, unit = Month)
        fun month(dateTimeComponents: DateTimeComponents) = TimePeriod(dateTimeComponents = dateTimeComponents, unit = Month)
        fun month(year: Int, month: kotlinx.datetime.Month) = TimePeriod(DateTimeComponents(year = year, month = month), unit = Month)

        fun year(instant: Instant) = TimePeriod(instant = instant, unit = Year)
        fun year(dateTimeComponents: DateTimeComponents) = TimePeriod(dateTimeComponents = dateTimeComponents, unit = Year)
        fun year(year: Int) = TimePeriod(DateTimeComponents(year = year), unit = Year)
    }

    fun <DifferenceUnit : TimeUnit> applying(difference: TimeDifference<DifferenceUnit>): TimePeriod<Unit> {
        val instant = firstInstant()
        return when (difference.unit) {
            is Nanosecond -> {
                val newInstant = instant.plus(difference.count.nanoseconds)
                TimePeriod(instant = newInstant, unit = unit)
            }
            is Second -> {
                val newInstant = instant.plus(difference.count.seconds)
                TimePeriod(instant = newInstant, unit = unit)
            }
            is Minute -> {
                val newInstant = instant.plus(difference.count.minutes)
                TimePeriod(instant = newInstant, unit = unit)
            }
            is Hour -> {
                val newInstant = instant.plus(difference.count.hours)
                TimePeriod(instant = newInstant, unit = unit)
            }
            is Day -> {
                val newInstant = instant.plus(difference.count.days)
                TimePeriod(instant = newInstant, unit = unit)
            }
            is Month -> {
                val month = requireNotNull(dateTimeComponents.month)
                val newMonthIndex = (month.ordinal + difference.count + 12) % 12
                val newMonth = kotlinx.datetime.Month.values()[newMonthIndex]
                val yearOffset = floor((month.ordinal + difference.count).toDouble() / 12.0)

                val newComponents = dateTimeComponents.copy(
                    year = dateTimeComponents.year + yearOffset.toInt(),
                    month = newMonth
                )

                TimePeriod(dateTimeComponents = newComponents, unit = unit)
            }
            else -> {
                val newComponents = dateTimeComponents.copy(
                    year = dateTimeComponents.year + difference.count
                )
                TimePeriod(dateTimeComponents = newComponents, unit = unit)
            }
        }
    }

    fun firstInstant(): Instant {
        val populatedComponents = dateTimeComponents.copy(
            month = dateTimeComponents.month ?: kotlinx.datetime.Month.JANUARY,
            dayOfMonth = dateTimeComponents.dayOfMonth ?: 1,
            hour = dateTimeComponents.hour ?: 0,
            minute = dateTimeComponents.minute ?: 0,
            second = dateTimeComponents.second ?: 0,
            nanosecond = dateTimeComponents.nanosecond ?: 0
        )

        return populatedComponents.toLocalDateTime().toInstant(UtcOffset.ZERO)
    }

    fun range(): ClosedRange<Instant> {
        val start = firstInstant()

        @Suppress("UNCHECKED_CAST")
        val unitLength = when (unit) {
            is Nanosecond -> 1.nanoseconds
            is Second -> 1.seconds
            is Minute -> 1.minutes
            is Hour -> 1.hours
            is Day -> 1.days
            is Month -> (this as TimePeriod<Month>).days().count().days
            else -> {
                (this as TimePeriod<Year>).months().fold(0) { daysSum, month -> daysSum + month.days().count() }.days
            }
        }

        val end = firstInstant().plus(unitLength)
        return start..end
    }

    override fun compareTo(other: TimePeriod<Unit>) = dateTimeComponents.compareTo(other.dateTimeComponents)
}

fun <Unit : HourOrSmaller> TimePeriod<Unit>.toLocalDateTime(timeZone: TimeZone) = firstInstant().toLocalDateTime(timeZone)
fun <Unit : DayOrSmaller> TimePeriod<Unit>.toLocalDate(timeZone: TimeZone) = firstInstant().toLocalDateTime(timeZone).date
