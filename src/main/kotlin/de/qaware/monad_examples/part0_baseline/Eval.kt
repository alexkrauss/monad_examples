package de.qaware.monad_examples.part0_baseline


fun eval(exp: Expression): Double =
        when (exp) {
            is Constant -> exp.num
            is Plus -> eval(exp.left) + eval(exp.right)
            is Div -> eval(exp.left) / eval(exp.right)
            is Range -> TODO("not supported")
        }

