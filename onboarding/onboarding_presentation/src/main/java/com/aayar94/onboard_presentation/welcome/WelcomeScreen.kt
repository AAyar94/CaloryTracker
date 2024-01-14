package com.aayar94.onboard_presentation.welcome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.aayar94.core_ui.LocalSpacing
import com.aayar94.onboard_presentation.component.ActionButton
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun WelcomeScreen(
    onNextClick: () -> Unit
) {
    val spacing = LocalSpacing.current
    val context = LocalContext.current
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(MaterialTheme.colors.surface)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.spaceMedium),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = com.aayar94.core.R.string.welcome_text),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h1
        )
        Spacer(modifier = Modifier.height(spacing.spaceMedium))
        ActionButton(
            text = stringResource(id = com.aayar94.core.R.string.next),
            onClick = { onNextClick() },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}