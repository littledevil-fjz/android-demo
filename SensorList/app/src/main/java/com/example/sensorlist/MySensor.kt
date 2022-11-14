package com.example.sensorlist

import android.annotation.TargetApi
import android.content.Context
import android.hardware.Sensor
import android.os.Build
import com.example.sensorlist.MySensor
import com.example.sensorlist.R

class MySensor @TargetApi(Build.VERSION_CODES.GINGERBREAD) constructor(
    sensor: Sensor,
    context: Context
) {
    private val context: Context
    var maximumRange: Float
    var minDelay = 0f
    var power: Float
    var resolution: Float
    var name: String
    var vendor: String
    var type: Int
    var version: Int
    val delayUnits: String
        get() = MICRO + "s"
    val powerUnits: String
        get() = "mA"
    val typeDescription: String
        get() {
            var description: String? = null
            description = when (type) {
                Sensor.TYPE_ACCELEROMETER -> context.resources.getString(
                    R.string.accelerometer
                )
                Sensor.TYPE_AMBIENT_TEMPERATURE -> context.resources.getString(
                    R.string.ambient_temperature
                )
                Sensor.TYPE_GRAVITY -> context.resources.getString(R.string.gravity)
                Sensor.TYPE_GYROSCOPE -> context.resources.getString(R.string.gyroscope)
                Sensor.TYPE_LIGHT -> context.resources.getString(R.string.light)
                Sensor.TYPE_LINEAR_ACCELERATION -> context.resources.getString(
                    R.string.linear_acceleration
                )
                Sensor.TYPE_MAGNETIC_FIELD -> context.resources.getString(
                    R.string.magnetic_field
                )
                Sensor.TYPE_ORIENTATION -> context.resources
                    .getString(R.string.orientation)
                Sensor.TYPE_PRESSURE -> context.resources.getString(R.string.pressure)
                Sensor.TYPE_PROXIMITY -> context.resources.getString(R.string.proximity)
                Sensor.TYPE_RELATIVE_HUMIDITY -> context.resources.getString(
                    R.string.relative_humidity
                )
                Sensor.TYPE_ROTATION_VECTOR -> context.resources.getString(
                    R.string.rotation_vector
                )
                Sensor.TYPE_TEMPERATURE -> context.resources
                    .getString(R.string.temperature)
                else -> context.resources.getString(R.string.unknown)
            }
            return description
        }
    val units: String
        get() {
            var units: String? = null
            units = when (type) {
                Sensor.TYPE_ACCELEROMETER -> "m/s" + SQUARE
                Sensor.TYPE_AMBIENT_TEMPERATURE -> "° C"
                Sensor.TYPE_GRAVITY -> "m/s" + SQUARE
                Sensor.TYPE_GYROSCOPE -> "rad/s"
                Sensor.TYPE_LIGHT -> "SI lux"
                Sensor.TYPE_LINEAR_ACCELERATION -> "m/s" + SQUARE
                Sensor.TYPE_MAGNETIC_FIELD -> MICRO + "T"
                Sensor.TYPE_ORIENTATION -> "° "
                Sensor.TYPE_PRESSURE -> "hPa"
                Sensor.TYPE_PROXIMITY -> "cm"
                Sensor.TYPE_RELATIVE_HUMIDITY -> ""
                Sensor.TYPE_ROTATION_VECTOR -> ""
                Sensor.TYPE_TEMPERATURE -> "° C"
                else -> "unknown"
            }
            return units
        }

    override fun toString(): String {
        return name
    }

    companion object {
        private const val MICRO = "&amp;#x3BC;"
        private val SDK = Build.VERSION.SDK_INT
        private const val SQUARE = "&amp;#xB2;"
    }

    init {
        name = sensor.name
        vendor = sensor.vendor
        type = sensor.type
        version = sensor.version
        maximumRange = sensor.maximumRange
        if (SDK >= Build.VERSION_CODES.GINGERBREAD) minDelay = sensor.minDelay.toFloat()
        power = sensor.power
        resolution = sensor.resolution
        this.context = context
    }
}