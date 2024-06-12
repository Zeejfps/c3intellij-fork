// This is a generated file. Not intended for manual editing.
package org.c3lang.intellij.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface C3TopLevel extends PsiElement {

  @Nullable
  C3Attributes getAttributes();

  @Nullable
  C3ConstDecl getConstDecl();

  @Nullable
  C3CtAssertStmt getCtAssertStmt();

  @Nullable
  C3CtEchoStmt getCtEchoStmt();

  @Nullable
  C3CtIncludeStmt getCtIncludeStmt();

  @Nullable
  C3DefDecl getDefDecl();

  @Nullable
  C3DistinctDeclaration getDistinctDeclaration();

  @Nullable
  C3Expr getExpr();

  @Nullable
  C3FaultDeclaration getFaultDeclaration();

  @Nullable
  C3FuncDefinition getFuncDefinition();

  @Nullable
  C3GlobalDecl getGlobalDecl();

  @Nullable
  C3ImportDecl getImportDecl();

  @Nullable
  C3InterfaceDefinition getInterfaceDefinition();

  @Nullable
  C3MacroDefinition getMacroDefinition();

  @Nullable
  C3TypeDecl getTypeDecl();

}
