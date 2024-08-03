package org.c3lang.intellij.projectWizard;

import com.intellij.openapi.module.ModuleType;
import org.c3lang.intellij.C3Icons;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class C3ModuleType extends ModuleType<C3ModuleBuilder> {

    static C3ModuleType Instance = new C3ModuleType();

    protected C3ModuleType() {
        super("MY_MODULE");
    }

    @Override
    public @NotNull C3ModuleBuilder createModuleBuilder() {
        return new C3ModuleBuilder();
    }

    @Override
    public @NotNull @Nls(capitalization = Nls.Capitalization.Title) String getName() {
        return "C3 bin";
    }

    @Override
    public @NotNull @Nls(capitalization = Nls.Capitalization.Sentence) String getDescription() {
        return "C3 Description";
    }

    @Override
    public @NotNull Icon getNodeIcon(boolean b) {
        return C3Icons.FILE;
    }
    
    public static C3ModuleType getInstance() {
        return Instance;
    }
}