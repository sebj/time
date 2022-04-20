class TimeDifference<Unit : TimeUnit> internal constructor(val count: Int, val unit: Unit) {
    companion object {
        fun nanoseconds(count: Int) = TimeDifference(count, Nanosecond)
        fun seconds(count: Int) = TimeDifference(count, Second)
        fun minutes(count: Int) = TimeDifference(count, Minute)
        fun hours(count: Int) = TimeDifference(count, Hour)
        fun days(count: Int) = TimeDifference(count, Day)
        fun months(count: Int) = TimeDifference(count, Month)
        fun years(count: Int) = TimeDifference(count, Year)
    }
}
