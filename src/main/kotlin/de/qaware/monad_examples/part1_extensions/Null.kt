package de.qaware.monad_examples.part1_extensions

import de.qaware.monad_examples.part0_baseline.*

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

