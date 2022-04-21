/**
 * There are 13 possible ways in which two values may be related to each other.
 *
 * See: [Allen's Interval](https://en.wikipedia.org/wiki/Allen%27s_interval_algebra)
 */
enum class Relation {
    /**
     * The first range occurs entirely before the second range.
     *
     * Example: Range `A` is before range `B`:
     * ```
     * ●--A--○
     *          ●--B--○
     * ```
     */
    Before,

    /**
     * The first range occurs entirely after the second range.
     *
     * Example: Range `A` is after range `B`:
     * ```
     * ●--B--○
     *          ●--A--○
     * ```
     */
    After,

    /**
     * The first range ends where the second range starts.
     *
     * Example: Range `A` meets range `B`:
     * ```
     * ●--A--○
     *       ●--B--○
     * ```
     */
    Meets,

    /**
     * The first range starts where the second range ends.
     *
     * Example: Range `A` is met by range `B`:
     * ```
     * ●--B--○
     *       ●--A--○
     * ```
     */
    IsMetBy,

    /**
     * The first range starts before the second range starts, and ends before the second range ends.
     *
     * Example: Range `A` overlaps range `B`:
     * ```
     * ●--A--○
     *     ●--B--○
     * ```
     */
    Overlaps,

    /**
     * The first range starts after the second range starts, and ends after the second range ends.
     *
     * Example: Range `A` is overlapped by range `B`:
     * ```
     * ●--B--○
     *     ●--A--○
     * ```
     */
    IsOverlappedBy,

    /**
     * The first range starts where the second range starts, and ends before the second range ends.
     *
     * Example: Range `A` starts range `B`:
     * ```
     * ●--A--○
     * ●----B----○
     * ```
     */
    Starts,

    /**
     * The first range starts where the second range starts, and ends after the second range ends.
     *
     * Example: Range `A` is started by range `B`:
     * ```
     * ●--B--○
     * ●----A----○
     * ```
     */
    IsStartedBy,

    /**
     * The first range starts after the second range starts, and ends before the second range ends.
     *
     * Example: Range `A` is during range `B`:
     * ````
     *   ●--A--○
     * ●----B----○
     * ```
     */
    During,

    /**
     * The first range starts before the second range starts, and ends after the second range ends.
     *
     * Example: Range `A` contains range `B`:
     * ```
     *   ●--B--○
     * ●----A----○
     * ```
     */
    Contains,

    /**
     * The first range starts after the second range starts, and ends with the second range.
     *
     * Example: Range `A` finishes range `B`:
     * ```
     *     ●--A--○
     * ●----B----○
     * ```
     */
    Finishes,

    /**
     * The first range starts before the second range starts, and ends with the second range.
     *
     * Example: Range `A` is finished by range `B`:
     * ```
     *     ●--B--○
     * ●----A----○
     * ```
     */
    IsFinishedBy,

    /**
     * The first and second ranges start and end together.
     *
     * Example: Range `A` equals range `B`:
     * ```
     * ●----A----○
     * ●----B----○
     * ```
     */
    Equal;

    private companion object {
        val meetings = setOf(Meets, IsMetBy, Starts, IsStartedBy, Finishes, IsFinishedBy)
        val overlappings = setOf(Overlaps, IsOverlappedBy, During, Contains)
    }

    /**
     * @return `true` if the relation describes two ranges that meet at any extreme.
     */
    val isMeeting: Boolean get() = meetings.contains(this)

    /**
     * @return `true` if the relation describes any kind of overlapping.
     */
    val isOverlapping: Boolean get() = overlappings.contains(this)

    /**
     * @return `true` if the relation describes disjointedness.
     */
    val isDisjoint: Boolean get() = this == Before || this == After

    /**
     * @return `true` if the relation describes equality.
     */
    val isEqual: Boolean get() = this == Equal
}

private fun <T : Comparable<T>> ClosedRange<T>.determineRelationship(other: ClosedRange<T>): Relation {
    if (start < other.start) {
        if (endInclusive < other.start) { return Relation.Before }
        if (endInclusive == other.start) { return Relation.Meets }
        if (endInclusive < other.start) { return Relation.Overlaps }
        if (endInclusive == other.start) { return Relation.IsFinishedBy }
        /* upperBound > other.upperBound */ return Relation.Contains
    } else if (start == other.start) {
        if (endInclusive < other.endInclusive) { return Relation.Starts }
        if (endInclusive == other.endInclusive) { return Relation.Equal }
        /* (endInclusive > other.endInclusive) */ return Relation.IsStartedBy
    } else /* start > other.start */ {
        if (start > other.endInclusive) { return Relation.After }
        if (start == other.endInclusive) { return Relation.IsMetBy }
        if (endInclusive < other.endInclusive) { return Relation.During }
        if (endInclusive == other.endInclusive) { return Relation.Finishes }
        /* endInclusive > other.endInclusive */ return Relation.IsOverlappedBy
    }
}

fun TimePeriod<*>.before(other: TimePeriod<*>): Boolean {
    val relation = range.determineRelationship(other.range)
    return relation == Relation.Before || relation == Relation.Meets
}

inline fun TimePeriod<*>.after(other: TimePeriod<*>) = other.before(this)

fun TimePeriod<*>.contains(other: TimePeriod<*>): Boolean {
    val relation = range.determineRelationship(other.range)
    return relation == Relation.Contains || relation == Relation.IsStartedBy || relation == Relation.IsFinishedBy
}

fun TimePeriod<*>.isDuring(other: TimePeriod<*>): Boolean {
    val relation = range.determineRelationship(other.range)
    return relation == Relation.During || relation == Relation.Starts || relation == Relation.Finishes
}

fun TimePeriod<*>.overlaps(other: TimePeriod<*>) = range.determineRelationship(other.range).isOverlapping
