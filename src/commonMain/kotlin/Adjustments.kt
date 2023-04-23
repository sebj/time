public fun <Unit : TimeUnit> TimePeriod<Unit>.offset(count: Int): TimePeriod<Unit> {
    if (count == 0) {
        return this
    }

    return applying(TimeDifference(count, smallestUnit))
}

// region Nanosecond
/**
 * Create a new [TimePeriod] by moving forward one nanosecond.
 */
public val <Unit> TimePeriod<Unit>.nextNanosecond: TimePeriod<Unit> where Unit : NanosecondOrSmaller, Unit : TimeUnit
    get() = addingNanoseconds(1)

/**
 * Create a new [TimePeriod] by moving backward one nanosecond.
 */
public val <Unit> TimePeriod<Unit>.previousNanosecond: TimePeriod<Unit> where Unit : NanosecondOrSmaller, Unit : TimeUnit
    get() = subtractingNanoseconds(1)

/**
 * Create a new [TimePeriod] by moving forward some number of nanoseconds.
 *
 * @param count The number of nanoseconds by which to move forward.
 */
public fun <Unit> TimePeriod<Unit>.addingNanoseconds(count: Int): TimePeriod<Unit> where Unit : NanosecondOrSmaller, Unit : TimeUnit =
    applying(TimeDifference.nanoseconds(count))

/**
 * Create a new [TimePeriod] by moving backward some number of nanoseconds.
 *
 * @param count The number of nanoseconds by which to move backward.
 */
public fun <Unit> TimePeriod<Unit>.subtractingNanoseconds(count: Int): TimePeriod<Unit> where Unit : NanosecondOrSmaller, Unit : TimeUnit =
    applying(TimeDifference.nanoseconds(-count))
// endregion

// region Second
/**
 * Create a new [TimePeriod] by moving forward one second.
 */
public val <Unit> TimePeriod<Unit>.nextSecond: TimePeriod<Unit> where Unit : SecondOrSmaller, Unit : TimeUnit
    get() = addingSeconds(1)

/**
 * Create a new [TimePeriod] by moving backward one second.
 */
public val <Unit> TimePeriod<Unit>.previousSecond: TimePeriod<Unit> where Unit : SecondOrSmaller, Unit : TimeUnit
    get() = subtractingSeconds(1)

/**
 * Create a new [TimePeriod] by moving forward some number of seconds.
 *
 * @param count The number of seconds by which to move forward.
 */
public fun <Unit> TimePeriod<Unit>.addingSeconds(count: Int): TimePeriod<Unit> where Unit : SecondOrSmaller, Unit : TimeUnit =
    applying(TimeDifference.seconds(count))

/**
 * Create a new [TimePeriod] by moving backward some number of seconds.
 *
 * @param count The number of seconds by which to move backward.
 */
public fun <Unit> TimePeriod<Unit>.subtractingSeconds(count: Int): TimePeriod<Unit> where Unit : SecondOrSmaller, Unit : TimeUnit =
    applying(TimeDifference.seconds(-count))
// endregion

// region Minute
/**
 * Create a new [TimePeriod] by moving forward one minute.
 */
public val <Unit> TimePeriod<Unit>.nextMinute: TimePeriod<Unit> where Unit : MinuteOrSmaller, Unit : TimeUnit
    get() = addingMinutes(1)

/**
 * Create a new [TimePeriod] by moving backward one minute.
 */
public val <Unit> TimePeriod<Unit>.previousMinute: TimePeriod<Unit> where Unit : MinuteOrSmaller, Unit : TimeUnit
    get() = subtractingMinutes(1)

/**
 * Create a new [TimePeriod] by moving forward some number of minutes.
 *
 * @param count The number of minutes by which to move forward.
 */
public fun <Unit> TimePeriod<Unit>.addingMinutes(count: Int): TimePeriod<Unit> where Unit : MinuteOrSmaller, Unit : TimeUnit =
    applying(TimeDifference.minutes(count))

/**
 * Create a new [TimePeriod] by moving backward some number of minutes.
 *
 * @param count The number of minutes by which to move backward.
 */
public fun <Unit> TimePeriod<Unit>.subtractingMinutes(count: Int): TimePeriod<Unit> where Unit : MinuteOrSmaller, Unit : TimeUnit =
    applying(TimeDifference.minutes(-count))
//endregion

// region Hour
/**
 * Create a new [TimePeriod] by moving forward one hour.
 */
public val <Unit> TimePeriod<Unit>.nextHour: TimePeriod<Unit> where Unit : HourOrSmaller, Unit : TimeUnit
    get() = addingHours(1)

