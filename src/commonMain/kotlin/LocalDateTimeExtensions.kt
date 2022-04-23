import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

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

fun LocalDateTime.secondPeriod() = TimePeriod.second(toDateTimeComponents())
fun LocalDateTime.minutePeriod() = TimePeriod.minute(toDateTimeComponents())
fun LocalDateTime.hourPeriod() = TimePeriod.hour(toDateTimeComponents())
fun LocalDateTime.dayPeriod() = TimePeriod.day(toDateTimeComponents())
fun LocalDateTime.monthPeriod() = TimePeriod.month(toDateTimeComponents())
fun LocalDateTime.yearPeriod() = TimePeriod.year(toDateTimeComponents())

fun LocalDate.dayPeriod() = TimePeriod.day(toDateTimeComponents())
fun LocalDate.monthPeriod() = TimePeriod.month(toDateTimeComponents())
fun LocalDate.yearPeriod() = TimePeriod.year(toDateTimeComponents())
