package com.soulvana.pinterestplus.view_models

class ToolBarViewModel : BaseViewModel() {

    var toolbarTitle: String = ""

    fun bind(title: String) {
        toolbarTitle = title
    }
}