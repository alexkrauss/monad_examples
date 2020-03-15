package de.qaware.monad_examples.scala

/**
 * Simple option type, implementing the monad abstraction.
 *
 * @tparam A The base type
 */
trait Option[+A] extends Monad[A, Option] {
  override def just[B](b: B): Option[B] = Some(b)
}
object Option {
  implicit val ev: None.type = None
}
case class Some[+A](a: A) extends Option[A] {
  override def flatMap[B](f: A => Monad[B, Option]): Monad[B, Option] = f(a)
}
case object None extends Option[Nothing] {
  override def flatMap[B](f: Nothing => Monad[B, Option]): Monad[B, Option] = None
}