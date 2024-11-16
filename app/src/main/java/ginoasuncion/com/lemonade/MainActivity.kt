package ginoasuncion.com.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ginoasuncion.com.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LemonadeApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun LemonadeApp(modifier: Modifier = Modifier) {
    LemonadeWithText(modifier = modifier.fillMaxSize())
}

fun generateList(): List<Int> {
    val steps = mutableListOf<Int>()
    steps.add(1)
    repeat((2..4).random()) {
        steps.add(2)
    }
    steps.add(3)
    steps.add(4)
    return steps
}

@Composable
fun LemonadeWithText(modifier: Modifier = Modifier) {
    var currentIndex by remember { mutableStateOf(1) }
    var stepList by remember { mutableStateOf(generateList()) }
    var stepIndex by remember { mutableStateOf(1) }

    if (currentIndex == 1) {
        stepList = generateList()
    }

    val imageResource = when (stepIndex) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    val imageDescriptionResource = when (stepIndex) {
        1 -> stringResource(R.string.lemon_tree_content_description)
        2 -> stringResource(R.string.lemon_content_description)
        3 -> stringResource(R.string.lemonade_glass_description)
        else -> stringResource(R.string.lemonade_glass_empty_content_description)
    }

    val textResource = when (stepIndex) {
        1 -> stringResource(R.string.tap_action)
        2 -> stringResource(R.string.squeeze_action)
        3 -> stringResource(R.string.drink_action)
        else -> stringResource(R.string.empty_action)
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                stepIndex = if (currentIndex in stepList.indices) stepList[currentIndex] else 1
                currentIndex = (currentIndex + 1) % stepList.size
            },
            modifier = Modifier.size(200.dp),
            shape = RoundedCornerShape(16.dp),
            colors = buttonColors(
                containerColor = Color(105, 205, 16, 50)
            )
        ) {
            Image(
                painter = painterResource(imageResource),
                contentDescription = imageDescriptionResource,
                modifier = Modifier
                    .fillMaxSize()

            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = textResource,
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}
