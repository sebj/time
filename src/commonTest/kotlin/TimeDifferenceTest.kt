import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs

class TimeDifferenceTest {

    @Test
    fun negated() {
        val difference = TimeDifference.hours(3).negated()
        assertEquals(
            expected = -3,
            actual = difference.count
        )
        assertIs<TimeUnit.Hour>(difference.smallestUnit)
    }

    @Test
    fun string() {
        val difference = TimeDifference.months(2)
        assertEquals(
            expected = "TimeDifference.Month(count=2)",
            actual = difference.toString()
        )
    }
}
