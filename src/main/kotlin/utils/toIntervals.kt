package utils

import java.time.Duration
import java.time.LocalDateTime

/**
 * Given a list of elements with [LocalDateTime] implemented and an interval,
 * it divides the elements by intervals by returning a [Map] with the index
 * of the interval and its measurements
 *
 * @throws [IllegalStateException] if the indicated [startOfSampling] is older than the earliest measurement
 */
fun <T>List<T>.toIntervals(
    startOfSampling: LocalDateTime,
    intervalMs: Long,
    measureTime: (T) -> LocalDateTime
): Map<Long, List<T>> {

    if(isEmpty()) return emptyMap()

    val measurementsSorted = sortedBy(measureTime)

    if(measureTime(measurementsSorted.first()) < startOfSampling) throw IllegalStateException("Start of sampling is after the earliest measurement")

     return measurementsSorted.groupBy { tMeasurement->
         //Calculate and order by index
        val timeElapsed = Duration.between(startOfSampling, measureTime(tMeasurement)).toMillis()
        (timeElapsed / intervalMs).let {
            if(timeElapsed.mod(intervalMs) == 0L) it-1
            else it
        }
    }

}