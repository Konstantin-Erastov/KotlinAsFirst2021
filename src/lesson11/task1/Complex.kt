@file:Suppress("UNUSED_PARAMETER")

package lesson11.task1

/**
 * Фабричный метод для создания комплексного числа из строки вида x+yi
 */
fun Complex(s: String): Complex {

    val a = s.split("+", "-", "i").filter { it.isNotBlank() }
    var re = a[0].toDouble()
    if (s[0] == '-') {
        re = -re
    }
    var im = a[1].toDouble()
    if ('+' !in s) {
        im = -im
    }
    return Complex(re, im)
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
    operator fun times(other: Complex): Complex {
        // z1=a+bi и z2=c+di является комплексное число z1z2 = (ac-bd)+i(ad+cb)
        val a = re
        val b = im
        val c = other.re
        val d = other.im
        return Complex(a * c - b * d, a * d + c * b)
    }

    /**
     * Деление
     */
    operator fun div(other: Complex): Complex {
        //z1=a+bi и z2=c+di: z1/z2= (a+bi)/ (c+di)= (ac+bd)/ (c2+d2)+ (bc-ad)/ (c2+d2))i
        val a = re
        val b = im
        val c = other.re
        val d = other.im
        return Complex((a * c + b * d) / (c * c + d * d), (b * c - a * d) / (c * c + d * d))
    }

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
    override fun toString(): String = re.toString() + im.toString() + "i"
}
