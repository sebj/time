import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlin.time.Duration

private class ScaledClock(private val scale: Double, private val base: Clock) : Clock {
    private val start = base.now()

    override fun now(): Instant {
        val baseNow = base.now()
        val elapsedTime = baseNow - start
        return start + (elapsedTime * scale)
    }
}

private class OffsetClock(private val offset: Duration, private val base: Clock) : Clock {
    override fun now() = base.now() + offset
}

/**
 * Offset a [Clock].
 *
 * @param delta The delta by which to create an offset [Clock].
 */
public fun Clock.offset(delta: Duration): Clock = OffsetClock(delta, this)

/**
 * Scale a [Clock].
 *
 * @param factor The factor by which to speed up or slow down time.
 */
public fun Clock.scaled(factor: Double): Clock {
    check(factor > 0) { "You cannot create a clock where time has stopped or flows backwards" }
    return ScaledClock(factor, this)
}
