/**
 * @return The second period containing this nanosecond.
 */
val <Unit : NanosecondOrSmaller> TimePeriod<Unit>.secondPeriod
    get() = TimePeriod.second(components)

/**
 * @return The minute period containing this [TimePeriod].
 */
val <Unit : SecondOrSmaller> TimePeriod<Unit>.minutePeriod
    get() = TimePeriod.minute(components)

/**
 * @return The hour period containing this [TimePeriod].
 */
val <Unit : MinuteOrSmaller> TimePeriod<Unit>.hourPeriod
    get() = TimePeriod.hour(components)

/**
 * @return The day period containing this [TimePeriod].
 */
val <Unit : HourOrSmaller> TimePeriod<Unit>.dayPeriod
    get() = TimePeriod.day(components)

/**
 * @return The month period containing this [TimePeriod].
 */
val <Unit : DayOrSmaller> TimePeriod<Unit>.monthPeriod
    get() = TimePeriod.month(components)

/**
 * @return The year period containing this [TimePeriod].
 */
val <Unit : MonthOrSmaller> TimePeriod<Unit>.yearPeriod
    get() = TimePeriod.year(components)
