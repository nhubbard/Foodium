package dev.shreyaspatil.foodium.utils

import java.time.LocalDateTime

/**
 * Returns [Boolean] based on current time.
 * Returns true if hours are between 06:00 pm - 07:00 am
 */
fun isNight(): Boolean {
    val currentHour = LocalDateTime.now().hour
    return (currentHour <= 7 || currentHour >= 18)
}