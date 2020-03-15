package de.qaware.monad_examples.part2_monadic.state_set

import de.qaware.monad_examples.part0_baseline.*


/*
 * We now define a monad that combines the two previous extensions of multiple results and counting of operations.
 * Thus, we must first define the result type, which is a mapping from a previous state, to a set of results and a
 * new state:
 */
typealias MyMonad<T> = (Int) -> Pair<Set<T>, Int>

/*
 * Defining the monad operations "just" and "bind" now gets somewhat hard to read, as it contains all the clutter that
 * we do not want to put into the business code.
 */
private fun <T> just(x: T): MyMonad<T> = { state -> Pair(setOf(x), state)}

private fun <T, U> MyMonad<T>.bind(f: (T) -> MyMonad<U>): MyMonad<U> = { state ->

    // run the first computation
    val (results, state1) = this(state)

    // for each result, we must run the followup computation "f". Every time, the state can be modified. We obtain
    // the set of all results, together with the final state.
    var currentState = state1
    val result : MutableSet<U> = mutableSetOf()
    for (x in results) {
        val (rs, newState) = f(x)(currentState)
        currentState = newState
        result.addAll(rs)
    }

    Pair(result, currentState)
}

private val increment: MyMonad<Unit> = { count -> Pair(setOf(Unit), count + 1) }

private fun where(condition: Boolean): MyMonad<Unit> = { count ->
    Pair(if (condition) setOf(Unit) else emptySet(), count) }


/**
 * Note that the actual business logic is pretty much unchanged, but it is now a branching, stateful computation.
 */
fun evalFullFeatured(exp: Expression): MyMonad<Double> =
        when (exp) {
            is Constant ->
                increment.bind {
                just(exp.num)
                }
            is Plus ->
                evalFullFeatured(exp.left)                     .bind { left ->
                evalFullFeatured(exp.right)                    .bind { right ->
                increment                                      .bind {
                just(left + right)
                }}}
            is Div ->
                evalFullFeatured(exp.left)                     .bind { left ->
                evalFullFeatured(exp.right)                    .bind { right ->
                where (right != 0.0)                  .bind {
                increment                                      .bind {
                just(left / right)
                }}}}

            is Range -> { s -> Pair((exp.from .. exp.to).map(Int::toDouble).toSet(), s + 1) }
}


