import kotlinx.datetime.Month
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class DateTimeComponentsTest {

    @Test
    fun toLocalDateThrowsExceptionWhenMonthMissing() {
        val components = DateTimeComponents(year = 2022)
        assertFailsWith(MissingDateTimeComponentsException::class) {
            components.toLocalDate()
        }
    }

    @Test
    fun toLocalDateThrowsExceptionWhenDayOfMonthMissing() {
        val components = DateTimeComponents(year = 2022, month = Month.FEBRUARY)
        assertFailsWith(MissingDateTimeComponentsException::class) {
            components.toLocalDate()
        }
    }

    @Test
    fun toLocalDate() {
        val components = DateTimeComponents(year = 2022, month = Month.FEBRUARY, dayOfMonth = 1)
        val date = components.toLocalDate()
        assertEquals(expected = components.year, date.year)
        assertEquals(expected = components.month, date.month)
        assertEquals(expected = components.dayOfMonth, date.dayOfMonth)
    }

    @Test
    fun toLocalDateTimeThrowsExceptionWhenMonthMissing() {
        val components = DateTimeComponents(year = 2022)
        assertFailsWith(MissingDateTimeComponentsException::class) {
            components.toLocalDateTime()
        }
    }

    @Test
    fun toLocalDateTimeThrowsExceptionWhenDayOfMonthMissing() {
        val components = DateTimeComponents(year = 2022, month = Month.FEBRUARY)
        assertFailsWith(MissingDateTimeComponentsException::class) {
            components.toLocalDateTime()
        }
    }

    @Test
    fun toLocalDateTimeThrowsExceptionWhenHourMissing() {
        val components = DateTimeComponents(year = 2022, month = Month.FEBRUARY, dayOfMonth = 1)
        assertFailsWith(MissingDateTimeComponentsException::class) {
            components.toLocalDateTime()
        }
    }

    @Test
    fun toLocalDateTime() {
        val components = DateTimeComponents(year = 2022, month = Month.FEBRUARY, dayOfMonth = 1, hour = 1)
        val dateTime = components.toLocalDateTime()
        assertEquals(expected = components.year, dateTime.year)
        assertEquals(expected = components.month, dateTime.month)
        assertEquals(expected = components.dayOfMonth, dateTime.dayOfMonth)
        assertEquals(expected = components.hour, dateTime.hour)
    }

    @Test
    fun string() {
        val components = DateTimeComponents(year = 2022, month = Month.APRIL, dayOfMonth = 22)
        assertEquals(
            expected = "DateTimeComponents(year=2022, month=APRIL, dayOfMonth=22)",
            actual = components.toString()
        )
    }
}
