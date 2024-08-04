package org.c3lang.intellij.projectWizard;

import com.intellij.ide.util.projectWizard.AbstractModuleBuilder;
import com.intellij.openapi.ui.ValidationInfo;
import com.intellij.openapi.util.NlsContexts;
import com.intellij.platform.ProjectTemplate;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class C3ProjectTemplate implements ProjectTemplate {

    @Override
    public @NotNull @NlsContexts.Label String getName() {
        return "";
    }

    @Override
    public @Nullable @NlsContexts.DetailedDescription String getDescription() {
        return "";
    }

    @Override
    public Icon getIcon() {
        return null;
    }

    @Override
    public @NotNull AbstractModuleBuilder createModuleBuilder() {
        return null;
    }

    @Override
    public @Nullable ValidationInfo validateSettings() {
        return null;
    }
}
