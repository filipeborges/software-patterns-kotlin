package br.com.filipeborges.pattern.structural

import java.math.BigDecimal

interface AccountManagement {
    fun updateAccountBalance(clientId: String, amount: BigDecimal)
}

class AccountData(
    private val map: MutableMap<String, BigDecimal> = mutableMapOf<String, BigDecimal>()
) : AccountManagement {

    override fun updateAccountBalance(clientId: String, amount: BigDecimal) {
        map[clientId] = amount
    }
}

class AccountManagementProxy(
    private val accountData: AccountData = AccountData(),
    private val operatorName: String
) : AccountManagement {

    override fun updateAccountBalance(clientId: String, amount: BigDecimal) {
        logOperation(clientId, amount)
        accountData.updateAccountBalance(clientId, amount)
    }

    private fun logOperation(clientId: String, amount: BigDecimal) {
        println("========= User perfoming operation: $operatorName ============")
        println("========= clientId: $clientId, amount: $amount ==========")
    }
}

fun buildAccountManagement(operatorName: String): AccountManagement = AccountManagementProxy(AccountData(), operatorName)

fun runProxy() {
    val accountManagement = buildAccountManagement("hello_world")
    accountManagement.updateAccountBalance("123456", BigDecimal("555.88"))
}
