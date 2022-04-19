private fun <Unit : TimeUnit> TimePeriod<Unit>.offset(count: Int, unit: TimeUnit): TimePeriod<Unit> {
    if (count == 0) {
        return this
    }

    return applying(TimeDifference(count = count, unit = unit))
}

internal fun <Unit : TimeUnit> TimePeriod<Unit>.next(unit: TimeUnit) = offset(1, unit = unit)
internal fun <Unit : TimeUnit> TimePeriod<Unit>.previous(unit: TimeUnit) = offset(count = -1, unit = unit)

// Second


val <Unit : SecondOrSmaller> TimePeriod<Unit>.nextSecond
    get() = next(Second)
val <Unit : SecondOrSmaller> TimePeriod<Unit>.previousSecond
    get() = previous(Second)

fun <Unit : SecondOrSmaller> TimePeriod<Unit>.addingSeconds(seconds: Int) = applying(TimeDifference.seconds(seconds))
fun <Unit : SecondOrSmaller> TimePeriod<Unit>.subtractingSeconds(seconds: Int) = applying(TimeDifference.seconds(-seconds))

// Minute

val <Unit : MinuteOrSmaller> TimePeriod<Unit>.nextMinute
    get() = next(Minute)
val <Unit : MinuteOrSmaller> TimePeriod<Unit>.previousMinute
    get() = previous(Minute)

fun <Unit : MinuteOrSmaller> TimePeriod<Unit>.addingMinutes(minutes: Int) = applying(TimeDifference.minutes(minutes))
fun <Unit : MinuteOrSmaller> TimePeriod<Unit>.subtractingMinutes(minutes: Int) = applying(TimeDifference.minutes(-minutes))

// Hour

val <Unit : HourOrSmaller> TimePeriod<Unit>.nextHour
    get() = next(Hour)
val <Unit : HourOrSmaller> TimePeriod<Unit>.previousHour
    get() = previous(Hour)

fun <Unit : HourOrSmaller> TimePeriod<Unit>.addingHours(hours: Int) = applying(TimeDifference.hours(hours))
fun <Unit : HourOrSmaller> TimePeriod<Unit>.subtractingHours(hours: Int) = applying(TimeDifference.hours(-hours))

// Day

val <Unit : DayOrSmaller> TimePeriod<Unit>.nextDay
    get() = next(Day)
val <Unit : DayOrSmaller> TimePeriod<Unit>.previousDay
    get() = previous(Day)

fun <Unit : DayOrSmaller> TimePeriod<Unit>.addingDays(days: Int) = applying(TimeDifference.days(days))
fun <Unit : DayOrSmaller> TimePeriod<Unit>.subtractingDays(days: Int) = applying(TimeDifference.days(-days))

// Month

val <Unit : MonthOrSmaller> TimePeriod<Unit>.nextMonth
    get() = next(Month)
val <Unit : MonthOrSmaller> TimePeriod<Unit>.previousMonth
    get() = previous(Month)

fun <Unit : MonthOrSmaller> TimePeriod<Unit>.adding(months: Int) = applying(TimeDifference.months(months))
fun <Unit : MonthOrSmaller> TimePeriod<Unit>.subtractingMonths(months: Int) = applying(TimeDifference.months(-months))

// Year

val <Unit : YearOrSmaller> TimePeriod<Unit>.nextYear
    get() = next(Year)
val <Unit : YearOrSmaller> TimePeriod<Unit>.previousYear
    get() = previous(Year)

fun <Unit : YearOrSmaller> TimePeriod<Unit>.addingYears(years: Int) = applying(TimeDifference.years(years))
fun <Unit : YearOrSmaller> TimePeriod<Unit>.subtractingYears(years: Int) = applying(TimeDifference.years(-years))
