package br.com.filipeborges.pattern.creational

import java.time.LocalDateTime
import java.time.LocalTime

// Abstract product
interface Button {
    fun render(): String
}

// Abstract factory which creates abstract products
interface AbstractFactory {
    fun createButton(): Button
}

// Concrete product 1
class WindowsButton: Button {
    override fun render() = "WindowsButton - Generated in ${LocalDateTime.now().plusDays(1)}"
}

// Concrete factory of product 1
class WindowsButtonFactory: AbstractFactory {
    override fun createButton() = WindowsButton()
}

// Concrete product 2
class LinuxButton: Button {
    override fun render() = "LinuxButton - Generated in ${LocalDateTime.now()}"
}

// Concrete factory of product 2
class LinuxButtonFactory: AbstractFactory {
    override fun createButton() = LinuxButton()
}

fun runAbstractFactory() {
    val factory = if (System.currentTimeMillis() % 2L == 0L) LinuxButtonFactory() else WindowsButtonFactory()
    val button = factory.createButton()
    println("=========== CREATED BUTTON ===============")
    println(button.render())
}
