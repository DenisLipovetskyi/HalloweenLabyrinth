package com.example.halloweenlabyrinth
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.halloweenlabyrinth.ui.theme.HalloweenLabyrinthTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HalloweenLabyrinthTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            PolicyTerms()
            val navController = rememberNavController()
            // Use navController here
        }
    }
}


@Composable
fun PolicyTerms() {
    Box(modifier = Modifier.fillMaxSize()) {
        val state = rememberLazyListState()

        LazyColumn(state = state, modifier = Modifier.fillMaxSize().background(Color.White)) {
            item {
                Text(
                    text = "\nPrivacy Policy\n\nLast updated: October 22, 2023\n\nThis Privacy Policy descri",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

        // Vertical Scrollbar
        androidx.compose.foundation.Scrollbar(
            rememberScrollbarAdapter(state),
            Modifier.align(alignment = androidx.compose.ui.Alignment.CenterEnd)
        )
    }
}

@Preview
@Composable
fun PolicyTermsPreview() {
    PolicyTerms()
}

