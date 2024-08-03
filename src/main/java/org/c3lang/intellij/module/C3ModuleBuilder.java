package org.c3lang.intellij.module;

import com.intellij.ide.util.projectWizard.ModuleBuilder;
import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import com.intellij.ide.util.projectWizard.WizardContext;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.openapi.util.Disposer;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.Nullable;

public class C3ModuleBuilder extends ModuleBuilder {

    
    public void setupRootModel(ModifiableRootModel modifiableRootModel)
            throws ConfigurationException {
        
        modifiableRootModel.clear();
    }

    @Override
    public @Nullable @NonNls String getBuilderId() {
        return "c3builder";
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
    
//    @Override
//    public boolean isTemplateBased() {
//        return true;
//    }

//    @Override
//    public ModuleWizardStep[] createWizardSteps(
//            @NotNull WizardContext wizardContext,
//            @NotNull ModulesProvider modulesProvider) {
//        return new ModuleWizardStep[]{
//                new C3ProjectWizardStep()
//                new ModuleWizardStep() {
//            @Override
//            public JComponent getComponent() {
//                return new JLabel("Put your content here");
//            }
//
//            @Override
//            public void updateDataModel() {
//
//            }
//        }
//        };
//    }
}
