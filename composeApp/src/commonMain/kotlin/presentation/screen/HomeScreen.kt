package presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import data.remote.api.CurrencyApiServiceImpl
import presentation.component.HomeHeader
import ui.theme.surfaceColor

class HomeScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel = getScreenModel<HomeViewModel>()
        val rateStatus by viewModel.rateStatus
        val source by viewModel.sourceCurrency
        val target by viewModel.targetCurrency
        var amount by rememberSaveable { mutableStateOf(0.0) }


        println(rateStatus)
        Column(
            modifier = Modifier.fillMaxSize().background(surfaceColor)
        ) {

            HomeHeader(
                status = rateStatus,
                onRatesRefresh = {
                    viewModel.sendEvent(HomeUiEvent.RefreshRates)
                },
                source = source,
                target = target,
                onSwitchClick = {},
                amount = amount,
                onAmountChange = { amount = it }
            )
        }
    }
}