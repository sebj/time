import kotlinx.datetime.Clock
import kotlin.test.Test
import kotlin.test.assertEquals

class RelationsTest {

    private val clock = Clock.System

    @Test
    fun before() {
        assertEquals(
            expected = true,
            actual = clock.thisSecond().before(clock.nextSecond())
        )

        assertEquals(
            expected = true,
            actual = clock.previousDay().before(clock.nextDay())
        )

        assertEquals(
            expected = true,
            actual = clock.thisMonth().before(clock.nextMonth())
        )

        assertEquals(
            expected = true,
            actual = clock.previousYear().before(clock.thisYear())
        )
    }

    @Test
    fun after() {
        assertEquals(
            expected = true,
            actual = clock.nextSecond().after(clock.thisSecond())
        )

        assertEquals(
            expected = false,
            actual = clock.thisSecond().after(clock.thisSecond())
        )

        assertEquals(
            expected = true,
            actual = clock.nextDay().after(clock.previousDay())
        )

        assertEquals(
            expected = false,
            actual = clock.nextDay().after(clock.nextDay())
        )

        assertEquals(
            expected = true,
            actual = clock.nextMonth().after(clock.thisMonth())
        )

        assertEquals(
            expected = false,
            actual = clock.thisMonth().after(clock.thisMonth())
        )

        assertEquals(
            expected = true,
            actual = clock.thisYear().after(clock.previousYear())
        )

        assertEquals(
            expected = false,
            actual = clock.thisYear().after(clock.thisYear())
        )
    }

    @Test
    fun minuteContainsSecond() {
        assertEquals(
            expected = true,
            actual = clock.thisMinute().contains(clock.thisSecond())
        )

        assertEquals(
            expected = false,
            actual = clock.previousMinute().contains(clock.thisSecond())
        )
    }

    @Test
    fun hourContains() {
        assertEquals(
            expected = true,
            actual = clock.thisHour().contains(clock.thisSecond())
        )

        assertEquals(
            expected = true,
            actual = clock.thisHour().contains(clock.thisMinute())
        )

        assertEquals(
            expected = false,
            actual = clock.previousHour().contains(clock.thisSecond())
        )
    }

    @Test
    fun dayContains() {
        assertEquals(
            expected = true,
            actual = clock.thisDay().contains(clock.thisSecond())
        )

        assertEquals(
            expected = true,
            actual = clock.thisDay().contains(clock.thisMinute())
        )

        assertEquals(
            expected = true,
            actual = clock.thisDay().contains(clock.thisHour())
        )

        assertEquals(
            expected = false,
            actual = clock.previousDay().contains(clock.thisSecond())
        )
    }

    @Test
    fun monthContains() {
        assertEquals(
            expected = true,
            actual = clock.thisMonth().contains(clock.thisSecond())
        )

        assertEquals(
            expected = true,
            actual = clock.thisMonth().contains(clock.thisMinute())
        )

        assertEquals(
            expected = true,
            actual = clock.thisMonth().contains(clock.today())
        )

        assertEquals(
            expected = false,
            actual = clock.previousMonth().contains(clock.thisSecond())
        )

        assertEquals(
            expected = false,
            actual = clock.previousMonth().contains(clock.thisMinute())
        )

        assertEquals(
            expected = false,
            actual = clock.previousMonth().contains(clock.today())
        )
    }
}
