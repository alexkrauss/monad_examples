package de.qaware.monad_examples

import de.qaware.monad_examples.part0_baseline.Constant
import de.qaware.monad_examples.part0_baseline.Div
import de.qaware.monad_examples.part0_baseline.Plus
import de.qaware.monad_examples.part0_baseline.Range
import de.qaware.monad_examples.part2_monadic.`null`.evalNullMonadic
import de.qaware.monad_examples.part2_monadic.set.evalSetMonadic
import de.qaware.monad_examples.part2_monadic.state.evalStateMonadic
import de.qaware.monad_examples.part2_monadic.state_set.evalFullFeatured
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class EvalTestMonadic {


    @Test
    fun testNull() {
        assertThat(evalNullMonadic(Div(Constant(1.0), Constant(0.0)))).isNull()
    }

    @Test
    fun testList() {

        assertThat(evalSetMonadic(Plus(Range(1, 3), Range(1, 3))))
                .containsExactly(2.0, 3.0, 4.0, 5.0, 6.0)

    }

    @Test
    fun testState() {
        assertThat(evalStateMonadic(Plus(Constant(2.0), Constant(2.0)))(0))
                .isEqualTo(Pair(4.0, 3))

        assertThat(evalStateMonadic(Plus(Constant(2.0), Plus(Constant(2.0), Plus(Constant(2.0), Constant(2.0)))))(0))
                .isEqualTo(Pair(8.0, 7))
    }

    @Test
    fun testStateAndSet() {
        // Divides 2 by each of -2, -1, 0, 1, 2, but skipping the division by zero. Also counts the operations.
        assertThat(evalFullFeatured(Div(Constant(2.0), Range(-2, +2)))(0))
                .isEqualTo(Pair(setOf(-1.0, -2.0, 2.0, 1.0), 6))
    }


}
