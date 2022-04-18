import kotlin.jvm.JvmName

internal fun <OutUnit : TimeUnit, LimitValue : Comparable<LimitValue>> TimePeriod<*>.generateSequence(
    outUnit: OutUnit,
    limitValueTransform: (DateTimeComponents) -> Comparable<LimitValue>?
): Sequence<TimePeriod<OutUnit>> {
    val start = firstInstant()
    val endValue = limitValueTransform(dateTimeComponents)
    return generateSequence(TimePeriod(start, unit = outUnit)) { timePeriod ->
        timePeriod.next(outUnit).takeUnless { limitValueTransform(it.dateTimeComponents) != endValue }
    }
}

@JvmName("minuteSeconds")
fun TimePeriod<Minute>.seconds() = generateSequence(Second) { it.minute }

@JvmName("hourSeconds")
fun TimePeriod<Hour>.seconds() = generateSequence(Second) { it.hour }
@JvmName("hourMinutes")
fun TimePeriod<Hour>.minutes() = generateSequence(Minute) { it.hour }

@JvmName("dayMinutes")
fun TimePeriod<Day>.minutes() = generateSequence(Minute) { it.dayOfMonth }
@JvmName("dayHours")
fun TimePeriod<Day>.hours() = generateSequence(Hour) { it.dayOfMonth }

@JvmName("monthHours")
fun TimePeriod<Month>.hours() = generateSequence(Hour) { it.month }
@JvmName("monthDays")
fun TimePeriod<Month>.days() = generateSequence(Day) { it.month }

fun TimePeriod<Year>.months() = generateSequence(Month) { it.year }
@JvmName("yearDays")
fun TimePeriod<Year>.days() = generateSequence(Day) { it.year }
