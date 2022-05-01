@file:Suppress("UNUSED_PARAMETER")

package lesson11.task1

import java.text.ParseException

/**
 * Фабричный метод для создания комплексного числа из строки вида x+yi
 */
fun Complex(s: String): Complex {

    var str = s.trim()
    if ("++" in str || "--" in str || "-+" in str || "+-" in str) {
        throw ParseException("String must be in form: x+yi", -1)
    }
    if (!str.endsWith("i")) {
        throw ParseException("String must be terminated by: i", -1)
    }
    try {
        val a = str.split("+", "-", "i").filter { it.isNotBlank() }
        var re = a[0].toDouble()
        if (str[0] == '-') {
            re = -re
        }

        var im = a[1].toDouble()
        str = str.substring(1)
        if ('+' !in str) {
            im = -im
        }
        return Complex(re, im)
    } catch (e: Exception) {
        throw ParseException(e.message, -1)
    }
}

/**
 * Класс "комплексное число".
 *
 * Общая сложность задания -- лёгкая, общая ценность в баллах -- 8.
 * Объект класса -- комплексное число вида x+yi.
 * Про принципы работы с комплексными числами см. статью Википедии "Комплексное число".
 *
 * Аргументы конструктора -- вещественная и мнимая часть числа.
 */
class Complex(val re: Double, val im: Double) {

    /**
     * Конструктор из вещественного числа
     */
    constructor(x: Double) : this(x, 0.0)

    /**
     * Сложение.
     */
    operator fun plus(other: Complex): Complex = Complex(re + other.re, im + other.im)

    /**
     * Смена знака (у обеих частей числа)
     */
    operator fun unaryMinus(): Complex = Complex(-re, -im)

    /**
     * Вычитание
     */
    operator fun minus(other: Complex): Complex = Complex(re - other.re, im - other.im)

    /**
     * Умножение
     */
    operator fun times(other: Complex): Complex =
        // z1=a+bi и z2=c+di является комплексное число z1z2 = (ac-bd)+i(ad+cb)
        Complex(re * other.re - im * other.im, re * other.im + other.re * im)

    /**
     * Деление
     */
    operator fun div(other: Complex): Complex =
        //z1=a+bi и z2=c+di: z1/z2= (a+bi)/ (c+di)= (ac+bd)/ (c2+d2)+ (bc-ad)/ (c2+d2))i
        Complex(
            (re * other.re + im * other.im) / (other.re * other.re + other.im * other.im),
            (im * other.re - re * other.im) / (other.re * other.re + other.im * other.im)
        )

    /**
     * Сравнение на равенство
     */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false
        other as Complex
        return re == other.re && im == other.im
    }

    /**
     * Преобразование в строку
     */
    override fun toString(): String {
        val plus = if (im > 0) {
            "+"
        } else {
            ""
        }
        return re.toString() + plus + im.toString() + "i"
    }

    override fun hashCode(): Int {
        var result = re.hashCode()
        result = 31 * result + im.hashCode()
        return result
    }
}
