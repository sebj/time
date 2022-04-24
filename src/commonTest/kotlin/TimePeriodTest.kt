import kotlinx.datetime.*
import kotlinx.datetime.Month
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.Duration.Companion.days

class TimePeriodTest {

    private val clock = Clock.System
    private val timeZone = TimeZone.UTC

    @Test
    fun equality() {
        val clock = clock
        assertEquals(clock.today(timeZone), clock.today(timeZone))
        assertEquals(clock.today(timeZone), clock.tomorrow(timeZone).previousDay)
    }

    @Test
    fun toLocalDate() {
        assertEquals(
            expected = clock.todayAt(timeZone),
            actual = clock.today(timeZone).toLocalDate()
        )
    }

    @Test
    fun toLocalDateTime() {
        val clock = clock
        val expected = clock.thisInstant().toLocalDateTime(timeZone)
        val actual = clock.thisNanosecond(timeZone).toLocalDateTime()

        assertEquals(expected = expected.date, actual = actual.date)
        assertEquals(expected = expected.hour, actual = actual.hour)
        assertEquals(expected = expected.minute, actual = actual.minute)
        assertEquals(expected = expected.second, actual = actual.second)
        assertEquals(expected = expected.nanosecond.toDouble(), actual = actual.nanosecond.toDouble(), absoluteTolerance = 5_000_000.0)
    }

    @Test
    fun applyDaysDifference() {
        val timeZone = TimeZone.UTC
        val expectedTomorrow = clock.now().plus(1.days).toLocalDateTime(timeZone).date
        val actualTomorrow = clock.thisHour(timeZone).addingDays(1).toLocalDate()

        assertEquals(
            expected = expectedTomorrow,
            actual = actualTomorrow
        )
    }

    @Test
    fun testMonthSubtraction() {
        val expectedDate = LocalDate(year = 2022, month = Month.APRIL, dayOfMonth = 1)
            .minus(12, DateTimeUnit.MONTH)

        val actualMonth = TimePeriod
            .month(timeZone = timeZone, year = 2022, month = Month.APRIL)
            .subtractingMonths(12)

        assertEquals(
            expected = expectedDate.month,
            actual = actualMonth.month
        )

        assertEquals(
            expected = expectedDate.year,
            actual = actualMonth.year
        )
    }

    @Test
    fun nextMonth() {
        val day = TimePeriod.day(timeZone = timeZone, year = 2022, month = Month.MARCH, dayOfMonth = 25).nextMonth

        assertEquals(
            expected = Month.APRIL,
            actual = day.month
        )

        assertEquals(
            expected = 25,
            actual = day.dayOfMonth
        )
    }

    @Test
    fun string() {
        val day = TimePeriod.day(timeZone = timeZone, year = 2022, month = Month.APRIL, dayOfMonth = 22)
        assertEquals(
            expected = "TimePeriod.Day(timeZone=Z, year=2022, month=APRIL, dayOfMonth=22)",
            actual = day.toString()
        )
    }

    @Test
    fun convertTimeZone() {
        val hour = TimePeriod.hour(
            timeZone = TimeZone.of("Europe/London"),
            year = 2022,
            month = Month.APRIL,
            dayOfMonth = 24,
            hour = 18
        )

        val parisHour = hour.convertToTimeZone(TimeZone.of("Europe/Paris"))
        assertEquals(
            expected = 19,
            actual = parisHour.hour
        )
    }
}
