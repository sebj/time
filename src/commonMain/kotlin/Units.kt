interface TimeUnit

interface NanosecondOrSmaller : SecondOrSmaller
interface SecondOrSmaller : MinuteOrSmaller
interface MinuteOrSmaller : HourOrSmaller
interface HourOrSmaller : DayOrSmaller
interface DayOrSmaller : MonthOrSmaller
interface MonthOrSmaller : YearOrSmaller
interface YearOrSmaller : TimeUnit

object Nanosecond : NanosecondOrSmaller
object Second : SecondOrSmaller
object Minute : MinuteOrSmaller
object Hour : HourOrSmaller
object Day : DayOrSmaller
object Month : MonthOrSmaller
object Year : YearOrSmaller
