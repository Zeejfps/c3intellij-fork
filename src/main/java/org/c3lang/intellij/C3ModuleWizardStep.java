package org.c3lang.intellij;

import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import com.intellij.openapi.Disposable;

import javax.swing.*;

public class C3ModuleWizardStep extends ModuleWizardStep implements Disposable {
    
    private C3ModuleSettingsForm moduleSettingsForm;
    
    public C3ModuleWizardStep() {
        moduleSettingsForm = new C3ModuleSettingsForm();
    }
    
    @Override
    public JComponent getComponent() {
        return moduleSettingsForm.getComponent();
    }

    @Override
    public void updateDataModel() {

    }

    @Override
    public void dispose() {

    }
}
