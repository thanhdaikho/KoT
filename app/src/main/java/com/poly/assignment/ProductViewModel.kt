package com.poly.assignment

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

    fun toggleSaveProduct(product: Product) {
        viewModelScope.launch {
            _savedProducts.update { currentSet ->
                val updatedSet = if (currentSet.contains(product)) {
                    Log.d("ProductViewModel", "Product removed: ${product.name}")

                    currentSet - product
                } else {
                    Log.d("ProductViewModel", "Product added: ${product.name}")

                    currentSet + product
                }
                updatedSet
            }
        }
    }

}