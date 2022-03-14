package com.iam2kabhishek.evaltrivia.expr

import java.util.ArrayList

class Expression(sizeOfQuestionElements: Int) {
    private val expressionElements: MutableList<ExpressionElement>
    fun addElement(expressionElement: ExpressionElement) {
        expressionElements.add(expressionElement)
    }

    val elements: List<ExpressionElement>
        get() = expressionElements

    fun size(): Int {
        return expressionElements.size
    }

    override fun toString(): String {
        val sb = StringBuilder()
        for (expressionElement in expressionElements) {
            sb.append(expressionElement)
        }
        return sb.toString().trim { it <= ' ' }
    }

    init {
        expressionElements = ArrayList(sizeOfQuestionElements)
    }
}