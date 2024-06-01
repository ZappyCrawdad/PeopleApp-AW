package com.example.people

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

class EditContactActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainContentPhone()
        }
    }
}

@Composable
fun MainContentPhone() {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val repositoryPhone = PhoneRepository(context)
    val repositoryEmail = EmailRepository(context)
    val repositoryNumberType = NumberTypeRepository(context)

    var textState by remember { mutableStateOf("") }
    var businessEntityID by remember { mutableStateOf("") }

    var phoneNumber by remember { mutableStateOf("") }
    var phoneNumberTypeID by remember { mutableStateOf("") }

    var emailAddress by remember { mutableStateOf("") }
    var emailAddressID by remember { mutableStateOf("") }
    var rowguid by remember { mutableStateOf("") }
    var modifiedDate by remember { mutableStateOf("") }

    var name by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        Button(
            onClick = { (context as? EditContactActivity)?.finish() },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Go Back")
        }

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
                        val phone = repositoryPhone.fetchPerson(textState)
                        val email = repositoryEmail.fetchEmail(textState)
                        val numberType = repositoryNumberType.fetchNumberType(textState)
                        //Phone
                        if (phone != null) {
                            businessEntityID = phone.BusinessEntityID
                            phoneNumber = phone.PhoneNumber
                            phoneNumberTypeID = phone.PhoneNumberTypeID
                            modifiedDate = phone.ModifiedDate

                            val numberType = repositoryNumberType.fetchNumberType(phone.PhoneNumberTypeID)

                            name = numberType?.Name ?: ""

                            if (name.isEmpty()) {
                                Toast.makeText(context, "No Name of Number data found", Toast.LENGTH_SHORT).show()
                            }

                        } else {
                            Toast.makeText(context, "No phone data found", Toast.LENGTH_SHORT).show()
                        }
                        //PhoneType
                        if (numberType != null) {
                            phoneNumberTypeID = numberType.PhoneNumberTypeID
                            name = numberType.Name
                            modifiedDate = numberType.ModifiedDate
                        } else {
                            Toast.makeText(context, "No number contact data found", Toast.LENGTH_SHORT).show()
                        }
                        //Email
                        if (email != null) {
                            businessEntityID = email.BusinessEntityID
                            emailAddress = email.EmailAddress
                            emailAddressID = email.EmailAddressID
                            rowguid = email.rowguid
                            modifiedDate = email.ModifiedDate
                        } else {
                            Toast.makeText(context, "No email data found", Toast.LENGTH_SHORT).show()
                        }
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Search Person")
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        TextField(
            value = emailAddress,
            onValueChange = { emailAddress = it },
            label = { Text("Email Address") },
            readOnly = false,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color(0xFFF1F1F1),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = { Text("Phone Number") },
            readOnly = false,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color(0xFFF1F1F1),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier.fillMaxWidth()
        )


        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name of Number Type") },
            readOnly = false,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color(0xFFF1F1F1),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                scope.launch {
                    //Email
                    val email = Email(
                        BusinessEntityID = businessEntityID,
                        EmailAddressID = emailAddressID,
                        EmailAddress = emailAddress,
                        rowguid = rowguid,
                        ModifiedDate = modifiedDate
                    )
                    val emailSuccess = repositoryEmail.updateEmail(email)
                    if (emailSuccess) {
                        Toast.makeText(context, "Email updated successfully", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        Toast.makeText(context, "Failed to update email", Toast.LENGTH_SHORT).show()
                    }

                    //Phone
                    val phone = Phone(
                        BusinessEntityID = businessEntityID,
                        PhoneNumber = phoneNumber,
                        PhoneNumberTypeID = phoneNumberTypeID,
                        ModifiedDate = modifiedDate
                    )
                    val phoneSuccess = repositoryPhone.updatePhone(phone)
                    if (phoneSuccess) {
                        Toast.makeText(context, "Phone updated successfully", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Failed to update phone", Toast.LENGTH_SHORT).show()
                    }

                    //Phone Type
                    val phoneType = NumberType(
                        PhoneNumberTypeID = phoneNumberTypeID,
                        Name = name,
                        ModifiedDate = modifiedDate
                    )
                    val phoneTypeSuccess = repositoryNumberType.updateNumberType(phoneType)
                    if (phoneSuccess) {
                        Toast.makeText(context, "PhoneType updated successfully", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Failed to update phoneType", Toast.LENGTH_SHORT).show()
                    }
                    //Datos aceptados:
                    /*
                    Cell
                    Home
                    Work
                     */
                }
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
fun DefaultPreviewPhone() {
    MainContentPhone()
}
