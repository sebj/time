import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayAt
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.Duration.Companion.days

class TimePeriodTest {

    private val clock = Clock.System

    @Test
    fun equality() {
        val clock = clock
        assertEquals(clock.today(), clock.today())
        assertEquals(clock.today(), clock.tomorrow().previousDay)
    }

    @Test
    fun toLocalDate() {
        assertEquals(
            expected = clock.todayAt(TimeZone.UTC),
            actual = clock.today().toLocalDate(TimeZone.UTC)
        )
    }

    @Test
    fun toLocalDateTime() {
        val clock = clock
        val expected = clock.thisInstant().toLocalDateTime(TimeZone.UTC)
        val actual = clock.thisNanosecond().toLocalDateTime(TimeZone.UTC)

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
        val actualTomorrow = clock.thisHour().addingDays(1).toLocalDate(timeZone)

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
            .month(year = 2022, month = Month.APRIL)
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
        val day = TimePeriod.day(year = 2022, month = Month.MARCH, dayOfMonth = 25).nextMonth

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
        val a = clock.today().firstHour.hour
        println(a.toString())
        val day = TimePeriod.day(year = 2022, month = Month.APRIL, dayOfMonth = 22)
        assertEquals(
            expected = "TimePeriod.Day(year=2022, month=APRIL, dayOfMonth=22)",
            actual = day.toString()
        )
    }
}
