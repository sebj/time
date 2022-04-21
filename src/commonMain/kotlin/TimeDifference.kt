data class TimeDifference<Unit : TimeUnit> internal constructor(internal val count: Int, internal val unit: Unit) {
    companion object {
        fun nanoseconds(count: Int) = TimeDifference(count, Nanosecond)
        fun seconds(count: Int) = TimeDifference(count, Second)
        fun minutes(count: Int) = TimeDifference(count, Minute)
        fun hours(count: Int) = TimeDifference(count, Hour)
        fun days(count: Int) = TimeDifference(count, Day)
        fun months(count: Int) = TimeDifference(count, Month)
        fun years(count: Int) = TimeDifference(count, Year)
    }

    internal fun negated() = copy(count = -count)
}

inline operator fun <Unit : TimeUnit> TimePeriod<Unit>.plus(difference: TimeDifference<Unit>) = this.applying(difference)
operator fun <Unit : TimeUnit> TimePeriod<Unit>.minus(difference: TimeDifference<Unit>) = this.applying(difference.negated())
