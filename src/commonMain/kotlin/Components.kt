@file:Suppress("unused")

import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.Month

public val <Unit> TimePeriod<Unit>.year: Int where Unit : YearOrSmaller, Unit : TimeUnit
    get() = components.year

public val <Unit> TimePeriod<Unit>.month: Month where Unit : MonthOrSmaller, Unit : TimeUnit
    get() = requireNotNull(components.month) { "A month TimePeriod must have a month value" }

public val <Unit> TimePeriod<Unit>.dayOfYear: Int where Unit : DayOrSmaller, Unit : TimeUnit
    get() = toLocalDate().dayOfYear

public val <Unit> TimePeriod<Unit>.dayOfMonth: Int where Unit : DayOrSmaller, Unit : TimeUnit
    get() = requireNotNull(components.dayOfMonth) { "A day TimePeriod must have a dayOfMonth value" }

public val <Unit> TimePeriod<Unit>.dayOfWeek: DayOfWeek where Unit : DayOrSmaller, Unit : TimeUnit
    get() = toLocalDate().dayOfWeek

public val <Unit> TimePeriod<Unit>.hour: Int where Unit : HourOrSmaller, Unit : TimeUnit
    get() = requireNotNull(components.hour) { "An hour TimePeriod must have a hour value" }

public val <Unit> TimePeriod<Unit>.minute: Int where Unit : MinuteOrSmaller, Unit : TimeUnit
    get() = requireNotNull(components.minute) { "A minute TimePeriod must have a minute value" }

public val <Unit> TimePeriod<Unit>.second: Int where Unit : SecondOrSmaller, Unit : TimeUnit
    get() = requireNotNull(components.second) { "A second TimePeriod must have a second value" }

public val <Unit> TimePeriod<Unit>.nanosecond: Int where Unit : NanosecondOrSmaller, Unit : TimeUnit
    get() = requireNotNull(components.nanosecond) { "A nanosecond TimePeriod must have a nanosecond value" }
