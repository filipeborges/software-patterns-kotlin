package br.com.filipeborges.pattern.structural

interface DiskOperation {
    fun save(path: String, data: Any)
}

interface NetworkOperation {
    fun send(host: String, port: Int, data: Any)
}

class MockDiskOperation : DiskOperation {
    override fun save(path: String, data: Any) = println("=========== Mock - Saved data on path: $path =============")
}

class MockNetworkOperation : NetworkOperation {
    override fun send(host: String, port: Int, data: Any) = println("=========== Mock - Sended data to: $host:$port =============")
}

class Validator {
    companion object {
        fun isDataValid(data: Any) = data is String
    }
}

// this is the facade
class DataManager(
    private val diskOperation: DiskOperation,
    private val networkOperation: NetworkOperation,
    private val isDataValid: (data: Any) -> Boolean
) {

    private companion object {
        const val DEFAULT_DISK_PATH = "/opt/saved_data.sav"
        const val DEFAULT_HOST = "localhost"
        const val DEFAULT_PORT = 35999
    }

    fun save(data: Any) {
        if (isDataValid(data)) {
            diskOperation.save(DEFAULT_DISK_PATH, data)
            networkOperation.send(DEFAULT_HOST, DEFAULT_PORT, data)
        }
    }
}

fun runFacade() {
    val dataManager = DataManager(
        MockDiskOperation(),
        MockNetworkOperation(),
        Validator.Companion::isDataValid
    )

    val validData = "This is a valid data"
    val invalidData = 9999

    dataManager.save(validData)
    dataManager.save(invalidData)
}