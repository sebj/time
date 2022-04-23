import kotlin.test.Test
import kotlin.test.assertEquals

class TimeDifferenceTest {

    @Test
    fun negated() {
        val difference = TimeDifference.hours(3).negated()
        assertEquals(
            expected = -3,
            actual = difference.count
        )
        assertEquals(
            expected = Hour,
            actual = difference.unit
        )
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
