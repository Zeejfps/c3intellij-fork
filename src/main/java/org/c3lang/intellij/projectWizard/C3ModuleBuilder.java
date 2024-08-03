package org.c3lang.intellij.projectWizard;

import com.intellij.ide.util.projectWizard.*;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.roots.ui.configuration.ModulesProvider;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.util.NlsContexts;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class C3ModuleBuilder extends ModuleBuilder {

    private static final String BUILDER_ID = "c3builder";


    @Override
    public @Nullable @NonNls String getBuilderId() {
        return BUILDER_ID;
    }

    @Override
    public ModuleType<?> getModuleType() {
        return C3ModuleType.getInstance();
    }


    @Nullable
    @Override
    public ModuleWizardStep getCustomOptionsStep(final WizardContext context, final Disposable parentDisposable) {
        final C3ModuleWizardStep step = new C3ModuleWizardStep();
        Disposer.register(parentDisposable, step);
        return step;
    }

    @Override
    public ModuleWizardStep[] createWizardSteps(@NotNull WizardContext wizardContext, @NotNull ModulesProvider modulesProvider) {
        return new ModuleWizardStep[0];
    }

    @Override
    public @Nullable ModuleWizardStep modifySettingsStep(@NotNull SettingsStep settingsStep) {
        
        return null;
    }
    
    @Override
    public ModuleWizardStep modifyProjectTypeStep(@NotNull SettingsStep settingsStep) {
        return null;
    }
    
    @Override
    protected @NotNull List<WizardInputField<?>> getAdditionalFields() {
        ArrayList<WizardInputField<?>> test = new ArrayList<WizardInputField<?>>();
        test.add(
            new WizardInputField<TextFieldWithBrowseButton>("asdf", "wasd") {
                @Override
                public @NlsContexts.Label String getLabel() {
                    return "Test";
                }

                @Override
                public TextFieldWithBrowseButton getComponent() {
                    return new TextFieldWithBrowseButton();
                }

                @Override
                public String getValue() {
                    return "wfwd";
                }
            }
        );
        return test;
    }

    @Override
    public boolean isTemplateBased() {
        return true;
    }
}
