# ⏱ Time

[![MIT License](https://img.shields.io/github/license/sebj/time?color=lightgray)](LICENSE)
[![Version 0.4.5](https://img.shields.io/github/v/release/sebj/time)](https://github.com/sebj/time/releases)
[![Build, Test & Deploy Documentation](https://github.com/sebj/time/workflows/Build%2C%20Test%20and%20Deploy%20Documentation/badge.svg)](https://github.com/sebj/time/actions/workflows/build-test-documentation.yaml)
[![Follow @sebj@mastodon.social](https://img.shields.io/mastodon/follow/000921252?domain=https%3A%2F%2Fmastodon.social&style=plastic)](https://mastodon.social/@sebj)

A Kotlin multiplatform library filling in the gaps of [`kotlinx-datetime`](https://github.com/Kotlin/kotlinx-datetime) with additional type-safe APIs for time periods, as equivalents to those found in the Swift library of the same name([`time`](https://github.com/davedelong/time)).

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

### Fetching the Current Time Period

```kotlin
val clock = Clock.System
val now = clock.thisInstant()
val today = clock.today()
val month = clock.thisMonth()
```

### Retrieving Components

Retrieve component values for a time period:
```kotlin
val today: TimePeriod<Day> = Clock.System.today()
val year = today.year // Ex: 2022
val month = today.month // Ex: APRIL
val day = today.day // Ex: 18
```

### Retrieving TimePeriods

Retrieve larger less-precise time periods for a time period:
```kotlin
val today: TimePeriod<Day> = Clock.System.today()
val month: TimePeriod<Month> = today.monthPeriod
val year: TimePeriod<Month> = today.yearPeriod
```

Retrieve smaller more-precise time periods for a time period:
```kotlin
val clock = Clock.System

val firstDayOfMonth: TimePeriod<Day> = clock.thisMonth().firstDay
val lastHourOfDay: TimePeriod<Hour> = clock.today().lastHour
val firstDayOfYear: TimePeriod<Day> = clock.thisYear().firstDay
```

### Iterating Over TimePeriods

```kotlin
val clock = Clock.System

val thisMonth = clock.thisMonth()
val daysInThisMonth = thisMonth.days

for (day in daysInThisMonth) {
    // …
}

val thisHour = clock.thisHour()
val minutesInThisHour = thisHour.minutes

for (minute in minutesInThisHour) {
    // …
}
```

### Determining the Relationship Between TimePeriods

```kotlin
val clock = Clock.System

val dayA: TimePeriod<Day> = ...
val dayB: TimePeriod<Day> = ...
val .. = dayA.after(dayB)

val thisMonth = clock.thisMonth()
val .. = dayA.during(thisMonth) // Equivalent to thisMonth.contains(dayA)

```

## ⚖️ License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.