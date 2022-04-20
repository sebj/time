import kotlinx.datetime.Clock
import kotlinx.datetime.Month
import kotlin.test.Test
import kotlin.test.assertEquals

class SequencesTest {

    private val clock = Clock.System

    @Test
    fun minuteSeconds() {
        val minute = clock.thisMinute()
        val seconds = minute.seconds
        assertEquals(expected = 60, actual = seconds.count())
        assertEquals(expected = 0, actual = seconds.first().second)
        assertEquals(expected = 59, actual = seconds.last().second)
    }

    @Test
    fun hourMinutes() {
        val hour = clock.thisHour()
        val minutes = hour.minutes
        assertEquals(expected = 60, actual = minutes.count())
        assertEquals(expected = 0, actual = minutes.first().minute)
        assertEquals(expected = 59, actual = minutes.last().minute)
    }

    @Test
    fun monthDays() {
        val month = TimePeriod.month(year = 2022, month = kotlinx.datetime.Month.FEBRUARY)
        val days = month.days.toList()
        assertEquals(expected = 28, actual = days.count())
        assertEquals(expected = 1, actual = days.first().dayOfMonth)
        assertEquals(expected = 28, actual = days.last().dayOfMonth)
    }

    @Test
    fun yearMonths() {
        val year = clock.thisYear()
        val months = year.months.toList()
        assertEquals(expected = 12, actual = months.count())
        assertEquals(expected = Month.JANUARY, actual = months.first().month)
        assertEquals(expected = Month.DECEMBER, actual = months.last().month)
    }
}
