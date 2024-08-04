package org.c3lang.intellij.sdk;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.process.ColoredProcessHandler;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.VirtualFile;

public class C3Sdk {
    
    // For testing only, will be deleted
    static final String TEST_SDK_PATH = ""; 
    static final String COMPILER_EXE_NAME = "c3c.exe";
    
    public void initBinaryProject(VirtualFile projectPath, String projectName) {
        GeneralCommandLine cmdLine = new GeneralCommandLine();
        cmdLine.setExePath(FileUtil.toSystemDependentName(TEST_SDK_PATH + "/" + COMPILER_EXE_NAME));
        cmdLine.setWorkDirectory(projectPath.getPath());
        cmdLine.addParameter("init");
        cmdLine.addParameter(projectName);
        System.out.println(cmdLine.getCommandLineString());
        try {
            ColoredProcessHandler handler = new ColoredProcessHandler(cmdLine);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
