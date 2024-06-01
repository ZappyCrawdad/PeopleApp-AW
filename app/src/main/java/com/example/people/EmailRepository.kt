//Email Rwpository
package com.example.people

import android.content.Context
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse

class EmailRepository(private val context: Context) {
    private val apiService2 = RetrofitEmail.apiService2

    suspend fun fetchEmail(businessEntityID: String): Email? = withContext(Dispatchers.IO) {
        try {
            val response = apiService2.getEmail(businessEntityID).awaitResponse()
            if (response.isSuccessful) {
                response.body()

            } else {
                showToast("Failed to fetch email: ${response.message()}")
                null
            }
        } catch (e: Exception) {
            showToast("Network Error: ${e.message}")
            null
        }
    }

    suspend fun createEmail(email: Email): Boolean = withContext(Dispatchers.IO) {
        try {
            val response = apiService2.createEmail(email).awaitResponse()
            if (response.isSuccessful) {
                true
            } else {
                showToast("Failed to create email: ${response.message()}")
                false
            }
        } catch (e: Exception) {
            showToast("Network Error: ${e.message}")
            false
        }
    }

    suspend fun updateEmail(email: Email): Boolean = withContext(Dispatchers.IO) {
        try {
            val response = apiService2.updateEmail(email).awaitResponse()
            if (response.isSuccessful) {
                true
            } else {
                withContext(Dispatchers.Main) {
                    showToast("Failed to update email: ${response.message()}")
                }
                false
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                showToast("Network Error: ${e.message}")
            }
            false
        }
    }

    private suspend fun showToast(message: String) = withContext(Dispatchers.Main) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}
