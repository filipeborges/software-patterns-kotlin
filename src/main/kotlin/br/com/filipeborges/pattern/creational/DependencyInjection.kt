package br.com.filipeborges.pattern.creational

interface Printable {
    fun data(): String
}

class PrettyPrinter(private val printable: Printable) {
    fun print() {
        println("=========== PRETTY PRINT ================")
        println("=========== Starting print ==============")
        println(printable.data())
        println("=========== Ended print =================")
    }
}

class FastPrintable : Printable {
    override fun data() = "Data from FastPrintable!"
}

class SlowPrintable : Printable {
    override fun data(): String {
        Thread.sleep(2000L)
        return "Data from SlowPrintable - Delayed 2 seconds!"
    }
}

class ObjectBuilder {

    private lateinit var objects: Array<Any>

    init {
        objects = arrayOf(
            FastPrintable(),
            SlowPrintable()
        )
    }

    fun getObjects() = objects
}

fun runDependencyInjection() {
    val objBuilder = ObjectBuilder()
    val printable = if (System.currentTimeMillis() % 2L == 0L) objBuilder.getObjects()[0] else objBuilder.getObjects()[1]
    val prettyPrinter = PrettyPrinter(printable as Printable)
    prettyPrinter.print()
}