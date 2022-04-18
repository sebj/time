import kotlinx.datetime.Clock
import kotlinx.datetime.Month
import kotlin.test.Test
import kotlin.test.assertEquals

class BoundsTest {

    @Test
    fun testYearFirstMonth() {
        val year = Clock.System.thisYear()
        val month = year.firstMonth()
        assertEquals(expected = year.year, actual = month.year)
        assertEquals(expected = Month.JANUARY, actual = month.month)
    }

    @Test
    fun testYearLastMonth() {
        val year = Clock.System.thisYear()
        val month = year.lastMonth()
        assertEquals(expected = year.year, actual = month.year)
        assertEquals(expected = Month.DECEMBER, actual = month.month)
    }

    @Test
    fun testYearFirstDay() {
        val year = Clock.System.thisYear()
        val day = year.firstDay()
        assertEquals(expected = year.year, actual = day.year)
        assertEquals(expected = Month.JANUARY, actual = day.month)
        assertEquals(expected = 1, actual = day.dayOfMonth)
    }

    @Test
    fun testYearLastDay() {
        val year = Clock.System.thisYear()
        val day = year.lastDay()
        assertEquals(expected = year.year, actual = day.year)
        assertEquals(expected = Month.DECEMBER, actual = day.month)
        assertEquals(expected = 31, actual = day.dayOfMonth)
    }

    @Test
    fun testMonthFirstDay() {
        val month = Clock.System.thisMonth()
        val day = month.firstDay()
        assertEquals(expected = month.year, actual = day.year)
        assertEquals(expected = month.month, actual = day.month)
        assertEquals(expected = 1, actual = day.dayOfMonth)
    }

    @Test
    fun testMonthLastDay() {
        val month = Clock.System.thisMonth()
        val day = month.lastDay()
        assertEquals(expected = month.year, actual = day.year)
        assertEquals(expected = month.month, actual = day.month)
    }

    @Test
    fun testMonthFirstHour() {
        val month = Clock.System.thisMonth()
        val hour = month.firstHour()
        assertEquals(expected = month.year, actual = hour.year)
        assertEquals(expected = month.month, actual = hour.month)
        assertEquals(expected = 1, actual = hour.dayOfMonth)
        assertEquals(expected = 0, actual = hour.hour)
    }

    @Test
    fun testMonthLastHour() {
        val month = Clock.System.thisMonth()
        val hour = month.lastHour()
        assertEquals(expected = month.year, actual = hour.year)
        assertEquals(expected = month.month, actual = hour.month)
        assertEquals(expected = 23, actual = hour.hour)
    }

    @Test
    fun testDayFirstHour() {
        val day = Clock.System.today()
        val hour = day.firstHour()
        assertEquals(expected = day.year, actual = hour.year)
        assertEquals(expected = day.month, actual = hour.month)
        assertEquals(expected = day.dayOfMonth, actual = hour.dayOfMonth)
        assertEquals(expected = 0, actual = hour.hour)
    }

    @Test
    fun testDayLastHour() {
        val day = Clock.System.today()
        val hour = day.lastHour()
        assertEquals(expected = day.year, actual = hour.year)
        assertEquals(expected = day.month, actual = hour.month)
        assertEquals(expected = day.dayOfMonth, actual = hour.dayOfMonth)
        assertEquals(expected = 23, actual = hour.hour)
    }

    @Test
    fun testDayFirstMinute() {
        val day = Clock.System.today()
        val minute = day.firstMinute()
        assertEquals(expected = day.year, actual = minute.year)
        assertEquals(expected = day.month, actual = minute.month)
        assertEquals(expected = day.dayOfMonth, actual = minute.dayOfMonth)
        assertEquals(expected = 0, actual = minute.hour)
        assertEquals(expected = 0, actual = minute.minute)
    }

    @Test
    fun testDayLastMinute() {
        val day = Clock.System.today()
        val minute = day.lastMinute()
        assertEquals(expected = day.year, actual = minute.year)
        assertEquals(expected = day.month, actual = minute.month)
        assertEquals(expected = day.dayOfMonth, actual = minute.dayOfMonth)
        assertEquals(expected = 23, actual = minute.hour)
        assertEquals(expected = 59, actual = minute.minute)
    }

