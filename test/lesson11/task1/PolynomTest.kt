package lesson11.task1

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Tag

class PolynomTest {

    private fun assertApproxEquals(expected: Polynom, actual: Polynom, eps: Double) {
        assertEquals(expected.degree(), actual.degree())
        for (i in 0..expected.degree()) {
            assertEquals(expected.coeff(i), actual.coeff(i), eps)
        }
    }

    @Test
    @Tag("4")
    fun getValue() {
        val p = Polynom(1.0, 3.0, 2.0)
        assertEquals(42.0, p.getValue(5.0), 1e-10)
        val l = Polynom(0.0, 3.0, 2.0)
        assertEquals(17.0, l.getValue(5.0), 1e-10)
        val lm = Polynom(0.0)
        assertEquals(0.0, lm.getValue(5.0), 1e-10)
    }

    @Test
    @Tag("4")
    fun degree() {
        val p = Polynom(1.0, 1.0, 1.0)
        assertEquals(2, p.degree())
        val q = Polynom(0.0)
        assertEquals(0, q.degree())
        val r = Polynom(0.0, 1.0, 2.0)
        assertEquals(1, r.degree())
        val u = Polynom(0.0, 0.0, 2.0)
        assertEquals(0, u.degree())
        val w = Polynom(1.0, 0.0)
        assertEquals(1, w.degree())
        val j = Polynom(0.0, 3.0, 0.0, 2.0)
        assertEquals(2, j.degree())
    }

    @Test
    @Tag("4")
    fun plus() {
        val p1 = Polynom(1.0, -2.0, -1.0, 4.0)
        val p2 = Polynom(1.0, 3.0, 2.0)
        val r = Polynom(1.0, -1.0, 2.0, 6.0)
        assertApproxEquals(r, p1 + p2, 1e-10)
        val g1 = Polynom(0.0, 1.0, -1.0, 4.0)
        val g2 = Polynom(0.0, 3.0, 2.0)
        val l = Polynom(0.0, 1.0, 2.0, 6.0)
        assertApproxEquals(l, g1 + g2, 1e-10)
        val a1 = Polynom(4.0)
        val a2 = Polynom(1.0, 3.0, 2.0)
        val p = Polynom(1.0, 3.0, 6.0)
        assertApproxEquals(p, a1 + a2, 1e-10)
        val o1 = Polynom(0.0)
        val o2 = Polynom(0.0)
        val w = Polynom(0.0)
        assertApproxEquals(w, o1 + o2, 1e-10)
    }

    @Test
    @Tag("4")
    operator fun unaryMinus() {
        val p = Polynom(1.0, -1.0, 2.0)
        val r = Polynom(-1.0, 1.0, -2.0)
        assertApproxEquals(r, -p, 1e-11)
    }

    @Test
    @Tag("4")
    fun minus() {
        val p1 = Polynom(1.0, -2.0, -1.0, 4.0)
        val p2 = Polynom(1.0, 3.0, 2.0)
        val r = Polynom(1.0, -3.0, -4.0, 2.0)
        assertApproxEquals(r, p1 - p2, 1e-10)
        assertApproxEquals(-r, p2 - p1, 1e-10)
    }

    @Test
    @Tag("6")
    fun times() {
        val p1 = Polynom(1.0, -2.0, -1.0, 4.0)
        val p2 = Polynom(1.0, 3.0, 2.0)
        val r = Polynom(1.0, 1.0, -5.0, -3.0, 10.0, 8.0)
        assertApproxEquals(r, p1 * p2, 1e-10)
        assertApproxEquals(r, p2 * p1, 1e-10)
        val s1 = Polynom(0.0)
        val s2 = Polynom(1.0, 3.0, 2.0)
        val t = Polynom(0.0)
        assertApproxEquals(t, s1 * s2, 1e-10)
        val a1 = Polynom(6.0, 1.0)
        val a2 = Polynom(2.0, 5.0, 3.0)
        val e = Polynom(12.0, 32.0, 23.0, 3.0)
        assertApproxEquals(e, a1 * a2, 1e-10)
    }

    @Test
    @Tag("8")
    fun div() {
        val p1 = Polynom(1.0, -2.0, -1.0, 4.0)
        val p2 = Polynom(1.0, 3.0, 2.0)
        val r = Polynom(1.0, -5.0)
        assertApproxEquals(r, p1 / p2, 1e-10)
        assertApproxEquals(Polynom(0.0), Polynom(0.0) / Polynom(2.0), 1e-10)
        assertApproxEquals(Polynom(1.0, 2.0), Polynom(2.0, 4.0) / Polynom(0.0, 2.0), 1e-10)
        assertApproxEquals(Polynom(2.0, 0.0), Polynom(2.0, 4.0, 2.0) / Polynom(0.0, 1.0, 2.0), 1e-10)
        assertApproxEquals(Polynom(2.0), Polynom(2.0, 4.0, 2.0) / Polynom(1.0, 0.0, 2.0), 1e-10)
        assertApproxEquals(Polynom(1.0, 1.0), Polynom(2.0, 2.0, 1.0) / Polynom(2.0, 0.0), 1e-10)
        assertApproxEquals(Polynom(1.0, 1.0), Polynom(2.0, 2.0, 1.0) / Polynom(0.0, 2.0, 0.0), 1e-10)
        assertApproxEquals(Polynom(1.0), Polynom(6.0, 0.0, 5.2) / Polynom(6.0, 0.0, 5.2), 1e-10)
    }

    @Test
    @Tag("8")
    fun rem() {
        val p1 = Polynom(1.0, -2.0, -1.0, 4.0)
        val p2 = Polynom(1.0, 3.0, 2.0)
        val r = Polynom(1.0, -5.0)
        val q = Polynom(12.0, 14.0)
        assertApproxEquals(q, p1 % p2, 1e-10)
        assertApproxEquals(p1, p2 * r + q, 1e-10)

        val a1 = Polynom(2.0, 2.0, 1.0)
        val a2 = Polynom(2.0, 0.0)
        val e = Polynom(1.0, 1.0)
        val w = Polynom(1.0)
        assertApproxEquals(w, a1 % a2, 1e-10)
        assertApproxEquals(a1, a2 * e + w, 1e-10)

        val c1 = Polynom(0.0, 2.0, 1.0)
        val c2 = Polynom(0.0, 2.0)
        val u = Polynom(1.0, 0.5)
        val j = Polynom(0.0)
        assertApproxEquals(j, c1 % c2, 1e-10)
        assertApproxEquals(c1, c2 * u + j, 1e-10)

    }

    @Test
    @Tag("4")
    fun equals() {
        assertEquals(Polynom(1.0, 2.0, 3.0), Polynom(1.0, 2.0, 3.0))
        assertEquals(Polynom(0.0, 2.0, 3.0), Polynom(2.0, 3.0))
    }

    @Test
    @Tag("6")
    fun hashCodeTest() {
        assertEquals(Polynom(1.0, 2.0, 3.0).hashCode(), Polynom(1.0, 2.0, 3.0).hashCode())
    }
}