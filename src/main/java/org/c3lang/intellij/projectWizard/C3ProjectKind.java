package org.c3lang.intellij.projectWizard;

public enum C3ProjectKind {
    Bin("init"),
    Lib("init-lub");
    
    private final String command;
    
    C3ProjectKind(String command) {
        this.command = command;
    }
    
    public String getCommand() {
        return command;
    }
}
