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
    val timeZone: TimeZone,
    internal val components: DateTimeComponents,
    internal val unit: Unit
) : Comparable<TimePeriod<Unit>> {

    @Throws(MissingDateTimeComponentsException::class)
    internal constructor(timeZone: TimeZone, instant: Instant, unit: Unit) : this(
        timeZone,
        instant.dateTimeComponents(timeZone, unit),
        unit
    )

    companion object {

        private fun nanosecond(timeZone: TimeZone, dateTimeComponents: DateTimeComponents) = TimePeriod(
            timeZone,
            dateTimeComponents,
            Nanosecond
        )
        fun nanosecond(timeZone: TimeZone, instant: Instant) = TimePeriod(
            timeZone,
            instant,
            Nanosecond
        )
        fun nanosecond(
            timeZone: TimeZone,
            year: Int,
            month: kotlinx.datetime.Month,
            dayOfMonth: Int,
            hour: Int,
            minute: Int,
            second: Int,
            nanosecond: Int
        ) = nanosecond(
            timeZone,
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

        internal fun second(timeZone: TimeZone, dateTimeComponents: DateTimeComponents) = TimePeriod(
            timeZone,
            components = dateTimeComponents.requireAndRestrict(Second),
            unit = Second
        )
        fun second(timeZone: TimeZone, instant: Instant) = TimePeriod(
            timeZone,
            instant,
            Second
        )
        fun second(
            timeZone: TimeZone,
            year: Int,
            month: kotlinx.datetime.Month,
            dayOfMonth: Int,
            hour: Int,
            minute: Int,
            second: Int
        ) = second(
            timeZone,
            DateTimeComponents(
                year = year,
                month = month,
                dayOfMonth = dayOfMonth,
                hour = hour,
                minute = minute,
                second = second
            )
        )

        internal fun minute(timeZone: TimeZone, dateTimeComponents: DateTimeComponents) = TimePeriod(
            timeZone,
            components = dateTimeComponents.requireAndRestrict(Minute),
            unit = Minute
        )
        fun minute(timeZone: TimeZone, instant: Instant) = TimePeriod(
            timeZone,
            instant,
            Minute
        )
        fun minute(
            timeZone: TimeZone,
            year: Int,
            month: kotlinx.datetime.Month,
            dayOfMonth: Int,
            hour: Int,
            minute: Int
        ) = minute(
            timeZone,
            DateTimeComponents(
                year = year,
                month = month,
                dayOfMonth = dayOfMonth,
                hour = hour,
                minute = minute
            )
        )

        internal fun hour(timeZone: TimeZone, dateTimeComponents: DateTimeComponents) = TimePeriod(
            timeZone,
            dateTimeComponents.requireAndRestrict(Hour),
            Hour
        )
        fun hour(timeZone: TimeZone, instant: Instant) = TimePeriod(
            timeZone,
            instant,
            Hour
        )
        fun hour(
            timeZone: TimeZone,
            year: Int,
            month: kotlinx.datetime.Month,
            dayOfMonth: Int,
            hour: Int
        ) = hour(
            timeZone,
            DateTimeComponents(
                year = year,
                month = month,
                dayOfMonth = dayOfMonth,
                hour = hour
            )
        )

        internal fun day(timeZone: TimeZone, dateTimeComponents: DateTimeComponents) = TimePeriod(
            timeZone,
            dateTimeComponents.requireAndRestrict(Day),
            Day
        )
        fun day(timeZone: TimeZone, instant: Instant) = TimePeriod(
            timeZone,
            instant,
            Day
        )
        fun day(
            timeZone: TimeZone,
            year: Int,
            month: kotlinx.datetime.Month,
            dayOfMonth: Int
        ) = day(
            timeZone,
            DateTimeComponents(
                year = year,
                month = month,
                dayOfMonth = dayOfMonth
            )
        )

        internal fun month(timeZone: TimeZone, dateTimeComponents: DateTimeComponents) = TimePeriod(
            timeZone,
            dateTimeComponents.requireAndRestrict(Month),
            Month
        )
        fun month(timeZone: TimeZone, instant: Instant) = TimePeriod(
            timeZone,
            instant,
            Month
        )
        fun month(timeZone: TimeZone, year: Int, month: kotlinx.datetime.Month) = month(
            timeZone,
            DateTimeComponents(
                year = year,
                month = month
            )
        )

        internal fun year(timeZone: TimeZone, dateTimeComponents: DateTimeComponents) = TimePeriod(
            timeZone,
            dateTimeComponents.requireAndRestrict(Year),
            Year
        )
        fun year(timeZone: TimeZone, instant: Instant) = TimePeriod(
            timeZone,
            instant,
            Year
        )
        fun year(timeZone: TimeZone, year: Int) = year(timeZone, DateTimeComponents(year = year))
    }

    fun <DifferenceUnit : TimeUnit> applying(difference: TimeDifference<DifferenceUnit>): TimePeriod<Unit> {
        return when (difference.unit) {
            is Nanosecond -> {
                val newInstant = approximateMidPoint.plus(difference.count.nanoseconds)
                TimePeriod(timeZone, newInstant, unit)
            }
            is Second -> {
                val newInstant = approximateMidPoint.plus(difference.count.seconds)
                TimePeriod(timeZone, newInstant, unit)
            }
            is Minute -> {
                val newInstant = approximateMidPoint.plus(difference.count.minutes)
                TimePeriod(timeZone, newInstant, unit)
            }
            is Hour -> {
                val newInstant = approximateMidPoint.plus(difference.count.hours)
                TimePeriod(timeZone, newInstant, unit)
            }
            is Day -> {
                val newInstant = approximateMidPoint.plus(difference.count.days)
                TimePeriod(timeZone, newInstant, unit)
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

                TimePeriod(timeZone, newComponents, unit)
            }
            is Year -> {
                val newComponents = components.copy(
                    year = components.year + difference.count
                )
                TimePeriod(timeZone, newComponents, unit)
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

            return populatedComponents.toLocalDateTime().toInstant(timeZone)
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

    internal val approximateMidPoint: Instant
        get() {
            val range = range
            val start = range.start
            val end = range.endInclusive
            val duration = end - start
            val midPoint = start + (duration / 2.0)
            return if (start > midPoint) start else midPoint
        }

    override fun compareTo(other: TimePeriod<Unit>) = components.compareTo(other.components)

    override fun toString() = buildString {
        append(TimePeriod::class.simpleName)
        append(".")
        append(unit::class.simpleName)
        append("(")

        val componentsString = buildList {
            add("timeZone=$timeZone")
            add("year=${components.year}")
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
fun <Unit : HourOrSmaller> TimePeriod<Unit>.toLocalDateTime() = firstInstant.toLocalDateTime(timeZone)

/**
 * Constructs a [kotlinx.datetime.LocalDate] from a [TimePeriod] with [Day] or finer precision.
 */
fun <Unit : DayOrSmaller> TimePeriod<Unit>.toLocalDate() = firstInstant.toLocalDateTime(timeZone).date

/**
 * Construct a new [TimePeriod] by converting the receiver to a new [TimeZone].
 */
fun <Unit : TimeUnit> TimePeriod<Unit>.convertToTimeZone(timeZone: TimeZone): TimePeriod<Unit> {
    return if (timeZone == this.timeZone) {
        this
    } else {
        TimePeriod(timeZone, approximateMidPoint, unit)
    }
}

private fun <Unit : TimeUnit> Instant.dateTimeComponents(timeZone: TimeZone, unit: Unit): DateTimeComponents {
    val dateTime = toLocalDateTime(timeZone)
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
