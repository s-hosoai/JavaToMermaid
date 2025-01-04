package org.example;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.IfStatement;

import java.io.File;
import java.nio.file.Files;

public class JavaParser {
    public String convert(File file) {

        ASTParser parser = ASTParser.newParser(AST.JLS22);
        try {
            var lines = Files.readAllLines(file.getAbsoluteFile().toPath());
            String text = Files.readString(file.toPath());
            parser.setSource(text.toCharArray());
            var visitor = new ControllFlowVisitor();
            var ast = parser.createAST(new NullProgressMonitor());
            ast.accept(visitor);
            System.out.println("Parse finished");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return "";
    }

    class ControllFlowVisitor extends ASTVisitor {
        @Override
        public boolean visit(IfStatement node) {
            System.out.println("IF Statement");
            return super.visit(node);
        }
    }
}
