import model.Measurement
import model.MeasurementType
import utils.getOlder
import utils.toIntervals
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

fun List<Measurement>.sample(
    startOfSampling: LocalDateTime,
    intervalMs: Long = 300000 //5 minutes
): Map<MeasurementType, List<Measurement>> {

    //Generic implementation: MeasurementType can be modified without affecting this function
     return MeasurementType.entries.associateWith { tMeasurementType->
         //"each type of measurement shall be sampled separately"
         filter { it.type == tMeasurementType }
             //Group measurements into intervals
             .toIntervals(
                 startOfSampling = startOfSampling,
                 intervalMs = intervalMs,
                 measureTime = { it.measurementTime }
             ).map { tInterval ->
                 //Get the oldest measurement of every interval
                 tInterval.value.getOlder(
                     measureTime = { it.measurementTime }
                 ).copy(
                     //Set the time to its interval end
                     measurementTime = startOfSampling.plus((tInterval.key + 1) * intervalMs, ChronoUnit.MILLIS)
                 )
             }
     }.filterValues { it.isNotEmpty() } //Clean empty measurements types

}