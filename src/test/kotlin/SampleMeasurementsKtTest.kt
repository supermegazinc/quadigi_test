import model.Measurement
import model.MeasurementType
import org.junit.Assert.*
import java.time.LocalDateTime
import kotlin.test.Test

class SampleMeasurementsKtTest {

    @Test
    fun `test sample function`() {

        val measurements = listOf(
            Measurement(LocalDateTime.parse("2017-01-03T10:04:45"), MeasurementType.TEMP, 35.79),
            Measurement(LocalDateTime.parse("2017-01-03T10:01:18"), MeasurementType.SPO2, 98.78),
            Measurement(LocalDateTime.parse("2017-01-03T10:09:07"), MeasurementType.TEMP, 35.01),
            Measurement(LocalDateTime.parse("2017-01-03T10:03:34"), MeasurementType.SPO2, 96.49),
            Measurement(LocalDateTime.parse("2017-01-03T10:02:01"), MeasurementType.TEMP, 35.82),
            Measurement(LocalDateTime.parse("2017-01-03T10:05:00"), MeasurementType.SPO2, 97.17),
            Measurement(LocalDateTime.parse("2017-01-03T10:05:01"), MeasurementType.SPO2, 95.08)
        )

        val expected = mapOf(
            MeasurementType.TEMP to listOf(
                Measurement(LocalDateTime.parse("2017-01-03T10:05:00"), MeasurementType.TEMP, 35.79),
                Measurement(LocalDateTime.parse("2017-01-03T10:10:00"), MeasurementType.TEMP, 35.01)
            ),
            MeasurementType.SPO2 to listOf(
                Measurement(LocalDateTime.parse("2017-01-03T10:05:00"), MeasurementType.SPO2, 97.17),
                Measurement(LocalDateTime.parse("2017-01-03T10:10:00"), MeasurementType.SPO2, 95.08)
            )
        )

        val startOfSampling = LocalDateTime.parse("2017-01-03T10:00:00")

        val result = measurements.sample(startOfSampling)
        assertEquals(expected, result)

    }
}