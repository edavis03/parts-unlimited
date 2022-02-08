package mil.army.futures.asitemplate

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PartsUnlimitedApplication

fun main(args: Array<String>) {
    runApplication<PartsUnlimitedApplication>(*args)
}