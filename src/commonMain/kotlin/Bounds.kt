import kotlin.jvm.JvmName

private fun <Unit : TimeUnit> TimePeriod<*>.first(unit: Unit) = TimePeriod(timeZone, firstInstant, unit)
private fun <Unit : TimeUnit> TimePeriod<*>.last(unit: Unit): TimePeriod<Unit> {
    return TimePeriod(timeZone, range.endInclusive, unit)
        .applying(TimeDifference(-1, unit))
}

val TimePeriod<Year>.firstMonth get() = first(Month)
val TimePeriod<Year>.lastMonth get() = last(Month)
@get:JvmName("yearFirstDay")
val TimePeriod<Year>.firstDay get() = first(Day)
@get:JvmName("yearLastDay")
val TimePeriod<Year>.lastDay get() = last(Day)

@get:JvmName("monthFirstDay")
val TimePeriod<Month>.firstDay get() = first(Day)
@get:JvmName("monthLastDay")
val TimePeriod<Month>.lastDay get() = last(Day)
@get:JvmName("monthFirstHour")
val TimePeriod<Month>.firstHour get() = first(Hour)
@get:JvmName("monthLastHour")
val TimePeriod<Month>.lastHour get() = last(Hour)

@get:JvmName("dayFirstHour")
val TimePeriod<Day>.firstHour get() = first(Hour)
@get:JvmName("dayLastHour")
val TimePeriod<Day>.lastHour get() = last(Hour)
@get:JvmName("dayFirstMinute")
val TimePeriod<Day>.firstMinute get() = first(Minute)
@get:JvmName("dayLastMinute")
val TimePeriod<Day>.lastMinute get() = last(Minute)
@get:JvmName("dayFirstSecond")
val TimePeriod<Day>.firstSecond get() = first(Second)
@get:JvmName("dayLastSecond")
val TimePeriod<Day>.lastSecond get() = last(Second)

@get:JvmName("hourFirstMinute")
val TimePeriod<Hour>.firstMinute get() = first(Minute)
@get:JvmName("hourLastMinute")
val TimePeriod<Hour>.lastMinute get() = last(Minute)
@get:JvmName("hourFirstSecond")
val TimePeriod<Hour>.firstSecond get() = first(Second)
@get:JvmName("hourLastSecond")
val TimePeriod<Hour>.lastSecond get() = last(Second)

@get:JvmName("minuteFirstSecond")
val TimePeriod<Minute>.firstSecond get() = first(Second)
@get:JvmName("minuteLastSecond")
val TimePeriod<Minute>.lastSecond get() = last(Second)
