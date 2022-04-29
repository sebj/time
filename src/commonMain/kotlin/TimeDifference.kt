data class TimeDifference<Unit : TimeUnit> internal constructor(internal val count: Int, internal val unit: Unit) {
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
        append(unit::class.simpleName)
        append("(count=")
        append(count)
        append(")")
    }
}

/**
 * Adds a [TimeDifference] to this [TimePeriod].
 */
operator fun <Unit : TimeUnit> TimePeriod<Unit>.plus(difference: TimeDifference<Unit>) = this.applying(difference)

/**
 * Subtracts a [TimeDifference] from this [TimePeriod].
 */
operator fun <Unit : TimeUnit> TimePeriod<Unit>.minus(difference: TimeDifference<Unit>) = this.applying(difference.negated())
