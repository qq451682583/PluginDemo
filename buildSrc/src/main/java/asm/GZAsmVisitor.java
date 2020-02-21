package asm;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import com.android.grouter_annotaion.GProtocol;

/**
 * 对class文件进行处理
 *
 * @author holy
 */
public class GZAsmVisitor extends ClassVisitor {

    /** 如果当前class有协议注解的时候 */
    public GZAnnotationVisitor mProtocolAnnotation;
    public String protocolActivityName;
    public GZMethodVisitor mMethodVisitor;

    public GZAsmVisitor(int api) {
        super(api);
    }

    public GZAsmVisitor(int api, ClassVisitor cv) {
        super(api, cv);
        GZLog.log("------------------- GZAsmVisitor constructor -----------------");
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
        GZLog.log("====== GZAsmVisitor visit ======");
        // com/android/plugindemo/MainActivity
        GZLog.log("=== visit.name === " + name);

        protocolActivityName = name.replace("/", ".");

        GZLog.log("====---- name经过转换之后 == " + protocolActivityName);
    }

    @Override
    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
        GZLog.log("=====---------- GZAsmVisitor visitAnnotation ----------=====");
        GZLog.log("=== visitAnnotation.desc === " + desc);
        AnnotationVisitor annotationVisitor = super.visitAnnotation(desc, visible);

        // 如果注解不为空的话
        if (Type.getDescriptor(GProtocol.class).equals(desc)) {
            mProtocolAnnotation = new GZAnnotationVisitor(Opcodes.ASM5, annotationVisitor, desc);
            return mProtocolAnnotation;
        }
        return annotationVisitor;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        GZLog.log("=====---------- visitMethod ----------=====");
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
        mMethodVisitor = new GZMethodVisitor(Opcodes.ASM5, mv, access, name, desc);
        return mMethodVisitor;
    }

    @Override
    public void visitEnd() {
        super.visitEnd();
        GZLog.log("====== GZAsmVisitor visitEnd ======");
        GZLog.log(".");
        GZLog.log(".");
        GZLog.log(".");
        GZLog.log(".");
    }
}
