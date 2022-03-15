package com.iam2kabhishek.evaltrivia.expr

import java.util.*

class Maker {
    private val randomGenerator = Random()
    val generateRandomExpressions: List<Expression>
        get() {
            val randomExpressions: MutableList<Expression> = ArrayList(NUMBER_OF_EXPRESSIONS)
            for (i in 0 until NUMBER_OF_EXPRESSIONS) {
                val randomExpressionElementsCapacity = randomExpressionElementsCapacity
                val expression = Expression(randomExpressionElementsCapacity)
                for (j in 0 until randomExpressionElementsCapacity) {
                    val isLastIteration = j + 1 == randomExpressionElementsCapacity
                    val expressionElement = ExpressionElement()
                    expressionElement.value = randomExpressionElementValue
                    expressionElement.operator =
                        if (isLastIteration) null else Operator.values()[randomGenerator.nextInt(
                            Operator.values().size
                        )]
                    expression.addElement(expressionElement)
                }
                randomExpressions.add(expression)
            }
            return randomExpressions
        }
    private val randomExpressionElementsCapacity: Int
        get() = getRandomIntegerFromRange(MIN_EXPRESSION_ELEMENTS, MAX_EXPRESSION_ELEMENTS)
    private val randomExpressionElementValue: Int
        get() = getRandomIntegerFromRange(
            MIN_EXPRESSION_ELEMENT_VALUE,
            MAX_EXPRESSION_ELEMENT_VALUE
        )

    private fun getRandomIntegerFromRange(min: Int, max: Int): Int {
        return randomGenerator.nextInt(max - min + 1) + min
    }

    companion object {
        private const val NUMBER_OF_EXPRESSIONS = 1000
        private const val MIN_EXPRESSION_ELEMENTS = 2
        private const val MAX_EXPRESSION_ELEMENTS = 4
        private const val MIN_EXPRESSION_ELEMENT_VALUE = 1
        private const val MAX_EXPRESSION_ELEMENT_VALUE = 20
        @JvmStatic
        fun main(args: Array<String>) {
            val expressionGenerator = Maker()
            val randomQuestions = expressionGenerator.generateRandomExpressions
            for (expression in randomQuestions) {
                println(expression)
            }
        }
    }
}

