package org.c3lang.intellij;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.platform.lsp.api.ProjectWideLspServerDescriptor;
import org.jetbrains.annotations.NotNull;

public class C3LspServerDescriptor extends ProjectWideLspServerDescriptor {
    public C3LspServerDescriptor(@NotNull Project project) {
        super(project, "c3 lsp");
    }

    @NotNull
    @Override
    public GeneralCommandLine createCommandLine() throws ExecutionException {
        return new GeneralCommandLine("");
    }

    @Override
    public boolean isSupportedFile(@NotNull VirtualFile virtualFile) {
        String extension = virtualFile.getExtension();
        if (extension == null)
            return false;

        return extension.equals("c3");
    }
}