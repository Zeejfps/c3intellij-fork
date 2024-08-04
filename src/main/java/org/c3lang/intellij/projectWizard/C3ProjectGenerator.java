package org.c3lang.intellij.projectWizard;

import com.intellij.ide.util.projectWizard.WebProjectTemplate;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.NlsContexts;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.platform.ProjectGeneratorPeer;
import org.c3lang.intellij.C3Icons;
import org.c3lang.intellij.sdk.C3Sdk;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class C3ProjectGenerator extends WebProjectTemplate<C3ProjectGeneratorSettings> {

    @Override
    public @NotNull @NlsContexts.Label String getName() {
        return "C3";
    }
    
    @Override
    public @NlsContexts.DetailedDescription String getDescription() {
        return "Asdf";
    }

    @Override
    public @Nullable Icon getLogo() {
        return C3Icons.FILE;
    }

    @Override
    public @NotNull ProjectGeneratorPeer<C3ProjectGeneratorSettings> createPeer() {
        return new C3ProjectGeneratorPeer();
    }

    @Override
    public void generateProject(@NotNull Project project, @NotNull VirtualFile baseDir, @NotNull C3ProjectGeneratorSettings settings, @NotNull Module module) {
        C3Sdk sdk = new C3Sdk();
        String projectPath = baseDir.getPath();
        sdk.initBinaryProject(projectPath, settings.getProjectName(), settings.getProjectKind());
    }
}

