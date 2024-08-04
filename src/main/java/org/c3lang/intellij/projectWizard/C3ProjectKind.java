package org.c3lang.intellij.projectWizard;

public enum C3ProjectKind {
    Exe("init"),
    Lib("init-lib");
    
    private final String command;
    
    C3ProjectKind(String command) {
        this.command = command;
    }
    
    public String getCommand() {
        return command;
    }
}
