fun <Unit : TimeUnit> TimePeriod<Unit>.offset(count: Int): TimePeriod<Unit> {
    if (count == 0) {
        return this
    }

    return applying(TimeDifference(count, unit))
}

// region Nanosecond
/**
 * Create a new [TimePeriod] by moving forward one nanosecond.
 */
val <Unit : NanosecondOrSmaller> TimePeriod<Unit>.nextNanosecond get() = addingNanoseconds(1)
/**
 * Create a new [TimePeriod] by moving backward one nanosecond.
 */
val <Unit : NanosecondOrSmaller> TimePeriod<Unit>.previousNanosecond get() = subtractingNanoseconds(1)

/**
 * Create a new [TimePeriod] by moving forward some number of nanoseconds.
 *
 * @param count The number of nanoseconds by which to move forward.
 */
fun <Unit : NanosecondOrSmaller> TimePeriod<Unit>.addingNanoseconds(count: Int) = applying(TimeDifference.nanoseconds(count))
/**
 * Create a new [TimePeriod] by moving backward some number of nanoseconds.
 *
 * @param count The number of nanoseconds by which to move backward.
 */
fun <Unit : NanosecondOrSmaller> TimePeriod<Unit>.subtractingNanoseconds(count: Int) = applying(TimeDifference.nanoseconds(-count))
// endregion

// region Second
/**
 * Create a new [TimePeriod] by moving forward one second.
 */
val <Unit : SecondOrSmaller> TimePeriod<Unit>.nextSecond get() = addingSeconds(1)
/**
 * Create a new [TimePeriod] by moving backward one second.
 */
val <Unit : SecondOrSmaller> TimePeriod<Unit>.previousSecond get() = subtractingSeconds(1)

/**
 * Create a new [TimePeriod] by moving forward some number of seconds.
 *
 * @param count The number of seconds by which to move forward.
 */
fun <Unit : SecondOrSmaller> TimePeriod<Unit>.addingSeconds(count: Int) = applying(TimeDifference.seconds(count))
/**
 * Create a new [TimePeriod] by moving backward some number of seconds.
 *
 * @param count The number of seconds by which to move backward.
 */
fun <Unit : SecondOrSmaller> TimePeriod<Unit>.subtractingSeconds(count: Int) = applying(TimeDifference.seconds(-count))
// endregion

// region Minute
/**
 * Create a new [TimePeriod] by moving forward one minute.
 */
val <Unit : MinuteOrSmaller> TimePeriod<Unit>.nextMinute get() = addingMinutes(1)
/**
 * Create a new [TimePeriod] by moving backward one minute.
 */
val <Unit : MinuteOrSmaller> TimePeriod<Unit>.previousMinute get() = subtractingMinutes(1)

/**
 * Create a new [TimePeriod] by moving forward some number of minutes.
 *
 * @param count The number of minutes by which to move forward.
 */
fun <Unit : MinuteOrSmaller> TimePeriod<Unit>.addingMinutes(count: Int) = applying(TimeDifference.minutes(count))
/**
 * Create a new [TimePeriod] by moving backward some number of minutes.
 *
 * @param count The number of minutes by which to move backward.
 */
fun <Unit : MinuteOrSmaller> TimePeriod<Unit>.subtractingMinutes(count: Int) = applying(TimeDifference.minutes(-count))
//endregion

// region Hour
/**
 * Create a new [TimePeriod] by moving forward one hour.
 */
val <Unit : HourOrSmaller> TimePeriod<Unit>.nextHour get() = addingHours(1)
/**
 * Create a new [TimePeriod] by moving backward one hour.
 */
val <Unit : HourOrSmaller> TimePeriod<Unit>.previousHour get() = subtractingHours(1)

/**
 * Create a new [TimePeriod] by moving forward some number of hours.
 *
 * @param count The number of hours by which to move forward.
 */
fun <Unit : HourOrSmaller> TimePeriod<Unit>.addingHours(count: Int) = applying(TimeDifference.hours(count))
/**
 * Create a new [TimePeriod] by moving backward some number of hours.
 *
 * @param count The number of hours by which to move backward.
 */
fun <Unit : HourOrSmaller> TimePeriod<Unit>.subtractingHours(count: Int) = applying(TimeDifference.hours(-count))
// endregion

// region Day
/**
 * Create a new [TimePeriod] by moving forward one day.
 */
val <Unit : DayOrSmaller> TimePeriod<Unit>.nextDay get() = addingDays(1)
/**
 * Create a new [TimePeriod] by moving backward one day.
 */
val <Unit : DayOrSmaller> TimePeriod<Unit>.previousDay get() = subtractingDays(1)

/**
 * Create a new [TimePeriod] by moving forward some number of days.
 *
 * @param count The number of days by which to move forward.
 */
fun <Unit : DayOrSmaller> TimePeriod<Unit>.addingDays(count: Int) = applying(TimeDifference.days(count))
/**
 * Create a new [TimePeriod] by moving backward some number of days.
 *
 * @param count The number of days by which to move backward.
 */
fun <Unit : DayOrSmaller> TimePeriod<Unit>.subtractingDays(count: Int) = applying(TimeDifference.days(-count))
//endregion

// region Month
/**
 * Create a new [TimePeriod] by moving forward one month.
 */
val <Unit : MonthOrSmaller> TimePeriod<Unit>.nextMonth get() = addingMonths(1)
/**
 * Create a new [TimePeriod] by moving backward one month.
 */
val <Unit : MonthOrSmaller> TimePeriod<Unit>.previousMonth get() = subtractingMonths(1)

/**
 * Create a new [TimePeriod] by moving forward some number of months.
 *
 * @param count The number of months by which to move forward.
 */
fun <Unit : MonthOrSmaller> TimePeriod<Unit>.addingMonths(count: Int) = applying(TimeDifference.months(count))
/**
 * Create a new [TimePeriod] by moving backward some number of months.
 *
 * @param count The number of months by which to move backward.
 */
fun <Unit : MonthOrSmaller> TimePeriod<Unit>.subtractingMonths(count: Int) = applying(TimeDifference.months(-count))
// endregion

// region Year
/**
 * Create a new [TimePeriod] by moving forward one year.
 */
val <Unit : YearOrSmaller> TimePeriod<Unit>.nextYear get() = addingYears(1)
/**
 * Create a new [TimePeriod] by moving backward one year.
 */
val <Unit : YearOrSmaller> TimePeriod<Unit>.previousYear get() = subtractingYears(1)

/**
 * Create a new [TimePeriod] by moving forward some number of years.
 *
 * @param count The number of years by which to move forward.
 */
fun <Unit : YearOrSmaller> TimePeriod<Unit>.addingYears(count: Int) = applying(TimeDifference.years(count))
/**
 * Create a new [TimePeriod] by moving backward some number of years.
 *
 * @param count The number of years by which to move backward.
 */
fun <Unit : YearOrSmaller> TimePeriod<Unit>.subtractingYears(count: Int) = applying(TimeDifference.years(-count))
// endregion
