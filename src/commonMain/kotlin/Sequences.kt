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
val TimePeriod<TimeUnit.Minute>.seconds get() = generateSequence(TimeUnit.Second) { it.minute }

@get:JvmName("hourSeconds")
val TimePeriod<TimeUnit.Hour>.seconds get() = generateSequence(TimeUnit.Second) { it.hour }

@get:JvmName("hourMinutes")
val TimePeriod<TimeUnit.Hour>.minutes get() = generateSequence(TimeUnit.Minute) { it.hour }

@get:JvmName("dayMinutes")
val TimePeriod<TimeUnit.Day>.minutes get() = generateSequence(TimeUnit.Minute) { it.dayOfMonth }

@get:JvmName("dayHours")
val TimePeriod<TimeUnit.Day>.hours get() = generateSequence(TimeUnit.Hour) { it.dayOfMonth }

@get:JvmName("monthHours")
val TimePeriod<TimeUnit.Month>.hours get() = generateSequence(TimeUnit.Hour) { it.month }

@get:JvmName("monthDays")
val TimePeriod<TimeUnit.Month>.days get() = generateSequence(TimeUnit.Day) { it.month }

val TimePeriod<TimeUnit.Year>.months get() = generateSequence(TimeUnit.Month) { it.year }

@get:JvmName("yearDays")
val TimePeriod<TimeUnit.Year>.days get() = generateSequence(TimeUnit.Day) { it.year }
