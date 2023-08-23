import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.UnsafeNumber
import kotlinx.cinterop.convert
import kotlinx.datetime.FixedOffsetTimeZone
import kotlinx.datetime.TimeZone
import platform.Foundation.NSDateComponents
import platform.Foundation.NSTimeZone
import platform.Foundation.timeZoneForSecondsFromGMT
import platform.Foundation.timeZoneWithAbbreviation
import platform.Foundation.timeZoneWithName

@ExperimentalForeignApi
@UnsafeNumber(["ios_arm64: kotlin.Int", "ios_simulator_arm64: kotlin.Int", "ios_x64: kotlin.Int", "macos_arm64: kotlin.Int", "macos_x64: kotlin.Int", "tvos_arm64: kotlin.Int", "tvos_simulator_arm64: kotlin.Int", "tvos_x64: kotlin.Int", "watchos_arm32: kotlin.Int", "watchos_arm64: kotlin.Int", "watchos_simulator_arm64: kotlin.Int", "watchos_x64: kotlin.Int"])
public fun TimePeriod<*>.toNSDateComponents(): NSDateComponents {
    val dateComponents = NSDateComponents()
    dateComponents.timeZone = timeZone.toNSTimeZone()
    dateComponents.year = components.year.convert()
    components.month?.let { dateComponents.month = it.ordinal.convert() }
    components.dayOfMonth?.let { dateComponents.day = it.convert() }
    components.hour?.let { dateComponents.hour = it.convert() }
    components.minute?.let { dateComponents.minute = it.convert() }
    components.second?.let { dateComponents.second = it.convert() }
    components.nanosecond?.let { dateComponents.nanosecond = it.convert() }
    return dateComponents
}

// Copied from https://github.com/Kotlin/kotlinx-datetime/blob/master/core/darwin/src/Converters.kt
// as this doesn't seem to be available in this source set despite being marked as `public`.
/**
 * Converts the [TimeZone] to [NSTimeZone].
 *
 * If the time zone is represented as a fixed number of seconds from UTC+0 (for example, if it is the result of a call
 * to `TimeZone.offset`) and the offset is not given in even minutes but also includes seconds, this method throws
 * `DateTimeException` to denote that lossy conversion would happen, as Darwin internally rounds the offsets to the
 * nearest minute.
 */
@ExperimentalForeignApi
@UnsafeNumber(["ios_arm64: kotlin.Int", "ios_simulator_arm64: kotlin.Int", "ios_x64: kotlin.Int", "macos_arm64: kotlin.Int", "macos_x64: kotlin.Int", "tvos_arm64: kotlin.Int", "tvos_simulator_arm64: kotlin.Int", "tvos_x64: kotlin.Int", "watchos_arm32: kotlin.Int", "watchos_arm64: kotlin.Int", "watchos_simulator_arm64: kotlin.Int", "watchos_x64: kotlin.Int"])
private fun TimeZone.toNSTimeZone(): NSTimeZone = if (this is FixedOffsetTimeZone) {
    require(offset.totalSeconds % 60 == 0) {
        "NSTimeZone cannot represent fixed-offset time zones with offsets not expressed in whole minutes: $this"
    }
    NSTimeZone.timeZoneForSecondsFromGMT(offset.totalSeconds.convert())
} else {
    NSTimeZone.timeZoneWithName(id) ?: NSTimeZone.timeZoneWithAbbreviation(id)!!
}
