package utils

import java.time.LocalDateTime

/**
 * Returns the oldest element of a list with an implemented [LocalDateTime]
 */
fun <T>List<T>.getOlder(
    measureTime: (T) -> LocalDateTime
): T {
    return maxBy { measureTime(it) }
}