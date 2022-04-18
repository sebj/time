import kotlinx.datetime.Clock
import kotlin.test.Test
import kotlin.test.assertEquals

class RelationsTest {

    @Test
    fun before() {
        assertEquals(
            expected = true,
            actual = Clock.System.thisSecond().before(Clock.System.nextSecond())
        )

        assertEquals(
            expected = true,
            actual = Clock.System.previousDay().before(Clock.System.nextDay())
        )

        assertEquals(
            expected = true,
            actual = Clock.System.thisMonth().before(Clock.System.nextMonth())
        )

        assertEquals(
            expected = true,
            actual = Clock.System.previousYear().before(Clock.System.thisYear())
        )
    }

    @Test
    fun after() {
        assertEquals(
            expected = true,
            actual = Clock.System.nextSecond().after(Clock.System.thisSecond())
        )

        assertEquals(
            expected = false,
            actual = Clock.System.thisSecond().after(Clock.System.thisSecond())
        )

        assertEquals(
            expected = true,
            actual = Clock.System.nextDay().after(Clock.System.previousDay())
        )

        assertEquals(
            expected = false,
            actual = Clock.System.nextDay().after(Clock.System.nextDay())
        )

        assertEquals(
            expected = true,
            actual = Clock.System.nextMonth().after(Clock.System.thisMonth())
        )

        assertEquals(
            expected = false,
            actual = Clock.System.thisMonth().after(Clock.System.thisMonth())
        )

        assertEquals(
            expected = true,
            actual = Clock.System.thisYear().after(Clock.System.previousYear())
        )

        assertEquals(
            expected = false,
            actual = Clock.System.thisYear().after(Clock.System.thisYear())
        )
    }

    @Test
    fun minuteContainsSecond() {
        assertEquals(
            expected = true,
            actual = Clock.System.thisMinute().contains(Clock.System.thisSecond())
        )

        assertEquals(
            expected = false,
            actual = Clock.System.previousMinute().contains(Clock.System.thisSecond())
        )
    }

    @Test
    fun hourContains() {
        assertEquals(
            expected = true,
            actual = Clock.System.thisHour().contains(Clock.System.thisSecond())
        )

        assertEquals(
            expected = true,
            actual = Clock.System.thisHour().contains(Clock.System.thisMinute())
        )

        assertEquals(
            expected = false,
            actual = Clock.System.previousHour().contains(Clock.System.thisSecond())
        )
    }

    @Test
    fun dayContains() {
        assertEquals(
            expected = true,
            actual = Clock.System.thisDay().contains(Clock.System.thisSecond())
        )

        assertEquals(
            expected = true,
            actual = Clock.System.thisDay().contains(Clock.System.thisMinute())
        )

        assertEquals(
            expected = true,
            actual = Clock.System.thisDay().contains(Clock.System.thisHour())
        )

        assertEquals(
            expected = false,
            actual = Clock.System.previousDay().contains(Clock.System.thisSecond())
        )
    }

    @Test
    fun monthContains() {
        assertEquals(
            expected = true,
            actual = Clock.System.thisMonth().contains(Clock.System.thisSecond())
        )

        assertEquals(
            expected = true,
            actual = Clock.System.thisMonth().contains(Clock.System.thisMinute())
        )

        assertEquals(
            expected = true,
            actual = Clock.System.thisMonth().contains(Clock.System.today())
        )

        assertEquals(
            expected = false,
            actual = Clock.System.previousMonth().contains(Clock.System.thisSecond())
        )

        assertEquals(
            expected = false,
            actual = Clock.System.previousMonth().contains(Clock.System.thisMinute())
        )

        assertEquals(
            expected = false,
            actual = Clock.System.previousMonth().contains(Clock.System.today())
        )
    }
}
