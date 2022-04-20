import kotlinx.datetime.Clock
import kotlin.test.Test
import kotlin.test.assertEquals

class DerivedPeriodsTest {

    private val clock = Clock.System

    @Test
    fun secondPeriod() {
        assertEquals(
            expected = clock.thisSecond(),
            actual = clock.thisNanosecond().secondPeriod
        )
    }

    @Test
    fun minutePeriod() {
        assertEquals(
            expected = clock.thisMinute(),
            actual = clock.thisNanosecond().minutePeriod
        )

        assertEquals(
            expected = clock.thisMinute(),
            actual = clock.thisSecond().minutePeriod
        )
    }

    @Test
    fun hourPeriod() {
        assertEquals(
            expected = clock.thisHour(),
            actual = clock.thisSecond().hourPeriod
        )

        assertEquals(
            expected = clock.thisHour(),
            actual = clock.thisMinute().hourPeriod
        )
    }

    @Test
    fun dayPeriod() {
        assertEquals(
            expected = clock.today(),
            actual = clock.thisSecond().dayPeriod
        )

        assertEquals(
            expected = clock.today(),
            actual = clock.thisMinute().dayPeriod
        )

        assertEquals(
            expected = clock.today(),
            actual = clock.thisHour().dayPeriod
        )
    }

    @Test
    fun monthPeriod() {
        assertEquals(
            expected = clock.thisMonth(),
            actual = clock.thisSecond().monthPeriod
        )

        assertEquals(
            expected = clock.thisMonth(),
            actual = clock.thisMinute().monthPeriod
        )

        assertEquals(
            expected = clock.thisMonth(),
            actual = clock.thisHour().monthPeriod
        )

        assertEquals(
            expected = clock.thisMonth(),
            actual = clock.today().monthPeriod
        )
    }

    @Test
    fun yearPeriod() {
        assertEquals(
            expected = clock.thisYear(),
            actual = clock.thisSecond().yearPeriod
        )

        assertEquals(
            expected = clock.thisYear(),
            actual = clock.thisMinute().yearPeriod
        )

        assertEquals(
            expected = clock.thisYear(),
            actual = clock.thisHour().yearPeriod
        )

        assertEquals(
            expected = clock.thisYear(),
            actual = clock.today().yearPeriod
        )

        assertEquals(
            expected = clock.thisYear(),
            actual = clock.thisMonth().yearPeriod
        )
    }
}
