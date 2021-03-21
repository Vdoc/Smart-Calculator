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
        var result = 0
        for (i in 0 until args.size) {
            result += args[i]
        }
        println(result)
        args.clear()
    }

    fun readInput() {
        val scanner = Scanner(System.`in`)
        var input = scanner.nextLine()
        while (input == "") input = scanner.nextLine()
        when (input) {
            "/exit" -> {
                exit = true
                return
            }
            "/help" -> {
                println("The program calculates the sum of numbers")
                readInput()
                return
            }
        }
        val ints = input.split(" ")
        for (i in ints) {
            args.add(i.toInt())
        }
//        args.add(ints[0].toInt())
//        if (ints.size > 1)
//        args.add(ints[1].toInt())
//        else args.add(0)

        printResults()
    }
}


