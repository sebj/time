import kotlin.jvm.JvmName

public data class TimeDifference<SmallestUnit : TimeUnit> internal constructor(
    internal val count: Int,
    internal val smallestUnit: SmallestUnit
) {
    public companion object {
        public fun nanoseconds(count: Int): TimeDifference<TimeUnit.Nanosecond> = TimeDifference(count, TimeUnit.Nanosecond)
        public fun seconds(count: Int): TimeDifference<TimeUnit.Second> = TimeDifference(count, TimeUnit.Second)
        public fun minutes(count: Int): TimeDifference<TimeUnit.Minute> = TimeDifference(count, TimeUnit.Minute)
        public fun hours(count: Int): TimeDifference<TimeUnit.Hour> = TimeDifference(count, TimeUnit.Hour)
        public fun days(count: Int): TimeDifference<TimeUnit.Day> = TimeDifference(count, TimeUnit.Day)
        public fun months(count: Int): TimeDifference<TimeUnit.Month> = TimeDifference(count, TimeUnit.Month)
        public fun years(count: Int): TimeDifference<TimeUnit.Year> = TimeDifference(count, TimeUnit.Year)
    }

    internal fun negated() = copy(count = -count)

    override fun toString(): String = buildString {
        append(TimeDifference::class.simpleName)
        append(".")
        append(smallestUnit::class.simpleName)
        append("(count=")
        append(count)
        append(")")
    }
}

// region Addition
/**
 * Adds a [TimeDifference] to this [TimePeriod].
 */
@JvmName("plusNanosecondOrBigger")
public operator fun <Unit> TimePeriod<TimeUnit.Nanosecond>.plus(difference: TimeDifference<Unit>): TimePeriod<TimeUnit.Nanosecond> where Unit : NanosecondOrBigger, Unit : TimeUnit =
    this.applying(difference)

/**
 * Adds a [TimeDifference] to this [TimePeriod].
 */
@JvmName("plusSecondOrBigger")
public operator fun <Unit> TimePeriod<TimeUnit.Second>.plus(difference: TimeDifference<Unit>): TimePeriod<TimeUnit.Second> where Unit : SecondOrBigger, Unit : TimeUnit =
    this.applying(difference)

/**
 * Adds a [TimeDifference] to this [TimePeriod].
 */
@JvmName("plusMinuteOrBigger")
public operator fun <Unit> TimePeriod<TimeUnit.Minute>.plus(difference: TimeDifference<Unit>): TimePeriod<TimeUnit.Minute> where Unit : MinuteOrBigger, Unit : TimeUnit =
    this.applying(difference)

/**
 * Adds a [TimeDifference] to this [TimePeriod].
 */
@JvmName("plusHourOrBigger")
public operator fun <Unit> TimePeriod<TimeUnit.Hour>.plus(difference: TimeDifference<Unit>): TimePeriod<TimeUnit.Hour> where Unit : HourOrBigger, Unit : TimeUnit =
    this.applying(difference)

/**
 * Adds a [TimeDifference] to this [TimePeriod].
 */
@JvmName("plusDayOrBigger")
public operator fun <Unit> TimePeriod<TimeUnit.Day>.plus(difference: TimeDifference<Unit>): TimePeriod<TimeUnit.Day> where Unit : DayOrBigger, Unit : TimeUnit =
    this.applying(difference)

/**
 * Adds a [TimeDifference] to this [TimePeriod].
 */
@JvmName("plusMonthOrBigger")
public operator fun <Unit> TimePeriod<TimeUnit.Month>.plus(difference: TimeDifference<Unit>): TimePeriod<TimeUnit.Month> where Unit : MonthOrBigger, Unit : TimeUnit =
    this.applying(difference)

/**
 * Adds a [TimeDifference] to this [TimePeriod].
 */
@JvmName("plusYear")
public operator fun TimePeriod<TimeUnit.Year>.plus(difference: TimeDifference<TimeUnit.Year>): TimePeriod<TimeUnit.Year> = this.applying(difference)
// endregion

// region Subtraction
/**
 * Subtracts a [TimeDifference] from this [TimePeriod].
 */
@JvmName("minusNanosecond")
public operator fun <Unit> TimePeriod<TimeUnit.Nanosecond>.minus(difference: TimeDifference<Unit>): TimePeriod<TimeUnit.Nanosecond> where Unit : NanosecondOrBigger, Unit : TimeUnit =
    this.applying(difference.negated())

/**
 * Subtracts a [TimeDifference] from this [TimePeriod].
 */
@JvmName("minusSecond")
public operator fun <Unit> TimePeriod<TimeUnit.Second>.minus(difference: TimeDifference<Unit>): TimePeriod<TimeUnit.Second> where Unit : SecondOrBigger, Unit : TimeUnit =
    this.applying(difference.negated())

/**
 * Subtracts a [TimeDifference] from this [TimePeriod].
 */
@JvmName("minusMinute")
public operator fun <Unit> TimePeriod<TimeUnit.Minute>.minus(difference: TimeDifference<Unit>): TimePeriod<TimeUnit.Minute> where Unit : MinuteOrBigger, Unit : TimeUnit =
    this.applying(difference.negated())

/**
 * Subtracts a [TimeDifference] from this [TimePeriod].
 */
@JvmName("minusHour")
public operator fun <Unit> TimePeriod<TimeUnit.Hour>.minus(difference: TimeDifference<Unit>): TimePeriod<TimeUnit.Hour> where Unit : HourOrBigger, Unit : TimeUnit =
    this.applying(difference.negated())

/**
 * Subtracts a [TimeDifference] from this [TimePeriod].
 */
@JvmName("minusDay")
public operator fun <Unit> TimePeriod<TimeUnit.Day>.minus(difference: TimeDifference<Unit>): TimePeriod<TimeUnit.Day> where Unit : DayOrBigger, Unit : TimeUnit =
    this.applying(difference.negated())

/**
 * Subtracts a [TimeDifference] from this [TimePeriod].
 */
@JvmName("minusMonth")
public operator fun <Unit> TimePeriod<TimeUnit.Month>.minus(difference: TimeDifference<Unit>): TimePeriod<TimeUnit.Month> where Unit : MonthOrBigger, Unit : TimeUnit =
    this.applying(difference.negated())

/**
 * Subtracts a [TimeDifference] from this [TimePeriod].
 */
@JvmName("minusYear")
public operator fun TimePeriod<TimeUnit.Year>.minus(difference: TimeDifference<TimeUnit.Year>): TimePeriod<TimeUnit.Year> = this.applying(difference.negated())
// endregion
