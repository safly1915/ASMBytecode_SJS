package asmintro;

import java.io.FileOutputStream;
import java.io.IOException;

import org.objectweb.asm.*;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

public class GenIfElse {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
		cw.visit(V11, ACC_PUBLIC, "IfElse", null, "java/lang/Object", null);
		
		MethodVisitor mV = cw.visitMethod(ACC_PUBLIC+ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
		mV.visitCode();
		
		mV.visitIntInsn(Opcodes.BIPUSH, 8);
		mV.visitVarInsn(Opcodes.ISTORE, 1);
		
		mV.visitIntInsn(Opcodes.BIPUSH, 8);
		mV.visitVarInsn(Opcodes.ISTORE, 2);
		
		Label trueStmt = new Label();
		Label finish = new Label();
		
		mV.visitVarInsn(Opcodes.ILOAD, 1);
		mV.visitVarInsn(Opcodes.ILOAD, 2);
		mV.visitJumpInsn(Opcodes.IF_ICMPEQ, trueStmt);
		
		mV.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
		mV.visitLdcInsn("! The numbers are not equal.");
		mV.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
		mV.visitJumpInsn(Opcodes.GOTO, finish);
		
		mV.visitLabel(trueStmt);
		mV.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
		mV.visitLdcInsn("The numbers are equal.");
		mV.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
		
		mV.visitLabel(finish);
		mV.visitInsn(Opcodes.RETURN);
		mV.visitMaxs(0, 0);
		mV.visitEnd();
		
		FileOutputStream out = new FileOutputStream("/Users/samsafly/Desktop/CS 322/ASMBytecode_SJS/IfElse.class");
		out.write(cw.toByteArray());
		out.close();
	}

}
