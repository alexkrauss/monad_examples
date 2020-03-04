package de.qaware.monad_examples.part0_baseline


sealed class Expression

data class Constant(val num: Double): Expression()
data class Plus(val left: Expression, val right: Expression): Expression()
// Subtraction and Multiplication omitted for brevity
data class Div(val left: Expression, val right: Expression): Expression()
data class Range(val from: Int, val to: Int): Expression()



