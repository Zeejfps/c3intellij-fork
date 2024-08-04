package org.c3lang.intellij.projectWizard;

import com.intellij.facet.ui.ValidationResult;
import com.intellij.ide.util.projectWizard.AbstractNewProjectStep;
import com.intellij.ide.util.projectWizard.CustomStepProjectGenerator;
import com.intellij.ide.util.projectWizard.ProjectSettingsStepBase;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.NlsContexts;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.wm.impl.welcomeScreen.AbstractActionWithPanel;
import com.intellij.platform.DirectoryProjectGenerator;
import com.intellij.platform.DirectoryProjectGeneratorBase;
import org.c3lang.intellij.C3Icons;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class C3CustomStepProjectGenerator implements
        DirectoryProjectGenerator<C3ProjectGeneratorSettings>,
        CustomStepProjectGenerator<C3ProjectGeneratorSettings> {
    @Override
    public AbstractActionWithPanel createStep(DirectoryProjectGenerator<C3ProjectGeneratorSettings> directoryProjectGenerator, AbstractNewProjectStep.AbstractCallback<C3ProjectGeneratorSettings> abstractCallback) {
        return new ProjectSettingsStepBase<C3ProjectGeneratorSettings>(this, abstractCallback);
    }

    @Override
    public @NotNull @NlsContexts.Label String getName() {
        return "C3";
    }

    @Override
    public @Nullable Icon getLogo() {
        return C3Icons.FILE;
    }

    @Override
    public void generateProject(@NotNull Project project, @NotNull VirtualFile virtualFile, @NotNull C3ProjectGeneratorSettings c3ProjectGeneratorSettings, @NotNull Module module) {

    }

    @Override
    public @NotNull ValidationResult validate(@NotNull String s) {
        return ValidationResult.OK;
    }
}
