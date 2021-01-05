package br.com.filipeborges.pattern.structural

//Implemented based on: https://ecs.syr.edu/faculty/fawcett/handouts/cse776/PatternPDFs/ExtensionObject.pdf

interface Component {
    fun printComponentClassName()
    fun getExtension(extensionName: String): ComponentExtension? = null
}

class TextComponent(var words: Array<String>) : Component {
    override fun getExtension(extensionName: String): ComponentExtension? = when(extensionName) {
        TextExtension::class.simpleName -> TextExtension(this)
        else -> super.getExtension(extensionName)
    }

    override fun printComponentClassName() = println(this::class.qualifiedName)
}

class EmptyComponent() : Component {
    override fun printComponentClassName() = println(this::class.qualifiedName)
}

abstract class ComponentExtension(val component: Component) {
    abstract fun printExtensionClassName()
}

class TextExtension(textComponent: TextComponent) : ComponentExtension(textComponent) {
    override fun printExtensionClassName() = println(this::class.qualifiedName)

    // Extension specific interface
    fun countWords() = (super.component as TextComponent).words.size
}

fun runExtensionObject() {
    val textComponent = TextComponent(arrayOf("This", "is", "a", "phrase", "in", "a", "text", "component"))
    val textExtension = TextExtension(textComponent)

    logComponentName(textComponent)
    logExtensionName(textExtension)
    useTextExtensionInterface(textComponent)
    useTextExtensionInterface(EmptyComponent())
}

private fun useTextExtensionInterface(component: Component) {
    val extension = component.getExtension("TextExtension") ?: return
    println("====== Number of words of TextComponent ======")
    println((extension as TextExtension).countWords())
}

private fun logComponentName(component: Component) {
    println("====== Logging component name.... =====")
    component.printComponentClassName()
}

private fun logExtensionName(componentExtension: ComponentExtension) {
    println("===== Logging extension name... ======")
    componentExtension.printExtensionClassName()
}