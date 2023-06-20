package com.sample.ui.home
class HomeScreenState(val state: WorkingStatus)

enum class WorkingStatus {
    Loaded,
    Loading,
}