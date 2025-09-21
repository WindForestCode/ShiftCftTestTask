package com.myschool.shiftcft.presentation.adapter.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MainScreen(modifier: Modifier = Modifier) {

    Scaffold(
        modifier = modifier.fillMaxSize(),
        content = { innerPadding ->
            Column(
                modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                Text("Hello Compose")
            }
        }

    )

}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen()
}