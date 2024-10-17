package com.hamza.ktor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.google.gson.annotations.SerializedName
import com.hamza.ktor.ui.theme.KtorExampleTheme
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.gson.gson

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KtorExampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                }
            }
        }
    }
}


fun sendLetter(letter: Letter) {
    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            gson()
        }
    }

    val response: HttpResponse = client.post("https://jsonplaceholder.typicode.com/posts") {
        contentType(ContentType.Application.Json)
        setBody(Letter(1, "I miss you", "How are you?"))
    }
}


//Template
data class Letter(
    @SerializedName("UserId") val userId: Int,
    @SerializedName("title") val title: String,
    @SerializedName("body") val body: String

)