/**
 * A marker interface for units of time that are equivalent to or smaller than a nanosecond.
 * @see TimeUnit.Nanosecond
 */
interface NanosecondOrSmaller : SecondOrSmaller

/**
 * A marker interface for units of time that are equivalent to or smaller than a second.
 * @see TimeUnit.Second
 */
interface SecondOrSmaller : MinuteOrSmaller

/**
 * A marker interface for units of time that are equivalent to or smaller than a minute.
 * @see TimeUnit.Minute
 */
interface MinuteOrSmaller : HourOrSmaller

/**
 * A marker interface for units of time that are equivalent to or smaller than an hour.
 * @see TimeUnit.Hour
 */
interface HourOrSmaller : DayOrSmaller

/**
 * A marker interface for units of time that are equivalent to or smaller than a day.
 * @see TimeUnit.Day
 */
interface DayOrSmaller : MonthOrSmaller

/**
 * A marker interface for units of time that are equivalent to or smaller than a month.
 * @see TimeUnit.Month
 */
interface MonthOrSmaller : YearOrSmaller

/**
 * A marker interface for units of time that are equivalent to or smaller than a year.
 * @see TimeUnit.Year
 */
interface YearOrSmaller

sealed class TimeUnit private constructor() {
    object Nanosecond : TimeUnit(), NanosecondOrSmaller
    object Second : TimeUnit(), SecondOrSmaller
    object Minute : TimeUnit(), MinuteOrSmaller
    object Hour : TimeUnit(), HourOrSmaller
    object Day : TimeUnit(), DayOrSmaller
    object Month : TimeUnit(), MonthOrSmaller
    object Year : TimeUnit(), YearOrSmaller
}
