/**
 * A marker interface for a unit of time.
 * @see Nanosecond
 * @see Second
 * @see Minute
 * @see Hour
 * @see Day
 * @see Month
 * @see Year
 */
interface TimeUnit

/**
 * A marker interface for units of time that are equivalent to or smaller than a nanosecond.
 * @see Nanosecond
 */
interface NanosecondOrSmaller : SecondOrSmaller

/**
 * A marker interface for units of time that are equivalent to or smaller than a second.
 * @see Second
 */
interface SecondOrSmaller : MinuteOrSmaller

/**
 * A marker interface for units of time that are equivalent to or smaller than a minute.
 * @see Minute
 */
interface MinuteOrSmaller : HourOrSmaller

/**
 * A marker interface for units of time that are equivalent to or smaller than a hour.
 * @see Hour
 */
interface HourOrSmaller : DayOrSmaller

/**
 * A marker interface for units of time that are equivalent to or smaller than a day.
 * @see Day
 */
interface DayOrSmaller : MonthOrSmaller

/**
 * A marker interface for units of time that are equivalent to or smaller than a month.
 * @see Month
 */
interface MonthOrSmaller : YearOrSmaller

/**
 * A marker interface for units of time that are equivalent to or smaller than a year.
 * @see Year
 */
interface YearOrSmaller : TimeUnit

/**
 * The nanosecond time unit.
 */
object Nanosecond : NanosecondOrSmaller

/**
 * The second time unit.
 */
object Second : SecondOrSmaller

/**
 * The minute time unit.
 */
object Minute : MinuteOrSmaller

/**
 * The hour time unit.
 */
object Hour : HourOrSmaller

/**
 * The day time unit.
 */
object Day : DayOrSmaller

/**
 * The month time unit.
 */
object Month : MonthOrSmaller

/**
 * The year time unit.
 */
object Year : YearOrSmaller
