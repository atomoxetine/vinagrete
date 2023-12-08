package vinagrete

import java.net.Socket
import java.net.SocketAddress
import java.io.InputStreamReader
import java.io.BufferedReader

fun connect(ip: String, port: Int) {
    val socket = Socket(ip, port)

    val text = BufferedReader(InputStreamReader(socket.inputStream)).readLine()

    println(text)

    socket.close()
}
