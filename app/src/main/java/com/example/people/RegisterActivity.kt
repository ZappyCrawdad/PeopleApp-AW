package com.example.people

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RegisterContent()
        }
    }
}

@Composable
fun RegisterContent() {
    val context = LocalContext.current

    // State variables for each form field
    var businessEntityId by remember { mutableStateOf("") }
    var personType by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var emailAddress by remember { mutableStateOf("") }
    var nameTelephoneType by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var phoneTypeId by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(0.dp)
    ) {
        // Botón de regreso
            Button(
                onClick = { (context as? RegisterActivity)?.finish() },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text("Go Back")
            }

        Spacer(modifier = Modifier.height(0.dp))

        // Business Entity ID
        Text("Business Entity:")

        TextField(
            value = "",
            onValueChange = {},
            label = { Text("BusinessEntityID") },
            modifier = Modifier.fillMaxWidth()
        )

        Text("Person Type:",
            modifier = Modifier.padding(top = 30.dp))

        TextField(
            value = "",
            onValueChange = {},
            label = { Text("Person Type") },
            modifier = Modifier.fillMaxWidth()
        )

        Text("First Name:")

        TextField(
            value = "",
            onValueChange = {},
            label = { Text("First Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Text("Last Name:")

        TextField(
            value = "",
            onValueChange = {},
            label = { Text("Last Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Text("Email Address:")

        TextField(
            value = "",
            onValueChange = {},
            label = { Text("EmailAddress") },
            modifier = Modifier.fillMaxWidth()
        )

        Text("Name of the telephone number type:")

        TextField(
            value = "",
            onValueChange = {},
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Text("Phone Number:")

        TextField(
            value = "",
            onValueChange = {},
            label = { Text("Phone Number") },
            modifier = Modifier.fillMaxWidth()
        )

        Text("Phone Type ID:")

        TextField(
            value = "",
            onValueChange = {},
            label = { Text("Phone Type ID") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                // Lógica para guardar
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(150.dp)
        ) {
            Text("Save")
        }


    }
}

@Preview(showBackground = true)
@Composable
fun RegisterPreview() {
    RegisterContent()
}
