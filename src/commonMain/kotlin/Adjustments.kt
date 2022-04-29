fun <Unit : TimeUnit> TimePeriod<Unit>.offset(count: Int): TimePeriod<Unit> {
    if (count == 0) {
        return this
    }

    return applying(TimeDifference(count, smallestUnit))
}

// region Nanosecond
/**
 * Create a new [TimePeriod] by moving forward one nanosecond.
 */
val <Unit> TimePeriod<Unit>.nextNanosecond where Unit : NanosecondOrSmaller, Unit : TimeUnit
    get() = addingNanoseconds(1)
/**
 * Create a new [TimePeriod] by moving backward one nanosecond.
 */
val <Unit> TimePeriod<Unit>.previousNanosecond where Unit : NanosecondOrSmaller, Unit : TimeUnit
    get() = subtractingNanoseconds(1)

/**
 * Create a new [TimePeriod] by moving forward some number of nanoseconds.
 *
 * @param count The number of nanoseconds by which to move forward.
 */
fun <Unit> TimePeriod<Unit>.addingNanoseconds(count: Int) where Unit : NanosecondOrSmaller, Unit : TimeUnit =
    applying(TimeDifference.nanoseconds(count))
/**
 * Create a new [TimePeriod] by moving backward some number of nanoseconds.
 *
 * @param count The number of nanoseconds by which to move backward.
 */
fun <Unit> TimePeriod<Unit>.subtractingNanoseconds(count: Int) where Unit : NanosecondOrSmaller, Unit : TimeUnit = applying(TimeDifference.nanoseconds(-count))
// endregion

// region Second
/**
 * Create a new [TimePeriod] by moving forward one second.
 */
val <Unit> TimePeriod<Unit>.nextSecond where Unit : SecondOrSmaller, Unit : TimeUnit
    get() = addingSeconds(1)
/**
 * Create a new [TimePeriod] by moving backward one second.
 */
val <Unit> TimePeriod<Unit>.previousSecond where Unit : SecondOrSmaller, Unit : TimeUnit
    get() = subtractingSeconds(1)

/**
 * Create a new [TimePeriod] by moving forward some number of seconds.
 *
 * @param count The number of seconds by which to move forward.
 */
fun <Unit> TimePeriod<Unit>.addingSeconds(count: Int) where Unit : SecondOrSmaller, Unit : TimeUnit =
    applying(TimeDifference.seconds(count))
/**
 * Create a new [TimePeriod] by moving backward some number of seconds.
 *
 * @param count The number of seconds by which to move backward.
 */
fun <Unit> TimePeriod<Unit>.subtractingSeconds(count: Int) where Unit : SecondOrSmaller, Unit : TimeUnit =
    applying(TimeDifference.seconds(-count))
// endregion

// region Minute
/**
 * Create a new [TimePeriod] by moving forward one minute.
 */
val <Unit> TimePeriod<Unit>.nextMinute where Unit : MinuteOrSmaller, Unit : TimeUnit
    get() = addingMinutes(1)
/**
 * Create a new [TimePeriod] by moving backward one minute.
 */
val <Unit> TimePeriod<Unit>.previousMinute where Unit : MinuteOrSmaller, Unit : TimeUnit
    get() = subtractingMinutes(1)

/**
 * Create a new [TimePeriod] by moving forward some number of minutes.
 *
 * @param count The number of minutes by which to move forward.
 */
fun <Unit> TimePeriod<Unit>.addingMinutes(count: Int) where Unit : MinuteOrSmaller, Unit : TimeUnit =
    applying(TimeDifference.minutes(count))
/**
 * Create a new [TimePeriod] by moving backward some number of minutes.
 *
 * @param count The number of minutes by which to move backward.
 */
fun <Unit> TimePeriod<Unit>.subtractingMinutes(count: Int) where Unit : MinuteOrSmaller, Unit : TimeUnit =
    applying(TimeDifference.minutes(-count))
//endregion

// region Hour
/**
 * Create a new [TimePeriod] by moving forward one hour.
 */
val <Unit> TimePeriod<Unit>.nextHour where Unit : HourOrSmaller, Unit : TimeUnit
    get() = addingHours(1)
