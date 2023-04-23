/**
 * A marker interface for units of time that are equivalent to or smaller than a nanosecond.
 * @see TimeUnit.Nanosecond
 */
internal interface NanosecondOrSmaller : SecondOrSmaller

/**
 * A marker interface for units of time that are equivalent to or smaller than a second.
 * @see TimeUnit.Second
 */
internal interface SecondOrSmaller : MinuteOrSmaller

/**
 * A marker interface for units of time that are equivalent to or smaller than a minute.
 * @see TimeUnit.Minute
 */
internal interface MinuteOrSmaller : HourOrSmaller

/**
 * A marker interface for units of time that are equivalent to or smaller than an hour.
 * @see TimeUnit.Hour
 */
internal interface HourOrSmaller : DayOrSmaller

/**
 * A marker interface for units of time that are equivalent to or smaller than a day.
 * @see TimeUnit.Day
 */
internal interface DayOrSmaller : MonthOrSmaller

/**
 * A marker interface for units of time that are equivalent to or smaller than a month.
 * @see TimeUnit.Month
 */
internal interface MonthOrSmaller : YearOrSmaller

/**
 * A marker interface for units of time that are equivalent to or smaller than a year.
 * @see TimeUnit.Year
 */
internal interface YearOrSmaller

/**
 * A marker interface for units of time that are equivalent to or bigger than a nanosecond.
 * @see TimeUnit.Nanosecond
 */
internal interface NanosecondOrBigger

/**
 * A marker interface for units of time that are equivalent to or bigger than a second.
 * @see TimeUnit.Second
 */
internal interface SecondOrBigger : NanosecondOrBigger

/**
 * A marker interface for units of time that are equivalent to or bigger than a minute.
 * @see TimeUnit.Minute
 */
internal interface MinuteOrBigger : SecondOrBigger

/**
 * A marker interface for units of time that are equivalent to or bigger than an hour.
 * @see TimeUnit.Hour
 */
internal interface HourOrBigger : MinuteOrBigger

/**
 * A marker interface for units of time that are equivalent to or bigger than a day.
 * @see TimeUnit.Day
 */
internal interface DayOrBigger : HourOrBigger

/**
 * A marker interface for units of time that are equivalent to or bigger than a month.
 * @see TimeUnit.Month
 */
internal interface MonthOrBigger : DayOrBigger

public sealed class TimeUnit private constructor() {
    public object Nanosecond : TimeUnit(), NanosecondOrSmaller, NanosecondOrBigger
    public object Second : TimeUnit(), SecondOrSmaller, SecondOrBigger
    public object Minute : TimeUnit(), MinuteOrSmaller, MinuteOrBigger
    public object Hour : TimeUnit(), HourOrSmaller, HourOrBigger
    public object Day : TimeUnit(), DayOrSmaller, DayOrBigger
    public object Month : TimeUnit(), MonthOrSmaller, MonthOrBigger
    public object Year : TimeUnit(), YearOrSmaller, MonthOrBigger
}
