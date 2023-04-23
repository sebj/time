import kotlin.jvm.JvmName

private fun <OutUnit : TimeUnit, LimitValue : Comparable<LimitValue>> TimePeriod<*>.generateSequence(
    outUnit: OutUnit,
    limitValueTransform: (DateTimeComponents) -> Comparable<LimitValue>?
): Sequence<TimePeriod<OutUnit>> {
    val start = firstInstant
    val endValue = limitValueTransform(components)
    return generateSequence(TimePeriod(timeZone, start, outUnit)) { timePeriod ->
        timePeriod
            .applying(TimeDifference(count = 1, smallestUnit = outUnit))
            .takeUnless { limitValueTransform(it.components) != endValue }
    }
}

@get:JvmName("minuteSeconds")
public val TimePeriod<TimeUnit.Minute>.seconds: Sequence<TimePeriod<TimeUnit.Second>> get() =
    generateSequence(TimeUnit.Second) { it.minute }

@get:JvmName("hourSeconds")
public val TimePeriod<TimeUnit.Hour>.seconds: Sequence<TimePeriod<TimeUnit.Second>> get() =
    generateSequence(TimeUnit.Second) { it.hour }

@get:JvmName("hourMinutes")
public val TimePeriod<TimeUnit.Hour>.minutes: Sequence<TimePeriod<TimeUnit.Minute>> get() =
    generateSequence(TimeUnit.Minute) { it.hour }

@get:JvmName("dayMinutes")
public val TimePeriod<TimeUnit.Day>.minutes: Sequence<TimePeriod<TimeUnit.Minute>> get() =
    generateSequence(TimeUnit.Minute) { it.dayOfMonth }

@get:JvmName("dayHours")
public val TimePeriod<TimeUnit.Day>.hours: Sequence<TimePeriod<TimeUnit.Hour>> get() =
    generateSequence(TimeUnit.Hour) { it.dayOfMonth }

@get:JvmName("monthHours")
public val TimePeriod<TimeUnit.Month>.hours: Sequence<TimePeriod<TimeUnit.Hour>> get() =
    generateSequence(TimeUnit.Hour) { it.month }

@get:JvmName("monthDays")
public val TimePeriod<TimeUnit.Month>.days: Sequence<TimePeriod<TimeUnit.Day>> get() =
    generateSequence(TimeUnit.Day) { it.month }

public val TimePeriod<TimeUnit.Year>.months: Sequence<TimePeriod<TimeUnit.Month>> get() =
    generateSequence(TimeUnit.Month) { it.year }

@get:JvmName("yearDays")
public val TimePeriod<TimeUnit.Year>.days: Sequence<TimePeriod<TimeUnit.Day>> get() =
    generateSequence(TimeUnit.Day) { it.year }
