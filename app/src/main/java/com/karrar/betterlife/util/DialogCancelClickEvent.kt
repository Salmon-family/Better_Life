package com.karrar.betterlife.util

sealed class DialogCancelClickEvent {
    object OnDialogCancelClick : DialogCancelClickEvent()
    object OnHabitUpdateClick : DialogCancelClickEvent()
    object OnHabitAddClick : DialogCancelClickEvent()
}