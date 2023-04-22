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

/**
 * A marker interface for units of time that are equivalent to or bigger than a nanosecond.
 * @see TimeUnit.Nanosecond
 */
interface NanosecondOrBigger

/**
 * A marker interface for units of time that are equivalent to or bigger than a second.
 * @see TimeUnit.Second
 */
interface SecondOrBigger : NanosecondOrBigger

/**
 * A marker interface for units of time that are equivalent to or bigger than a minute.
 * @see TimeUnit.Minute
 */
interface MinuteOrBigger : SecondOrBigger

/**
 * A marker interface for units of time that are equivalent to or bigger than an hour.
 * @see TimeUnit.Hour
 */
interface HourOrBigger : MinuteOrBigger

/**
 * A marker interface for units of time that are equivalent to or bigger than a day.
 * @see TimeUnit.Day
 */
interface DayOrBigger : HourOrBigger

/**
 * A marker interface for units of time that are equivalent to or bigger than a month.
 * @see TimeUnit.Month
 */
interface MonthOrBigger : DayOrBigger

sealed class TimeUnit private constructor() {
    object Nanosecond : TimeUnit(), NanosecondOrSmaller, NanosecondOrBigger
    object Second : TimeUnit(), SecondOrSmaller, SecondOrBigger
    object Minute : TimeUnit(), MinuteOrSmaller, MinuteOrBigger
    object Hour : TimeUnit(), HourOrSmaller, HourOrBigger
    object Day : TimeUnit(), DayOrSmaller, DayOrBigger
    object Month : TimeUnit(), MonthOrSmaller, MonthOrBigger
    object Year : TimeUnit(), YearOrSmaller, MonthOrBigger
}
