/**
 * @return The second period containing this nanosecond.
 */
val <Unit> TimePeriod<Unit>.secondPeriod where Unit : NanosecondOrSmaller, Unit : TimeUnit
    get() = TimePeriod.second(timeZone, components)

/**
 * @return The minute period containing this [TimePeriod].
 */
val <Unit> TimePeriod<Unit>.minutePeriod where Unit : SecondOrSmaller, Unit : TimeUnit
    get() = TimePeriod.minute(timeZone, components)

/**
 * @return The hour period containing this [TimePeriod].
 */
val <Unit> TimePeriod<Unit>.hourPeriod where Unit : MinuteOrSmaller, Unit : TimeUnit
    get() = TimePeriod.hour(timeZone, components)

/**
 * @return The day period containing this [TimePeriod].
 */
val <Unit> TimePeriod<Unit>.dayPeriod where Unit : HourOrSmaller, Unit : TimeUnit
    get() = TimePeriod.day(timeZone, components)

/**
 * @return The month period containing this [TimePeriod].
 */
val <Unit> TimePeriod<Unit>.monthPeriod where Unit : DayOrSmaller, Unit : TimeUnit
    get() = TimePeriod.month(timeZone, components)

/**
 * @return The year period containing this [TimePeriod].
 */
val <Unit> TimePeriod<Unit>.yearPeriod where Unit : MonthOrSmaller, Unit : TimeUnit
    get() = TimePeriod.year(timeZone, components)
