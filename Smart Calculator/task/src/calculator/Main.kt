package calculator

import java.util.*

//var test = true
var test = false

fun main() {
    val calculator = Calculator()

    while (!calculator.exit) {
        calculator.clear()
        try {
            calculator.readInput()
        } catch (e: NumberFormatException) {
            println("Invalid expression")
        }
    }
    println("Bye!")
}

class Calculator {
    var exit: Boolean = false
//    var list: MutableList<Int> = mutableListOf<Int>()
    var list: MutableList<String> = mutableListOf<String>()
//    var args: MutableList<Char> = mutableListOf<Char>()
//    val list: MutableList<List<String>> = mutableListOf<List<String>>()

    var input = ""
    var lastIndex = 0
    var counter = 0
    var result = 0

    fun clear() {
        input = ""
        counter = 0
        result = 0
        list = mutableListOf<String>()
        var exit: Boolean = false
    }

    fun readInput() {
        val scanner = Scanner(System.`in`)
        input = scanner.nextLine()
        while (input == "") input = scanner.nextLine()
        if (input[0] == '/') {
            when (input) {
                "/exit" -> {
                    exit = true
//                    return
                }
                "/help" -> {
                    println("The program calculates the sum of numbers")
                    readInput()
//                    return
                }
                else -> {
                    println("Unknown command.")
                    readInput()
//                    return
                }
            }
            return
        }
//        val ints = input.split("")
//        for (i in ints) {
//            args.add(i.toInt())
//        }

        eliminateDublicates()

        if (test) {
            println("---------")
            for (i in list) {
                println(i)
            }
            println("---------")
        }

        calculate()
        println(result)

        if (test) {
            println("---------")
            for (i in list) {
                println(i)
            }
            println("---------")
        }
    }



    private fun eliminateDublicates() {
        var ch = 'd'
        lastIndex = input.length - 1
        if (input[lastIndex] == '+') throw NumberFormatException()
        if (input[lastIndex] == '-') throw NumberFormatException()

        while (counter < input.length) {

            /**/
            if (test) println("i = " + counter + " =" + input[counter] + "=")
            /**/

            when {
                input[counter].isDigit() -> {
                    ch = 'd'
                    if (counter + 1 < input.length) {
                        list.add(input[counter] + checkNext(counter + 1, 'd'))
                    } else list.add(input[counter].toString())
                }
                input[counter] == '+' -> {
                    ch = '+'
                    if (counter + 1 < input.length) {
                        checkNext(counter + 1, '+')
                    }
                    list.add("+")
                }
                input[counter] == '-' -> {
                    ch = '-'
                    var minuses = "-"
                    if (counter + 1 < input.length)
                        minuses += checkNext(counter + 1, '-')
                    if (minuses.length % 2 == 0)
                        list.add("+")
                    else
                        list.add("-")
                }
                input[counter] == ' ' -> {}
                else -> {
                    throw NumberFormatException()
                }
            }
            counter++
        }
        counter = 0
    }

    private fun checkNext(i: Int, x: Char): String {
        when (x) {
            'd' -> {
                if (input[i].isDigit()) {
                    counter++

                    /**/
                    if (test) println("d i = " + i + " =" + input[counter] + "=")
                    /**/

                    return input[i] + if (counter + 1 < input.length) checkNext(i + 1, 'd') else ""
                }
            }
            '+' -> {
                if (input[i] == '+') {
                    counter++

                    /**/
                    if (test) println("+ i = " + i + " =" + input[counter] + "=")
                    /**/

                    return "+" + if (counter + 1 < input.length) checkNext(i + 1, '+') else ""
                }
            }
            '-' -> {
                if (input[i] == '-') {
                    counter++

                    /**/
                    if (test) println("- i = " + i + " =" + input[counter] + "=")
                    /**/

                    return '-' + if (counter + 1 < input.length) checkNext(i + 1, '-') else ""
                }
            }
//            else -> return ""
        }
        return ""
    }

    private fun calculate() {
        counter = 0
        var st: String = ""
        var operator = "+"

//        for (i in list) {
//            if (i.)
//        }
        var digit = false

        // TODO попробуй перый символ 0 добавить
        if (list.get(0) == "-") {
            result = 0 - list.get(1).toInt()
            counter += 2
            digit = true
        }
        //
        if (list.get(0) == "+") {
            result = list.get(1).toInt()
            counter += 2
            digit = true
        }

        while (counter < list.size) {
            st = list.get(counter)
            when {
                st[0].isDigit() -> {
                    if (digit) throw NumberFormatException()
                    when (operator) {
                        "+" -> result = result + st.toInt()
                        "-" -> result = result - st.toInt()
                        else -> result = st.toInt()
                    }
                    digit = true
                }
                st[0] == '+' -> {
                    if (!digit) throw NumberFormatException()
                    operator = "+"
                    digit = false
                }
                st[0] == '-' -> {
                    if (!digit) throw NumberFormatException()
                    operator = "-"
                    digit = false
                }
                else -> continue
//                else -> throw NumberFormatException()
            }
            counter++
        }
    }
}


