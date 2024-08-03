package org.c3lang.intellij.projectWizard;

import com.intellij.ide.projectWizard.ProjectCategory;
import com.intellij.ide.util.projectWizard.ModuleBuilder;
import com.intellij.ide.util.projectWizard.WizardContext;
import com.intellij.ide.wizard.LanguageNewProjectWizard;
import com.intellij.ide.wizard.NewProjectWizardLanguageStep;
import com.intellij.ide.wizard.NewProjectWizardStep;
import com.intellij.openapi.observable.properties.PropertyGraph;
import com.intellij.openapi.util.UserDataHolder;
import com.intellij.ui.dsl.builder.Panel;
import com.intellij.ui.dsl.builder.SegmentedButton;
import kotlin.Unit;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class C3ProjectWizard extends ProjectCategory implements LanguageNewProjectWizard {
    @NotNull
    @Override
    public String getName() {
        return "C3";
    }

    @NotNull
    @Override
    public NewProjectWizardStep createStep(@NotNull NewProjectWizardLanguageStep newProjectWizardLanguageStep) {
        return new C3NewProjectWizardStep();
    }

    @Override
    public @NotNull ModuleBuilder createModuleBuilder() {
        return new C3ModuleBuilder();
    }
}

class C3NewProjectWizardStep implements NewProjectWizardStep {

    @NotNull
    @Override
    public WizardContext getContext() {
        return null;
    }

    @NotNull
    @Override
    public PropertyGraph getPropertyGraph() {
        return null;
    }

    @NotNull
    @Override
    public Keywords getKeywords() {
        return null;
    }

    @NotNull
    @Override
    public UserDataHolder getData() {
        return null;
    }

    @Override
    public void setupUI(@NotNull Panel builder) {
        builder.row("Type", row -> {
            ArrayList<String> buttons = new ArrayList<>();
            buttons.add("bin");
            buttons.add("lib");
            row.segmentedButton(buttons, s -> {
                return s;
            });
            
            return Unit.INSTANCE;
        });
    }
}
