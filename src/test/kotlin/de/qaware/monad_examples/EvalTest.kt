package de.qaware.monad_examples

import de.qaware.monad_examples.part0_baseline.eval
import de.qaware.monad_examples.part1_extensions.evalSet
import de.qaware.monad_examples.part1_extensions.evalNull
import de.qaware.monad_examples.part1_extensions.evalState
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class EvalTest {



    @Test
    fun testSimpleEval() {

        assertThat(eval(Constant(1.0))).isEqualTo(1.0)
        assertThat(eval(Plus(Constant(1.0), Constant(1.0)))).isEqualTo(2.0)
        assertThat(eval(Plus(Constant(2.0), Constant(2.0)))).isEqualTo(4.0)
    }



    @Test
    fun testNull() {
        assertThat(evalNull(Div(Constant(1.0), Constant(0.0)))).isNull()


    }

    @Test
    fun testList() {

        assertThat(evalSet(Plus(Range(1, 3), Range(1, 3))))
                .containsExactly(2.0, 3.0, 4.0, 5.0, 6.0)

    }

    @Test
    fun testState() {

        assertThat(evalState(Plus(Constant(2.0), Constant(2.0)), 0))
                .isEqualTo(Pair(4.0, 3))

    }


}
