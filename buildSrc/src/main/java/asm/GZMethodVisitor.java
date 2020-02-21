package asm;

import com.android.grouter_annotaion.GCost;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.AdviceAdapter;

/**
 * 插入统计代码
 *
 * @author holy
 */
public class GZMethodVisitor extends AdviceAdapter {

    protected GZMethodVisitor(int api, MethodVisitor mv, int access, String name, String desc) {
        super(api, mv, access, name, desc);
        this.mv = mv;
        methodName = name;
    }

    /**
     * 是否有注释可以插入代码
     */
    public boolean isInject;
    private String methodName;
    private MethodVisitor mv;

    @Override
    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
        if (Type.getDescriptor(GCost.class).equals(desc)) {
            isInject = true;
        }
        return super.visitAnnotation(desc, visible);
    }

    @Override
    protected void onMethodEnter() {
        if (isInject) {
            GZLog.log("====== 开始插入方法 = " + methodName);

            // GCostManager.addStartTime("xxxx", System.currentTimeMillis());
            mv.visitLdcInsn(methodName);
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "com/android/grouter/GCostManager", "addStartTime", "(Ljava/lang/String;J)V", false);
        }
    }

    @Override
    protected void onMethodExit(int opcode) {
        if (isInject) {
            // GCostManager.addEndTime("xxxx", System.currentTimeMillis());
            mv.visitLdcInsn(methodName);
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "com/android/grouter/GCostManager", "addEndTime", "(Ljava/lang/String;J)V", false);

            // GCostManager.startCost("xxxx");
            mv.visitLdcInsn(methodName);
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "com/android/grouter/GCostManager", "startCost", "(Ljava/lang/String;)V", false);

            GZLog.log("==== 插入结束 ====");
        }
    }
}
