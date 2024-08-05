package org.c3lang.intellij.projectWizard;

import com.intellij.execution.process.ColoredProcessHandler;
import com.intellij.execution.process.ProcessAdapter;
import com.intellij.execution.process.ProcessEvent;
import com.intellij.ide.RecentProjectsManager;
import com.intellij.ide.impl.OpenProjectTask;
import com.intellij.ide.impl.OpenProjectTaskKt;
import com.intellij.ide.impl.TrustedPaths;
import com.intellij.ide.util.projectWizard.AbstractNewProjectStep;
import com.intellij.ide.util.projectWizard.ProjectSettingsStepBase;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.WriteAction;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.DumbService;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ex.ProjectManagerEx;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.util.UserDataHolder;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.wm.IdeFocusManager;
import com.intellij.openapi.wm.IdeFrame;
import com.intellij.platform.DirectoryProjectGenerator;
import com.intellij.platform.ProjectGeneratorPeer;
import kotlin.Unit;
import org.c3lang.intellij.sdk.C3Sdk;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Consumer;

public class C3ProjectSettingsStep extends ProjectSettingsStepBase<C3ProjectGeneratorSettings> {

    public C3ProjectSettingsStep(DirectoryProjectGenerator<C3ProjectGeneratorSettings> projectGenerator, AbstractNewProjectStep.AbstractCallback<C3ProjectGeneratorSettings> callback) {
        super(projectGenerator, new C3NewProjectStepCallback());
    }

    // NOTE(Zee): I need to hijack this here because I don't want to automatically have the IDE generate the folder
    static class C3NewProjectStepCallback extends AbstractNewProjectStep.AbstractCallback<C3ProjectGeneratorSettings> {
        @Override
        public void consume(@Nullable ProjectSettingsStepBase<C3ProjectGeneratorSettings> settingsStep, 
                            @NotNull ProjectGeneratorPeer<C3ProjectGeneratorSettings> projectGeneratorPeer) {

            C3ProjectGeneratorSettings actualSettings = projectGeneratorPeer.getSettings();
            String fullProjectPathStr = settingsStep.getProjectLocation();
            Path fullProjectPath = Paths.get(fullProjectPathStr);

            C3Sdk sdk = new C3Sdk();
            Path projectPath = fullProjectPath.getParent();
            String projectName = fullProjectPath.getFileName().toString();

            createProjectPathDirectories(projectPath);
            
            C3ProjectKind projectKind = actualSettings.getProjectKind();
            
            ColoredProcessHandler processHandler = sdk.initBinaryProject(projectPath.toString(), projectName, projectKind);
            processHandler.addProcessListener(new ProcessAdapter() {
                @Override
                public void processTerminated(@NotNull ProcessEvent event) {
                    Path finalFullProjectPath = fullProjectPath;
                    if (projectKind == C3ProjectKind.Lib) {
                        finalFullProjectPath = Path.of(projectPath.toString(), projectName + ".c3l");
                    }

                    System.out.println(finalFullProjectPath.toString());
                    VirtualFile baseDir = LocalFileSystem.getInstance().refreshAndFindFileByPath(FileUtil.toSystemIndependentName(finalFullProjectPath.toString()));

                    VfsUtil.markDirtyAndRefresh(false, true, true, baseDir);
                    RecentProjectsManager.getInstance().setLastProjectCreationLocation(projectPath);

                    setProjectAsTrusted(finalFullProjectPath);
                    Project project = openProject(finalFullProjectPath);

                    VirtualFile mainFile = LocalFileSystem.getInstance().refreshAndFindFileByPath(FileUtil.toSystemIndependentName(Path.of(finalFullProjectPath.toString(), "src", "main.c3").toString()));
                    System.out.println("Main File: " + mainFile);

                    if (mainFile != null) {
                        ApplicationManager.getApplication().invokeAndWait(() -> {
                            final FileEditorManager manager = FileEditorManager.getInstance(project);
                            manager.openFile(mainFile, true);
                        });
                    }
                }
            });

            processHandler.startNotify();        
        }
        
        private void setProjectAsTrusted(Path projectPath) {
            TrustedPaths.getInstance().setProjectPathTrusted(projectPath, true);
        }
        
        private Project openProject(Path projectPath) {
            IdeFrame frame = IdeFocusManager.getGlobalInstance().getLastFocusedFrame();
            Project projectToClose = frame != null ? frame.getProject() : null;
            OpenProjectTask openProjectTask = createOpenProjectOptions(projectToClose, null);
            return ProjectManagerEx.getInstanceEx().openProject(projectPath, openProjectTask);
        }

        private void createProjectPathDirectories(Path path) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        protected static @NotNull OpenProjectTask createOpenProjectOptions(@Nullable Project projectToClose, @Nullable Consumer<? super UserDataHolder> extraUserData) {

            return OpenProjectTaskKt.OpenProjectTask((builder) -> {
                builder.setProjectToClose(projectToClose);
                builder.setNewProject(true);
                builder.setRunConfigurators(true);
                builder.setProjectCreatedWithWizard(true);
                builder.setRefreshVfsNeeded(false);
                builder.withBeforeOpenCallback((project) -> {
                    if (extraUserData != null) {
                        extraUserData.accept(project);
                    }

                    // Code taken from AbstractNewProjectStep
                    project.putUserData(new Key<>("abstract.new.project.step.created"), true);
                    return true;
                });
                return Unit.INSTANCE;
            });
        }
    }
}
