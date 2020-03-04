package de.qaware.monad_examples.part1_extensions

import de.qaware.monad_examples.*

typealias Counter = Int

fun evalState(exp: Expression, count: Counter): Pair<Double, Counter> =
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


