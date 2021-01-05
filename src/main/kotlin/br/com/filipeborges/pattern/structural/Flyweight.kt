package br.com.filipeborges.pattern.structural

// this is the flyweight object (will store some immutable state, that is shared)
// variable state is received by method 'renderCharacter()'
class Character(
    val code: Int,
    val key: Char
) {

    fun renderCharacter(x: Int, y: Int, size: Int) {
        println("======= Rendering character - code: $code, key: $key ==============")
        println("**** Passed data: x: $x, y: $y, size: $size ****")
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Character

        if (code != other.code) return false
        if (key != other.key) return false

        return true
    }

    override fun hashCode(): Int {
        var result = code
        result = 31 * result + key.hashCode()
        return result
    }

}

class CharacterBuilder {
    private companion object {
        val characters = mutableListOf<Character>()
    }

    fun getCharacter(key: Char): Character {
        var character = characters.find { it.key == key }
        return if (character != null) character else {
            println("==== Creating character: $key =====")
            character = Character(key.toInt(), key)
            characters.add(character)
            character
        }
    }
}

fun runFlyweight() {
    val characterBuilder = CharacterBuilder()
    val charSize = 14

    characterBuilder.getCharacter('A').renderCharacter(0, 0, charSize) // first character
    characterBuilder.getCharacter('A').renderCharacter(1, 0, charSize) // second character
    characterBuilder.getCharacter('B').renderCharacter(2, 0, charSize) // third character
    characterBuilder.getCharacter('c').renderCharacter(3, 0, charSize) // fourth character
}