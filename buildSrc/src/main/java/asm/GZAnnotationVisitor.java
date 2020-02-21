package asm;

import org.objectweb.asm.AnnotationVisitor;

/**
 * @author holy
 */
public class GZAnnotationVisitor extends AnnotationVisitor {

    public String annotationValue;

    public GZAnnotationVisitor(int api) {
        super(api);
    }

    public GZAnnotationVisitor(int api, AnnotationVisitor av) {
        super(api, av);
        GZLog.log("-------- ===== GZAnnotationVisitor constructor ===== --------");
    }

    public GZAnnotationVisitor(int api, AnnotationVisitor av, String desc) {
        super(api, av);
    }

    @Override
    public void visit(String name, Object value) {
        super.visit(name, value);
        GZLog.log("==== GZAnnotationVisitor visit ====");
        GZLog.log("==== AnnotationVisitor.visit.value ==== " + value);
        annotationValue = value.toString();
    }

    @Override
    public void visitEnd() {
        super.visitEnd();
        GZLog.log("==== GZAnnotationVisitor.visitEnd ====");
    }
}
