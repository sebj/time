import kotlinx.datetime.LocalDateTime
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs

class LocalDateTimeExtensionsTest {

    private val dateTime = LocalDateTime(
        year = 2022,
        month = kotlinx.datetime.Month.JANUARY,
        dayOfMonth = 1,
        hour = 9,
        minute = 30,
        second = 15,
        nanosecond = 2
    )

    private val date = dateTime.date

    @Test
    fun dateTimeSecondPeriod() {
        val period = dateTime.secondPeriod()
        assertIs<TimePeriod<Second>>(period)
        assertEquals(expected = dateTime.year, actual = period.year)
        assertEquals(expected = dateTime.month, actual = period.month)
        assertEquals(expected = dateTime.dayOfMonth, actual = period.dayOfMonth)
        assertEquals(expected = dateTime.hour, actual = period.hour)
        assertEquals(expected = dateTime.minute, actual = period.minute)
        assertEquals(expected = dateTime.second, actual = period.second)
    }

    @Test
    fun dateTimeMinutePeriod() {
        val period = dateTime.minutePeriod()
        assertIs<TimePeriod<Minute>>(period)
        assertEquals(expected = dateTime.year, actual = period.year)
        assertEquals(expected = dateTime.month, actual = period.month)
        assertEquals(expected = dateTime.dayOfMonth, actual = period.dayOfMonth)
        assertEquals(expected = dateTime.hour, actual = period.hour)
        assertEquals(expected = dateTime.minute, actual = period.minute)
    }

    @Test
    fun dateTimeHourPeriod() {
        val period = dateTime.hourPeriod()
        assertIs<TimePeriod<Hour>>(period)
        assertEquals(expected = dateTime.year, actual = period.year)
        assertEquals(expected = dateTime.month, actual = period.month)
        assertEquals(expected = dateTime.dayOfMonth, actual = period.dayOfMonth)
        assertEquals(expected = dateTime.hour, actual = period.hour)
    }

    @Test
    fun dateTimeDayPeriod() {
        val period = dateTime.dayPeriod()
        assertIs<TimePeriod<Day>>(period)
        assertEquals(expected = dateTime.year, actual = period.year)
        assertEquals(expected = dateTime.month, actual = period.month)
        assertEquals(expected = dateTime.dayOfMonth, actual = period.dayOfMonth)
    }

    @Test
    fun dateTimeMonthPeriod() {
        val period = dateTime.monthPeriod()
        assertIs<TimePeriod<Month>>(period)
        assertEquals(expected = dateTime.year, actual = period.year)
        assertEquals(expected = dateTime.month, actual = period.month)
    }

    @Test
    fun dateTimeYearPeriod() {
        val period = dateTime.yearPeriod()
        assertIs<TimePeriod<Year>>(period)
        assertEquals(expected = dateTime.year, actual = period.year)
    }

    @Test
    fun dateDayPeriod() {
        val period = date.dayPeriod()
        assertIs<TimePeriod<Day>>(period)
        assertEquals(expected = dateTime.year, actual = period.year)
        assertEquals(expected = dateTime.month, actual = period.month)
        assertEquals(expected = dateTime.dayOfMonth, actual = period.dayOfMonth)
    }

    @Test
    fun dateMonthPeriod() {
        val period = date.monthPeriod()
        assertIs<TimePeriod<Month>>(period)
        assertEquals(expected = dateTime.year, actual = period.year)
        assertEquals(expected = dateTime.month, actual = period.month)
    }

    @Test
    fun dateYearPeriod() {
        val period = date.yearPeriod()
        assertIs<TimePeriod<Year>>(period)
        assertEquals(expected = dateTime.year, actual = period.year)
    }
}
