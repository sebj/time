import kotlin.jvm.JvmName

private fun <Unit : TimeUnit> TimePeriod<*>.first(unit: Unit) = TimePeriod(timeZone, firstInstant, unit)
private fun <Unit : TimeUnit> TimePeriod<*>.last(unit: Unit): TimePeriod<Unit> {
    return TimePeriod(timeZone, range.endInclusive, unit)
        .applying(TimeDifference(-1, unit))
}

public val TimePeriod<TimeUnit.Year>.firstMonth: TimePeriod<TimeUnit.Month> get() = first(TimeUnit.Month)
public val TimePeriod<TimeUnit.Year>.lastMonth: TimePeriod<TimeUnit.Month> get() = last(TimeUnit.Month)

@get:JvmName("yearFirstDay")
public val TimePeriod<TimeUnit.Year>.firstDay: TimePeriod<TimeUnit.Day> get() = first(TimeUnit.Day)

@get:JvmName("yearLastDay")
public val TimePeriod<TimeUnit.Year>.lastDay: TimePeriod<TimeUnit.Day> get() = last(TimeUnit.Day)

@get:JvmName("monthFirstDay")
public val TimePeriod<TimeUnit.Month>.firstDay: TimePeriod<TimeUnit.Day> get() = first(TimeUnit.Day)

@get:JvmName("monthLastDay")
public val TimePeriod<TimeUnit.Month>.lastDay: TimePeriod<TimeUnit.Day> get() = last(TimeUnit.Day)

@get:JvmName("monthFirstHour")
public val TimePeriod<TimeUnit.Month>.firstHour: TimePeriod<TimeUnit.Hour> get() = first(TimeUnit.Hour)

@get:JvmName("monthLastHour")
public val TimePeriod<TimeUnit.Month>.lastHour: TimePeriod<TimeUnit.Hour> get() = last(TimeUnit.Hour)

@get:JvmName("dayFirstHour")
public val TimePeriod<TimeUnit.Day>.firstHour: TimePeriod<TimeUnit.Hour> get() = first(TimeUnit.Hour)

@get:JvmName("dayLastHour")
public val TimePeriod<TimeUnit.Day>.lastHour: TimePeriod<TimeUnit.Hour> get() = last(TimeUnit.Hour)

@get:JvmName("dayFirstMinute")
public val TimePeriod<TimeUnit.Day>.firstMinute: TimePeriod<TimeUnit.Minute> get() = first(TimeUnit.Minute)

@get:JvmName("dayLastMinute")
public val TimePeriod<TimeUnit.Day>.lastMinute: TimePeriod<TimeUnit.Minute> get() = last(TimeUnit.Minute)

@get:JvmName("dayFirstSecond")
public val TimePeriod<TimeUnit.Day>.firstSecond: TimePeriod<TimeUnit.Second> get() = first(TimeUnit.Second)

@get:JvmName("dayLastSecond")
public val TimePeriod<TimeUnit.Day>.lastSecond: TimePeriod<TimeUnit.Second> get() = last(TimeUnit.Second)

@get:JvmName("hourFirstMinute")
public val TimePeriod<TimeUnit.Hour>.firstMinute: TimePeriod<TimeUnit.Minute> get() = first(TimeUnit.Minute)

@get:JvmName("hourLastMinute")
public val TimePeriod<TimeUnit.Hour>.lastMinute: TimePeriod<TimeUnit.Minute> get() = last(TimeUnit.Minute)

@get:JvmName("hourFirstSecond")
public val TimePeriod<TimeUnit.Hour>.firstSecond: TimePeriod<TimeUnit.Second> get() = first(TimeUnit.Second)

@get:JvmName("hourLastSecond")
public val TimePeriod<TimeUnit.Hour>.lastSecond: TimePeriod<TimeUnit.Second> get() = last(TimeUnit.Second)

@get:JvmName("minuteFirstSecond")
public val TimePeriod<TimeUnit.Minute>.firstSecond: TimePeriod<TimeUnit.Second> get() = first(TimeUnit.Second)

@get:JvmName("minuteLastSecond")
public val TimePeriod<TimeUnit.Minute>.lastSecond: TimePeriod<TimeUnit.Second> get() = last(TimeUnit.Second)
