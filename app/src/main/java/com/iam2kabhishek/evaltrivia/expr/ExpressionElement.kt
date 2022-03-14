package com.iam2kabhishek.evaltrivia.expr

class ExpressionElement {
    var value = 0
    var operator: Operator? = null

    override fun toString(): String {
        return value.toString() + (if (operator == null) "" else " " + operator!!.displayValue) + " "
    }
}