    @Test
    fun testDayFirstSecond() {
        val day = Clock.System.today()
        val second = day.firstSecond()
        assertEquals(expected = day.year, actual = second.year)
        assertEquals(expected = day.month, actual = second.month)
        assertEquals(expected = day.dayOfMonth, actual = second.dayOfMonth)
        assertEquals(expected = 0, actual = second.hour)
        assertEquals(expected = 0, actual = second.minute)
        assertEquals(expected = 0, actual = second.second)
    }

    @Test
    fun testDayLastSecond() {
        val day = Clock.System.today()
        val second = day.lastSecond()
        assertEquals(expected = day.year, actual = second.year)
        assertEquals(expected = day.month, actual = second.month)
        assertEquals(expected = day.dayOfMonth, actual = second.dayOfMonth)
        assertEquals(expected = 23, actual = second.hour)
        assertEquals(expected = 59, actual = second.minute)
        assertEquals(expected = 59, actual = second.second)
    }

    @Test
    fun testHourFirstMinute() {
        val hour = Clock.System.thisHour()
        val minute = hour.firstMinute()
        assertEquals(expected = hour.year, actual = minute.year)
        assertEquals(expected = hour.month, actual = minute.month)
        assertEquals(expected = hour.dayOfMonth, actual = minute.dayOfMonth)
        assertEquals(expected = hour.hour, actual = minute.hour)
        assertEquals(expected = 0, actual = minute.minute)
    }

    @Test
    fun testHourLastMinute() {
        val hour = Clock.System.thisHour()
        val minute = hour.lastMinute()
        assertEquals(expected = hour.year, actual = minute.year)
        assertEquals(expected = hour.month, actual = minute.month)
        assertEquals(expected = hour.dayOfMonth, actual = minute.dayOfMonth)
        assertEquals(expected = hour.hour, actual = minute.hour)
        assertEquals(expected = 59, actual = minute.minute)
    }

    @Test
    fun testHourFirstSecond() {
        val hour = Clock.System.thisHour()
        val second = hour.firstSecond()
        assertEquals(expected = hour.year, actual = second.year)
        assertEquals(expected = hour.month, actual = second.month)
        assertEquals(expected = hour.dayOfMonth, actual = second.dayOfMonth)
        assertEquals(expected = hour.hour, actual = second.hour)
        assertEquals(expected = 0, actual = second.minute)
        assertEquals(expected = 0, actual = second.second)
    }

    @Test
    fun testHourLastSecond() {
        val hour = Clock.System.thisHour()
        val second = hour.lastSecond()
        assertEquals(expected = hour.year, actual = second.year)
        assertEquals(expected = hour.month, actual = second.month)
        assertEquals(expected = hour.dayOfMonth, actual = second.dayOfMonth)
        assertEquals(expected = hour.hour, actual = second.hour)
        assertEquals(expected = 59, actual = second.minute)
        assertEquals(expected = 59, actual = second.second)
    }

    @Test
    fun testMinuteFirstSecond() {
        val minute = Clock.System.thisMinute()
        val second = minute.firstSecond()
        assertEquals(expected = minute.year, actual = second.year)
        assertEquals(expected = minute.month, actual = second.month)
        assertEquals(expected = minute.dayOfMonth, actual = second.dayOfMonth)
        assertEquals(expected = minute.hour, actual = second.hour)
        assertEquals(expected = minute.minute, actual = second.minute)
        assertEquals(expected = 0, actual = second.second)
    }

    @Test
    fun testMinuteLastSecond() {
        val minute = Clock.System.thisMinute()
        val second = minute.lastSecond()
        assertEquals(expected = minute.year, actual = second.year)
        assertEquals(expected = minute.month, actual = second.month)
        assertEquals(expected = minute.dayOfMonth, actual = second.dayOfMonth)
        assertEquals(expected = minute.hour, actual = second.hour)
        assertEquals(expected = minute.minute, actual = second.minute)
        assertEquals(expected = 59, actual = second.second)
    }
}
