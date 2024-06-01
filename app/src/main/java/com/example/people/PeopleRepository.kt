import android.content.Context
import android.widget.Toast
import com.example.people.Person
import com.example.people.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse

class PeopleRepository(private val context: Context) {
    private val apiService = RetrofitClient.apiService

    suspend fun fetchPerson(businessEntityID: String): Person? = withContext(Dispatchers.IO) {
        try {
            val response = apiService.getPerson(businessEntityID).awaitResponse()
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

    suspend fun createPerson(person: Person): Boolean = withContext(Dispatchers.IO) {
        try {
            val response = apiService.createPerson(person).awaitResponse()
            if (response.isSuccessful) {
                true
            } else {
                showToast("Failed to create person: ${response.message()}")
                false
            }
        } catch (e: Exception) {
            showToast("Network Error: ${e.message}")
            false
        }
    }

    suspend fun updatePerson(id: String, person: Person): Boolean = withContext(Dispatchers.IO) {
        try {
            val response = apiService.updatePerson(id, person).awaitResponse()
            if (response.isSuccessful) {
                true
            } else {
                showToast("Failed to update person: ${response.message()}")
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