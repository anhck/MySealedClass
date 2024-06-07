package com.adn.mysealedclass

class MyUseCaseSealed {

    sealed class UIState {
        data object Loading : UIState()
        data class Success(val data: String) : UIState()
        data class Error(val exception: Exception) : UIState()
    }

    fun updateUI(state: UIState) {
        when (state) {
            is UIState.Error -> showError(state.exception)
            UIState.Loading -> showLoading()
            is UIState.Success -> showData(state.data)
        }
    }


    sealed class Payment{
        data class CreditCard(val number: String, val expirationDate: String) : Payment()
        data class PayPal(val email: String) : Payment()
        data object Cash : Payment()
    }

    fun processPayment(payment: Payment) {
        when (payment) {
            is Payment.CreditCard -> processCreditCardPayment(payment.number, payment.expirationDate)
            is Payment.PayPal -> processPayPalPayment(payment.email)
            Payment.Cash -> processCashPayment()
        }
    }

    private fun processCashPayment() {

    }

    private fun processPayPalPayment(email: String) {

    }

    private fun processCreditCardPayment(number: String, expirationDate: String) {

    }


    private fun showData(data: String) {

    }

    private fun showError(exception: Exception) {

    }

    private fun showLoading() {

    }

}