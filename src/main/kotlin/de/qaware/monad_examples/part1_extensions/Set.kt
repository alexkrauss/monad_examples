package de.qaware.monad_examples.part1_extensions

import de.qaware.monad_examples.part0_baseline.*

/**
 * This is another, orthogonal extension of the algorithm. Instead of just one result, it may return a set of results,
 * stemming from the "Range" construction.
 *
 * The extra complication due to sets is handled moderately well using flatmap and map, but it still requires notable
 * modifications to the code, which look quite different from the ones for null handling.
 */
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

