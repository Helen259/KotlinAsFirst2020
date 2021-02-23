@file:Suppress("UNUSED_PARAMETER")

package lesson11.task1

import kotlin.math.max
import kotlin.math.pow

/**
 * Класс "полином с вещественными коэффициентами".
 *
 * Общая сложность задания -- средняя, общая ценность в баллах -- 16.
 * Объект класса -- полином от одной переменной (x) вида 7x^4+3x^3-6x^2+x-8.
 * Количество слагаемых неограничено.
 *
 * Полиномы можно складывать -- (x^2+3x+2) + (x^3-2x^2-x+4) = x^3-x^2+2x+6,
 * вычитать -- (x^3-2x^2-x+4) - (x^2+3x+2) = x^3-3x^2-4x+2,
 * умножать -- (x^2+3x+2) * (x^3-2x^2-x+4) = x^5+x^4-5x^3-3x^2+10x+8,
 * делить с остатком -- (x^3-2x^2-x+4) / (x^2+3x+2) = x-5, остаток 12x+16
 * вычислять значение при заданном x: при x=5 (x^2+3x+2) = 42.
 *
 * В конструктор полинома передаются его коэффициенты, начиная со старшего.
 * Нули в середине и в конце пропускаться не должны, например: x^3+2x+1 --> Polynom(1.0, 2.0, 0.0, 1.0)
 * Старшие коэффициенты, равные нулю, игнорировать, например Polynom(0.0, 0.0, 5.0, 3.0) соответствует 5x+3
 */
class Polynom(vararg coeffs: Double) {
    private val list = if (coeffs.all { it == 0.0 }) listOf(0.0) else coeffs.toMutableList().dropWhile { it == 0.0 }

    constructor(list: List<Double>) : this(*list.toDoubleArray())

    /**
     * Геттер: вернуть значение коэффициента при x^i
     */

    fun coeff(i: Int): Double = list[i]

    /**
     * Расчёт значения при заданном x
     */
    fun getValue(x: Double): Double {
        var value = 0.0
        for (i in list.indices) {
            value += list[i] * x.pow(list.size - i - 1)
        }
        return value
    }

    /**
     * Степень (максимальная степень x при ненулевом слагаемом, например 2 для x^2+x+1).
     *
     * Степень полинома с нулевыми коэффициентами считать равной 0.
     * Слагаемые с нулевыми коэффициентами игнорировать, т.е.
     * степень 0x^2+0x+2 также равна 0.
     */
    fun degree(): Int {
        for (i in list.indices) {
            return list.size - i - 1
        }
        return 0
    }

    /**
     * Сложение
     */
    operator fun plus(other: Polynom): Polynom {
        val list1 = this.list.reversed()
        val list2 = other.list.reversed()
        val res = MutableList(max(list.size, other.list.size)) { 0.0 }
        for (i in res.indices) {
            if (i in list2.indices)
                res[i] += list2[i]
            if (i in list1.indices)
                res[i] += list1[i]
        }
        val res2 = res.reversed()
        return Polynom(res2)
    }


    /**
     * Смена знака (при всех слагаемых)
     */
    operator fun unaryMinus(): Polynom = Polynom(list.map { -it })


    /**
     * Вычитание
     */
    operator fun minus(other: Polynom): Polynom = this.plus(other.unaryMinus())


    /**
     * Умножение
     */
    operator fun times(other: Polynom): Polynom {
        val res = MutableList(this.degree() + other.degree() + 1) { 0.0 }
        this.list.forEachIndexed { ithis, value1 ->
            other.list.forEachIndexed { iother, value2 ->
                res[ithis + iother] += value1 * value2
            }
        }
        return Polynom(res)
    }

    /**
     * Деление
     *
     * Про операции деления и взятия остатка см. статью Википедии
     * "Деление многочленов столбиком". Основные свойства:
     *
     * Если A / B = C и A % B = D, то A = B * C + D и степень D меньше степени B
     */
    operator fun div(other: Polynom): Polynom {
        val res = mutableListOf<Double>()
        var divisible = this.list
        when {
            this.degree() < other.degree() -> throw IllegalArgumentException()
            other == Polynom(0.0) -> throw IllegalArgumentException()
        }
        while (divisible.size >= other.list.size) {
            val c = divisible.first() / other.list.first()
            res.add(c)
            val m = other.list.map { it * -c }
            val list = List(divisible.size - other.list.size) { 0.0 }
            val list2 = m + list
            divisible = divisible.zip(list2).map { it.first + it.second }.toMutableList()
            divisible.removeAt(0)
        }
        return Polynom(res)
    }


    /**
     * Взятие остатка
     */
    operator fun rem(other: Polynom): Polynom {
        when {
            this.degree() < other.degree() -> throw IllegalArgumentException()
            other == Polynom(0.0) -> throw IllegalArgumentException()
        }
        return this - (this / other) * other
    }

    /* {val res = mutableListOf<Double>()
        var divisible = this.list
        when {
            this.degree() < other.degree() -> throw IllegalArgumentException()
            other == Polynom(0.0) -> throw IllegalArgumentException()
        }
        while (divisible.size >= other.list.size) {
            val c = divisible.first() / other.list.first()
            res.add(c)
            val m = other.list.map { it * -c }
            val list = List(divisible.size - other.list.size) { 0.0 }
            val list2 = m + list
            divisible = divisible.zip(list2).map { it.first + it.second }.toMutableList()
            divisible.removeAt(0)
        }
        return Polynom(divisible)
    }*/


    /**
     * Сравнение на равенство
     */
    override fun equals(other: Any?): Boolean = other is Polynom && this.list == other.list


    /**
     * Получение хеш-кода
     */
    override fun hashCode(): Int = list.hashCode()
}

