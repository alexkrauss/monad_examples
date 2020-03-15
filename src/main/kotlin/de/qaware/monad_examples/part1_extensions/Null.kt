package de.qaware.monad_examples.part1_extensions

import de.qaware.monad_examples.part0_baseline.*

/**
 * Extending the evaluation function with the handling of null values: Division by zero should be avoided and replaced
 * by a "null" result, which propagates to the top of the expression. E.g. 3 + (1 / 0) ==> null
 *
 * Note how the handling of null values significantly complicates the code, obscuring the actual evaluation logic.
 */
fun evalNull(exp: Expression): Double? =
        when (exp) {
            is Constant -> exp.num
            is Plus -> {
                val leftResult = evalNull(exp.left)
                if (leftResult == null) null
                else {
                    val rightResult = evalNull(exp.right)
                    if (rightResult == null) null
                    else leftResult + rightResult
                }
            }

            is Div -> {
                val leftResult = evalNull(exp.left)
                if (leftResult == null) null
                else {
                    val rightResult = evalNull(exp.right)
                    if (rightResult == null || rightResult == 0.0) null
                    else leftResult / rightResult
                }
            }
            is Range -> TODO("not supported")
        }

