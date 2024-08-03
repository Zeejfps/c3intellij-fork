package org.c3lang.intellij.projectWizard;

import com.intellij.facet.ui.ValidationResult;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.NlsContexts;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.platform.DirectoryProjectGenerator;
import com.intellij.platform.ProjectGeneratorPeer;
import org.c3lang.intellij.C3Icons;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class C3ProjectGenerator implements DirectoryProjectGenerator<C3ProjectGeneratorSettings> {

    @Override
    public @NotNull @NlsContexts.Label String getName() {
        return "C3 Project Generator";
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
        return null;
    }
    
    @Override
    public @NotNull ProjectGeneratorPeer<C3ProjectGeneratorSettings> createPeer() {
        return new C3ProjectGeneratorPeer();
    }
}

