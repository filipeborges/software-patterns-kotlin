package br.com.filipeborges.pattern.structural

import java.lang.ArithmeticException

class Calculator(
    var operand1: Long,
    var operand2: Long
) {
    fun sum() = operand1 + operand2
    fun sub() = operand1 - operand2
    fun mul() = operand1 * operand2
    fun div() = operand1 / operand2

    override fun toString(): String {
        return "Calculator(operand1=$operand1, operand2=$operand2)"
    }
}

// Will convert Calculator interface to a much simpler interface
// Not thread safe
class CalculatorAdapter private constructor() {
    companion object {
        private val calculator = Calculator(0, 0)

        fun sum(operand1: Long, operand2: Long): Long {
            calculator.operand1 = operand1
            calculator.operand2 = operand2
            return calculator.sum()
        }

        fun sub(operand1: Long, operand2: Long): Long {
            calculator.operand1 = operand1
            calculator.operand2 = operand2
            return calculator.sub()
        }

        fun mul(operand1: Long, operand2: Long): Long {
            calculator.operand1 = operand1
            calculator.operand2 = operand2
            return calculator.mul()
        }

        fun div(operand1: Long, operand2: Long): Long {
            calculator.operand1 = operand1
            calculator.operand2 = operand2
            return try { calculator.div() } catch (e: ArithmeticException) { 0 }
        }
    }
}

fun runAdapter() {
    println("===============${CalculatorAdapter.sum(1,1)}===============")
    println("===============${CalculatorAdapter.sub(0,1)}===============")
    println("===============${CalculatorAdapter.mul(4,1)}===============")
    println("===============${CalculatorAdapter.div(1,1)}===============")
}