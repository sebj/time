import kotlinx.datetime.Month

@Throws(IllegalTimeUnitException::class)
internal fun <Unit: TimeUnit> computeDifference(from: TimePeriod<Unit>, to: TimePeriod<Unit>): TimeDifference<Unit> {
    val durationDifference = to.approximateMidPoint - from.approximateMidPoint

    @Suppress("UNCHECKED_CAST")
    when (val smallestUnit = from.smallestUnit) {
        is TimeUnit.Nanosecond -> return TimeDifference(durationDifference.inWholeNanoseconds.toInt(), smallestUnit)
        is TimeUnit.Second -> return TimeDifference(durationDifference.inWholeSeconds.toInt(), smallestUnit)
        is TimeUnit.Minute -> return TimeDifference(durationDifference.inWholeMinutes.toInt(), smallestUnit)
        is TimeUnit.Hour -> return TimeDifference(durationDifference.inWholeHours.toInt(), smallestUnit)
        is TimeUnit.Day -> return TimeDifference(durationDifference.inWholeDays.toInt(), smallestUnit)
        is TimeUnit.Month -> {
            if (from == to) {
                return TimeDifference(0, smallestUnit)
            }

            val fromMonth = from as TimePeriod<TimeUnit.Month>
            val toMonth = to as TimePeriod<TimeUnit.Month>

            if (fromMonth.month == toMonth.month) {
                val yearsDifference = toMonth.year - fromMonth.year
                val yearsDifferenceAsMonths = yearsDifference * Month.values().count()
                return TimeDifference(yearsDifferenceAsMonths, smallestUnit)
            } else if (toMonth > fromMonth) {
                val remainingFromYearMonths = Month.values().count() - fromMonth.month.ordinal
                val monthsToToMonth = toMonth.month.ordinal

                val yearsDifference = (toMonth.year - fromMonth.year) - 1
                val yearsDifferenceInMonths = yearsDifference * Month.values().count()

                return TimeDifference(remainingFromYearMonths + monthsToToMonth + yearsDifferenceInMonths, smallestUnit)
            } else {
                val remainingToYearMonths = Month.values().count() - toMonth.month.ordinal
                val monthsFromFromMonth = fromMonth.month.ordinal

                val yearsDifference = (fromMonth.year - toMonth.year) - 1
                val yearsDifferenceInMonths = yearsDifference * Month.values().count()

                return TimeDifference(-(remainingToYearMonths + monthsFromFromMonth + yearsDifferenceInMonths), smallestUnit)
            }
        }
        is TimeUnit.Year -> {
            val fromYear = from as TimePeriod<TimeUnit.Year>
            val toYear = to as TimePeriod<TimeUnit.Year>
            return TimeDifference(toYear.year - fromYear.year, smallestUnit)
        }
        else -> throw IllegalTimeUnitException("Unexpected unit $smallestUnit")
    }
}

public fun <Unit: TimeUnit> TimePeriod<Unit>.difference(to: TimePeriod<Unit>): TimeDifference<Unit> {
    return computeDifference(this, to)
}

public operator fun <Unit: TimeUnit> TimePeriod<Unit>.minus(other: TimePeriod<Unit>): TimeDifference<Unit> {
    return difference(other)
}