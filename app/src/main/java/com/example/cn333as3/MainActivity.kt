package com.example.cn333as3

import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cn333as3.ui.theme.Cn333as3Theme
import kotlin.random.Random.Default.nextInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Cn333as3Theme {
                // A surface container using the 'background' color from the theme
                TextandButton()
            }
        }
    }
}

var random : Int = nextInt(1,1000)
var count = 0

@Composable
fun TextandButton() {
    var text by remember { mutableStateOf("") }
    var Score = remember { mutableStateOf("") }
    val ans = remember { mutableStateOf(TextFieldValue()) }
    var Hint = remember { mutableStateOf("Guess your number") }

    fun checkAns(){
    if (ans.value.text.toInt() < random) {
        count++
        Hint.value = "More than your number"
        Score.value = ""

    } else if (ans.value.text.toInt() > random) {
        count++
        Hint.value = "Less than your number "
        Score.value = ""

    } else {
        count++
        Hint.value = "That's correct"
        Score.value = "You tried" + " "+count + " times. Enter number for play again"
        count = 0
        random = nextInt(1, 1000)
    }
    }


    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        Text(text = Hint.value)

        TextField(value = ans.value,
            onValueChange = { ans.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp) ,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

        Button(onClick = { checkAns() }) {
            Text(
                text = "submit",
            )
        }
        Text(text = Score.value , modifier = Modifier.padding(8.dp))
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Cn333as3Theme {
        TextandButton()
    }

}