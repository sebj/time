/**
 * @return The second period containing this nanosecond.
 */
val <Unit : NanosecondOrSmaller> TimePeriod<Unit>.secondPeriod
    get() = TimePeriod.second(timeZone, components)

/**
 * @return The minute period containing this [TimePeriod].
 */
val <Unit : SecondOrSmaller> TimePeriod<Unit>.minutePeriod
    get() = TimePeriod.minute(timeZone, components)

/**
 * @return The hour period containing this [TimePeriod].
 */
val <Unit : MinuteOrSmaller> TimePeriod<Unit>.hourPeriod
    get() = TimePeriod.hour(timeZone, components)

/**
 * @return The day period containing this [TimePeriod].
 */
val <Unit : HourOrSmaller> TimePeriod<Unit>.dayPeriod
    get() = TimePeriod.day(timeZone, components)

/**
 * @return The month period containing this [TimePeriod].
 */
val <Unit : DayOrSmaller> TimePeriod<Unit>.monthPeriod
    get() = TimePeriod.month(timeZone, components)

/**
 * @return The year period containing this [TimePeriod].
 */
val <Unit : MonthOrSmaller> TimePeriod<Unit>.yearPeriod
    get() = TimePeriod.year(timeZone, components)
