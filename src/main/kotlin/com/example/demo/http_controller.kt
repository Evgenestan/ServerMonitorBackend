package com.example.demo

import com.example.demo.model.Server
import com.example.demo.ws.MonitorSenderHandler
import com.example.demo.ws.monitorHandler
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
class HTMLController {

    @PostMapping("/rest")
    fun test(
        @RequestParam name: String,
        @RequestParam cpuTemp: Double,
        @RequestParam gpuTemp: Double,
        @RequestParam cpuFan: Double,
        @RequestParam gpuFan: Double,
        @RequestParam motherFan: Double,
        @RequestParam cpuLoad: Double,
        @RequestParam gpuLoad: Double,
    ) {
        val server = Server(
            name = name,
            cpuTemp = cpuTemp.toInt(),
            gpuTemp = gpuTemp.toInt(),
            cpuFan = cpuFan.toInt(),
            gpuFan = gpuFan.toInt(),
            motherFan = motherFan.toInt(),
            cpuLoad = cpuLoad.toInt(),
            gpuLoad = gpuLoad.toInt(),
        )
//        model.asMap()["title"] = "test"
//        return "test"
        monitorHandler.sendServerData(server)
    }
}