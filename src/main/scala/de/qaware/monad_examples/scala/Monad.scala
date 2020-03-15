package de.qaware.monad_examples.scala


/**
 * Unlike in Kotlin, the general concept of a monad can be expressed in Scala as a trait with a higher-kinded
 * type parameter.
 *
 * Note that "bind" is called "flatMap" here, following Scala conventions.
 *
 * @tparam A The base type
 * @tparam F The monad
 */
trait Monad[+A, F[_]] {
  def just[B](b: B): Monad[B, F]
  def flatMap[B](f: A => Monad[B, F]): Monad[B, F]
  // Helper for for comprehensions
  def map[B](f: A => B): Monad[B, F] = flatMap { a: A =>
    just(f(a))
  }
}