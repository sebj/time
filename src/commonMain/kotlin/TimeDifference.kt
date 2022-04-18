class TimeDifference<Unit : TimeUnit> internal constructor(val count: Int, val unit: Unit) {

    companion object {
        fun seconds(count: Int) = TimeDifference<SecondOrSmaller>(count = count, unit = Second)
        fun minutes(count: Int) = TimeDifference<MinuteOrSmaller>(count = count, unit = Minute)
        fun hours(count: Int) = TimeDifference<HourOrSmaller>(count = count, unit = Hour)
        fun days(count: Int) = TimeDifference<DayOrSmaller>(count = count, unit = Day)
        fun months(count: Int) = TimeDifference<MonthOrSmaller>(count = count, unit = Month)
        fun years(count: Int) = TimeDifference<YearOrSmaller>(count = count, unit = Year)
    }
}
