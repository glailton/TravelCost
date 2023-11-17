package glailton.io.github.travelcost.ui.presentation.screens.home

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import glailton.io.github.travelcost.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    vm: HomeViewModel
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val state = vm.state.collectAsState().value
            val context = LocalContext.current

            OutlinedTextField(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .background(Color.White),
                value = state.distance,
                onValueChange = { vm.updateInfo(distance = it) },
                label = { Text(text = stringResource(R.string.distance)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                supportingText = {
                    Text(
                        text = stringResource(R.string.total_kilometers),
                        textAlign = TextAlign.Center,
                        style = LocalTextStyle.current.copy(textAlign = TextAlign.Center)
                    )
                },
                isError = state.isError
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.price,
                onValueChange = { vm.updateInfo(price = it) },
                label = { Text(text = stringResource(R.string.price)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                supportingText = {
                    Text(
                        text = stringResource(R.string.price_per_liter),
                        textAlign = TextAlign.Center
                    )
                },
                isError = state.isError
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.autonomy,
                onValueChange = { vm.updateInfo(autonomy = it) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                label = { Text(text = stringResource(R.string.autonomy)) },
                supportingText = {
                    Text(
                        text = stringResource(R.string.kilometers_per_liter),
                        textAlign = TextAlign.Center
                    )
                },
                isError = state.isError
            )

            Text(text = stringResource(R.string.total_expense))
            Text(
                text = stringResource(id = R.string.total, state.result),
                style = MaterialTheme.typography.labelLarge.copy(fontSize = 50.sp)
            )

            Button(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp), onClick = { vm.calculate() }) {
                Text(
                    text = stringResource(R.string.calculate),
                    style = MaterialTheme.typography.labelLarge.copy(fontSize = 20.sp)
                )
            }

            LaunchedEffect(state.isError) {
                if (state.isError) {
                    Toast.makeText(context,
                        context.getString(R.string.fill_all_fields), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}