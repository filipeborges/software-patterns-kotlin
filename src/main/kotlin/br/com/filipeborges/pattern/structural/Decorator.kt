package br.com.filipeborges.pattern.structural

interface HtmlGenerator {
    fun buildHtmlMessage(message: String): String
}

class SimpleHtmlGenerator : HtmlGenerator {
    override fun buildHtmlMessage(message: String) = buildHtmlContainer(buildHtmlBody(message))

    private fun buildHtmlContainer(htmlBody: String) = "<html>$htmlBody</html>"

    private fun buildHtmlBody(message: String) = "<body><div><p>$message</p></div></body>"
}

abstract class EnhancedHtmlGenerator(private val htmlGenerator: HtmlGenerator) : HtmlGenerator {
    override fun buildHtmlMessage(message: String) = htmlGenerator.buildHtmlMessage(message)
}

class SimpleEnhancedHtmlGenerator(htmlGenerator: HtmlGenerator) : EnhancedHtmlGenerator(htmlGenerator) {
    override fun buildHtmlMessage(message: String): String {
        val html = super.buildHtmlMessage(message)
        return html.replace("<html>", "<html>${buildHtmlHeader()}")
    }

    private fun buildHtmlHeader() = "<head><style>p { color: #a8324a }</style></head>"
}

abstract class EnhancedHtmlGenerator2(private val enhancedHtmlGenerator: EnhancedHtmlGenerator) : EnhancedHtmlGenerator(enhancedHtmlGenerator) {
    override fun buildHtmlMessage(message: String) = enhancedHtmlGenerator.buildHtmlMessage(message)
}

class SimpleEnhancedHtmlGenerator2(enhancedHtmlGenerator: EnhancedHtmlGenerator) : EnhancedHtmlGenerator2(enhancedHtmlGenerator) {
    override fun buildHtmlMessage(message: String): String {
        println("======= Generating HTML... =========")
        return super.buildHtmlMessage(message)
    }
}

fun runDecorator() {
    var htmlGenerator: HtmlGenerator = SimpleHtmlGenerator()
    println(htmlGenerator.buildHtmlMessage("Hello World!"))

    htmlGenerator = SimpleEnhancedHtmlGenerator(htmlGenerator)
    println(htmlGenerator.buildHtmlMessage("Hello World!"))

    htmlGenerator = SimpleEnhancedHtmlGenerator2(htmlGenerator)
    println(htmlGenerator.buildHtmlMessage("Hello World!"))
}