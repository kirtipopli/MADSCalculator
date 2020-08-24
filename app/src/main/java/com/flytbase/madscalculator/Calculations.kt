package com.flytbase.madscalculator

import android.content.Context
import android.util.Log
import android.widget.Toast
import java.util.*


/**
 * Created by mihir on 18-08-2018.
 */
class Calculations(private val ctx: Context) {
    private val binaryOperators =
        ArrayList<String>()
    var numbers = ArrayList<String>()
    var answer: String? = null

    /**
     * currentStatus = {binary, num};
     */
    var currentStatus = "null"
    fun clear(): Boolean {
        try {
            numbers.clear()
            answer = "0"
            calc(numbers)
        } catch (e: Exception) {
            calc(numbers)
            return false
        }
        currentStatus = "null"
        return true
    }

    fun ce(): Boolean {
        return try {
            when (currentStatus) {
                "num" -> numbers.removeAt(numbers.size - 1)
                "binary" -> numbers.removeAt(numbers.size - 1)
            }
            if (numbers.size == 0) {
                currentStatus = "null"
                return true
            }
            currentStatus = getStatus(numbers.size - 1)
            calc(numbers)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun bs(): Boolean {
        if (currentStatus == "num") {
            try {
                numbers[numbers.size - 1] = formatNumber(
                    numbers[numbers.size - 1]
                        .substring(0, numbers[numbers.size - 1].length - 1)
                ).toString()
            } catch (e: Exception) {
                numbers[numbers.size - 1] = "0"
                return false
            }
        }
        calc(numbers)
        return true
    }

    fun numberClicked(number: String): Boolean {
        when (currentStatus) {
            "null" -> numbers.add(number)
            "num" -> numbers[numbers.size - 1] =
                formatNumber(numbers[numbers.size - 1] + number).toString()
            "binary" -> numbers.add(number)
            else -> Toast.makeText(
                ctx,
                "Error while numberClicked(), currentStatus = $currentStatus",
                Toast.LENGTH_SHORT
            ).show()
        }
        currentStatus = "num"
        calc(numbers)
        return true
    }

    val currentNumber: String
        get() = if (currentStatus != "num") {
            Log.d("calc", "current Number returned : 0")
            "0"
        } else {
            Log.d("calc", "current Number returned : " + numbers[numbers.size - 1])
            numbers[numbers.size - 1]
        }

    fun evaluateAnswer(): Boolean {
        answer = doMADS(numbers)
        calc(numbers)
        return true
    }

    fun calc(numbers: ArrayList<String>): String {
        var num = ""
        for (number in numbers) {
            num += "$number "
        }
        Log.d("calc", num)
        return num
    }

    private fun doMADS(numbers: ArrayList<String>): String {
        while (numbers.size != 1) {
            Log.d("calc", "doMADS:")
            calc(numbers)

            if (contains(binaryOperators, numbers)) {
                var i = 0
                while (i < binaryOperators.size) {
                    if (numbers.contains(binaryOperators[i])) {
                        for (j in numbers.indices) {
                            if (numbers[j] == binaryOperators[i]) {
                                val ans = evaluateBinary(
                                    numbers[j - 1],
                                    numbers[j],
                                    numbers[j + 1]
                                )
                                numbers.removeAt(j - 1)
                                numbers.removeAt(j - 1)
                                numbers[j - 1] = ans
                                i = 0
                                break
                            }
                        }
                    }
                    i++
                }
            }
        }
        currentStatus = "num"
        return formatNumber(numbers[0]).toString()
    }

    private fun contains(
        source: ArrayList<String>,
        target: ArrayList<String>
    ): Boolean {
        for (`val` in target) {
            if (source.contains(`val`)) {
                return true
            }
        }
        return false
    }

    private fun getStatus(index: Int): String {
        return if (binaryOperators.contains(numbers[index])) {
            "binary"
        } else if (numbers[index] == "0") {
            if (numbers.size - 1 == index) {
                numbers.removeAt(index)
            }
            "null"
        } else {
            "num"
        }
    }

    private fun formatNumber(number: String): String? {
        var number: String? = number
        Log.d("calc", "Format Number : $number")
        return if (number == null || number == "" || number.isEmpty()) {
            "0"
        } else try {
            if (number.toDouble() == number.split("\\.").toTypedArray()[0].toLong()
                    .toDouble()
            ) {
                number.split("\\.").toTypedArray()[0].toLong().toString()
            } else {
                number.toDouble().toString()
            }
        } catch (nfe: NumberFormatException) {
            try {
                number.toDouble().toString()
            } catch (nfe2: NumberFormatException) {
                try {
                    number = number.substring(0, number.length - 1)
                    number.toDouble().toString()
                } catch (e: Exception) {
                    number
                }
            }
        }
    }

    private fun evaluateBinary(
        number1: String,
        operation: String,
        number2: String
    ): String {
        Log.d("calc", "binary : $number1, $operation, $number2")
        var answer = "error"
        when (operation) {
            "*" -> answer = multiplication(number1, number2)
            "+" -> answer = addition(number1, number2)
            "/" -> answer = division(number1, number2)
            "-" -> answer = subtraction(number1, number2)
            else -> Toast.makeText(
                ctx,
                "error in evaluating Binary operation : $operation",
                Toast.LENGTH_SHORT
            ).show()
        }
        return try {
            formatNumber(answer).toString()
        } catch (e: Exception) {
            answer
        }
    }

    private fun multiplication(num1: String, num2: String): String {
        return (num1.toDouble() * num2.toDouble()).toString()
    }

    private fun division(num1: String, num2: String): String {
        return (num1.toDouble() / num2.toDouble()).toString()
    }

    private fun addition(num1: String, num2: String): String {
        return (num1.toDouble() + num2.toDouble()).toString()
    }

    private fun subtraction(num1: String, num2: String): String {
        return (num1.toDouble() - num2.toDouble()).toString()
    }

    init {
        /*
         * binaryOperators in order...
         */
        binaryOperators.add("*")
        binaryOperators.add("+")
        binaryOperators.add("/")
        binaryOperators.add("-")
    }

    fun operatorClicked(operator: String): Boolean {
        Log.d("calc", "Operator : $operator")

        if (binaryOperators.contains(operator)) {
            when (currentStatus) {
                "null" -> {
                    numbers.add("0")
                    numbers.add(operator)
                    currentStatus = "binary"
                }
                "num" -> {
                    numbers.add(operator)
                    currentStatus = "binary"
                }
                "binary" -> {
                    numbers[numbers.size - 1] = operator
                    currentStatus = "binary"
                }
                else -> Toast.makeText(
                    ctx,
                    "Error while binary, currentStatus = $currentStatus",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        return false
    }

}