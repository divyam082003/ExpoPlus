package com.gd.expoplus.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.gd.expoplus.utils.ExpenseCategories

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddExpenseScreen(

    onSaveClick: (
        category: String,
        amount: Double,
        note: String
    ) -> Unit,
    onBackClick : () -> Unit
)
{
    val categories = ExpenseCategories.list

    var category by remember {
        mutableStateOf("")
    }

    var amount by remember {
        mutableStateOf("")
    }

    var note by remember {
        mutableStateOf("")
    }

    var categoryError by remember {
        mutableStateOf(false)
    }

    var amountError by remember {
        mutableStateOf(false)
    }

    Scaffold(

        containerColor = Color(0xFF120704),

        topBar = {

            TopAppBar(

                title = {

                    Text(
                        text = "Add Expense",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                },

                navigationIcon = {

                    IconButton(
                        onClick = onBackClick
                    ) {

                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF120704)
                )
            )
        }

    ) { paddingValues ->

        LazyColumn(

            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),

            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            item {

                Spacer(modifier = Modifier.height(8.dp))
            }

            item {

                Text(
                    text = "Category",
                    color = Color(0xFF63E65C),
                    style = MaterialTheme.typography.titleMedium
                )

                if (categoryError) {

                    Text(
                        text = "Please select category",
                        color = Color.Red,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            item {

                LazyVerticalGrid(

                    columns = GridCells.Fixed(3),

                    horizontalArrangement = Arrangement.spacedBy(10.dp),

                    verticalArrangement = Arrangement.spacedBy(5.dp),

                    modifier = Modifier.height(150.dp)

                ) {

                    items(categories.size) { index ->

                        val selectedCategory = categories[index]

                        val isSelected =
                            category == selectedCategory

                        FilterChip(

                            selected = isSelected,

                            onClick = {

                                category = selectedCategory
                            },

                            label = {

                                Text(selectedCategory)
                            },

                            modifier = Modifier.fillMaxWidth(),

                            colors = FilterChipDefaults.filterChipColors(

                                selectedContainerColor =
                                    Color(0xFF63E65C),

                                selectedLabelColor =
                                    Color.Black,

                                containerColor =
                                    Color(0xFF1E1E1E),

                                labelColor =
                                    Color.White
                            )
                        )
                    }
                }
            }


            item {

                Text(
                    text = "Amount",
                    color = Color(0xFF63E65C),
                    style = MaterialTheme.typography.titleMedium
                )
            }

            item {

                OutlinedTextField(

                    value = amount,

                    onValueChange = { value ->

                        if (
                            value.all {
                                it.isDigit() || it == '.'
                            }
                        ) {

                            amount = value
                        }
                    },

                    isError = amountError,

                    singleLine = true,

                    leadingIcon = {

                        Text(
                            text = "₹",
                            color = Color.White
                        )
                    },

                    textStyle = MaterialTheme.typography.headlineSmall.copy(
                        color = Color.White
                    ),

                    placeholder = {

                        Text(
                            text = "0.00",
                            color = Color.Gray
                        )
                    },

                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),

                    shape = RoundedCornerShape(18.dp),

                    colors = OutlinedTextFieldDefaults.colors(

                        focusedBorderColor =
                            Color(0xFF63E65C),

                        unfocusedBorderColor =
                            Color.DarkGray,

                        focusedTextColor =
                            Color.White,

                        unfocusedTextColor =
                            Color.White,

                        cursorColor =
                            Color(0xFF63E65C)
                    ),

                    modifier = Modifier.fillMaxWidth()
                )
                if (amountError) {

                    Text(
                        text = "Enter valid amount",
                        color = Color.Red,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            item {

                Text(
                    text = "Note",
                    color = Color(0xFF63E65C),
                    style = MaterialTheme.typography.titleMedium
                )
            }

            item {

                OutlinedTextField(

                    value = note,

                    onValueChange = {

                        note = it
                    },

                    placeholder = {

                        Text(
                            text = "Add a note...",
                            color = Color.Gray
                        )
                    },

                    shape = RoundedCornerShape(18.dp),

                    colors = OutlinedTextFieldDefaults.colors(

                        focusedBorderColor =
                            Color(0xFF63E65C),

                        unfocusedBorderColor =
                            Color.DarkGray,

                        focusedTextColor =
                            Color.White,

                        unfocusedTextColor =
                            Color.White,

                        cursorColor =
                            Color(0xFF63E65C)
                    ),

                    modifier = Modifier.fillMaxWidth()
                )
            }

            item {

                Spacer(modifier = Modifier.height(12.dp))
            }

            item {

                Button(

                    onClick = {

                        categoryError = category.isEmpty()

                        amountError =
                            amount.isEmpty() ||
                                    amount.toDoubleOrNull() == null

                        if (!categoryError && !amountError) {

                            onSaveClick(

                                category,

                                amount.toDoubleOrNull() ?: 0.0,

                                note
                            )
                        }
                    },

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(58.dp),

                    shape = RoundedCornerShape(18.dp),

                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF63E65C)
                    )
                ) {

                    Text(
                        text = "Save Expense",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            item {

                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}