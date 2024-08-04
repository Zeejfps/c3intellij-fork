package org.c3lang.intellij.projectWizard;

import com.intellij.ide.util.projectWizard.AbstractModuleBuilder;
import com.intellij.ide.util.projectWizard.WizardContext;
import com.intellij.openapi.ui.ValidationInfo;
import com.intellij.openapi.util.NlsContexts;
import com.intellij.platform.ProjectTemplate;
import com.intellij.platform.ProjectTemplatesFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class C3ProjectTemplatesFactory extends ProjectTemplatesFactory {
    @Override
    public String @NotNull [] getGroups() {
        return new String[0];
    }

    @Override
    public ProjectTemplate @NotNull [] createTemplates(@Nullable String s, @NotNull WizardContext wizardContext) {
        return new ProjectTemplate[0];
    }
}

