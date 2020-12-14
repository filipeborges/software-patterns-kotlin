package br.com.filipeborges.pattern.structural

interface Render {
    fun print(data: String)
}

class CornerlessShapeRender : Render {
    override fun print(data: String) {
        println("========= Render for cornerless shapes =============")
        println(data)
    }
}

class GenericShapeRender : Render {
    override fun print(data: String) {
        println("========= Render for generic shapes ================")
        println(data)
    }
}

abstract class Shape(private val render: Render) {
    abstract fun render()
}

class Circle(private val render: Render) : Shape(render) {
    override fun render() {
        this.render.print("Circle data")
    }
}

class Square(private val render: Render) : Shape(render) {
    override fun render() {
        this.render.print("Square data")
    }
}

fun runBridge() {
    val shape1: Shape = Circle(CornerlessShapeRender())
    val shape2: Shape = Square(GenericShapeRender())
    shape1.render()
    shape2.render()
}