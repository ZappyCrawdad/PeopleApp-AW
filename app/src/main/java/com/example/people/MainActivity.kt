//MainActivity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.people.EditContactActivity
import com.example.people.RegisterActivity
import com.example.people.ExampleActivity
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainContent()
        }
    }
}

@Composable
fun MainContent() {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val repository = PeopleRepository(context)

    var textState by remember { mutableStateOf("") }
    var businessEntityID by remember { mutableStateOf("") }
    var personType by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var nameStyle by remember { mutableStateOf(false) }
    var title by remember { mutableStateOf("") }
    var middleName by remember { mutableStateOf("") }
    var suffix by remember { mutableStateOf("") }
    var emailPromotion by remember { mutableStateOf("") }
    var additionalContactInfo by remember { mutableStateOf("") }
    var demographics by remember { mutableStateOf("") }
    var rowguid by remember { mutableStateOf("") }
    var modifiedDate by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            "Persons",
            fontSize = 45.sp,
            color = Color.Black,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        TextField(
            value = textState,
            onValueChange = { textState = it },
            label = { Text("Enter the ID", color = Color.DarkGray, fontSize = 16.sp) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color(0xFFF1F1F1),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Button(
                onClick = {
                    scope.launch {
                        val person = repository.fetchPerson(textState)
                        if (person != null) {
                            businessEntityID = person.BusinessEntityID
                            personType = person.PersonType
                            firstName = person.FirstName
                            lastName = person.LastName
                            nameStyle = person.NameStyle
                            title = person.Title
                            middleName = person.MiddleNameval
                            suffix = person.Suffix
                            emailPromotion = person.EmailPromotion
                            additionalContactInfo = person.AdditionalContactInfo
                            demographics = person.Demographics
                            rowguid = person.rowguid
                            modifiedDate = person.ModifiedDate
                        } else {
                            Toast.makeText(context, "No data found", Toast.LENGTH_SHORT).show()
                        }
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Search Person")
            }

            Button(
                onClick = {
                    val intent = Intent(context, EditContactActivity::class.java)
                    context.startActivity(intent)
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Edit Person")
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                val intent = Intent(context, RegisterActivity::class.java)
                context.startActivity(intent)
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(150.dp)
        ) {
            Text("New Person")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            "Business Entity ID: $businessEntityID",
            fontSize = 20.sp,
            color = Color.DarkGray,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Text(
            "Person Type: $personType",
            fontSize = 20.sp,
            color = Color.DarkGray,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Text(
            "First Name: $firstName",
            fontSize = 20.sp,
            color = Color.DarkGray,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Text(
            "Last Name: $lastName",
            fontSize = 20.sp,
            color = Color.DarkGray,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        //Campo para experimentar
        /*Button(
            onClick = {
                val intent = Intent(context, ExampleActivity::class.java)
                context.startActivity(intent)
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(150.dp)
        ) {
            Text("EXAMPLE")
        }*/

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainContent()
}
