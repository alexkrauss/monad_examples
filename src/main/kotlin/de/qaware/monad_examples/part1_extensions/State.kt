package de.qaware.monad_examples.part1_extensions

import de.qaware.monad_examples.part0_baseline.*

/**
 * Yet another modification. Now we add a little bit of state, by counting the calculation steps as we go. When we choose
 * not to use mutable state, the state must be passed explicitly through all recursive calls and the new state is passed
 * back.
 */
fun evalState(exp: Expression, count: Int): Pair<Double, Int> =
        when (exp) {
            is Constant -> Pair(exp.num, count + 1)
            is Plus ->  {
                val (left, count1) = evalState(exp.left, count)
                val (right, count2) = evalState(exp.right, count1)
                Pair(left + right, count2 + 1)
            }
            is Div -> {
                val (left, count1) = evalState(exp.left, count)
                val (right, count2) = evalState(exp.right, count1)
                Pair(left / right, count2 + 1)
            }
            is Range -> TODO("not supported")
}


