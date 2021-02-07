package br.com.filipeborges.pattern.behavioural

// Receiver object
class FileSystemMock(
        private val fileSystem: MutableMap<String, MutableList<String>> = mutableMapOf()
) {

    fun create(path: String) {
        fileSystem[path] = mutableListOf()
    }

    fun write(path: String, data: String) {
        fileSystem[path]?.add(data) ?: logInvalidPath(path)
    }

    fun printContent(path: String) {
        fileSystem[path]?.run {
            println("===== File ($path) content ======")
            forEach { data -> print(data) }
        } ?: logInvalidPath(path)
    }

    private fun logInvalidPath(path: String) = println("======= Path: $path does not exist ======")
}

// Command interface
interface FileSystemOperation {
    fun execute()
}

class CreateFile(
        private val fileSystem: FileSystemMock,
        private val path: String
) : FileSystemOperation {

    override fun execute() {
        fileSystem.create(path)
    }

}

class WriteToFile(
        private val fileSystem: FileSystemMock,
        private val data: String,
        private val path: String
) : FileSystemOperation {

    override fun execute() {
        fileSystem.write(path, data)
    }

}

class PrintFile(
        private val fileSystem: FileSystemMock,
        private val path: String
) : FileSystemOperation {

    override fun execute() {
        fileSystem.printContent(path)
    }

}

// Invoker
class Invoker(
        private val operationsHistory: MutableList<FileSystemOperation> = mutableListOf()
) {
    fun executeOperation(op: FileSystemOperation) {
        operationsHistory.add(op)
        op.execute()
    }
}

// Client
fun runCommand() {
    val fileSystem = FileSystemMock()
    val invoker = Invoker()

    invoker.executeOperation(CreateFile(fileSystem, "/home/user/dummy/file.txt"))
    invoker.executeOperation(WriteToFile(fileSystem, "Hello World[1]\n", "/home/user/dummy/file.txt"))
    invoker.executeOperation(WriteToFile(fileSystem, "Hello World[2]\n", "/home/user/dummy/file.txt"))
    invoker.executeOperation(WriteToFile(fileSystem, "Hello World[3]\n", "/home/user/dummy/file.txt"))
    invoker.executeOperation(PrintFile(fileSystem, "/home/user/dummy/file.txt"))

    invoker.executeOperation(CreateFile(fileSystem, "/home/user/dummy/A.txt"))
    invoker.executeOperation(WriteToFile(fileSystem, "Hello World[1] from A\n", "/home/user/dummy/A.txt"))
    invoker.executeOperation(WriteToFile(fileSystem, "Hello World[2] from A\n", "/home/user/dummy/A.txt"))
    invoker.executeOperation(WriteToFile(fileSystem, "Hello World[3] from A\n", "/home/user/dummy/A.txt"))
    invoker.executeOperation(PrintFile(fileSystem, "/home/user/dummy/A.txt"))
}
