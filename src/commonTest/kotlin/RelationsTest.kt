import kotlinx.datetime.Clock
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class RelationsTest {

    private val clock = Clock.System

    @Test
    fun before() {
        assertTrue(clock.thisSecond().before(clock.nextSecond()))
        assertTrue(clock.previousDay().before(clock.nextDay()))
        assertTrue(clock.thisMonth().before(clock.nextMonth()))
        assertTrue(clock.previousYear().before(clock.thisYear()))
    }

    @Test
    fun after() {
        assertTrue(clock.nextSecond().after(clock.thisSecond()))
        assertFalse(clock.thisSecond().after(clock.thisSecond()))

        assertTrue(clock.nextDay().after(clock.previousDay()))
        assertFalse(clock.nextDay().after(clock.nextDay()))

        assertTrue(clock.nextMonth().after(clock.thisMonth()))
        assertFalse(clock.thisMonth().after(clock.thisMonth()))

        assertTrue(clock.thisYear().after(clock.previousYear()))
        assertFalse(clock.thisYear().after(clock.thisYear()))
    }

    @Test
    fun minuteContainsSecond() {
        assertTrue(clock.thisMinute().contains(clock.thisSecond()))
        assertFalse(clock.previousMinute().contains(clock.thisSecond()))
    }

    @Test
    fun hourContains() {
        assertTrue(clock.thisHour().contains(clock.thisSecond()))
        assertTrue(clock.thisHour().contains(clock.thisMinute()))
        assertFalse(clock.previousHour().contains(clock.thisSecond()))
    }

    @Test
    fun dayContains() {
        assertTrue(clock.thisDay().contains(clock.thisSecond()))
        assertTrue(clock.thisDay().contains(clock.thisMinute()))
        assertTrue(clock.thisDay().contains(clock.thisHour()))
        assertFalse(clock.previousDay().contains(clock.thisSecond()))
    }

    @Test
    fun monthContains() {
        assertTrue(clock.thisMonth().contains(clock.thisSecond()))
        assertTrue(clock.thisMonth().contains(clock.thisMinute()))
        assertTrue(clock.thisMonth().contains(clock.today()))
        assertFalse(clock.previousMonth().contains(clock.thisSecond()))
        assertFalse(clock.previousMonth().contains(clock.thisMinute()))
        assertFalse(clock.previousMonth().contains(clock.today()))
    }
}
