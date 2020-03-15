package de.qaware.monad_examples.scala

sealed trait Expression
case class Constant(num: Double) extends Expression
case class Plus(left: Expression, right: Expression) extends Expression


