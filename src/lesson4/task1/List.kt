@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import lesson3.task1.minDivisor
import java.util.*
import kotlin.math.sqrt
import kotlin.random.Random.Default.nextInt

// Урок 4: списки
// Максимальное количество баллов = 12
// Рекомендуемое количество баллов = 8
// Вместе с предыдущими уроками = 24/33

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая (2 балла)
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double = v.fold(0.0) { previousResult, element ->
    sqrt(sqr(previousResult) + sqr(element))
}

/**
 * Простая (2 балла)
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double = list.fold(0.0) { previousResult, element ->
    previousResult + element / list.size
}

/**
 * Средняя (3 балла)
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    val mean2 = mean(list)
    for (i in 0 until list.size) {
        list[i] -= mean2
    }
    return list
}

/**
 * Средняя (3 балла)
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int = TODO()

/**
 * Средняя (3 балла)
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int = TODO()

/**
 * Средняя (3 балла)
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> = TODO()

/**
 * Средняя (3 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    val m = mutableListOf<Int>()
    var number = n
    while (number > 1) {
        val f = minDivisor(number)
        m.add(f)
        number /= f
    }
    return m
}


/**
 * Сложная (4 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String = factorize(n).joinToString(separator = "*")

/**
 * Средняя (3 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    var number = n
    val itog = mutableListOf<Int>()
    if (number == 0) {
        itog.add(0)
    }
    while (number > 0) {
        itog.add(number % base)
        number /= base
    }
    return itog.reversed()
}

/**
 * Сложная (4 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
fun convertToString(n: Int, base: Int): String = TODO()

/**
 * Средняя (3 балла)
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int = TODO()


/**
 * Сложная (4 балла)
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun decimalFromString(str: String, base: Int): Int = TODO()

/**
 * Сложная (5 баллов)
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    var number = n
    var result = ""
    val zip = mapOf(
        1000 to "M", 900 to "CM", 500 to "D", 400 to "CD", 100 to "C", 90 to "XC", 50 to "L", 40 to "XL", 10 to "X",
        9 to "IX", 5 to "V", 4 to "IV", 1 to "I"
    )
    for ((arabic, roman) in zip) {
        while (number >= arabic) {
            result += roman
            number -= arabic
        }
    }
    return result
}


/**
 * Очень сложная (7 баллов)
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    val length = n.toString().length
    val numbers1 = mapOf(
        900 to "девятьсот", 800 to "восемьсот", 700 to "семьсот", 600 to "шестьсот", 500 to "пятьсот",
        400 to "четыреста", 300 to "триста", 200 to "двести", 100 to "сто",
        90 to "девяносто", 80 to "восемьдесят", 70 to "семьдесят", 60 to "шестьдесят", 50 to "пятьдесят",
        40 to "сорок", 30 to "тридцать", 20 to "двадцать", 19 to "девятнадцать",
        18 to "восемнадцать", 17 to "семнадцать", 16 to "шестнадцать", 15 to "пятнадцать",
        14 to "четырнадцать", 13 to "тринадцать", 12 to "двенадцать", 11 to "одиннадцать", 10 to "десять",
        9 to "девять", 8 to "восемь", 7 to "семь", 6 to "шесть", 5 to "пять",
        4 to "четыре", 3 to "три", 2 to "два", 1 to "одна"
    )
    val numbers24 = mapOf( // на 4 разряде 2, 3, 4
        900 to "девятьсот", 800 to "восемьсот", 700 to "семьсот", 600 to "шестьсот", 500 to "пятьсот",
        400 to "четыреста", 300 to "триста", 200 to "двести", 100 to "сто",
        90 to "девяносто", 80 to "восемьдесят", 70 to "семьдесят", 60 to "шестьдесят", 50 to "пятьдесят",
        40 to "сорок", 30 to "тридцать", 20 to "двадцать", 19 to "девятнадцать",
        18 to "восемнадцать", 17 to "семнадцать", 16 to "шестнадцать", 15 to "пятнадцать",
        14 to "четырнадцать", 13 to "тринадцать", 12 to "двенадцать", 11 to "одиннадцать", 10 to "десять",
        9 to "девять", 8 to "восемь", 7 to "семь", 6 to "шесть", 5 to "пять",
        4 to "четыре", 3 to "три", 2 to "две", 1 to "один"
    )
    val numbers59 = mapOf( // на 4 разряде 5..9 либо 0
        900 to "девятьсот", 800 to "восемьсот", 700 to "семьсот", 600 to "шестьсот", 500 to "пятьсот",
        400 to "четыреста", 300 to "триста", 200 to "двести", 100 to "сто",
        90 to "девяносто", 80 to "восемьдесят", 70 to "семьдесят", 60 to "шестьдесят", 50 to "пятьдесят",
        40 to "сорок", 30 to "тридцать", 20 to "двадцать", 19 to "девятнадцать",
        18 to "восемнадцать", 17 to "семнадцать", 16 to "шестнадцать", 15 to "пятнадцать",
        14 to "четырнадцать", 13 to "тринадцать", 12 to "двенадцать", 11 to "одиннадцать", 10 to "десять",
        9 to "девять", 8 to "восемь", 7 to "семь", 6 to "шесть", 5 to "пять",
        4 to "четыре", 3 to "три", 2 to "два", 1 to "один"
    )
    var worker = n
    var result = ""
    if (length / 4 >= 1) {
        val rightBound = if (length % 3 == 0) 3 else length % 3
        worker = n.toString().substring(0, rightBound).toInt()

        if (n % 10000 / 1000 in 2..4 && n % 100000 / 10000 != 1) {
            val pair = getRussianNumberString(numbers24, worker, result, n, rightBound, length, "тысячи")
            result = pair.first
            worker = pair.second

        } else if ((n % 10000 / 1000) == 1 && n % 100000 / 10000 != 1) {
            val pair = getRussianNumberString(numbers1, worker, result, n, rightBound, length, "тысяча")
            result = pair.first
            worker = pair.second

        } else {
            val pair = getRussianNumberString(numbers59, worker, result, n, rightBound, length, "тысяч")
            result = pair.first
            worker = pair.second
        }
    }

    for ((number, russian) in numbers59) {
        while (worker >= number) {
            result += "$russian "
            worker -= number
        }
        if (worker == 0) return result.trimEnd()
    }
    return result.trimEnd()
}
private fun getRussianNumberString(
    numbers1: Map<Int, String>,
    worker: Int,
    result: String,
    n: Int,
    rightBound: Int,
    length: Int,
    suffix: String
): Pair<String, Int> {
    var worker1 = worker
    var result1 = result
    for ((number, russian) in numbers1) {
        while (worker1 >= number) {
            result1 += "$russian "
            worker1 -= number
        }
        if (worker1 == 0) {
            result1 += "$suffix "
            worker1 = n.toString().substring(rightBound, length).toInt()
            break
        }
    }
    return Pair(result1, worker1)
}

