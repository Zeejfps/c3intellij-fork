package org.c3lang.intellij;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.platform.lsp.api.LspServerSupportProvider;
import org.jetbrains.annotations.NotNull;

public class C3LspServerSupportProvider implements LspServerSupportProvider {

    @Override
    public void fileOpened(@NotNull Project project, @NotNull VirtualFile virtualFile, @NotNull LspServerSupportProvider.LspServerStarter lspServerStarter) {


        String extension = virtualFile.getExtension();
        if (extension == null)
            return;
        
        boolean isFileSupported = extension.equals("c3");
        if (isFileSupported) {
            lspServerStarter.ensureServerStarted(new C3LspServerDescriptor(project));
        }
    }
}
