package com.example.demo.ws

import com.example.demo.model.Server
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketHandler
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.config.annotation.*
import org.springframework.web.socket.handler.AbstractWebSocketHandler


class MonitorSenderHandler: AbstractWebSocketHandler() {

    private val sessionList = ArrayList<WebSocketSession>()

    private fun emit(session: WebSocketSession, data: Map<String, Any>) = session.sendMessage(TextMessage(ObjectMapper().writeValueAsString(data)))
    private fun broadcast(data: Map<String, Any>) = sessionList.forEach { emit(it, data) }

    fun sendServerData(server:Server) {
        //print("connect")
        //print(sessionList.size)
        broadcast(server.toJson())
    }

    @Throws(Exception::class)
    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        sessionList -= session
    }

    override  fun  afterConnectionEstablished(session: WebSocketSession) {
        sessionList += session
    }

    public override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
//        val server = Server(
//            name = "abra",
//            cpuTemp = 15.0,
//            gpuTemp = 30.0,
//            cpuFan = 1056.0,
//            gpuFan = 0.0,
//            motherFan = 2547.0,
//            cpuLoad = 54.0,
//            gpuLoad = 1.0,
//        )
        //sessionList.add(session)
        //print(message)
        //val json = ObjectMapper().readTree(message.payload)
        //print(json)
    }
}


var monitorHandler = MonitorSenderHandler()


@Configuration
@EnableWebSocket
class WSConfig : WebSocketConfigurer {
    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        registry.addHandler(monitorHandler, "/ws").setAllowedOrigins("*")
    }

    @Bean
    fun myHandler(): WebSocketHandler? {
        return MonitorSenderHandler()
    }
}

