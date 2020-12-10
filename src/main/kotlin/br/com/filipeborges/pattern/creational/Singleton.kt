package br.com.filipeborges.pattern.creational

// More traditional Singleton style
class Singleton private constructor() {
    companion object {
        private var instance: Singleton? = null

        fun getInstance(): Singleton {
            if (instance == null) instance = Singleton()
            return instance!!
        }
    }
}

// Kotlin singleton style
object SingletonKotlinStyle {
    fun print() = println("========= SingletonKotlinStyle print! ===========")
}


fun runSingleton() {
    println("======== Singleton instance: ${Singleton.getInstance()} ============")
    println("======== Singleton instance: ${Singleton.getInstance()} ============")
    println("======== Singleton instance: ${Singleton.getInstance()} ============")
    println("======== Singleton instance: $SingletonKotlinStyle ============")
    println("======== Singleton instance: $SingletonKotlinStyle ============")
    println("======== Singleton instance: $SingletonKotlinStyle ============")
    SingletonKotlinStyle.print()
}