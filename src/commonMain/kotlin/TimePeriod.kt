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

data class TimePeriod<Unit : TimeUnit> internal constructor(
    internal val components: DateTimeComponents,
    internal val unit: Unit
) : Comparable<TimePeriod<Unit>> {

    @Throws(MissingDateTimeComponentsException::class)
    internal constructor(instant: Instant, unit: Unit) : this(instant.dateTimeComponents(unit).requireAndRestrict(unit), unit)

    companion object {

        private fun nanosecond(dateTimeComponents: DateTimeComponents) = TimePeriod(
            components = dateTimeComponents,
            unit = Nanosecond
        )
        fun nanosecond(instant: Instant) = TimePeriod(
            instant = instant,
            unit = Nanosecond
        )
        fun nanosecond(
            year: Int,
            month: kotlinx.datetime.Month,
            dayOfMonth: Int,
            hour: Int,
            minute: Int,
            second: Int,
            nanosecond: Int
        ) = nanosecond(
            DateTimeComponents(
                year = year,
                month = month,
                dayOfMonth = dayOfMonth,
                hour = hour,
                minute = minute,
                second = second,
                nanosecond = nanosecond
            )
        )

        internal fun second(dateTimeComponents: DateTimeComponents) = TimePeriod(
            components = dateTimeComponents.requireAndRestrict(Second),
            unit = Second
        )
        fun second(instant: Instant) = TimePeriod(
            instant = instant,
            unit = Second
        )
        fun second(
            year: Int,
            month: kotlinx.datetime.Month,
            dayOfMonth: Int,
            hour: Int,
            minute: Int,
            second: Int
        ) = second(
            DateTimeComponents(
                year = year,
                month = month,
                dayOfMonth = dayOfMonth,
                hour = hour,
                minute = minute,
                second = second
            )
        )

        internal fun minute(dateTimeComponents: DateTimeComponents) = TimePeriod(
            components = dateTimeComponents.requireAndRestrict(Minute),
            unit = Minute
        )
        fun minute(instant: Instant) = TimePeriod(
            instant = instant,
            unit = Minute
        )
        fun minute(
            year: Int,
            month: kotlinx.datetime.Month,
            dayOfMonth: Int,
            hour: Int,
            minute: Int
        ) = minute(
            DateTimeComponents(
                year = year,
                month = month,
                dayOfMonth = dayOfMonth,
                hour = hour,
                minute = minute
            )
        )

        internal fun hour(dateTimeComponents: DateTimeComponents) = TimePeriod(
            components = dateTimeComponents.requireAndRestrict(Hour),
            unit = Hour
        )
        fun hour(instant: Instant) = TimePeriod(
            instant = instant,
            unit = Hour
        )
        fun hour(
            year: Int,
            month: kotlinx.datetime.Month,
            dayOfMonth: Int,
            hour: Int
        ) = hour(
            DateTimeComponents(
                year = year,
                month = month,
                dayOfMonth = dayOfMonth,
                hour = hour
            )
        )

        internal fun day(dateTimeComponents: DateTimeComponents) = TimePeriod(
            components = dateTimeComponents.requireAndRestrict(Day),
            unit = Day
        )
        fun day(instant: Instant) = TimePeriod(
            instant = instant,
            unit = Day
        )
        fun day(
            year: Int,
            month: kotlinx.datetime.Month,
            dayOfMonth: Int
        ) = day(
            DateTimeComponents(
                year = year,
                month = month,
                dayOfMonth = dayOfMonth
            )
        )

        internal fun month(dateTimeComponents: DateTimeComponents) = TimePeriod(
            components = dateTimeComponents.requireAndRestrict(Month),
            unit = Month
        )
        fun month(instant: Instant) = TimePeriod(
            instant = instant,
            unit = Month
        )
        fun month(year: Int, month: kotlinx.datetime.Month) = month(
            DateTimeComponents(
                year = year,
                month = month
            )
        )

        internal fun year(dateTimeComponents: DateTimeComponents) = TimePeriod(
            components = dateTimeComponents.requireAndRestrict(Year),
            unit = Year
        )
        fun year(instant: Instant) = TimePeriod(
            instant = instant,
            unit = Year
        )
        fun year(year: Int) = year(DateTimeComponents(year = year))
    }

    fun <DifferenceUnit : TimeUnit> applying(difference: TimeDifference<DifferenceUnit>): TimePeriod<Unit> {
        val instant = firstInstant
        return when (difference.unit) {
            is Nanosecond -> {
                val newInstant = instant.plus(difference.count.nanoseconds)
                TimePeriod(newInstant, unit)
            }
            is Second -> {
                val newInstant = instant.plus(difference.count.seconds)
                TimePeriod(newInstant, unit)
            }
            is Minute -> {
                val newInstant = instant.plus(difference.count.minutes)
                TimePeriod(newInstant, unit)
            }
            is Hour -> {
                val newInstant = instant.plus(difference.count.hours)
                TimePeriod(newInstant, unit)
            }
            is Day -> {
                val newInstant = instant.plus(difference.count.days)
                TimePeriod(newInstant, unit)
            }
            is Month -> {
                val month = requireNotNull(components.month)
                val newMonthIndex = (month.ordinal + difference.count + 12) % 12
                val newMonth = kotlinx.datetime.Month.values()[newMonthIndex]
                val yearOffset = floor((month.ordinal + difference.count).toDouble() / 12.0)

                val newComponents = components.copy(
                    year = components.year + yearOffset.toInt(),
                    month = newMonth
                )

                TimePeriod(newComponents, unit)
            }
            is Year -> {
                val newComponents = components.copy(
                    year = components.year + difference.count
                )
                TimePeriod(newComponents, unit)
            }
            else -> throw IllegalTimeUnitException("Unable to apply difference with unexpected unit ${difference.unit}")
        }
    }

    /**
     * @return The first [Instant] known to occur within this [TimePeriod].
     */
    val firstInstant: Instant
        get() {
            val populatedComponents = components.copy(
                month = components.month ?: kotlinx.datetime.Month.JANUARY,
                dayOfMonth = components.dayOfMonth ?: 1,
                hour = components.hour ?: 0,
                minute = components.minute ?: 0,
                second = components.second ?: 0,
                nanosecond = components.nanosecond ?: 0
            )

            return populatedComponents.toLocalDateTime().toInstant(UtcOffset.ZERO)
        }

    @Deprecated("It's impossible to know the last instant of a calendar value, just like it's impossible to know the last number before 1.0")
    val lastInstant: Instant
        get() {
            throw NotImplementedError()
        }

    /**
     * Retrieve the range of [Instant]s described by this [TimePeriod].
     */
    val range: ClosedRange<Instant>
        get() {
            val start = firstInstant

            @Suppress("UNCHECKED_CAST")
            val unitLength = when (unit) {
                is Nanosecond -> 1.nanoseconds
                is Second -> 1.seconds
                is Minute -> 1.minutes
                is Hour -> 1.hours
                is Day -> 1.days
                is Month -> (this as TimePeriod<Month>).days.count().days
                is Year -> {
                    (this as TimePeriod<Year>).months.fold(0) { daysSum, month -> daysSum + month.days.count() }.days
                }
                else -> throw IllegalTimeUnitException("Unexpected unit $unit")
            }

            val end = start.plus(unitLength)
            return start..end
        }

    override fun compareTo(other: TimePeriod<Unit>) = components.compareTo(other.components)

    override fun toString() = buildString {
        append(TimePeriod::class.simpleName)
        append(".")
        append(unit::class.simpleName)
        append("(")

        val componentsString = buildList {
            add("year=" + components.year)
            components.month?.let { add("month=$it") }
            components.dayOfMonth?.let { add("dayOfMonth=$it") }
            components.hour?.let { add("hour=$it") }
            components.minute?.let { add("minute=$it") }
            components.second?.let { add("second=$it") }
            components.nanosecond?.let { add("nanosecond=$it") }
        }.joinToString(separator = ", ")

        append(componentsString)
        append(")")
    }
}

/**
 * Constructs a [kotlinx.datetime.LocalDateTime] from a [TimePeriod] with [Hour] or smaller precision.
 */
fun <Unit : HourOrSmaller> TimePeriod<Unit>.toLocalDateTime(timeZone: TimeZone) = firstInstant.toLocalDateTime(timeZone)

/**
 * Constructs a [kotlinx.datetime.LocalDate] from a [TimePeriod] with [Day] or finer precision.
 */
fun <Unit : DayOrSmaller> TimePeriod<Unit>.toLocalDate(timeZone: TimeZone) = firstInstant.toLocalDateTime(timeZone).date

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
        is Year -> {
            DateTimeComponents(
                year = dateTime.year
            )
        }
        else -> throw IllegalTimeUnitException("Unexpected unit $unit")
    }
}