/**
 * Create a new [TimePeriod] by moving backward one hour.
 */
public val <Unit> TimePeriod<Unit>.previousHour: TimePeriod<Unit> where Unit : HourOrSmaller, Unit : TimeUnit
    get() = subtractingHours(1)

/**
 * Create a new [TimePeriod] by moving forward some number of hours.
 *
 * @param count The number of hours by which to move forward.
 */
public fun <Unit> TimePeriod<Unit>.addingHours(count: Int): TimePeriod<Unit> where Unit : HourOrSmaller, Unit : TimeUnit =
    applying(TimeDifference.hours(count))

/**
 * Create a new [TimePeriod] by moving backward some number of hours.
 *
 * @param count The number of hours by which to move backward.
 */
public fun <Unit> TimePeriod<Unit>.subtractingHours(count: Int): TimePeriod<Unit> where Unit : HourOrSmaller, Unit : TimeUnit =
    applying(TimeDifference.hours(-count))
// endregion

// region Day
/**
 * Create a new [TimePeriod] by moving forward one day.
 */
public val <Unit> TimePeriod<Unit>.nextDay: TimePeriod<Unit> where Unit : DayOrSmaller, Unit : TimeUnit
    get() = addingDays(1)

/**
 * Create a new [TimePeriod] by moving backward one day.
 */
public val <Unit> TimePeriod<Unit>.previousDay: TimePeriod<Unit> where Unit : DayOrSmaller, Unit : TimeUnit
    get() = subtractingDays(1)

/**
 * Create a new [TimePeriod] by moving forward some number of days.
 *
 * @param count The number of days by which to move forward.
 */
public fun <Unit> TimePeriod<Unit>.addingDays(count: Int): TimePeriod<Unit> where Unit : DayOrSmaller, Unit : TimeUnit =
    applying(TimeDifference.days(count))

/**
 * Create a new [TimePeriod] by moving backward some number of days.
 *
 * @param count The number of days by which to move backward.
 */
public fun <Unit> TimePeriod<Unit>.subtractingDays(count: Int): TimePeriod<Unit> where Unit : DayOrSmaller, Unit : TimeUnit =
    applying(TimeDifference.days(-count))
//endregion

// region Month
/**
 * Create a new [TimePeriod] by moving forward one month.
 */
public val <Unit> TimePeriod<Unit>.nextMonth: TimePeriod<Unit> where Unit : MonthOrSmaller, Unit : TimeUnit
    get() = addingMonths(1)

/**
 * Create a new [TimePeriod] by moving backward one month.
 */
public val <Unit> TimePeriod<Unit>.previousMonth: TimePeriod<Unit> where Unit : MonthOrSmaller, Unit : TimeUnit
    get() = subtractingMonths(1)

/**
 * Create a new [TimePeriod] by moving forward some number of months.
 *
 * @param count The number of months by which to move forward.
 */
public fun <Unit> TimePeriod<Unit>.addingMonths(count: Int): TimePeriod<Unit> where Unit : MonthOrSmaller, Unit : TimeUnit =
    applying(TimeDifference.months(count))

/**
 * Create a new [TimePeriod] by moving backward some number of months.
 *
 * @param count The number of months by which to move backward.
 */
public fun <Unit> TimePeriod<Unit>.subtractingMonths(count: Int): TimePeriod<Unit> where Unit : MonthOrSmaller, Unit : TimeUnit =
    applying(TimeDifference.months(-count))
// endregion

// region Year
/**
 * Create a new [TimePeriod] by moving forward one year.
 */
public val <Unit> TimePeriod<Unit>.nextYear: TimePeriod<Unit> where Unit : YearOrSmaller, Unit : TimeUnit
    get() = addingYears(1)

/**
 * Create a new [TimePeriod] by moving backward one year.
 */
public val <Unit> TimePeriod<Unit>.previousYear: TimePeriod<Unit> where Unit : YearOrSmaller, Unit : TimeUnit
    get() = subtractingYears(1)

/**
 * Create a new [TimePeriod] by moving forward some number of years.
 *
 * @param count The number of years by which to move forward.
 */
public fun <Unit> TimePeriod<Unit>.addingYears(count: Int): TimePeriod<Unit> where Unit : YearOrSmaller, Unit : TimeUnit =
    applying(TimeDifference.years(count))

/**
 * Create a new [TimePeriod] by moving backward some number of years.
 *
 * @param count The number of years by which to move backward.
 */
public fun <Unit> TimePeriod<Unit>.subtractingYears(count: Int): TimePeriod<Unit> where Unit : YearOrSmaller, Unit : TimeUnit =
    applying(TimeDifference.years(-count))
// endregion
