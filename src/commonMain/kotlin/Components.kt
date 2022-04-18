@file:Suppress("unused")

val <Unit : YearOrSmaller> TimePeriod<Unit>.year
    get() = dateTimeComponents.year

val <Unit : MonthOrSmaller> TimePeriod<Unit>.month
    get() = requireNotNull(dateTimeComponents.month) { "A month TimePeriod must have a month value" }

val <Unit : DayOrSmaller> TimePeriod<Unit>.dayOfMonth
    get() = requireNotNull(dateTimeComponents.dayOfMonth) { "A day TimePeriod must have a dayOfMonth value" }

val <Unit : HourOrSmaller> TimePeriod<Unit>.hour
    get() = requireNotNull(dateTimeComponents.hour) { "An hour TimePeriod must have a hour value" }

val <Unit : MinuteOrSmaller> TimePeriod<Unit>.minute
    get() = requireNotNull(dateTimeComponents.minute) { "A minute TimePeriod must have a minute value" }

val <Unit : SecondOrSmaller> TimePeriod<Unit>.second
    get() = requireNotNull(dateTimeComponents.second) { "A second TimePeriod must have a second value" }

val <Unit : NanosecondOrSmaller> TimePeriod<Unit>.nanosecond
    get() = requireNotNull(dateTimeComponents.nanosecond) { "A nanosecond TimePeriod must have a nanosecond value" }
