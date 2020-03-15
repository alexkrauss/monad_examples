package de.qaware.monad_examples.scala

object Calculator {

  /**
   * Here, we can define the evaluation function in a generic way over all monads.
   * Also note haw Scala's for-comprehensions help with syntax.
   *
   * @param exp the expression
   * @param monad (implicit) the monad structure
   * @tparam F the result type constructor, which must have a monadic structure
   * @return
   */
  def eval[F[_]](exp: Expression)(implicit monad: Monad[Double, F]): Monad[Double, F] = exp match {
    case Constant(d) =>
      monad.just[Double](d)
    case Plus(e1, e2) =>
      for {
        v1 <- eval(e1)
        v2 <- eval(e2)
      } yield v1 + v2
  }
}