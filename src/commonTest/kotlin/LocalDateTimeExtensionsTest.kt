import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
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
    private val timeZone = TimeZone.UTC

    @Test
    fun dateTimeSecondPeriod() {
        val period = dateTime.secondPeriod(timeZone)
        assertIs<TimePeriod<TimeUnit.Second>>(period)
        assertEquals(expected = dateTime.year, actual = period.year)
        assertEquals(expected = dateTime.month, actual = period.month)
        assertEquals(expected = dateTime.dayOfMonth, actual = period.dayOfMonth)
        assertEquals(expected = dateTime.hour, actual = period.hour)
        assertEquals(expected = dateTime.minute, actual = period.minute)
        assertEquals(expected = dateTime.second, actual = period.second)
    }

    @Test
    fun dateTimeMinutePeriod() {
        val period = dateTime.minutePeriod(timeZone)
        assertIs<TimePeriod<TimeUnit.Minute>>(period)
        assertEquals(expected = dateTime.year, actual = period.year)
        assertEquals(expected = dateTime.month, actual = period.month)
        assertEquals(expected = dateTime.dayOfMonth, actual = period.dayOfMonth)
        assertEquals(expected = dateTime.hour, actual = period.hour)
        assertEquals(expected = dateTime.minute, actual = period.minute)
    }

    @Test
    fun dateTimeHourPeriod() {
        val period = dateTime.hourPeriod(timeZone)
        assertIs<TimePeriod<TimeUnit.Hour>>(period)
        assertEquals(expected = dateTime.year, actual = period.year)
        assertEquals(expected = dateTime.month, actual = period.month)
        assertEquals(expected = dateTime.dayOfMonth, actual = period.dayOfMonth)
        assertEquals(expected = dateTime.hour, actual = period.hour)
    }

    @Test
    fun dateTimeDayPeriod() {
        val period = dateTime.dayPeriod(timeZone)
        assertIs<TimePeriod<TimeUnit.Day>>(period)
        assertEquals(expected = dateTime.year, actual = period.year)
        assertEquals(expected = dateTime.month, actual = period.month)
        assertEquals(expected = dateTime.dayOfMonth, actual = period.dayOfMonth)
    }

    @Test
    fun dateTimeMonthPeriod() {
        val period = dateTime.monthPeriod(timeZone)
        assertIs<TimePeriod<TimeUnit.Month>>(period)
        assertEquals(expected = dateTime.year, actual = period.year)
        assertEquals(expected = dateTime.month, actual = period.month)
    }

    @Test
    fun dateTimeYearPeriod() {
        val period = dateTime.yearPeriod(timeZone)
        assertIs<TimePeriod<TimeUnit.Year>>(period)
        assertEquals(expected = dateTime.year, actual = period.year)
    }

    @Test
    fun dateDayPeriod() {
        val period = date.dayPeriod(timeZone)
        assertIs<TimePeriod<TimeUnit.Day>>(period)
        assertEquals(expected = dateTime.year, actual = period.year)
        assertEquals(expected = dateTime.month, actual = period.month)
        assertEquals(expected = dateTime.dayOfMonth, actual = period.dayOfMonth)
    }

    @Test
    fun dateMonthPeriod() {
        val period = date.monthPeriod(timeZone)
        assertIs<TimePeriod<TimeUnit.Month>>(period)
        assertEquals(expected = dateTime.year, actual = period.year)
        assertEquals(expected = dateTime.month, actual = period.month)
    }

    @Test
    fun dateYearPeriod() {
        val period = date.yearPeriod(timeZone)
        assertIs<TimePeriod<TimeUnit.Year>>(period)
        assertEquals(expected = dateTime.year, actual = period.year)
    }
}
