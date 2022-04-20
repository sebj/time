private fun <Unit : TimeUnit> TimePeriod<Unit>.offset(count: Int, unit: TimeUnit): TimePeriod<Unit> {
    if (count == 0) {
        return this
    }

    return applying(TimeDifference(count, unit))
}

internal fun <Unit : TimeUnit> TimePeriod<Unit>.next(unit: TimeUnit) = offset(1, unit)
internal fun <Unit : TimeUnit> TimePeriod<Unit>.previous(unit: TimeUnit) = offset(-1, unit)

// Nanosecond

/**
 * Create a new [TimePeriod] by moving forward one nanosecond.
 */
val <Unit : NanosecondOrSmaller> TimePeriod<Unit>.nextNanosecond
    get() = next(Nanosecond)
/**
 * Create a new [TimePeriod] by moving backward one nanosecond.
 */
val <Unit : NanosecondOrSmaller> TimePeriod<Unit>.previousNanosecond
    get() = previous(Nanosecond)

fun <Unit : NanosecondOrSmaller> TimePeriod<Unit>.addingNanoseconds(count: Int) = applying(TimeDifference.nanoseconds(count))
fun <Unit : NanosecondOrSmaller> TimePeriod<Unit>.subtractingNanoseconds(count: Int) = applying(TimeDifference.nanoseconds(-count))

// Second

/**
 * Create a new [TimePeriod] by moving forward one second.
 */
val <Unit : SecondOrSmaller> TimePeriod<Unit>.nextSecond
    get() = next(Second)
/**
 * Create a new [TimePeriod] by moving backward one second.
 */
val <Unit : SecondOrSmaller> TimePeriod<Unit>.previousSecond
    get() = previous(Second)

fun <Unit : SecondOrSmaller> TimePeriod<Unit>.addingSeconds(count: Int) = applying(TimeDifference.seconds(count))
fun <Unit : SecondOrSmaller> TimePeriod<Unit>.subtractingSeconds(count: Int) = applying(TimeDifference.seconds(-count))

// Minute

/**
 * Create a new [TimePeriod] by moving forward one minute.
 */
val <Unit : MinuteOrSmaller> TimePeriod<Unit>.nextMinute
    get() = next(Minute)
/**
 * Create a new [TimePeriod] by moving backward one minute.
 */
val <Unit : MinuteOrSmaller> TimePeriod<Unit>.previousMinute
    get() = previous(Minute)

fun <Unit : MinuteOrSmaller> TimePeriod<Unit>.addingMinutes(count: Int) = applying(TimeDifference.minutes(count))
fun <Unit : MinuteOrSmaller> TimePeriod<Unit>.subtractingMinutes(count: Int) = applying(TimeDifference.minutes(-count))

// Hour

/**
 * Create a new [TimePeriod] by moving forward one hour.
 */
val <Unit : HourOrSmaller> TimePeriod<Unit>.nextHour
    get() = next(Hour)
/**
 * Create a new [TimePeriod] by moving backward one hour.
 */
val <Unit : HourOrSmaller> TimePeriod<Unit>.previousHour
    get() = previous(Hour)

fun <Unit : HourOrSmaller> TimePeriod<Unit>.addingHours(count: Int) = applying(TimeDifference.hours(count))
fun <Unit : HourOrSmaller> TimePeriod<Unit>.subtractingHours(count: Int) = applying(TimeDifference.hours(-count))

// Day

/**
 * Create a new [TimePeriod] by moving forward one day.
 */
val <Unit : DayOrSmaller> TimePeriod<Unit>.nextDay
    get() = next(Day)
/**
 * Create a new [TimePeriod] by moving backward one day.
 */
val <Unit : DayOrSmaller> TimePeriod<Unit>.previousDay
    get() = previous(Day)

fun <Unit : DayOrSmaller> TimePeriod<Unit>.addingDays(count: Int) = applying(TimeDifference.days(count))
fun <Unit : DayOrSmaller> TimePeriod<Unit>.subtractingDays(count: Int) = applying(TimeDifference.days(-count))

// Month

/**
 * Create a new [TimePeriod] by moving forward one month.
 */
val <Unit : MonthOrSmaller> TimePeriod<Unit>.nextMonth
    get() = next(Month)
/**
 * Create a new [TimePeriod] by moving backward one month.
 */
val <Unit : MonthOrSmaller> TimePeriod<Unit>.previousMonth
    get() = previous(Month)

fun <Unit : MonthOrSmaller> TimePeriod<Unit>.adding(count: Int) = applying(TimeDifference.months(count))
fun <Unit : MonthOrSmaller> TimePeriod<Unit>.subtractingMonths(count: Int) = applying(TimeDifference.months(-count))

// Year

/**
 * Create a new [TimePeriod] by moving forward one year.
 */
val <Unit : YearOrSmaller> TimePeriod<Unit>.nextYear
    get() = next(Year)
/**
 * Create a new [TimePeriod] by moving backward one year.
 */
val <Unit : YearOrSmaller> TimePeriod<Unit>.previousYear
    get() = previous(Year)

fun <Unit : YearOrSmaller> TimePeriod<Unit>.addingYears(count: Int) = applying(TimeDifference.years(count))
fun <Unit : YearOrSmaller> TimePeriod<Unit>.subtractingYears(count: Int) = applying(TimeDifference.years(-count))
