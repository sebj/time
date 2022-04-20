val <Unit : NanosecondOrSmaller> TimePeriod<Unit>.secondPeriod
    get() = TimePeriod.second(components)

val <Unit : SecondOrSmaller> TimePeriod<Unit>.minutePeriod
    get() = TimePeriod.minute(components)

val <Unit : MinuteOrSmaller> TimePeriod<Unit>.hourPeriod
    get() = TimePeriod.hour(components)

val <Unit : HourOrSmaller> TimePeriod<Unit>.dayPeriod
    get() = TimePeriod.day(components)

val <Unit : DayOrSmaller> TimePeriod<Unit>.monthPeriod
    get() = TimePeriod.month(components)

val <Unit : MonthOrSmaller> TimePeriod<Unit>.yearPeriod
    get() = TimePeriod.year(components)
