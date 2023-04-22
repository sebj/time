import kotlin.jvm.JvmName

data class TimeDifference<SmallestUnit : TimeUnit> internal constructor(
    internal val count: Int,
    internal val smallestUnit: SmallestUnit
) {
    companion object {
        fun nanoseconds(count: Int) = TimeDifference(count, TimeUnit.Nanosecond)
        fun seconds(count: Int) = TimeDifference(count, TimeUnit.Second)
        fun minutes(count: Int) = TimeDifference(count, TimeUnit.Minute)
        fun hours(count: Int) = TimeDifference(count, TimeUnit.Hour)
        fun days(count: Int) = TimeDifference(count, TimeUnit.Day)
        fun months(count: Int) = TimeDifference(count, TimeUnit.Month)
        fun years(count: Int) = TimeDifference(count, TimeUnit.Year)
    }

    internal fun negated() = copy(count = -count)

    override fun toString() = buildString {
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
operator fun <Unit> TimePeriod<TimeUnit.Nanosecond>.plus(difference: TimeDifference<Unit>) where Unit : NanosecondOrBigger, Unit : TimeUnit =
    this.applying(difference)

/**
 * Adds a [TimeDifference] to this [TimePeriod].
 */
@JvmName("plusSecondOrBigger")
operator fun <Unit> TimePeriod<TimeUnit.Second>.plus(difference: TimeDifference<Unit>) where Unit : SecondOrBigger, Unit : TimeUnit =
    this.applying(difference)

/**
 * Adds a [TimeDifference] to this [TimePeriod].
 */
@JvmName("plusMinuteOrBigger")
operator fun <Unit> TimePeriod<TimeUnit.Minute>.plus(difference: TimeDifference<Unit>) where Unit : MinuteOrBigger, Unit : TimeUnit =
    this.applying(difference)

/**
 * Adds a [TimeDifference] to this [TimePeriod].
 */
@JvmName("plusHourOrBigger")
operator fun <Unit> TimePeriod<TimeUnit.Hour>.plus(difference: TimeDifference<Unit>) where Unit : HourOrBigger, Unit : TimeUnit =
    this.applying(difference)

/**
 * Adds a [TimeDifference] to this [TimePeriod].
 */
@JvmName("plusDayOrBigger")
operator fun <Unit> TimePeriod<TimeUnit.Day>.plus(difference: TimeDifference<Unit>) where Unit : DayOrBigger, Unit : TimeUnit =
    this.applying(difference)

/**
 * Adds a [TimeDifference] to this [TimePeriod].
 */
@JvmName("plusMonthOrBigger")
operator fun <Unit> TimePeriod<TimeUnit.Month>.plus(difference: TimeDifference<Unit>) where Unit : MonthOrBigger, Unit : TimeUnit =
    this.applying(difference)

/**
 * Adds a [TimeDifference] to this [TimePeriod].
 */
@JvmName("plusYear")
operator fun TimePeriod<TimeUnit.Year>.plus(difference: TimeDifference<TimeUnit.Year>) = this.applying(difference)
// endregion

// region Subtraction
/**
 * Subtracts a [TimeDifference] from this [TimePeriod].
 */
@JvmName("minusNanosecond")
operator fun <Unit> TimePeriod<TimeUnit.Nanosecond>.minus(difference: TimeDifference<Unit>) where Unit : NanosecondOrBigger, Unit : TimeUnit =
    this.applying(difference.negated())

/**
 * Subtracts a [TimeDifference] from this [TimePeriod].
 */
@JvmName("minusSecond")
operator fun <Unit> TimePeriod<TimeUnit.Second>.minus(difference: TimeDifference<Unit>) where Unit : SecondOrBigger, Unit : TimeUnit =
    this.applying(difference.negated())

/**
 * Subtracts a [TimeDifference] from this [TimePeriod].
 */
@JvmName("minusMinute")
operator fun <Unit> TimePeriod<TimeUnit.Minute>.minus(difference: TimeDifference<Unit>) where Unit : MinuteOrBigger, Unit : TimeUnit =
    this.applying(difference.negated())

/**
 * Subtracts a [TimeDifference] from this [TimePeriod].
 */
@JvmName("minusHour")
operator fun <Unit> TimePeriod<TimeUnit.Hour>.minus(difference: TimeDifference<Unit>) where Unit : HourOrBigger, Unit : TimeUnit =
    this.applying(difference.negated())

/**
 * Subtracts a [TimeDifference] from this [TimePeriod].
 */
@JvmName("minusDay")
operator fun <Unit> TimePeriod<TimeUnit.Day>.minus(difference: TimeDifference<Unit>) where Unit : DayOrBigger, Unit : TimeUnit =
    this.applying(difference.negated())

/**
 * Subtracts a [TimeDifference] from this [TimePeriod].
 */
@JvmName("minusMonth")
operator fun <Unit> TimePeriod<TimeUnit.Month>.minus(difference: TimeDifference<Unit>) where Unit : MonthOrBigger, Unit : TimeUnit =
    this.applying(difference.negated())

/**
 * Subtracts a [TimeDifference] from this [TimePeriod].
 */
@JvmName("minusYear")
operator fun TimePeriod<TimeUnit.Year>.minus(difference: TimeDifference<TimeUnit.Year>) = this.applying(difference.negated())
// endregion
