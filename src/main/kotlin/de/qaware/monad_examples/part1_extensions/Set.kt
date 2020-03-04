package de.qaware.monad_examples.part1_extensions

import de.qaware.monad_examples.*


fun evalSet(exp: Expression): Set<Double> =
        when (exp) {
            is Constant -> setOf(exp.num)
            is Plus -> {
                val leftResults = evalSet(exp.left)
                val rightResults = evalSet(exp.right)
                leftResults.flatMap { l -> rightResults.map { r -> l + r } }.toSet()
            }
            is Div -> {
                val leftResults = evalSet(exp.left)
                val rightResults = evalSet(exp.right)
                        .filter { it != 0.0 }  // do not divide by zero
                leftResults.flatMap { l -> rightResults.map { r -> l / r } }.toSet()
            }
            is Range -> (exp.from .. exp.to).map(Int::toDouble).toSet()

        }

