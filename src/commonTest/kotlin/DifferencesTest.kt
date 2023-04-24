import kotlinx.datetime.Clock
import kotlin.test.Test
import kotlin.test.assertEquals

class DifferencesTest {

    private val clock = Clock.System

    @Test
    fun differenceNanoseconds() {
        val thisNanosecond = clock.thisNanosecond()
        val nextNanosecond = thisNanosecond + TimeDifference.nanoseconds(1)

        assertEquals(
            actual = (thisNanosecond - thisNanosecond).nanoseconds,
            expected = 0
        )
        assertEquals(
            actual = (nextNanosecond - thisNanosecond).nanoseconds,
            expected = -1
        )
    }

    @Test
    fun differenceSeconds() {
        assertEquals(
            actual = (clock.thisSecond() - clock.thisSecond()).seconds,
            expected = 0
        )
        assertEquals(
            actual = (clock.nextSecond() - clock.thisSecond()).seconds,
            expected = -1
        )
    }

    @Test
    fun differenceMinutes() {
        assertEquals(
            actual = (clock.thisMinute() - clock.thisMinute()).minutes,
            expected = 0
        )
        assertEquals(
            actual = (clock.nextMinute() - clock.thisMinute()).minutes,
            expected = -1
        )
    }

    @Test
    fun differenceHours() {
        assertEquals(
            actual = (clock.thisHour() - clock.thisHour()).hours,
            expected = 0
        )
        assertEquals(
            actual = (clock.nextHour() - clock.thisHour()).hours,
            expected = -1
        )
    }

    @Test
    fun differenceDays() {
        assertEquals(
            actual = (clock.today() - clock.today()).days,
            expected = 0
        )
        assertEquals(
            actual = (clock.tomorrow() - clock.today()).days,
            expected = -1
        )
    }

    @Test
    fun differenceMonths() {
        assertEquals(
            actual = (clock.thisMonth() - clock.thisMonth()).months,
            expected = 0
        )
        assertEquals(
            actual = (clock.nextMonth() - clock.thisMonth()).months,
            expected = -1
        )
    }

    @Test
    fun differenceYears() {
        assertEquals(
            actual = (clock.thisYear() - clock.thisYear()).years,
            expected = 0
        )
        assertEquals(
            actual = (clock.nextYear() - clock.thisYear()).years,
            expected = -1
        )
    }
}