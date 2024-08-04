package org.c3lang.intellij.projectWizard

import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.dsl.builder.RowLayout
import com.intellij.ui.dsl.builder.panel

fun demoBasics(): DialogPanel {
    return panel {
        row("Test") {
            segmentedButton(items = listOf("bin", "lib"), renderer = {
                it
            })
        }.layout(RowLayout.PARENT_GRID)
    }
}