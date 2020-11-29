package br.com.filipeborges.pattern.creational

class Computer private constructor(
    var cpu: String?,
    var motherBoard: String?,
    var ram: Int?,
    var gpu: String?,
    var psu: Int?
) {

    override fun toString(): String {
        return "Computer(cpu=$cpu, motherBoard=$motherBoard, ram=$ram, gpu=$gpu, psu=$psu)"
    }

    class Builder(
        private var cpu: String? = null,
        private var motherBoard: String? = null,
        private var ram: Int? = null,
        private var gpu: String? = null,
        private var psu: Int? = null
    ) {
        fun cpu(cpu: String) = apply { this.cpu = cpu }
        fun motherBoard(motherBoard: String) = apply { this.motherBoard = motherBoard }
        fun ram(ram: Int) = apply { this.ram = ram }
        fun gpu(gpu: String) = apply { this.gpu = gpu }
        fun psu(psu: Int) = apply { this.psu = psu }
        fun build() = Computer(cpu, motherBoard, ram, gpu, psu)
    }

}

fun runBuilder() {
    val computer = Computer.Builder()
        .cpu("AMD FX")
        .motherBoard("ASUS")
        .ram(8)
        .gpu("RX 580")
        .psu(500)
        .build()

    println(computer)
}