package de.qaware.monad_examples.part2_monadic

import de.qaware.monad_examples.part0_baseline.*


typealias Stateful<T, S> = (S) -> Pair<T, S>

private fun <T, S> just(x: T): Stateful<T, S> = { state -> Pair(x, state)}

private fun <T, U, S> Stateful<T, S>.bind(f: (T) -> Stateful<U, S>): Stateful<U, S> = { state ->
    val (x, state1) = this(state)
    f(x)(state1)
}

val increment: Stateful<Unit, Int> = { count -> Pair(Unit, count + 1) }


fun evalStateMonadic(exp: Expression): Stateful<Double, Int> =
        when (exp) {
            is Constant ->
                increment                                       .bind {
                just<Double, Int>(exp.num)
                }
            is Plus ->
                evalStateMonadic(exp.left)                      .bind { left ->
                evalStateMonadic(exp.right)                     .bind { right ->
                increment                                       .bind {
                just<Double, Int>(left + right)
                }}}
            is Div ->
                evalStateMonadic(exp.left)                      .bind { left ->
                evalStateMonadic(exp.right)                     .bind { right ->
                increment                                       .bind {
                just<Double, Int>(left / right)
                }}}

            is Range -> TODO("not supported")
}


