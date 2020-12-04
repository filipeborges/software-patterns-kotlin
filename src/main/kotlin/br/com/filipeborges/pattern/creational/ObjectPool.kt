package br.com.filipeborges.pattern.creational

class Data(
    var p1: String? = null,
    var p2: String? = null
)

// Class not thread safe
class SimpleObjectPool(
    private val available: ArrayList<Data> = ArrayList(),
    private val inUse: ArrayList<Data> = ArrayList()
) {

    fun getObject(): Data {
        val obj = if (available.isNotEmpty()) available.removeAt(0) else Data()
        inUse.add(obj)
        return obj
    }

    fun releaseObject(obj: Data) {
        resetObject(obj)
        returnObject(obj)
    }

    private fun resetObject(obj: Data) {
        obj.p1 = null
        obj.p2 = null
    }

    private fun returnObject(obj: Data) {
        inUse.remove(obj)
        available.add(obj)
    }

    fun numberOfInstances() = available.size + inUse.size

}

fun runObjectPool() {
    val objectPool = SimpleObjectPool()

    val data1 = objectPool.getObject()
    val data2 = objectPool.getObject()
    val data3 = objectPool.getObject()

    println("========= OBJECT POOL SIZE -> ${objectPool.numberOfInstances()} =============")

    objectPool.releaseObject(data1)
    objectPool.releaseObject(data2)
    objectPool.releaseObject(data3)

    val data4 = objectPool.getObject()
    val data5 = objectPool.getObject()
    val data6 = objectPool.getObject()

    println("========= OBJECT POOL SIZE -> ${objectPool.numberOfInstances()} =============")

    objectPool.releaseObject(data1)
    objectPool.releaseObject(data2)
    objectPool.releaseObject(data3)

    val data7 = objectPool.getObject()
    val data8 = objectPool.getObject()
    val data9 = objectPool.getObject()
    val data10 = objectPool.getObject()

    println("========= OBJECT POOL SIZE -> ${objectPool.numberOfInstances()} =============")
}