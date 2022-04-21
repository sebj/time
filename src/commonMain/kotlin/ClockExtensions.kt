import kotlinx.datetime.Clock

/**
 * @return The [kotlinx.datetime.Instant] corresponding to the current time, according to this [Clock].
 */
fun Clock.thisInstant() = now()

private fun <Unit : TimeUnit> Clock.thisPeriod(unit: Unit) = TimePeriod(now(), unit)

/**
 * Retrieve the current year of the [Clock].
 */
fun Clock.thisYear() = thisPeriod(Year)
/**
 * Retrieve the current month of the [Clock].
 */
fun Clock.thisMonth() = thisPeriod(Month)
/**
 * Retrieve the current day of the [Clock].
 */
fun Clock.thisDay() = thisPeriod(Day)
/**
 * Retrieve the current hour of the [Clock].
 */
fun Clock.thisHour() = thisPeriod(Hour)
/**
 * Retrieve the current minute of the [Clock].
 */
fun Clock.thisMinute() = thisPeriod(Minute)
/**
 * Retrieve the current second of the [Clock].
 */
fun Clock.thisSecond() = thisPeriod(Second)
/**
 * Retrieve the current nanosecond of the [Clock].
 */
fun Clock.thisNanosecond() = thisPeriod(Nanosecond)

/**
 * Retrieve the current day of the [Clock].
 */
fun Clock.today() = thisDay()
fun Clock.tomorrow() = today().nextDay
fun Clock.yesterday() = today().previousDay

fun Clock.nextYear() = thisYear().nextYear
fun Clock.nextMonth() = thisMonth().nextMonth
fun Clock.nextDay() = thisDay().nextDay
fun Clock.nextHour() = thisHour().nextHour
fun Clock.nextMinute() = thisMinute().nextMinute
fun Clock.nextSecond() = thisSecond().nextSecond

fun Clock.previousYear() = thisYear().previousYear
fun Clock.previousMonth() = thisMonth().previousMonth
fun Clock.previousDay() = thisDay().previousDay
fun Clock.previousHour() = thisHour().previousHour
fun Clock.previousMinute() = thisMinute().previousMinute
fun Clock.previousSecond() = thisSecond().previousSecond
