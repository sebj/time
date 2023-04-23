import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone

private fun LocalDateTime.toDateTimeComponents() = DateTimeComponents(
    year = year,
    month = month,
    dayOfMonth = dayOfMonth,
    hour = hour,
    minute = minute,
    second = second,
    nanosecond = nanosecond
)

private fun LocalDate.toDateTimeComponents() = DateTimeComponents(
    year = year,
    month = month,
    dayOfMonth = dayOfMonth
)

public fun LocalDateTime.secondPeriod(timeZone: TimeZone): TimePeriod<TimeUnit.Second> =
    TimePeriod.second(timeZone, toDateTimeComponents())

public fun LocalDateTime.minutePeriod(timeZone: TimeZone): TimePeriod<TimeUnit.Minute> =
    TimePeriod.minute(timeZone, toDateTimeComponents())

public fun LocalDateTime.hourPeriod(timeZone: TimeZone): TimePeriod<TimeUnit.Hour> =
    TimePeriod.hour(timeZone, toDateTimeComponents())

public fun LocalDateTime.dayPeriod(timeZone: TimeZone): TimePeriod<TimeUnit.Day> =
    TimePeriod.day(timeZone, toDateTimeComponents())

public fun LocalDateTime.monthPeriod(timeZone: TimeZone): TimePeriod<TimeUnit.Month> =
    TimePeriod.month(timeZone, toDateTimeComponents())

public fun LocalDateTime.yearPeriod(timeZone: TimeZone): TimePeriod<TimeUnit.Year> =
    TimePeriod.year(timeZone, toDateTimeComponents())

public fun LocalDate.dayPeriod(timeZone: TimeZone): TimePeriod<TimeUnit.Day> =
    TimePeriod.day(timeZone, toDateTimeComponents())

public fun LocalDate.monthPeriod(timeZone: TimeZone): TimePeriod<TimeUnit.Month> =
    TimePeriod.month(timeZone, toDateTimeComponents())

public fun LocalDate.yearPeriod(timeZone: TimeZone): TimePeriod<TimeUnit.Year> =
    TimePeriod.year(timeZone, toDateTimeComponents())
