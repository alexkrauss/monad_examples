package de.qaware.monad_examples.part2_monadic

import de.qaware.monad_examples.*

private fun <T> just(x: T): Set<T> = setOf(x)
private fun <T, U> Set<T>.bind(f: (x: T) -> Set<U>): Set<U> = this.flatMap(f).toSet()

private fun where(condition: Boolean): Set<Unit> = if (condition) setOf(Unit) else emptySet()

fun evalSetMonadic(exp: Expression): Set<Double> =
        when (exp) {
            is Constant -> just(exp.num)
            is Plus ->
                evalSetMonadic(exp.left)                       .bind { left ->
                evalSetMonadic(exp.right)                      .bind { right ->
                just(left + right)
                }}
            is Div ->
                evalSetMonadic(exp.left)                       .bind { left ->
                evalSetMonadic(exp.right)                      .bind { right ->
                where(right != 0.0)                  .bind {
                just(left / right)
                }}}

            is Range -> (exp.from .. exp.to).map(Int::toDouble).toSet()

        }

