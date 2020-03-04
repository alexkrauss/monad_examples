package de.qaware.monad_examples.part1_extensions

import de.qaware.monad_examples.part0_baseline.*

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


