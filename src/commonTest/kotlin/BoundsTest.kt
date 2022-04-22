import kotlinx.datetime.Clock
import kotlinx.datetime.Month
import kotlin.test.Test
import kotlin.test.assertEquals

class BoundsTest {

    private val clock = Clock.System

    @Test
    fun yearFirstMonth() {
        val year = clock.thisYear()
        val month = year.firstMonth
        assertEquals(expected = year.year, actual = month.year)
        assertEquals(expected = Month.JANUARY, actual = month.month)
    }

    @Test
    fun yearLastMonth() {
        val year = clock.thisYear()
        val month = year.lastMonth
        assertEquals(expected = year.year, actual = month.year)
        assertEquals(expected = Month.DECEMBER, actual = month.month)
    }

    @Test
    fun yearFirstDay() {
        val year = clock.thisYear()
        val day = year.firstDay
        assertEquals(expected = year.year, actual = day.year)
        assertEquals(expected = Month.JANUARY, actual = day.month)
        assertEquals(expected = 1, actual = day.dayOfMonth)
    }

    @Test
    fun yearLastDay() {
        val year = clock.thisYear()
        val day = year.lastDay
        assertEquals(expected = year.year, actual = day.year)
        assertEquals(expected = Month.DECEMBER, actual = day.month)
        assertEquals(expected = 31, actual = day.dayOfMonth)
    }

    @Test
    fun monthFirstDay() {
        val month = clock.thisMonth()
        val day = month.firstDay
        assertEquals(expected = month.year, actual = day.year)
        assertEquals(expected = month.month, actual = day.month)
        assertEquals(expected = 1, actual = day.dayOfMonth)
    }

    @Test
    fun monthLastDay() {
        val month = clock.thisMonth()
        val day = month.lastDay
        assertEquals(expected = month.year, actual = day.year)
        assertEquals(expected = month.month, actual = day.month)
    }

    @Test
    fun monthFirstHour() {
        val month = clock.thisMonth()
        val hour = month.firstHour
        assertEquals(expected = month.year, actual = hour.year)
        assertEquals(expected = month.month, actual = hour.month)
        assertEquals(expected = 1, actual = hour.dayOfMonth)
        assertEquals(expected = 0, actual = hour.hour)
    }

    @Test
    fun monthLastHour() {
        val month = clock.thisMonth()
        val hour = month.lastHour
        assertEquals(expected = month.year, actual = hour.year)
        assertEquals(expected = month.month, actual = hour.month)
        assertEquals(expected = 23, actual = hour.hour)
    }

    @Test
    fun dayFirstHour() {
        val day = clock.today()
        val hour = day.firstHour
        assertEquals(expected = day.year, actual = hour.year)
        assertEquals(expected = day.month, actual = hour.month)
        assertEquals(expected = day.dayOfMonth, actual = hour.dayOfMonth)
        assertEquals(expected = 0, actual = hour.hour)
    }

    @Test
    fun dayLastHour() {
        val day = clock.today()
        val hour = day.lastHour
        assertEquals(expected = day.year, actual = hour.year)
        assertEquals(expected = day.month, actual = hour.month)
        assertEquals(expected = day.dayOfMonth, actual = hour.dayOfMonth)
        assertEquals(expected = 23, actual = hour.hour)
    }

    @Test
    fun dayFirstMinute() {
        val day = clock.today()
        val minute = day.firstMinute
        assertEquals(expected = day.year, actual = minute.year)
        assertEquals(expected = day.month, actual = minute.month)
        assertEquals(expected = day.dayOfMonth, actual = minute.dayOfMonth)
        assertEquals(expected = 0, actual = minute.hour)
        assertEquals(expected = 0, actual = minute.minute)
    }

    @Test
    fun dayLastMinute() {
        val day = clock.today()
        val minute = day.lastMinute
        assertEquals(expected = day.year, actual = minute.year)
        assertEquals(expected = day.month, actual = minute.month)
        assertEquals(expected = day.dayOfMonth, actual = minute.dayOfMonth)
        assertEquals(expected = 23, actual = minute.hour)
        assertEquals(expected = 59, actual = minute.minute)
    }

