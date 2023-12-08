package vinagrete

import java.net.ServerSocket
import java.net.InetAddress
import java.net.NetworkInterface
import java.io.PrintWriter
import vinagrete.getPrivateIPv4Address



fun getPrivateIPv4Address(): String {
    try {
        val networkInterfaces = NetworkInterface.getNetworkInterfaces()

        while (networkInterfaces.hasMoreElements()) {
            val networkInterface = networkInterfaces.nextElement()
            val interfaceAddresses = networkInterface.interfaceAddresses

            for (interfaceAddress in interfaceAddresses) {
                val inetAddress = interfaceAddress.address

                if (!inetAddress.isLoopbackAddress && inetAddress.isSiteLocalAddress && inetAddress is InetAddress) {
                    return inetAddress.hostAddress
                }
            }
        }
} catch (e: Exception) {
        e.printStackTrace()
    }

    throw Exception("Machine not connected to any local area network.")
}

fun listen(port: Int) {
    val serverSocket = ServerSocket(port)

    println("Listening for connections on port: ${port}")
    println("Current ip address: ${getPrivateIPv4Address()}")

    val clientSocket = serverSocket.accept()

    PrintWriter(clientSocket.outputStream, true).println("Hello World!")
    clientSocket.outputStream.flush()

    serverSocket.close()
}
