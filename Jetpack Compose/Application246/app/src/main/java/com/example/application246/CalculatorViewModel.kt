package com.example.application246

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class CalculatorViewModel : ViewModel() {
    val calculatorState = mutableStateOf(CalculatorState())

    fun onButtonClick(label: String){
        buttonClickHandler(
            button = label,
            currentState = calculatorState.value,
            updateState = { newState -> calculatorState.value = newState }
        )
    }

    private fun buttonClickHandler(
        button: String,
        currentState: CalculatorState,
        updateState: (CalculatorState) -> Unit
    ){
        var newState = currentState

        when (button) {
            ".", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" -> {
                if (newState.isNewNumber) {
                    newState = newState.copy(
                        display = if (button == ".") "0." else button,
                        isNewNumber = false
                    )
                }
                else {
                    val newDisplay = if ((newState.display == "0") and (button != ".")) button else newState.display + button
                    newState = newState.copy(display = newDisplay)
                }
            }
            "+", "-", "x", "/" -> {
                val currentNumber = newState.display.toDoubleOrNull() ?: 0.0

                newState = if ((newState.previousValue == null) or (newState.currentOperator == null)) {
                    newState.copy(
                        previousValue = currentNumber,
                        currentOperator = button,
                        isNewNumber = true
                    )
                } else {
                    val result = calculate(newState.previousValue!!, currentNumber, newState.currentOperator!!)

                    newState.copy(
                        display = result.toString().removeSuffix(".0"),
                        previousValue = result,
                        currentOperator = button,
                        isNewNumber = true
                    )
                }
            }
            "=" -> {
                val currentNumber = newState.display.toDoubleOrNull() ?: 0.0
                val result = if ((newState.previousValue != null) and (newState.currentOperator != null)) {
                    calculate(newState.previousValue!!, currentNumber, newState.currentOperator!!)
                } else {
                    currentNumber
                }

                newState = newState.copy(
                    display = result.toString().removeSuffix(".0"),
                    previousValue = null,
                    currentOperator = null,
                    isNewNumber = true
                )
            }
            "+/-" -> {
                if (newState.display != "0"){
                    val number = newState.display.toDoubleOrNull() ?: 0.0
                    newState = newState.copy(display = (-number).toString())
                }
            }
            "%" -> {
                val number = newState.display.toDoubleOrNull() ?: 0.0
                newState = newState.copy(display = (number / 100).toString())
            }
            "C" -> newState = CalculatorState()
        }
        updateState(newState)
    }

    private fun calculate(a: Double, b: Double, operator: String): Double{
        return when (operator) {
            "+" -> a + b
            "-" -> a - b
            "x" -> a * b
            "/" -> if (b != 0.0) a / b else 0.0
            else -> b
        }
    }
}