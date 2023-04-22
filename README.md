# ⏱ Time

[![MIT License](https://img.shields.io/github/license/sebj/time?color=lightgray)](LICENSE)
[![Version 0.4.5](https://img.shields.io/github/v/release/sebj/time)](https://github.com/sebj/time/releases)
[![Build, Test & Deploy Documentation](https://github.com/sebj/time/workflows/Build%2C%20Test%20and%20Deploy%20Documentation/badge.svg)](https://github.com/sebj/time/actions/workflows/build-test-documentation.yaml)
[![Follow @sebj@mastodon.social](https://img.shields.io/mastodon/follow/000921252?domain=https%3A%2F%2Fmastodon.social&style=plastic)](https://mastodon.social/@sebj)

A Kotlin multiplatform library filling in the gaps of [`kotlinx-datetime`](https://github.com/Kotlin/kotlinx-datetime) with additional type-safe APIs for time periods, as equivalents to those found in the Swift library of the same name([`time`](https://github.com/davedelong/time)).

Supported targets:
* Apple
  * iOS
  * macOS
  * tvOS
  * watchOS
* JVM
* Linux

Supported `TimePeriod` units:
* Year
* Month
* Day
* Hour
* Minute
* Second
* Nanosecond

## ⬇️ Installation

Add the dependency to your Gradle build file:
```
dependencies {
    implementation("me.sebj:time:0.4.5")
}
```

## 💡 Usage Examples

### Fetching The Current Time Period

```kotlin
val clock = Clock.System
val now: Instant = clock.thisInstant()
val today: TimePeriod<TimeUnit.Day> = clock.today()
val month: TimePeriod<TimeUnit.Month> = clock.thisMonth()
```

### Retrieving Components

Retrieve component values for a time period:
```kotlin
val today: TimePeriod<TimeUnit.Day> = Clock.System.today()
val year = today.year // Ex: 2022
val month = today.month // Ex: APRIL
val day = today.day // Ex: 18
```

### Retrieving TimePeriods

Retrieve larger less-precise time periods for a time period:
```kotlin
val today: TimePeriod<TimeUnit.Day> = Clock.System.today()
val month: TimePeriod<TimeUnit.Month> = today.monthPeriod
val year: TimePeriod<TimeUnit.Year> = today.yearPeriod
```

Retrieve smaller more-precise time periods for a time period:
```kotlin
val clock = Clock.System

val firstDayOfMonth: TimePeriod<TimeUnit.Day> = clock.thisMonth().firstDay
val lastHourOfDay: TimePeriod<TimeUnit.Hour> = clock.today().lastHour
val firstDayOfYear: TimePeriod<TimeUnit.Day> = clock.thisYear().firstDay
```

### Iterating Over TimePeriods

```kotlin
val clock = Clock.System

val thisMonth: TimePeriod<TimeUnit.Month> = clock.thisMonth()
val daysInThisMonth = thisMonth.days

for (day in daysInThisMonth) {
    // …
}

val thisHour: TimePeriod<TimeUnit.Hour> = clock.thisHour()
val minutesInThisHour = thisHour.minutes

for (minute in minutesInThisHour) {
    // …
}
```

### Determining The Relationship Between TimePeriods

```kotlin
val clock = Clock.System

val dayA: TimePeriod<TimeUnit.Day> = ...
val dayB: TimePeriod<TimeUnit.Day> = ...
val ..: Boolean = dayA.after(dayB)
// Also: `before`, `overlaps`, or compare specific relationship using `relation` (see Relations.kt)

val thisMonth = clock.thisMonth()
val ..: Boolean = dayA.during(thisMonth) // Equivalent to `thisMonth.contains(dayA)`
```

### Adjusting TimePeriods
```kotlin
val today = Clock.System.today()
val dayAfterTomorrow = today + TimeDifference.days(2)
_ = today + TimeDifference.hours(1) // Compiler error – not implemented, as this is invalid
_ = today + TimeDifference.minutes(1) // Compiler error – not implemented, as this is invalid

val thisMonth = today.monthPeriod
val monthBeforeLast = thisMonth - TimeDifference.months(2)
_ = today - TimeDifference.nanoseconds(1) // Compiler error – not implemented, as this is invalid
_ = today - TimeDifference.seconds(1) // Compiler error – not implemented, as this is invalid
```

## ⚖️ License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.