package com.example.demo.model

class Server(
    val name: String,
    val cpuTemp: Int,
    val gpuTemp: Int,
    val cpuFan: Int,
    val gpuFan: Int,
    val motherFan: Int,
    val cpuLoad: Int,
    val gpuLoad: Int,
) {
    fun toJson(): Map<String, Any> {
        return mapOf(
            "name" to this.name,
            "cpuTemp" to cpuTemp,
            "gpuTemp" to gpuTemp,
            "cpuTemp" to cpuTemp,
            "cpuFan" to cpuFan,
            "gpuTemp" to gpuTemp,
            "gpuFan" to gpuFan,
            "motherFan" to motherFan,
            "cpuLoad" to cpuLoad,
            "gpuLoad" to gpuLoad
        )
    }
}