package com.aayar94.onboard_presentation.gender

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aaayar94.core.domain.model.Gender
import com.aaayar94.core.util.UiEvent
import com.aayar94.core_ui.LocalSpacing
import com.aayar94.onboard_presentation.component.ActionButton
import com.aayar94.onboard_presentation.component.GenderButton
import com.aayar94.onboard_presentation.component.ProgressIndicator
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.plcoding.onboarding_presentation.gender.GenderViewModel

@Composable
fun GenderScreen(
    onNextClick: () -> Unit,
    viewModel: GenderViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(MaterialTheme.colors.surface)
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Success -> onNextClick()
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
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            ProgressIndicator(currentStep = 0, totalStep = 6)
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            Text(
                text = stringResource(id = com.aayar94.core.R.string.whats_your_gender),
                style = MaterialTheme.typography.h3
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            Row {
                GenderButton(
                    text = stringResource(id = com.aayar94.core.R.string.male),
                    isSelected = viewModel.selectedGender is Gender.Male,
                    color = MaterialTheme.colors.primaryVariant,
                    selectedTextColor = Color.White,
                    onClick = {
                        viewModel.onGenderClick(Gender.Male)
                    },
                    textStyle = MaterialTheme.typography.button.copy(
                        fontWeight = FontWeight.Normal
                    ),
                    icon = com.aayar94.core.R.drawable.ic_man
                )
                Spacer(modifier = Modifier.width(spacing.spaceMedium))
                GenderButton(
                    text = stringResource(id = com.aayar94.core.R.string.female),
                    isSelected = viewModel.selectedGender is Gender.Female,
                    color = MaterialTheme.colors.primaryVariant,
                    selectedTextColor = Color.White,
                    onClick = {
                        viewModel.onGenderClick(Gender.Female)
                    },
                    textStyle = MaterialTheme.typography.button.copy(
                        fontWeight = FontWeight.Normal
                    ),
                    icon = com.aayar94.core.R.drawable.ic_woman
                )
            }
        }
        ActionButton(
            text = stringResource(id = com.aayar94.core.R.string.next),
            onClick = viewModel::onNextClick,
            modifier = Modifier.fillMaxWidth().padding(horizontal = spacing.spaceMedium).align(Alignment.BottomCenter)
        )
    }
}