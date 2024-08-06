package com.poly.assignment

import ProductItem
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {
    private val _savedProducts = MutableStateFlow<Set<Product>>(emptySet())
    val savedProducts: StateFlow<Set<Product>> = _savedProducts

    private val _cartProducts = MutableStateFlow<Set<ProductItem>>(emptySet())
    val cartProducts: StateFlow<Set<ProductItem>> = _cartProducts

    private val _totalPrice = MutableStateFlow(0.0)
    val totalPrice: StateFlow<Double> = _totalPrice

    private val _billDetail = MutableStateFlow<BillDetail?>(null)
    val billDetail: StateFlow<BillDetail?> = _billDetail

    fun toggleSaveProduct(product: Product) {
        viewModelScope.launch {
            _savedProducts.update { currentSet ->
                if (currentSet.contains(product)) {
                    currentSet - product
                } else {
                    currentSet + product
                }
            }
        }
    }

    fun toggleCartProduct(productItem: ProductItem) {
        _cartProducts.update { currentSet ->
            if (currentSet.contains(productItem)) {
                currentSet - productItem
            } else {
                currentSet + productItem
            }
        }
        calculateTotalPrice()
    }

    fun updateProductQuantity(productItem: ProductItem, newQuantity: Int) {
        _cartProducts.update { currentSet ->
            currentSet.map {
                if (it.product.id == productItem.product.id) {
                    it.copy(quantity = newQuantity)
                } else {
                    it
                }
            }.toSet()
        }
        calculateTotalPrice()
    }

    private fun calculateTotalPrice() {
        val total = _cartProducts.value.sumOf { it.product.new_price * it.quantity }
        _totalPrice.value = total
    }

    fun createBillDetail(
        userId: String,
        address: String,
        paymentMethod: String,
        cartItems: Set<ProductItem>,
        totalAmount: Double
    ) {
        viewModelScope.launch {
            val billItems = cartItems.map { productItem ->
                BillItem(
                    productId = productItem.product.id,
                    quantity = productItem.quantity,
                    totalPrice = productItem.product.new_price * productItem.quantity
                )
            }
            val billDetail = BillDetail(
                userID = userId,
                address = address,
                paymentMethod = paymentMethod,
                items = billItems,
                totalAmount = totalAmount
            )
            _billDetail.value = billDetail

            // Optionally, save to backend or local database
        }
    }
}

data class BillDetail(
    val userID: String,
    val address: String,
    val paymentMethod: String,
    val items: List<BillItem>,
    val totalAmount: Double
)

data class BillItem(
    val productId: Int,
    val quantity: Int,
    val totalPrice: Double
)