package org.c3lang.intellij.projectWizard

import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.dsl.builder.panel

fun demoBasics(): DialogPanel {
    return panel {
        row("Type") {
            segmentedButton(items = listOf("bin", "lib"), renderer = {
                it
            })
        }
    }
}