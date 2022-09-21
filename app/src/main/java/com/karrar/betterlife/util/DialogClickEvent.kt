package com.karrar.betterlife.util

sealed class DialogClickEvent {
    object OnDialogClick : DialogClickEvent()
    object OnHabitUpdateClick : DialogClickEvent()
    object OnHabitAddClick : DialogClickEvent()
}