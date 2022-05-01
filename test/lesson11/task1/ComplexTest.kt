package lesson11.task1

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Tag
import java.text.ParseException

internal class ComplexTest {

    private fun assertApproxEquals(expected: Complex, actual: Complex, eps: Double) {
        assertEquals(expected.re, actual.re, eps)
        assertEquals(expected.im, actual.im, eps)
    }

    @Test
    @Tag("2")
    fun plus() {
        assertApproxEquals(Complex("4-2i"), Complex("1+2i") + Complex("3-4i"), 1e-10)
    }

    @Test
    @Tag("2")
    operator fun unaryMinus() {
        assertApproxEquals(Complex(1.0, -2.0), -Complex(-1.0, 2.0), 1e-10)
    }

    @Test
    @Tag("2")
    fun minus() {
        assertApproxEquals(Complex("2-6i"), Complex("3-4i") - Complex("1+2i"), 1e-10)
    }

    @Test
    @Tag("4")
    fun times() {
        assertApproxEquals(Complex("11+2i"), Complex("1+2i") * Complex("3-4i"), 1e-10)
    }

    @Test
    @Tag("4")
    fun div() {
        assertApproxEquals(Complex("1+2i"), Complex("11+2i") / Complex("3-4i"), 1e-10)
    }

    @Test
    @Tag("2")
    fun equals() {
        assertApproxEquals(Complex(1.0, 2.0), Complex("1+2i"), 1e-12)
        assertApproxEquals(Complex(1.0, 0.0), Complex(1.0), 1e-12)
    }

    @Test
    fun testComplex() {
        assertApproxEquals(Complex(1.0, -2.0), Complex("+1-2i"), 1e-12)
        assertApproxEquals(Complex(-1.0, -2.0), Complex("-1-2i"), 1e-12)
        assertApproxEquals(Complex(1.0, 2.0), Complex("+1+2i"), 1e-12)
        assertApproxEquals(Complex(-1.0, 2.0), Complex("-1+2i"), 1e-12)
        assertThrows(ParseException::class.java) { Complex("") }
        assertThrows(ParseException::class.java) { Complex("i") }
        assertThrows(ParseException::class.java) { Complex("1i") }
        assertThrows(ParseException::class.java) { Complex("1") }
        assertThrows(ParseException::class.java) { Complex("1++2i") }
        assertThrows(ParseException::class.java) { Complex("1+2i+") }
        assertThrows(ParseException::class.java) { Complex("#1+2i") }
        assertThrows(ParseException::class.java) { Complex("1+ii") }
        assertThrows(ParseException::class.java) { Complex("1-i") }
    }

    @Test
    fun testToString() {
        assertEquals("1.0+2.0i", Complex(1.0, 2.0).toString())
        assertEquals("-1.0-2.0i", Complex(-1.0, -2.0).toString())
        assertEquals("-1.0+2.0i", Complex(-1.0, 2.0).toString())
        assertEquals("1.0-2.0i", Complex(1.0, -2.0).toString())
    }
}