import kotlin.jvm.JvmName

private fun <Unit : TimeUnit> TimePeriod<*>.first(unit: Unit) = TimePeriod(timeZone, firstInstant, unit)
private fun <Unit : TimeUnit> TimePeriod<*>.last(unit: Unit): TimePeriod<Unit> {
    return TimePeriod(timeZone, range.endInclusive, unit)
        .applying(TimeDifference(-1, unit))
}

val TimePeriod<TimeUnit.Year>.firstMonth get() = first(TimeUnit.Month)
val TimePeriod<TimeUnit.Year>.lastMonth get() = last(TimeUnit.Month)

@get:JvmName("yearFirstDay")
val TimePeriod<TimeUnit.Year>.firstDay get() = first(TimeUnit.Day)

@get:JvmName("yearLastDay")
val TimePeriod<TimeUnit.Year>.lastDay get() = last(TimeUnit.Day)

@get:JvmName("monthFirstDay")
val TimePeriod<TimeUnit.Month>.firstDay get() = first(TimeUnit.Day)

@get:JvmName("monthLastDay")
val TimePeriod<TimeUnit.Month>.lastDay get() = last(TimeUnit.Day)

@get:JvmName("monthFirstHour")
val TimePeriod<TimeUnit.Month>.firstHour get() = first(TimeUnit.Hour)

@get:JvmName("monthLastHour")
val TimePeriod<TimeUnit.Month>.lastHour get() = last(TimeUnit.Hour)

@get:JvmName("dayFirstHour")
val TimePeriod<TimeUnit.Day>.firstHour get() = first(TimeUnit.Hour)

@get:JvmName("dayLastHour")
val TimePeriod<TimeUnit.Day>.lastHour get() = last(TimeUnit.Hour)

@get:JvmName("dayFirstMinute")
val TimePeriod<TimeUnit.Day>.firstMinute get() = first(TimeUnit.Minute)

@get:JvmName("dayLastMinute")
val TimePeriod<TimeUnit.Day>.lastMinute get() = last(TimeUnit.Minute)

@get:JvmName("dayFirstSecond")
val TimePeriod<TimeUnit.Day>.firstSecond get() = first(TimeUnit.Second)

@get:JvmName("dayLastSecond")
val TimePeriod<TimeUnit.Day>.lastSecond get() = last(TimeUnit.Second)

@get:JvmName("hourFirstMinute")
val TimePeriod<TimeUnit.Hour>.firstMinute get() = first(TimeUnit.Minute)

@get:JvmName("hourLastMinute")
val TimePeriod<TimeUnit.Hour>.lastMinute get() = last(TimeUnit.Minute)

@get:JvmName("hourFirstSecond")
val TimePeriod<TimeUnit.Hour>.firstSecond get() = first(TimeUnit.Second)

@get:JvmName("hourLastSecond")
val TimePeriod<TimeUnit.Hour>.lastSecond get() = last(TimeUnit.Second)

@get:JvmName("minuteFirstSecond")
val TimePeriod<TimeUnit.Minute>.firstSecond get() = first(TimeUnit.Second)

@get:JvmName("minuteLastSecond")
val TimePeriod<TimeUnit.Minute>.lastSecond get() = last(TimeUnit.Second)
