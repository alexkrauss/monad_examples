package de.qaware.monad_examples.part2_monadic

import de.qaware.monad_examples.*

private fun <T>just(x: T): T? = x
private fun <T, U>T?.bind(f: (x:T) -> U?) = this?.let(f)

private fun where(condition: Boolean): Unit? = if (condition) Unit else null

fun evalNullMonadic(exp: Expression): Double? =
        when (exp) {
            is Constant -> just(exp.num)
            is Plus ->
                evalNullMonadic(exp.left)            .bind { left ->
                evalNullMonadic(exp.right)           .bind { right ->
                just(left + right)
                }}
            is Div ->
                evalNullMonadic(exp.left)            .bind { left ->
                evalNullMonadic(exp.right)           .bind { right ->
                where(right != 0.0)        .bind {
                just(left / right)
                }}}
            is Range -> TODO("not supported")
        }

