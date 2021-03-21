package calculator

import java.util.*

fun main() {
    val calculator = Calculator()

    while (!calculator.exit) {
        calculator.readInput()
    }
    println("Bye!")
}

class Calculator {
    var exit: Boolean = false
    var args: MutableList<Int> = mutableListOf<Int>()
//    val list: MutableList<List<String>> = mutableListOf<List<String>>()

    fun printResults() {
        println(args[0] + args[1])
        args.clear()
    }

    fun readInput() {
        val scanner = Scanner(System.`in`)
        var input = scanner.nextLine()
        while (input == "") input = scanner.nextLine()
        if (input == "/exit") {
            exit = true
            return
        }
        val ints = input.split(" ")
        args.add(ints[0].toInt())
        if (ints.size > 1)
        args.add(ints[1].toInt())
        else args.add(0)

        printResults()
    }
}


