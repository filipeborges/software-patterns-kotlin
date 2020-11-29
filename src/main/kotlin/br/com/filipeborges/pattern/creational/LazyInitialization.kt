package br.com.filipeborges.pattern.creational


class ExpensiveInit {
    init {
        Thread.sleep(4000L)
    }

    override fun toString(): String {
        return "ExpensiveInit()"
    }
}

class Factory {

    companion object {
        private var expensiveObjectMap: HashMap<Int, Any> = HashMap<Int, Any>()

        // Delay object creation until accessor method is called
        fun getObject(type: Int) = expensiveObjectMap.getOrPut(1, { ExpensiveInit() })
    }

}

fun runLazyInitialization() {
    println(Factory.getObject(1))
    println(Factory.getObject(1))
}