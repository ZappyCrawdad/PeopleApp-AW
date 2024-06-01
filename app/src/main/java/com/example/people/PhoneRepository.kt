package com.example.people

import com.example.people.RetrofitPhone
import com.example.people.Phone
import android.content.Context
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse

class PhoneRepository(private val context: Context) {
    private val apiService1 = RetrofitPhone.apiService1

    suspend fun fetchPerson(businessEntityID: String): Phone? = withContext(Dispatchers.IO) {
        try {
            val response = apiService1.getPhone(businessEntityID).awaitResponse()
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

    suspend fun createPhone(phone: Phone): Boolean = withContext(Dispatchers.IO) {
        try {
            val response = apiService1.createPhone(phone).awaitResponse()
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

    suspend fun updatePhone(phone: Phone): Boolean = withContext(Dispatchers.IO) {
        try {
            val response = apiService1.updatePhone(phone).awaitResponse()
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
