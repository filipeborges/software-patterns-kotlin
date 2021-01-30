package br.com.filipeborges.pattern.behavioural

import java.time.LocalDateTime

interface Logger {

    var log: (msg: String, level: Level) -> Unit

    enum class Level {
        DEBUG, INFO, WARN, ERROR, FATAL
    }

    fun append(next: Logger): Logger {
        val currentLogger = log
        log = { msg, level ->
            currentLogger(msg, level)
            next.log(msg, level)
        }
        return this
    }
}

class ConsoleLogger(private vararg val levels: Logger.Level) : Logger {
    override var log: (msg: String, level: Logger.Level) -> Unit = {
        msg, level -> if (levels.asIterable().any { level == it }) println("[CONSOLE] $msg")
    }
}

class FileLogger(private vararg val levels: Logger.Level) : Logger {
    override var log: (msg: String, level: Logger.Level) -> Unit = {
        msg, level -> if (levels.asIterable().any { level == it }) println("[FILE] $msg")
    }
}

class EmailLogger(private vararg val levels: Logger.Level) : Logger {
    override var log: (msg: String, level: Logger.Level) -> Unit = {
        msg, level -> if (levels.asIterable().any { level == it }) println("[EMAIL] $msg")
    }
}

fun printTimeStamp(logger: Logger) = logger.log("Current timestamp: ${System.currentTimeMillis()}", Logger.Level.DEBUG)

fun printDate(logger: Logger) = logger.log("Current date: ${LocalDateTime.now()}", Logger.Level.INFO)

fun runChainOfResponsibility() {
    val logger = ConsoleLogger(Logger.Level.DEBUG, Logger.Level.INFO, Logger.Level.WARN)
            .append(FileLogger(Logger.Level.INFO, Logger.Level.WARN))
            .append(EmailLogger(Logger.Level.ERROR, Logger.Level.FATAL))

    printTimeStamp(logger)
    printDate(logger)
}