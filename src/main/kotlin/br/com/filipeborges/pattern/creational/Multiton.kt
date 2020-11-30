package br.com.filipeborges.pattern.creational

// this pattern can be thought as registry of singletons
// Instance creation will be controlled by getInstance() method

class Job private constructor(var jobName: String) {

    companion object {
        private val instances: HashMap<String, Job> = HashMap()
        fun getInstance(jobName: String) = instances.getOrPut(jobName.toLowerCase(), { Job(jobName.toLowerCase()) })
    }

}

fun runMultiton() {
    println(Job.getInstance("Engineer"))
    println(Job.getInstance("engineer"))
    println(Job.getInstance("boxer"))
    println(Job.getInstance("BOXER"))
}