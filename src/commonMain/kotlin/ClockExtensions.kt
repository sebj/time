import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone

/**
 * @return The [kotlinx.datetime.Instant] corresponding to the current time, according to this [Clock] and [TimeZone].
 */
public fun Clock.thisInstant(): Instant = now()

private fun <Unit : TimeUnit> Clock.thisPeriod(timeZone: TimeZone, unit: Unit) = TimePeriod(timeZone, now(), unit)

/**
 * Retrieve the current year of the [Clock] and [TimeZone].
 */
public fun Clock.thisYear(timeZone: TimeZone = TimeZone.currentSystemDefault()): TimePeriod<TimeUnit.Year> =
    thisPeriod(timeZone, TimeUnit.Year)

/**
 * Retrieve the current month of the [Clock] and [TimeZone].
 */
public fun Clock.thisMonth(timeZone: TimeZone = TimeZone.currentSystemDefault()): TimePeriod<TimeUnit.Month> =
    thisPeriod(timeZone, TimeUnit.Month)

/**
 * Retrieve the current day of the [Clock] and [TimeZone].
 */
public fun Clock.thisDay(timeZone: TimeZone = TimeZone.currentSystemDefault()): TimePeriod<TimeUnit.Day> =
    thisPeriod(timeZone, TimeUnit.Day)

/**
 * Retrieve the current hour of the [Clock] and [TimeZone].
 */
public fun Clock.thisHour(timeZone: TimeZone = TimeZone.currentSystemDefault()): TimePeriod<TimeUnit.Hour> =
    thisPeriod(timeZone, TimeUnit.Hour)

/**
 * Retrieve the current minute of the [Clock] and [TimeZone].
 */
public fun Clock.thisMinute(timeZone: TimeZone = TimeZone.currentSystemDefault()): TimePeriod<TimeUnit.Minute> =
    thisPeriod(timeZone, TimeUnit.Minute)

/**
 * Retrieve the current second of the [Clock] and [TimeZone].
 */
public fun Clock.thisSecond(timeZone: TimeZone = TimeZone.currentSystemDefault()): TimePeriod<TimeUnit.Second> =
    thisPeriod(timeZone, TimeUnit.Second)

/**
 * Retrieve the current nanosecond of the [Clock] and [TimeZone].
 */
public fun Clock.thisNanosecond(timeZone: TimeZone = TimeZone.currentSystemDefault()): TimePeriod<TimeUnit.Nanosecond> =
    thisPeriod(timeZone, TimeUnit.Nanosecond)

/**
 * Retrieve the current day of the [Clock] and [TimeZone].
 */
public fun Clock.today(timeZone: TimeZone = TimeZone.currentSystemDefault()): TimePeriod<TimeUnit.Day> =
    thisDay(timeZone)

public fun Clock.tomorrow(timeZone: TimeZone = TimeZone.currentSystemDefault()): TimePeriod<TimeUnit.Day> =
    today(timeZone).nextDay

public fun Clock.yesterday(timeZone: TimeZone = TimeZone.currentSystemDefault()): TimePeriod<TimeUnit.Day> =
    today(timeZone).previousDay

public fun Clock.nextYear(timeZone: TimeZone = TimeZone.currentSystemDefault()): TimePeriod<TimeUnit.Year> =
    thisYear(timeZone).nextYear

public fun Clock.nextMonth(timeZone: TimeZone = TimeZone.currentSystemDefault()): TimePeriod<TimeUnit.Month> =
    thisMonth(timeZone).nextMonth

public fun Clock.nextDay(timeZone: TimeZone = TimeZone.currentSystemDefault()): TimePeriod<TimeUnit.Day> =
    thisDay(timeZone).nextDay

public fun Clock.nextHour(timeZone: TimeZone = TimeZone.currentSystemDefault()): TimePeriod<TimeUnit.Hour> =
    thisHour(timeZone).nextHour

public fun Clock.nextMinute(timeZone: TimeZone = TimeZone.currentSystemDefault()): TimePeriod<TimeUnit.Minute> =
    thisMinute(timeZone).nextMinute

public fun Clock.nextSecond(timeZone: TimeZone = TimeZone.currentSystemDefault()): TimePeriod<TimeUnit.Second> =
    thisSecond(timeZone).nextSecond

public fun Clock.previousYear(timeZone: TimeZone = TimeZone.currentSystemDefault()): TimePeriod<TimeUnit.Year> =
    thisYear(timeZone).previousYear

public fun Clock.previousMonth(timeZone: TimeZone = TimeZone.currentSystemDefault()): TimePeriod<TimeUnit.Month> =
    thisMonth(timeZone).previousMonth

public fun Clock.previousDay(timeZone: TimeZone = TimeZone.currentSystemDefault()): TimePeriod<TimeUnit.Day> =
    thisDay(timeZone).previousDay

public fun Clock.previousHour(timeZone: TimeZone = TimeZone.currentSystemDefault()): TimePeriod<TimeUnit.Hour> =
    thisHour(timeZone).previousHour

public fun Clock.previousMinute(timeZone: TimeZone = TimeZone.currentSystemDefault()): TimePeriod<TimeUnit.Minute> =
    thisMinute(timeZone).previousMinute

public fun Clock.previousSecond(timeZone: TimeZone = TimeZone.currentSystemDefault()): TimePeriod<TimeUnit.Second> =
    thisSecond(timeZone).previousSecond
