package de.qaware.monad_examples.part2_monadic

import de.qaware.monad_examples.*
import de.qaware.monad_examples.part1_extensions.Counter


typealias Stateful<T, S> = (s: S) -> Pair<T, S>

private fun <T, S> just(x: T): Stateful<T, S> = { state -> Pair(x, state)}

private fun <T, U, S> Stateful<T, S>.bind(f: (x:T) -> Stateful<U, S>): Stateful<U, S> = { state ->
    val (x, state1) = this(state)
    f(x)(state1)
}

val increment: Stateful<Unit, Counter> = { count -> Pair(Unit, count + 1) }


fun evalMonadicState(exp: Expression): Stateful<Double, Counter> =
        when (exp) {
            is Constant ->
                increment                                       .bind {
                just<Double, Counter>(exp.num)
                }
            is Plus ->
                evalMonadicState(exp.left)                      .bind { left ->
                evalMonadicState(exp.right)                     .bind { right ->
                increment                                       .bind {
                just<Double, Counter>(left + right)
                }}}
            is Div ->
                evalMonadicState(exp.left)                      .bind { left ->
                evalMonadicState(exp.right)                     .bind { right ->
                increment                                       .bind {
                just<Double, Counter>(left / right)
                }}}

            is Range -> TODO("not supported")
}


