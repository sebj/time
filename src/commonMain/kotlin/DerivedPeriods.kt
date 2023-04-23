/**
 * @return The second period containing this nanosecond.
 */
public val <Unit> TimePeriod<Unit>.secondPeriod: TimePeriod<TimeUnit.Second> where Unit : NanosecondOrSmaller, Unit : TimeUnit
    get() = TimePeriod.second(timeZone, components)

/**
 * @return The minute period containing this [TimePeriod].
 */
public val <Unit> TimePeriod<Unit>.minutePeriod: TimePeriod<TimeUnit.Minute> where Unit : SecondOrSmaller, Unit : TimeUnit
    get() = TimePeriod.minute(timeZone, components)

/**
 * @return The hour period containing this [TimePeriod].
 */
public val <Unit> TimePeriod<Unit>.hourPeriod: TimePeriod<TimeUnit.Hour> where Unit : MinuteOrSmaller, Unit : TimeUnit
    get() = TimePeriod.hour(timeZone, components)

/**
 * @return The day period containing this [TimePeriod].
 */
public val <Unit> TimePeriod<Unit>.dayPeriod: TimePeriod<TimeUnit.Day> where Unit : HourOrSmaller, Unit : TimeUnit
    get() = TimePeriod.day(timeZone, components)

/**
 * @return The month period containing this [TimePeriod].
 */
public val <Unit> TimePeriod<Unit>.monthPeriod: TimePeriod<TimeUnit.Month> where Unit : DayOrSmaller, Unit : TimeUnit
    get() = TimePeriod.month(timeZone, components)

/**
 * @return The year period containing this [TimePeriod].
 */
public val <Unit> TimePeriod<Unit>.yearPeriod: TimePeriod<TimeUnit.Year> where Unit : MonthOrSmaller, Unit : TimeUnit
    get() = TimePeriod.year(timeZone, components)
