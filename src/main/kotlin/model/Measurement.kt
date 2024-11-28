package model

import java.time.LocalDateTime

data class Measurement(
    val measurementTime: LocalDateTime,
    val type: MeasurementType,
    val measurementValue: Double
)
