package com.mleiva.calendar_events.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.mleiva.calendar_events.ui.screens.home.HomeViewModel

/***
 * Project: calendar-events
 * From: com.mleiva.calendar_events.ui.common
 * Creted by: Marcelo Leiva on 20-08-2024 at 16:49
 ***/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpinnerDropdown(viewModel: HomeViewModel) {
    var expanded by remember { mutableStateOf(false) } // Controla la expansión del menú

    // Muestra el nombre visible del elemento seleccionado
    val selectedItem = viewModel.selectedItem.value

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        TextField(
            value = selectedItem.displayName,
            onValueChange = {},
            readOnly = true,
            label = { Text("Select a country") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true}
                .menuAnchor()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            viewModel.items.forEach { item ->
                DropdownMenuItem(
                    text = { Text(text = item.displayName) },
                    onClick = {
                        viewModel.onItemSelected(item)
                        expanded = false
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}