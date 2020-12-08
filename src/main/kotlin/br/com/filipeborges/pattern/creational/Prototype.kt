package br.com.filipeborges.pattern.creational

abstract class Prototype(
    var prop1: String? = null,
    var prop2: String? = null
) {
    abstract fun clone(): Prototype
}

class ConcretePrototype1 : Prototype() {
    override fun clone(): Prototype {
        val obj = ConcretePrototype1()
        obj.prop1 = this.prop1
        obj.prop2 = this.prop2
        return obj
    }
}

fun runPrototype() {
    val prototype1: Prototype = ConcretePrototype1()
    prototype1.prop1 = "ConcretePrototype1 - Prop1 = Value1"
    prototype1.prop2 = "ConcretePrototype1 - Prop2 = Value2"
    val prototype2: Prototype = prototype1.clone()

    println("=====Prototype 1 instance -> ${prototype1}=============")
    println(prototype1.prop1)
    println(prototype1.prop2)
    println("=====Prototype 2 instance -> ${prototype2}=============")
    println(prototype2.prop1)
    println(prototype2.prop2)

}