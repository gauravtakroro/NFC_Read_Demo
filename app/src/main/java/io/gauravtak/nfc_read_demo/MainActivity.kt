package io.gauravtak.nfc_read_demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import io.gauravtak.nfc_read_demo.ui.theme.NFC_Read_DemoTheme

class MainActivity : ComponentActivity() {
    private var viewModel: MainViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = viewModels<MainViewModel>().value
        setContent {
            NFC_Read_DemoTheme {
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxHeight()) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                        Greeting(name = "! \n Welcome to NFC Read Demo")
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!",
        color = Color.Black,
        style = MaterialTheme.typography.displayLarge,
        textAlign = TextAlign.Center
    )
}


@Composable
fun NFCReadValueUI(viewModel: MainViewModel) {
    val currentNFCReadValue = viewModel.getCurrentNFCReadValue().observeAsState().value
    Text(text = currentNFCReadValue ?: "",
        color = Color.Black,
        style = MaterialTheme.typography.displayLarge,
        textAlign = TextAlign.Center
    )
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NFC_Read_DemoTheme {
        Greeting("Android")
    }
}