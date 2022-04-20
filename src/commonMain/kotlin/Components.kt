@file:Suppress("unused")

val <Unit : YearOrSmaller> TimePeriod<Unit>.year
    get() = components.year

val <Unit : MonthOrSmaller> TimePeriod<Unit>.month
    get() = requireNotNull(components.month) { "A month TimePeriod must have a month value" }

val <Unit : DayOrSmaller> TimePeriod<Unit>.dayOfMonth
    get() = requireNotNull(components.dayOfMonth) { "A day TimePeriod must have a dayOfMonth value" }

val <Unit : HourOrSmaller> TimePeriod<Unit>.hour
    get() = requireNotNull(components.hour) { "An hour TimePeriod must have a hour value" }

val <Unit : MinuteOrSmaller> TimePeriod<Unit>.minute
    get() = requireNotNull(components.minute) { "A minute TimePeriod must have a minute value" }

val <Unit : SecondOrSmaller> TimePeriod<Unit>.second
    get() = requireNotNull(components.second) { "A second TimePeriod must have a second value" }

val <Unit : NanosecondOrSmaller> TimePeriod<Unit>.nanosecond
    get() = requireNotNull(components.nanosecond) { "A nanosecond TimePeriod must have a nanosecond value" }
