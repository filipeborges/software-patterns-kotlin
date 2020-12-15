package br.com.filipeborges.pattern.structural

import java.math.BigDecimal

// Common interface
interface Account {
    fun printInfo()
}

class SingleBankAccount(
    var name: String,
    var number: String,
    var balance: BigDecimal
) : Account {

    private fun printId() = println("$name-$number")

    private fun printStatus() = println(balance.compareTo(BigDecimal.ZERO) > 0)

    override fun printInfo() {
        printId()
        printStatus()
    }

}

class Bank : Account {
    private var accounts = ArrayList<SingleBankAccount>()

    fun addAccount(value: SingleBankAccount) = accounts.add(value)

    override fun printInfo() = accounts.forEach { it.printInfo() }
}

fun runComposite() {
    val account1 = SingleBankAccount("Name 1", "123456", BigDecimal("400.43"))
    val account2 = SingleBankAccount("Name 2", "222222", BigDecimal("-123.00"))
    val bank = Bank()

    bank.addAccount(account1)
    bank.addAccount(account2)

    // Print accounts info
    bank.printInfo()
}