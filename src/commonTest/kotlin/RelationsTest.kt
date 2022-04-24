import kotlinx.datetime.Clock
import kotlinx.datetime.Month
import kotlinx.datetime.TimeZone
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class RelationsTest {

    private val clock = Clock.System
    private val timeZone = TimeZone.UTC

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

    @Test
    fun meetsAndIsMetBy() {
        val a = TimePeriod.day(timeZone = timeZone, year = 2022, month = Month.JANUARY, dayOfMonth = 1)
        val b = TimePeriod.day(timeZone = timeZone, year = 2022, month = Month.JANUARY, dayOfMonth = 2)

        val aToB = a.relation(b)
        assertEquals(expected = Relation.Meets, actual = aToB)
        assertTrue(a.before(b))

        val bToA = b.relation(a)
        assertEquals(expected = Relation.IsMetBy, actual = bToA)
        assertTrue(b.after(a))
    }

    @Test
    fun startsAndIsStartedBy() {
        val a = TimePeriod.day(timeZone = timeZone, year = 2022, month = Month.JANUARY, dayOfMonth = 1)
        val b = TimePeriod.month(timeZone = timeZone, year = 2022, month = Month.JANUARY)

        val aToB = a.relation(b)
        assertEquals(expected = Relation.Starts, actual = aToB)
        assertTrue(a.during(b))
        assertFalse(b.during(a))

        val bToA = b.relation(a)
        assertEquals(expected = Relation.IsStartedBy, actual = bToA)
        assertTrue(b.contains(a))
        assertFalse(a.contains(b))
    }

    @Test
    fun duringAndContains() {
        val a = TimePeriod.day(timeZone = timeZone, year = 2022, month = Month.JANUARY, dayOfMonth = 2)
        val b = TimePeriod.month(timeZone = timeZone, year = 2022, month = Month.JANUARY)

        val aToB = a.relation(b)
        assertEquals(expected = Relation.During, actual = aToB)
        assertTrue(a.during(b))
        assertFalse(b.during(a))

        val bToA = b.relation(a)
        assertEquals(expected = Relation.Contains, actual = bToA)
        assertTrue(b.contains(a))
        assertFalse(a.contains(b))
    }

    @Test
    fun finishesAndIsFinishedBy() {
        val a = TimePeriod.day(timeZone = timeZone, year = 2022, month = Month.JANUARY, dayOfMonth = 31)
        val b = TimePeriod.month(timeZone = timeZone, year = 2022, month = Month.JANUARY)

        val aToB = a.relation(b)
        assertEquals(expected = Relation.Finishes, actual = aToB)
        assertTrue(a.during(b))
        assertFalse(b.during(a))

        val bToA = b.relation(a)
        assertEquals(expected = Relation.IsFinishedBy, actual = bToA)
        assertTrue(b.contains(a))
        assertFalse(a.contains(b))
    }

    @Test
    fun equal() {
        val a = TimePeriod.day(timeZone = timeZone, year = 2022, month = Month.JANUARY, dayOfMonth = 31)
        val b = TimePeriod.day(timeZone = timeZone, year = 2022, month = Month.JANUARY, dayOfMonth = 31)

        val aToB = a.relation(b)
        assertEquals(expected = Relation.Equal, actual = aToB)

        val bToA = b.relation(a)
        assertEquals(expected = Relation.Equal, actual = bToA)
    }
}
