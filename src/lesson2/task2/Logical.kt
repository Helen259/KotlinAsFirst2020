@file:Suppress("UNUSED_PARAMETER")

package lesson2.task2

import lesson1.task1.sqr
import kotlin.math.abs
import kotlin.math.sqrt

/**
 * Пример
 *
 * Лежит ли точка (x, y) внутри окружности с центром в (x0, y0) и радиусом r?
 */
fun pointInsideCircle(x: Double, y: Double, x0: Double, y0: Double, r: Double) =
    sqr(x - x0) + sqr(y - y0) <= sqr(r)

/**
 * Простая (2 балла)
 *
 * Четырехзначное число назовем счастливым, если сумма первых двух ее цифр равна сумме двух последних.
 * Определить, счастливое ли заданное число, вернуть true, если это так.
 */
fun isNumberHappy(number: Int): Boolean =
    ((number / 1000 + number / 100 % 10) == (number / 10 % 10 + number % 10))

/**
 * Простая (2 балла)
 *
 * На шахматной доске стоят два ферзя (ферзь бьет по вертикали, горизонтали и диагоналям).
 * Определить, угрожают ли они друг другу. Вернуть true, если угрожают.
 * Считать, что ферзи не могут загораживать друг друга.
 */
fun queenThreatens(x1: Int, y1: Int, x2: Int, y2: Int): Boolean =
    ((x1 == x2) || (y1 == y2) || (abs(x1 - x2) == abs(y1 - y2)))


/**
 * Простая (2 балла)
 *
 * Дан номер месяца (от 1 до 12 включительно) и год (положительный).
 * Вернуть число дней в этом месяце этого года по григорианскому календарю.
 */
fun daysInMonth(month: Int, year: Int): Int {
    if ((month == 2) && (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0))) return 29
    return when (month) {
        1, 3, 5, 7, 8, 10, 12 -> 31
        4, 6, 9, 11 -> 30
        else -> 28
    }
}

/**
 * Простая (2 балла)
 *
 * Проверить, лежит ли окружность с центром в (x1, y1) и радиусом r1 целиком внутри
 * окружности с центром в (x2, y2) и радиусом r2.
 * Вернуть true, если утверждение верно
 */
fun circleInside(
    x1: Double, y1: Double, r1: Double,
    x2: Double, y2: Double, r2: Double
): Boolean =
    sqrt(sqr(x2 - x1) + sqr(y2 - y1)) <= r2 - r1


/**
 * Средняя (3 балла)
 *
 * Определить, пройдет ли кирпич со сторонами а, b, c сквозь прямоугольное отверстие в стене со сторонами r и s.
 * Стороны отверстия должны быть параллельны граням кирпича.
 * Считать, что совпадения длин сторон достаточно для прохождения кирпича, т.е., например,
 * кирпич 4 х 4 х 4 пройдёт через отверстие 4 х 4.
 * Вернуть true, если кирпич пройдёт
 */
fun brickPasses(a: Int, b: Int, c: Int, r: Int, s: Int): Boolean {
    val minH: Int
    val maxH: Int
    val avgS: Int
    val minS: Int
    if (r > s) {
        minH = s
        maxH = r
    } else {
        minH = r
        maxH = s
    }
    if (a < b && a < c) {
        minS = a
        avgS = if (b > c) c else b
    } else if (b < a && b < c) {
        minS = b
        avgS = if (a > c) c else a
    } else {
        minS = c
        avgS = if (a > b) b else a
    }
    return (maxH >= avgS && minH >= minS)
}

