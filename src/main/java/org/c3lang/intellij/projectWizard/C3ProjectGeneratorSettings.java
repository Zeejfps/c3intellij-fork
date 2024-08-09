package org.c3lang.intellij.projectWizard;

public class C3ProjectGeneratorSettings {

    private C3ProjectKind projectKind = C3ProjectKind.Exe;
    
    public void setProjectKind(C3ProjectKind projectKind) {
        this.projectKind = projectKind;
    }

    public C3ProjectKind getProjectKind() {
        return projectKind;
    }
}
