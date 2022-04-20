private fun <Unit : TimeUnit> TimePeriod<Unit>.offset(count: Int, unit: TimeUnit): TimePeriod<Unit> {
    if (count == 0) {
        return this
    }

    return applying(TimeDifference(count, unit))
}

internal fun <Unit : TimeUnit> TimePeriod<Unit>.next(unit: TimeUnit) = offset(1, unit)
internal fun <Unit : TimeUnit> TimePeriod<Unit>.previous(unit: TimeUnit) = offset(-1, unit)

// Second

val <Unit : SecondOrSmaller> TimePeriod<Unit>.nextSecond
    get() = next(Second)
val <Unit : SecondOrSmaller> TimePeriod<Unit>.previousSecond
    get() = previous(Second)

fun <Unit : SecondOrSmaller> TimePeriod<Unit>.addingSeconds(count: Int) = applying(TimeDifference.seconds(count))
fun <Unit : SecondOrSmaller> TimePeriod<Unit>.subtractingSeconds(count: Int) = applying(TimeDifference.seconds(-count))

// Minute

val <Unit : MinuteOrSmaller> TimePeriod<Unit>.nextMinute
    get() = next(Minute)
val <Unit : MinuteOrSmaller> TimePeriod<Unit>.previousMinute
    get() = previous(Minute)

fun <Unit : MinuteOrSmaller> TimePeriod<Unit>.addingMinutes(count: Int) = applying(TimeDifference.minutes(count))
fun <Unit : MinuteOrSmaller> TimePeriod<Unit>.subtractingMinutes(count: Int) = applying(TimeDifference.minutes(-count))

// Hour

val <Unit : HourOrSmaller> TimePeriod<Unit>.nextHour
    get() = next(Hour)
val <Unit : HourOrSmaller> TimePeriod<Unit>.previousHour
    get() = previous(Hour)

fun <Unit : HourOrSmaller> TimePeriod<Unit>.addingHours(count: Int) = applying(TimeDifference.hours(count))
fun <Unit : HourOrSmaller> TimePeriod<Unit>.subtractingHours(count: Int) = applying(TimeDifference.hours(-count))

// Day

val <Unit : DayOrSmaller> TimePeriod<Unit>.nextDay
    get() = next(Day)
val <Unit : DayOrSmaller> TimePeriod<Unit>.previousDay
    get() = previous(Day)

fun <Unit : DayOrSmaller> TimePeriod<Unit>.addingDays(count: Int) = applying(TimeDifference.days(count))
fun <Unit : DayOrSmaller> TimePeriod<Unit>.subtractingDays(count: Int) = applying(TimeDifference.days(-count))

// Month

val <Unit : MonthOrSmaller> TimePeriod<Unit>.nextMonth
    get() = next(Month)
val <Unit : MonthOrSmaller> TimePeriod<Unit>.previousMonth
    get() = previous(Month)

fun <Unit : MonthOrSmaller> TimePeriod<Unit>.adding(count: Int) = applying(TimeDifference.months(count))
fun <Unit : MonthOrSmaller> TimePeriod<Unit>.subtractingMonths(count: Int) = applying(TimeDifference.months(-count))

// Year

val <Unit : YearOrSmaller> TimePeriod<Unit>.nextYear
    get() = next(Year)
val <Unit : YearOrSmaller> TimePeriod<Unit>.previousYear
    get() = previous(Year)

fun <Unit : YearOrSmaller> TimePeriod<Unit>.addingYears(count: Int) = applying(TimeDifference.years(count))
fun <Unit : YearOrSmaller> TimePeriod<Unit>.subtractingYears(count: Int) = applying(TimeDifference.years(-count))