/**
 * Create a new [TimePeriod] by moving backward one hour.
 */
val <Unit> TimePeriod<Unit>.previousHour where Unit : HourOrSmaller, Unit : TimeUnit
    get() = subtractingHours(1)

/**
 * Create a new [TimePeriod] by moving forward some number of hours.
 *
 * @param count The number of hours by which to move forward.
 */
fun <Unit> TimePeriod<Unit>.addingHours(count: Int) where Unit : HourOrSmaller, Unit : TimeUnit =
    applying(TimeDifference.hours(count))
/**
 * Create a new [TimePeriod] by moving backward some number of hours.
 *
 * @param count The number of hours by which to move backward.
 */
fun <Unit> TimePeriod<Unit>.subtractingHours(count: Int) where Unit : HourOrSmaller, Unit : TimeUnit =
    applying(TimeDifference.hours(-count))
// endregion

// region Day
/**
 * Create a new [TimePeriod] by moving forward one day.
 */
val <Unit> TimePeriod<Unit>.nextDay where Unit : DayOrSmaller, Unit : TimeUnit
    get() = addingDays(1)
/**
 * Create a new [TimePeriod] by moving backward one day.
 */
val <Unit> TimePeriod<Unit>.previousDay where Unit : DayOrSmaller, Unit : TimeUnit
    get() = subtractingDays(1)

/**
 * Create a new [TimePeriod] by moving forward some number of days.
 *
 * @param count The number of days by which to move forward.
 */
fun <Unit> TimePeriod<Unit>.addingDays(count: Int) where Unit : DayOrSmaller, Unit : TimeUnit =
    applying(TimeDifference.days(count))
/**
 * Create a new [TimePeriod] by moving backward some number of days.
 *
 * @param count The number of days by which to move backward.
 */
fun <Unit> TimePeriod<Unit>.subtractingDays(count: Int) where Unit : DayOrSmaller, Unit : TimeUnit =
    applying(TimeDifference.days(-count))
//endregion

// region Month
/**
 * Create a new [TimePeriod] by moving forward one month.
 */
val <Unit> TimePeriod<Unit>.nextMonth where Unit : MonthOrSmaller, Unit : TimeUnit
    get() = addingMonths(1)
/**
 * Create a new [TimePeriod] by moving backward one month.
 */
val <Unit> TimePeriod<Unit>.previousMonth where Unit : MonthOrSmaller, Unit : TimeUnit
    get() = subtractingMonths(1)

/**
 * Create a new [TimePeriod] by moving forward some number of months.
 *
 * @param count The number of months by which to move forward.
 */
fun <Unit> TimePeriod<Unit>.addingMonths(count: Int) where Unit : MonthOrSmaller, Unit : TimeUnit =
    applying(TimeDifference.months(count))
/**
 * Create a new [TimePeriod] by moving backward some number of months.
 *
 * @param count The number of months by which to move backward.
 */
fun <Unit> TimePeriod<Unit>.subtractingMonths(count: Int) where Unit : MonthOrSmaller, Unit : TimeUnit =
    applying(TimeDifference.months(-count))
// endregion

// region Year
/**
 * Create a new [TimePeriod] by moving forward one year.
 */
val <Unit> TimePeriod<Unit>.nextYear where Unit : YearOrSmaller, Unit : TimeUnit
    get() = addingYears(1)
/**
 * Create a new [TimePeriod] by moving backward one year.
 */
val <Unit> TimePeriod<Unit>.previousYear where Unit : YearOrSmaller, Unit : TimeUnit
    get() = subtractingYears(1)

/**
 * Create a new [TimePeriod] by moving forward some number of years.
 *
 * @param count The number of years by which to move forward.
 */
fun <Unit> TimePeriod<Unit>.addingYears(count: Int) where Unit : YearOrSmaller, Unit : TimeUnit =
    applying(TimeDifference.years(count))
/**
 * Create a new [TimePeriod] by moving backward some number of years.
 *
 * @param count The number of years by which to move backward.
 */
fun <Unit> TimePeriod<Unit>.subtractingYears(count: Int) where Unit : YearOrSmaller, Unit : TimeUnit =
    applying(TimeDifference.years(-count))
// endregion
