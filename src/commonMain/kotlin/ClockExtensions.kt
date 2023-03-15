import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone

/**
 * @return The [kotlinx.datetime.Instant] corresponding to the current time, according to this [Clock] and [TimeZone].
 */
fun Clock.thisInstant() = now()

private fun <Unit : TimeUnit> Clock.thisPeriod(timeZone: TimeZone, unit: Unit) = TimePeriod(timeZone, now(), unit)

/**
 * Retrieve the current year of the [Clock] and [TimeZone].
 */
fun Clock.thisYear(timeZone: TimeZone = TimeZone.currentSystemDefault()) = thisPeriod(timeZone, TimeUnit.Year)

/**
 * Retrieve the current month of the [Clock] and [TimeZone].
 */
fun Clock.thisMonth(timeZone: TimeZone = TimeZone.currentSystemDefault()) = thisPeriod(timeZone, TimeUnit.Month)

/**
 * Retrieve the current day of the [Clock] and [TimeZone].
 */
fun Clock.thisDay(timeZone: TimeZone = TimeZone.currentSystemDefault()) = thisPeriod(timeZone, TimeUnit.Day)

/**
 * Retrieve the current hour of the [Clock] and [TimeZone].
 */
fun Clock.thisHour(timeZone: TimeZone = TimeZone.currentSystemDefault()) = thisPeriod(timeZone, TimeUnit.Hour)

/**
 * Retrieve the current minute of the [Clock] and [TimeZone].
 */
fun Clock.thisMinute(timeZone: TimeZone = TimeZone.currentSystemDefault()) = thisPeriod(timeZone, TimeUnit.Minute)

/**
 * Retrieve the current second of the [Clock] and [TimeZone].
 */
fun Clock.thisSecond(timeZone: TimeZone = TimeZone.currentSystemDefault()) = thisPeriod(timeZone, TimeUnit.Second)

/**
 * Retrieve the current nanosecond of the [Clock] and [TimeZone].
 */
fun Clock.thisNanosecond(timeZone: TimeZone = TimeZone.currentSystemDefault()) = thisPeriod(timeZone, TimeUnit.Nanosecond)

/**
 * Retrieve the current day of the [Clock] and [TimeZone].
 */
fun Clock.today(timeZone: TimeZone = TimeZone.currentSystemDefault()) = thisDay(timeZone)
fun Clock.tomorrow(timeZone: TimeZone = TimeZone.currentSystemDefault()) = today(timeZone).nextDay
fun Clock.yesterday(timeZone: TimeZone = TimeZone.currentSystemDefault()) = today(timeZone).previousDay

fun Clock.nextYear(timeZone: TimeZone = TimeZone.currentSystemDefault()) = thisYear(timeZone).nextYear
fun Clock.nextMonth(timeZone: TimeZone = TimeZone.currentSystemDefault()) = thisMonth(timeZone).nextMonth
fun Clock.nextDay(timeZone: TimeZone = TimeZone.currentSystemDefault()) = thisDay(timeZone).nextDay
fun Clock.nextHour(timeZone: TimeZone = TimeZone.currentSystemDefault()) = thisHour(timeZone).nextHour
fun Clock.nextMinute(timeZone: TimeZone = TimeZone.currentSystemDefault()) = thisMinute(timeZone).nextMinute
fun Clock.nextSecond(timeZone: TimeZone = TimeZone.currentSystemDefault()) = thisSecond(timeZone).nextSecond

fun Clock.previousYear(timeZone: TimeZone = TimeZone.currentSystemDefault()) = thisYear(timeZone).previousYear
fun Clock.previousMonth(timeZone: TimeZone = TimeZone.currentSystemDefault()) = thisMonth(timeZone).previousMonth
fun Clock.previousDay(timeZone: TimeZone = TimeZone.currentSystemDefault()) = thisDay(timeZone).previousDay
fun Clock.previousHour(timeZone: TimeZone = TimeZone.currentSystemDefault()) = thisHour(timeZone).previousHour
fun Clock.previousMinute(timeZone: TimeZone = TimeZone.currentSystemDefault()) = thisMinute(timeZone).previousMinute
fun Clock.previousSecond(timeZone: TimeZone = TimeZone.currentSystemDefault()) = thisSecond(timeZone).previousSecond
