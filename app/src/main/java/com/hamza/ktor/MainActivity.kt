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
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.gson.gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KtorExampleTheme {
                sendLetter()
            }
        }
    }
}


fun sendLetter() {
    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            gson()
        }
    }


    CoroutineScope(Dispatchers.IO).launch {
        val response: HttpResponse = client.post("https://jsonplaceholder.typicode.com/posts") {
            contentType(ContentType.Application.Json)
            setBody(Letter(1, "I miss you", "How are you?"))
        }

        println("Ktor Response: ${response.status}")
        println("Ktor Response: ${response.bodyAsText()}")
    }
}


//Template
data class Letter(
    @SerializedName("UserId") val userId: Int,
    @SerializedName("title") val title: String,
    @SerializedName("body") val body: String

)