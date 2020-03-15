package de.qaware.monad_examples.part0_baseline

/**
 * A simple recursive data type of expressions, which form the input for our simple function.
 */
sealed class Expression

data class Constant(val num: Double): Expression()
data class Plus(val left: Expression, val right: Expression): Expression()
// Subtraction and Multiplication omitted for brevity
data class Div(val left: Expression, val right: Expression): Expression()

// The Range construction, used in later extensions, represents not a single value but a set, i.e.,
//   Range(1, 4) denotes the set {1, 2, 3, 4}
data class Range(val from: Int, val to: Int): Expression()



