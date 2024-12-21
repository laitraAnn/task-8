import kotlin.random.Random

data class Philosopher(val id: Int) {
    var isEating: Boolean = false
}

fun main() {
    while (true) {
        print("Введите количество философов >>> ")
        val count = readln().toIntOrNull() ?: continue
        if (count < 1) {
            continue
        }

        val philosophers = List(count) { Philosopher(it + 1) }
        val forks = MutableList(count) { true } // true означает, что вилка свободна

        // Перемешиваем философов для случайного выбора
        val indices = (0 until count).shuffled()

        for (index in indices) {
            val leftFork = (index + count - 1) % count
            val rightFork = (index + 1) % count

            // Философ пытается взять вилки
            if (forks[leftFork] && forks[rightFork]) {
                // Если обе вилки свободны, философ берет их
                forks[leftFork] = false
                forks[rightFork] = false
                philosophers[index].isEating = true
            }
        }

        // Выводим результат
        println("Результат:")
        philosophers.forEach { philosopher ->
            if (philosopher.isEating) {
                println("Философ ${philosopher.id} обедает.")
            } else {
                println("Философ ${philosopher.id} размышляет.")
            }
        }

        print("Ещё раз? (y/n) >>> ")
        if (readln().lowercase() != "y") {
            break
        }
    }
}