import kotlin.jvm.JvmName

private fun <OutUnit : TimeUnit, LimitValue : Comparable<LimitValue>> TimePeriod<*>.generateSequence(
    outUnit: OutUnit,
    limitValueTransform: (DateTimeComponents) -> Comparable<LimitValue>?
): Sequence<TimePeriod<OutUnit>> {
    val start = firstInstant
    val endValue = limitValueTransform(components)
    return generateSequence(TimePeriod(timeZone, start, outUnit)) { timePeriod ->
        timePeriod
            .applying(TimeDifference(count = 1, unit = outUnit))
            .takeUnless { limitValueTransform(it.components) != endValue }
    }
}

@get:JvmName("minuteSeconds")
val TimePeriod<Minute>.seconds get() = generateSequence(Second) { it.minute }

@get:JvmName("hourSeconds")
val TimePeriod<Hour>.seconds get() = generateSequence(Second) { it.hour }
@get:JvmName("hourMinutes")
val TimePeriod<Hour>.minutes get() = generateSequence(Minute) { it.hour }

@get:JvmName("dayMinutes")
val TimePeriod<Day>.minutes get() = generateSequence(Minute) { it.dayOfMonth }
@get:JvmName("dayHours")
val TimePeriod<Day>.hours get() = generateSequence(Hour) { it.dayOfMonth }

@get:JvmName("monthHours")
val TimePeriod<Month>.hours get() = generateSequence(Hour) { it.month }
@get:JvmName("monthDays")
val TimePeriod<Month>.days get() = generateSequence(Day) { it.month }

val TimePeriod<Year>.months get() = generateSequence(Month) { it.year }

@get:JvmName("yearDays")
val TimePeriod<Year>.days get() = generateSequence(Day) { it.year }
