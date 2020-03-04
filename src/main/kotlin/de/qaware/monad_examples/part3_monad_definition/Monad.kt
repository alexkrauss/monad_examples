package de.qaware.monad_examples.part3_monad_definition

/**
 * Here, "Monad" should actually be a variable... a higher-kinded type variable.
 */
interface Monad<T> {

    fun <U> bind(f : (x: T) -> Monad<U>): Monad<U>
}
