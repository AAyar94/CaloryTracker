package com.aayar94.onboard_presentation.nutrient_goal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.aaayar94.core.util.UiEvent
import com.aayar94.core.R
import com.aayar94.core_ui.LocalSpacing
import com.aayar94.onboard_presentation.component.ActionButton
import com.aayar94.onboard_presentation.component.ProgressIndicator
import com.aayar94.onboard_presentation.component.UnitTextField
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun NutrientGoalScreen(
    scaffoldState: ScaffoldState,
    onNextClick: () -> Unit,
    viewModel: NutrientGoalViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
    val context = LocalContext.current
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(MaterialTheme.colors.surface)
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Success -> onNextClick()
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message.asString(context = context)
                    )
                }

                else -> Unit
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.spaceLarge)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ProgressIndicator(currentStep = 6, totalStep = 6)
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            Text(
                text = stringResource(id = R.string.what_are_your_nutrient_goals),
                style = MaterialTheme.typography.h3
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            UnitTextField(
                value = viewModel.state.carbsRatio,
                onValueChange = { viewModel.onEvent(NutrientGoalEvent.OnCarbRatioEnter(it)) },
                unit = stringResource(
                    id = R.string.percent_carbs
                )
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            UnitTextField(
                value = viewModel.state.proteinRatio,
                onValueChange = { viewModel.onEvent(NutrientGoalEvent.OnProteinRatioEnter(it)) },
                unit = stringResource(
                    id = R.string.percent_proteins
                )
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            UnitTextField(
                value = viewModel.state.fatRatio,
                onValueChange = { viewModel.onEvent(NutrientGoalEvent.OnFatRatioEnter(it)) },
                unit = stringResource(
                    id = R.string.percent_fats
                )
            )
        }
        ActionButton(
            text = stringResource(id = R.string.next),
            onClick = { viewModel.onEvent(NutrientGoalEvent.OnNextClick) },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(horizontal = spacing.spaceMedium)
        )
    }
}