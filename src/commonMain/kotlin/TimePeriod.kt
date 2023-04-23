import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlin.math.floor
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.nanoseconds
import kotlin.time.Duration.Companion.seconds

public data class TimePeriod<SmallestUnit : TimeUnit> internal constructor(
    val timeZone: TimeZone,
    internal val components: DateTimeComponents,
    internal val smallestUnit: SmallestUnit
) : Comparable<TimePeriod<SmallestUnit>> {

    @Throws(MissingDateTimeComponentsException::class)
    internal constructor(timeZone: TimeZone, instant: Instant, unit: SmallestUnit) : this(
        timeZone,
        instant.dateTimeComponents(timeZone, unit),
        unit
    )

    public companion object {

        private fun nanosecond(timeZone: TimeZone, dateTimeComponents: DateTimeComponents): TimePeriod<TimeUnit.Nanosecond> =
            TimePeriod(
                timeZone,
                dateTimeComponents,
                TimeUnit.Nanosecond
            )
        public fun nanosecond(timeZone: TimeZone, instant: Instant): TimePeriod<TimeUnit.Nanosecond> = TimePeriod(
            timeZone,
            instant,
            TimeUnit.Nanosecond
        )
        public fun nanosecond(
            timeZone: TimeZone,
            year: Int,
            month: kotlinx.datetime.Month,
            dayOfMonth: Int,
            hour: Int,
            minute: Int,
            second: Int,
            nanosecond: Int
        ): TimePeriod<TimeUnit.Nanosecond> = nanosecond(
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

        internal fun second(timeZone: TimeZone, dateTimeComponents: DateTimeComponents): TimePeriod<TimeUnit.Second> =
            TimePeriod(
                timeZone,
                dateTimeComponents.requireAndRestrict(TimeUnit.Second),
                TimeUnit.Second
            )
        public fun second(timeZone: TimeZone, instant: Instant): TimePeriod<TimeUnit.Second> = TimePeriod(
            timeZone,
            instant,
            TimeUnit.Second
        )
        public fun second(
            timeZone: TimeZone,
            year: Int,
            month: kotlinx.datetime.Month,
            dayOfMonth: Int,
            hour: Int,
            minute: Int,
            second: Int
        ): TimePeriod<TimeUnit.Second> = second(
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

        internal fun minute(timeZone: TimeZone, dateTimeComponents: DateTimeComponents): TimePeriod<TimeUnit.Minute> =
            TimePeriod(
                timeZone,
                dateTimeComponents.requireAndRestrict(TimeUnit.Minute),
                TimeUnit.Minute
            )
        public fun minute(timeZone: TimeZone, instant: Instant): TimePeriod<TimeUnit.Minute> = TimePeriod(
            timeZone,
            instant,
            TimeUnit.Minute
        )
        public fun minute(
            timeZone: TimeZone,
            year: Int,
            month: kotlinx.datetime.Month,
            dayOfMonth: Int,
            hour: Int,
            minute: Int
        ): TimePeriod<TimeUnit.Minute> = minute(
            timeZone,
            DateTimeComponents(
                year = year,
                month = month,
                dayOfMonth = dayOfMonth,
                hour = hour,
                minute = minute
            )
        )

        internal fun hour(timeZone: TimeZone, dateTimeComponents: DateTimeComponents): TimePeriod<TimeUnit.Hour> =
            TimePeriod(
                timeZone,
                dateTimeComponents.requireAndRestrict(TimeUnit.Hour),
                TimeUnit.Hour
            )
        public fun hour(timeZone: TimeZone, instant: Instant): TimePeriod<TimeUnit.Hour> = TimePeriod(
            timeZone,
            instant,
            TimeUnit.Hour
        )
        public fun hour(
            timeZone: TimeZone,
            year: Int,
            month: kotlinx.datetime.Month,
            dayOfMonth: Int,
            hour: Int
        ): TimePeriod<TimeUnit.Hour> = hour(
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
            dateTimeComponents.requireAndRestrict(TimeUnit.Day),
            TimeUnit.Day
        )
        public fun day(timeZone: TimeZone, instant: Instant): TimePeriod<TimeUnit.Day> = TimePeriod(
            timeZone,
            instant,
            TimeUnit.Day
        )
        public fun day(
            timeZone: TimeZone,
            year: Int,
            month: kotlinx.datetime.Month,
            dayOfMonth: Int
        ): TimePeriod<TimeUnit.Day> = day(
            timeZone,
            DateTimeComponents(
                year = year,
                month = month,
                dayOfMonth = dayOfMonth
            )
        )

        internal fun month(timeZone: TimeZone, dateTimeComponents: DateTimeComponents): TimePeriod<TimeUnit.Month> =
            TimePeriod(
                timeZone,
                dateTimeComponents.requireAndRestrict(TimeUnit.Month),
                TimeUnit.Month
            )
        public fun month(timeZone: TimeZone, instant: Instant): TimePeriod<TimeUnit.Month> = TimePeriod(
            timeZone,
            instant,
            TimeUnit.Month
        )
        public fun month(timeZone: TimeZone, year: Int, month: kotlinx.datetime.Month): TimePeriod<TimeUnit.Month> =
            month(
                timeZone,
                DateTimeComponents(
                    year = year,
                    month = month
                )
            )

        internal fun year(timeZone: TimeZone, dateTimeComponents: DateTimeComponents): TimePeriod<TimeUnit.Year> =
            TimePeriod(
                timeZone,
                dateTimeComponents.requireAndRestrict(TimeUnit.Year),
                TimeUnit.Year
            )
        public fun year(timeZone: TimeZone, instant: Instant): TimePeriod<TimeUnit.Year> = TimePeriod(
            timeZone,
            instant,
            TimeUnit.Year
        )
        public fun year(timeZone: TimeZone, year: Int): TimePeriod<TimeUnit.Year> =
            year(timeZone, DateTimeComponents(year = year))
    }

    public fun <DifferenceUnit : TimeUnit> applying(difference: TimeDifference<DifferenceUnit>): TimePeriod<SmallestUnit> {
        return when (difference.smallestUnit) {
            is TimeUnit.Nanosecond -> {
                val newInstant = approximateMidPoint.plus(difference.count.nanoseconds)
                TimePeriod(timeZone, newInstant, smallestUnit)
            }
            is TimeUnit.Second -> {
                val newInstant = approximateMidPoint.plus(difference.count.seconds)
                TimePeriod(timeZone, newInstant, smallestUnit)
            }
            is TimeUnit.Minute -> {
                val newInstant = approximateMidPoint.plus(difference.count.minutes)
                TimePeriod(timeZone, newInstant, smallestUnit)
            }
            is TimeUnit.Hour -> {
                val newInstant = approximateMidPoint.plus(difference.count.hours)
                TimePeriod(timeZone, newInstant, smallestUnit)
            }
            is TimeUnit.Day -> {
                val newInstant = approximateMidPoint.plus(difference.count.days)
                TimePeriod(timeZone, newInstant, smallestUnit)
            }
            is TimeUnit.Month -> {
                val month = requireNotNull(components.month)
                val newMonthIndex = (month.ordinal + difference.count + 12) % 12
                val newMonth = kotlinx.datetime.Month.values()[newMonthIndex]
                val yearOffset = floor((month.ordinal + difference.count).toDouble() / 12.0)

                val newComponents = components.copy(
                    year = components.year + yearOffset.toInt(),
                    month = newMonth
                )

                TimePeriod(timeZone, newComponents, smallestUnit)
            }
            is TimeUnit.Year -> {
                val newComponents = components.copy(
                    year = components.year + difference.count
                )
                TimePeriod(timeZone, newComponents, smallestUnit)
            }
            else -> throw IllegalTimeUnitException("Unable to apply difference with unexpected unit ${difference.smallestUnit}")
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
            val unitLength = when (smallestUnit) {
                is TimeUnit.Nanosecond -> 1.nanoseconds
                is TimeUnit.Second -> 1.seconds
                is TimeUnit.Minute -> 1.minutes
                is TimeUnit.Hour -> 1.hours
                is TimeUnit.Day -> 1.days
                is TimeUnit.Month -> (this as TimePeriod<TimeUnit.Month>).days.count().days
                is TimeUnit.Year -> {
                    (this as TimePeriod<TimeUnit.Year>).months.fold(0) { daysSum, month -> daysSum + month.days.count() }.days
                }
                else -> throw IllegalTimeUnitException("Unexpected unit $smallestUnit")
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

    override fun compareTo(other: TimePeriod<SmallestUnit>): Int = components.compareTo(other.components)

    override fun toString(): String = buildString {
        append(TimePeriod::class.simpleName)
        append(".")
        append(smallestUnit::class.simpleName)
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
 * Constructs a [kotlinx.datetime.LocalDateTime] from a [TimePeriod] with [HourOrSmaller] precision.
 */
public fun <Unit> TimePeriod<Unit>.toLocalDateTime(): LocalDateTime where Unit : TimeUnit, Unit : HourOrSmaller =
    firstInstant.toLocalDateTime(timeZone)

/**
 * Constructs a [kotlinx.datetime.LocalDate] from a [TimePeriod] with [DayOrSmaller] precision.
 */
public fun <Unit> TimePeriod<Unit>.toLocalDate(): LocalDate where Unit : TimeUnit, Unit : DayOrSmaller =
    firstInstant.toLocalDateTime(timeZone).date

/**
 * Construct a new [TimePeriod] by converting the receiver to a new [TimeZone].
 */
public fun <Unit : TimeUnit> TimePeriod<Unit>.convertToTimeZone(timeZone: TimeZone): TimePeriod<Unit> {
    return if (timeZone == this.timeZone) {
        this
    } else {
        TimePeriod(timeZone, approximateMidPoint, smallestUnit)
    }
}

private fun <Unit : TimeUnit> Instant.dateTimeComponents(timeZone: TimeZone, unit: Unit): DateTimeComponents {
    val dateTime = toLocalDateTime(timeZone)
    return when (unit) {
        is TimeUnit.Nanosecond -> {
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
        is TimeUnit.Second -> {
            DateTimeComponents(
                year = dateTime.year,
                month = dateTime.month,
                dayOfMonth = dateTime.dayOfMonth,
                hour = dateTime.hour,
                minute = dateTime.minute,
                second = dateTime.second
            )
        }
        is TimeUnit.Minute -> {
            DateTimeComponents(
                year = dateTime.year,
                month = dateTime.month,
                dayOfMonth = dateTime.dayOfMonth,
                hour = dateTime.hour,
                minute = dateTime.minute
            )
        }
        is TimeUnit.Hour -> {
            DateTimeComponents(
                year = dateTime.year,
                month = dateTime.month,
                dayOfMonth = dateTime.dayOfMonth,
                hour = dateTime.hour
            )
        }
        is TimeUnit.Day -> {
            DateTimeComponents(
                year = dateTime.year,
                month = dateTime.month,
                dayOfMonth = dateTime.dayOfMonth
            )
        }
        is TimeUnit.Month -> {
            DateTimeComponents(
                year = dateTime.year,
                month = dateTime.month
            )
        }
        is TimeUnit.Year -> {
            DateTimeComponents(
                year = dateTime.year
            )
        }
        else -> throw IllegalTimeUnitException("Unexpected unit $unit")
    }
}
