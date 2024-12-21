import java.util.*
import kotlin.random.Random

fun main() {
    while (true) {
        print("Введите количество философов >>> ")
        val count = readln().toIntOrNull() ?: continue
        if (count < 1) {
            continue
        }
        val forks = List(count) { _ -> true }.toMutableList()
        val indices = (0..<count).toMutableList()
        while (indices.isNotEmpty()) {
            val index = indices.random()
            indices.remove(index)
            val takeNext = Random.nextBoolean()
            var forkTaken = false
            var takenAsExpected = false
            if (takeNext) {
                if (forks[(index + 1) % count]) {
                    forks[(index + 1) % count] = false
                    forkTaken = true
                    takenAsExpected = true
                } else if (forks[index]) {
                    forks[index] = false
                    forkTaken = true
                    takenAsExpected = false
                }
            } else {
                if (forks[index]) {
                    forks[index] = false
                    forkTaken = true
                    takenAsExpected = true
                } else if (forks[(index + 1) % count]) {
                    forks[(index + 1) % count] = false
                    forkTaken = true
                    takenAsExpected = false
                }
            }

            if (forkTaken) {
                val takenFrom: String = if (takeNext) {
                    if (takenAsExpected) {
                        "справа"
                    } else {
                        "слева"
                    }
                } else {
                    if (takenAsExpected) {
                        "слева"
                    } else {
                        "справа"
                    }
                }
                println("Философ ${index + 1} взял вилку $takenFrom")
            } else {
                println("Философ ${index + 1} не смог взять вилку")
            }
        }
        print("Ещё раз? (y/n) >>> ")
        if (readln().lowercase(Locale.getDefault()) != "y") {
            break;
        }
    }
}