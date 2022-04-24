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

fun LocalDateTime.secondPeriod(timeZone: TimeZone) = TimePeriod.second(timeZone, toDateTimeComponents())
fun LocalDateTime.minutePeriod(timeZone: TimeZone) = TimePeriod.minute(timeZone, toDateTimeComponents())
fun LocalDateTime.hourPeriod(timeZone: TimeZone) = TimePeriod.hour(timeZone, toDateTimeComponents())
fun LocalDateTime.dayPeriod(timeZone: TimeZone) = TimePeriod.day(timeZone, toDateTimeComponents())
fun LocalDateTime.monthPeriod(timeZone: TimeZone) = TimePeriod.month(timeZone, toDateTimeComponents())
fun LocalDateTime.yearPeriod(timeZone: TimeZone) = TimePeriod.year(timeZone, toDateTimeComponents())

fun LocalDate.dayPeriod(timeZone: TimeZone) = TimePeriod.day(timeZone, toDateTimeComponents())
fun LocalDate.monthPeriod(timeZone: TimeZone) = TimePeriod.month(timeZone, toDateTimeComponents())
fun LocalDate.yearPeriod(timeZone: TimeZone) = TimePeriod.year(timeZone, toDateTimeComponents())
