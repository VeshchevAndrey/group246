package com.example.application246

data class CalculatorState(
    val display: String = "0",
    val previousValue: Double? = null,
    val currentOperator: String? = null,
    val isNewNumber: Boolean = true
)
