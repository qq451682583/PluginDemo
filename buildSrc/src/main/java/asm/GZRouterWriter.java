package asm;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.util.HashMap;
import java.util.Map;

/**
 * 生成路由class文件
 *
 * @author holy
 */
public class GZRouterWriter implements Opcodes {

    public byte[] generateClass(String pkg, HashMap<String, String> metas) {

        GZLog.log("!!!!!!!!!!!!!--------- GZRouterWriter generateClass ----------!!!!!!!!!!!! = " + metas.size());

        ClassWriter cw = new ClassWriter(0);
        FieldVisitor fv;
        MethodVisitor mv;

        GZLog.log("!!!!!!!!!!!!!--------- GZRouterWriter generate super class ----------!!!!!!!!!!!! ");
        cw.visit(Opcodes.V1_7, Opcodes.ACC_PUBLIC + Opcodes.ACC_SUPER, pkg, null, "java/lang/Object", null);


        GZLog.log("!!!!!!!!!!!!!--------- GZRouterWriter generate static field ----------!!!!!!!!!!!! ");
        fv = cw.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, "map", "Ljava/util/HashMap;", "Ljava/util/HashMap<Ljava/lang/String;Ljava/lang/String;>;", null);
        fv.visitEnd();


        GZLog.log("!!!!!!!!!!!!!--------- GZRouterWriter generate constructor ----------!!!!!!!!!!!! ");
        mv = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
        mv.visitCode();
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(1, 1);
        mv.visitEnd();


        GZLog.log("!!!!!!!!!!!!!--------- GZRouterWriter generate getMap ----------!!!!!!!!!!!! ");
        mv = cw.visitMethod(Opcodes.ACC_PUBLIC, "getMap", "()Ljava/util/HashMap;", "()Ljava/util/HashMap<Ljava/lang/String;Ljava/lang/String;>;", null);
        mv.visitCode();
        mv.visitFieldInsn(Opcodes.GETSTATIC, pkg, "map", "Ljava/util/HashMap;");
        mv.visitInsn(Opcodes.ARETURN);
        mv.visitMaxs(1, 1);
        mv.visitEnd();

        GZLog.log("!!!!!!!!!!!!!--------- GZRouterWriter generate static module ----------!!!!!!!!!!!! ");
        mv = cw.visitMethod(Opcodes.ACC_STATIC, "<clinit>", "()V", null, null);
        mv.visitCode();
        mv.visitTypeInsn(Opcodes.NEW, "java/util/HashMap");
        mv.visitInsn(Opcodes.DUP);
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/util/HashMap", "<init>", "()V", false);
        mv.visitFieldInsn(Opcodes.PUTSTATIC, pkg, "map", "Ljava/util/HashMap;");


        GZLog.log("!!!!!!!!!!!!!--------- GZRouterWriter generate static write data ----------!!!!!!!!!!!! ");
        for (Map.Entry<String, String> entrySet : metas.entrySet()) {
            String key = entrySet.getKey();
            String value = entrySet.getValue();
            GZLog.log("=== key === " + key);
            GZLog.log("=== value === " + value);
            mv.visitFieldInsn(Opcodes.GETSTATIC, pkg, "map", "Ljava/util/HashMap;");
            mv.visitLdcInsn(key);
            mv.visitLdcInsn(value);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/util/HashMap", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", false);
            mv.visitInsn(Opcodes.POP);
        }
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(3, 0);
        mv.visitEnd();

        cw.visitEnd();
        GZLog.log("!!!!!!!!!!!!!--------- GZRouterWriter generate end ----------!!!!!!!!!!!! ");
        return cw.toByteArray();
    }
}