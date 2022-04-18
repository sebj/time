private fun <Unit : TimeUnit> TimePeriod<Unit>.offset(count: Int, unit: TimeUnit): TimePeriod<Unit> {
    if (count == 0) {
        return this
    }

    return applying(TimeDifference(count = count, unit = unit))
}

internal fun <Unit : TimeUnit> TimePeriod<Unit>.next(unit: TimeUnit) = offset(1, unit = unit)
internal fun <Unit : TimeUnit> TimePeriod<Unit>.previous(unit: TimeUnit) = offset(count = -1, unit = unit)

// Second

fun <Unit : SecondOrSmaller> TimePeriod<Unit>.nextSecond() = next(Second)
fun <Unit : SecondOrSmaller> TimePeriod<Unit>.previousSecond() = previous(Second)

fun <Unit : SecondOrSmaller> TimePeriod<Unit>.addingSeconds(seconds: Int) = applying(TimeDifference.seconds(seconds))
fun <Unit : SecondOrSmaller> TimePeriod<Unit>.subtractingSeconds(seconds: Int) = applying(TimeDifference.seconds(-seconds))

// Minute

fun <Unit : MinuteOrSmaller> TimePeriod<Unit>.nextMinute() = next(Minute)
fun <Unit : MinuteOrSmaller> TimePeriod<Unit>.previousMinute() = previous(Minute)

fun <Unit : MinuteOrSmaller> TimePeriod<Unit>.addingMinutes(minutes: Int) = applying(TimeDifference.minutes(minutes))
fun <Unit : MinuteOrSmaller> TimePeriod<Unit>.subtractingMinutes(minutes: Int) = applying(TimeDifference.minutes(-minutes))

// Hour

fun <Unit : HourOrSmaller> TimePeriod<Unit>.nextHour() = next(Hour)
fun <Unit : HourOrSmaller> TimePeriod<Unit>.previousHour() = previous(Hour)

fun <Unit : HourOrSmaller> TimePeriod<Unit>.addingHours(hours: Int) = applying(TimeDifference.hours(hours))
fun <Unit : HourOrSmaller> TimePeriod<Unit>.subtractingHours(hours: Int) = applying(TimeDifference.hours(-hours))

// Day

fun <Unit : DayOrSmaller> TimePeriod<Unit>.nextDay() = next(Day)
fun <Unit : DayOrSmaller> TimePeriod<Unit>.previousDay() = previous(Day)

fun <Unit : DayOrSmaller> TimePeriod<Unit>.addingDays(days: Int) = applying(TimeDifference.days(days))
fun <Unit : DayOrSmaller> TimePeriod<Unit>.subtractingDays(days: Int) = applying(TimeDifference.days(-days))

// Month

fun <Unit : MonthOrSmaller> TimePeriod<Unit>.nextMonth() = next(Month)
fun <Unit : MonthOrSmaller> TimePeriod<Unit>.previousMonth() = previous(Month)

fun <Unit : MonthOrSmaller> TimePeriod<Unit>.addingMonths(months: Int) = applying(TimeDifference.months(months))
fun <Unit : MonthOrSmaller> TimePeriod<Unit>.subtractingMonths(months: Int) = applying(TimeDifference.months(-months))

// Year

fun <Unit : YearOrSmaller> TimePeriod<Unit>.nextYear() = next(Year)
fun <Unit : YearOrSmaller> TimePeriod<Unit>.previousYear() = previous(Year)

fun <Unit : YearOrSmaller> TimePeriod<Unit>.addingYears(years: Int) = applying(TimeDifference.years(years))
fun <Unit : YearOrSmaller> TimePeriod<Unit>.subtractingYears(years: Int) = applying(TimeDifference.years(-years))