    @Test
    fun dayFirstSecond() {
        val day = clock.today()
        val second = day.firstSecond
        assertEquals(expected = day.year, actual = second.year)
        assertEquals(expected = day.month, actual = second.month)
        assertEquals(expected = day.dayOfMonth, actual = second.dayOfMonth)
        assertEquals(expected = 0, actual = second.hour)
        assertEquals(expected = 0, actual = second.minute)
        assertEquals(expected = 0, actual = second.second)
    }

    @Test
    fun dayLastSecond() {
        val day = clock.today()
        val second = day.lastSecond
        assertEquals(expected = day.year, actual = second.year)
        assertEquals(expected = day.month, actual = second.month)
        assertEquals(expected = day.dayOfMonth, actual = second.dayOfMonth)
        assertEquals(expected = 23, actual = second.hour)
        assertEquals(expected = 59, actual = second.minute)
        assertEquals(expected = 59, actual = second.second)
    }

    @Test
    fun hourFirstMinute() {
        val hour = clock.thisHour()
        val minute = hour.firstMinute
        assertEquals(expected = hour.year, actual = minute.year)
        assertEquals(expected = hour.month, actual = minute.month)
        assertEquals(expected = hour.dayOfMonth, actual = minute.dayOfMonth)
        assertEquals(expected = hour.hour, actual = minute.hour)
        assertEquals(expected = 0, actual = minute.minute)
    }

    @Test
    fun hourLastMinute() {
        val hour = clock.thisHour()
        val minute = hour.lastMinute
        assertEquals(expected = hour.year, actual = minute.year)
        assertEquals(expected = hour.month, actual = minute.month)
        assertEquals(expected = hour.dayOfMonth, actual = minute.dayOfMonth)
        assertEquals(expected = hour.hour, actual = minute.hour)
        assertEquals(expected = 59, actual = minute.minute)
    }

    @Test
    fun hourFirstSecond() {
        val hour = clock.thisHour()
        val second = hour.firstSecond
        assertEquals(expected = hour.year, actual = second.year)
        assertEquals(expected = hour.month, actual = second.month)
        assertEquals(expected = hour.dayOfMonth, actual = second.dayOfMonth)
        assertEquals(expected = hour.hour, actual = second.hour)
        assertEquals(expected = 0, actual = second.minute)
        assertEquals(expected = 0, actual = second.second)
    }

    @Test
    fun hourLastSecond() {
        val hour = clock.thisHour()
        val second = hour.lastSecond
        assertEquals(expected = hour.year, actual = second.year)
        assertEquals(expected = hour.month, actual = second.month)
        assertEquals(expected = hour.dayOfMonth, actual = second.dayOfMonth)
        assertEquals(expected = hour.hour, actual = second.hour)
        assertEquals(expected = 59, actual = second.minute)
        assertEquals(expected = 59, actual = second.second)
    }

    @Test
    fun minuteFirstSecond() {
        val minute = clock.thisMinute()
        val second = minute.firstSecond
        assertEquals(expected = minute.year, actual = second.year)
        assertEquals(expected = minute.month, actual = second.month)
        assertEquals(expected = minute.dayOfMonth, actual = second.dayOfMonth)
        assertEquals(expected = minute.hour, actual = second.hour)
        assertEquals(expected = minute.minute, actual = second.minute)
        assertEquals(expected = 0, actual = second.second)
    }

    @Test
    fun minuteLastSecond() {
        val minute = clock.thisMinute()
        val second = minute.lastSecond
        assertEquals(expected = minute.year, actual = second.year)
        assertEquals(expected = minute.month, actual = second.month)
        assertEquals(expected = minute.dayOfMonth, actual = second.dayOfMonth)
        assertEquals(expected = minute.hour, actual = second.hour)
        assertEquals(expected = minute.minute, actual = second.minute)
        assertEquals(expected = 59, actual = second.second)
    }
}
