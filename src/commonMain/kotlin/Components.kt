@file:Suppress("unused")

val <Unit> TimePeriod<Unit>.year where Unit : YearOrSmaller, Unit : TimeUnit
    get() = components.year

val <Unit> TimePeriod<Unit>.month where Unit : MonthOrSmaller, Unit : TimeUnit
    get() = requireNotNull(components.month) { "A month TimePeriod must have a month value" }

val <Unit> TimePeriod<Unit>.dayOfYear where Unit : DayOrSmaller, Unit : TimeUnit
    get() = toLocalDate().dayOfYear

val <Unit> TimePeriod<Unit>.dayOfMonth where Unit : DayOrSmaller, Unit : TimeUnit
    get() = requireNotNull(components.dayOfMonth) { "A day TimePeriod must have a dayOfMonth value" }

val <Unit> TimePeriod<Unit>.dayOfWeek where Unit : DayOrSmaller, Unit : TimeUnit
    get() = toLocalDate().dayOfWeek

val <Unit> TimePeriod<Unit>.hour where Unit : HourOrSmaller, Unit : TimeUnit
    get() = requireNotNull(components.hour) { "An hour TimePeriod must have a hour value" }

val <Unit> TimePeriod<Unit>.minute where Unit : MinuteOrSmaller, Unit : TimeUnit
    get() = requireNotNull(components.minute) { "A minute TimePeriod must have a minute value" }

val <Unit> TimePeriod<Unit>.second where Unit : SecondOrSmaller, Unit : TimeUnit
    get() = requireNotNull(components.second) { "A second TimePeriod must have a second value" }

val <Unit> TimePeriod<Unit>.nanosecond where Unit : NanosecondOrSmaller, Unit : TimeUnit
    get() = requireNotNull(components.nanosecond) { "A nanosecond TimePeriod must have a nanosecond value" }
