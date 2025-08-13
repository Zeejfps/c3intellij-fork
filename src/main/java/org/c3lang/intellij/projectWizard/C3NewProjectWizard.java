package org.c3lang.intellij.projectWizard;

import com.intellij.ide.wizard.AbstractNewProjectWizardStep;
import com.intellij.ide.wizard.LanguageNewProjectWizard;
import com.intellij.ide.wizard.NewProjectWizardLanguageStep;
import com.intellij.ide.wizard.NewProjectWizardStep;
import org.jetbrains.annotations.NotNull;

public class C3NewProjectWizard implements LanguageNewProjectWizard {
    @NotNull
    @Override
    public String getName() {
        return "C3";
    }

    @NotNull
    @Override
    public NewProjectWizardStep createStep(@NotNull NewProjectWizardLanguageStep newProjectWizardLanguageStep) {
        return new Step(newProjectWizardLanguageStep);
    }
    
    private class Step extends AbstractNewProjectWizardStep {

        public Step(@NotNull NewProjectWizardStep parentStep) {
            super(parentStep);
        }
        
        
    }
}
