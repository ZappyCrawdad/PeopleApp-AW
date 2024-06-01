package com.example.people

import com.example.people.RetrofitPhone
import com.example.people.Phone
import android.content.Context
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse

class NumberTypeRepository(private val context: Context) {
    private val apiService3 = RetrofitNumberType.apiService3

    suspend fun fetchNumberType(phoneNumberTypeID: String): NumberType? = withContext(Dispatchers.IO) {
        try {
            val response = apiService3.getNumberType(phoneNumberTypeID).awaitResponse()
            if (response.isSuccessful) {
                response.body()
            } else {
                showToast("Failed to fetch person: ${response.message()}")
                null
            }
        } catch (e: Exception) {
            showToast("Network Error: ${e.message}")
            null
        }
    }

    suspend fun createNumberType(numberType: NumberType): Boolean = withContext(Dispatchers.IO) {
        try {
            val response = apiService3.createNumberType(numberType).awaitResponse()
            if (response.isSuccessful) {
                true
            } else {
                showToast("Failed to create phone: ${response.message()}")
                false
            }
        } catch (e: Exception) {
            showToast("Network Error: ${e.message}")
            false
        }
    }

    suspend fun updateNumberType(numberType: NumberType): Boolean = withContext(Dispatchers.IO) {
        try {
            val response = apiService3.updateNumberType(numberType).awaitResponse()
            if (response.isSuccessful) {
                true
            } else {
                showToast("Failed to update phone: ${response.message()}")
                false
            }
        } catch (e: Exception) {
            showToast("Network Error: ${e.message}")
            false
        }
    }

    private suspend fun showToast(message: String) = withContext(Dispatchers.Main) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}
