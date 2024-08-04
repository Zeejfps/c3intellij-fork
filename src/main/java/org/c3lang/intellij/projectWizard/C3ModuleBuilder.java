package org.c3lang.intellij.projectWizard;

//import com.intellij.ide.projectWizard.ProjectSettingsStep;
import com.intellij.ide.util.projectWizard.ModuleBuilder;
import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import com.intellij.ide.util.projectWizard.SettingsStep;
import com.intellij.ide.util.projectWizard.WizardContext;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.roots.ui.configuration.ModulesProvider;
import com.intellij.openapi.ui.DialogPanel;
import com.intellij.openapi.util.Disposer;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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

    @Override
    public boolean isOpenProjectSettingsAfter() {
        return false;
    }

    @Nullable
    @Override
    public ModuleWizardStep getCustomOptionsStep(final WizardContext context, final Disposable parentDisposable) {
        return new ModuleWizardStep() {
            @Override
            public JComponent getComponent() {
                DialogPanel panel = UitestKt.demoBasics();
                return panel;
            }

            @Override
            public void updateDataModel() {

            }
        };

//        return super.getCustomOptionsStep()
//        final C3ModuleWizardStep step = new C3ModuleWizardStep();
//        Disposer.register(parentDisposable, step);
//        return step;
//        context.requestNextStep();
//        return null;
//            return new ProjectSettingsStep(context);
//        return null;
    }

    @Override
    public ModuleWizardStep[] createWizardSteps(@NotNull WizardContext wizardContext, @NotNull ModulesProvider modulesProvider) {
        return ModuleWizardStep.EMPTY_ARRAY;
    }

//    @Override
//    public @Nullable ModuleWizardStep modifySettingsStep(@NotNull SettingsStep settingsStep) {
//        DialogPanel panel = UitestKt.demoBasics();
//        settingsStep.addSettingsComponent(panel);
//        return null;
//    }

//    @Override
//    public ModuleWizardStep modifyProjectTypeStep(@NotNull SettingsStep settingsStep) {
//        return null;
//    }
    
//    @Override
//    protected @NotNull List<WizardInputField<?>> getAdditionalFields() {
//        DialogPanel panel = DemoBasicsKt.demoBasics();
//        
//        ArrayList<WizardInputField<?>> test = new ArrayList<>();
//        test.add(
//            new WizardInputField<DialogPanel>("asdf", "wasd") {
//                @Override
//                public @NlsContexts.Label String getLabel() {
//                    return "Test";
//                }
//
//                @Override
//                public DialogPanel getComponent() {
//                    return panel;
//                }
//
//                @Override
//                public String getValue() {
//                    return "wfwd";
//                }
//            }
//        );
//        return test;
//    }

//    @Override
//    public boolean isTemplateBased() {
//        return true;
//    }

//    @Override
//    public @NotNull List<Class<? extends ModuleWizardStep>> getIgnoredSteps() {
//        return Collections.singletonList(ProjectSettingsStep.class);
//    }

//    @Override
//    public ModuleWizardStep[] createFinishingSteps(@NotNull WizardContext wizardContext, @NotNull ModulesProvider modulesProvider) {
//        return ModuleWizardStep.EMPTY_ARRAY;
//    }
}
