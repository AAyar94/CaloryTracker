package com.aayar94.calorytracker.navigation

import androidx.navigation.NavController
import com.aaayar94.core.util.UiEvent

fun NavController.navigate(event: UiEvent.Navigate) {
    this.navigate(event.route)
}