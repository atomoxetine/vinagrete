package vinagrete

fun main(args: Array<String>) {
    val argsMap = HashMap<String, String?>()

    for (i in args.indices) {
        if (!args[i].startsWith("--")) continue
        val key = args[i]
        val value = if (args[i+1].startsWith("--")) null else args[i+1]

        argsMap.put(key, value)
    }

    try {
        if (argsMap.contains("--server")) {
            val port = Integer.parseInt(argsMap.get("--port"))
            try {
                listen(port)
            } catch (e: Exception) {
                println("Error while running server: ${e.message}")
            }
        } else if (argsMap.contains("--client")) {
            val port = Integer.parseInt(argsMap.get("--port"))
            val ip = argsMap.get("--ip")!!
            try {
                connect(ip, port)
            } catch (e: Exception) {
                println("Error while running client: ${e.message}")
            }
        } else {
            throw Exception()
        }
    } catch (e: Exception) {
        println(e.stackTraceToString())

        println("\n\nargsMap: ")
        var argsTxt = ""
        for (entry in argsMap.entries) {
            argsTxt += "${entry.key}: '${entry.value}'\n"
        }
        println(argsTxt+"\n")
    }
}
