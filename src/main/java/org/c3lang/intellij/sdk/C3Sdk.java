package org.c3lang.intellij.sdk;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.process.ColoredProcessHandler;
import com.intellij.openapi.util.io.FileUtil;
import org.c3lang.intellij.C3SettingsState;
import org.c3lang.intellij.projectWizard.C3ProjectKind;

public class C3Sdk {
    
    
    public ColoredProcessHandler initBinaryProject(String projectPath, String projectName, C3ProjectKind kind) {
        String pathTiCompiler = C3SettingsState.getInstance().sdk;
        GeneralCommandLine cmdLine = new GeneralCommandLine();
        cmdLine.setExePath(FileUtil.toSystemDependentName(pathTiCompiler));
        cmdLine.setWorkDirectory(projectPath);
        cmdLine.addParameter(kind.getCommand());
        cmdLine.addParameter(projectName);
        System.out.println(cmdLine.getCommandLineString());
        try {
            ColoredProcessHandler handler = new ColoredProcessHandler(cmdLine);
            return handler;
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
