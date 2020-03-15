package de.qaware.monad_examples.part0_baseline

/**
 * A very simple evaluation function for expressions.
 *
 * This is the baseline for further extensions.
 */
fun eval(exp: Expression): Double =
        when (exp) {
            is Constant -> exp.num
            is Plus -> eval(exp.left) + eval(exp.right)
            is Div -> eval(exp.left) / eval(exp.right)
            is Range -> TODO("not supported")
        }

