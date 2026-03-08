package asmintro;

import java.io.FileOutputStream;
import java.io.IOException;

import org.objectweb.asm.*;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

public class GenPrStr {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
		cw.visit(V11, ACC_PUBLIC, "PrStr", null, "java/lang/Object", null);
		
		MethodVisitor mV = cw.visitMethod(ACC_PUBLIC+ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
		mV.visitCode();
		
		mV.visitLdcInsn("GoKnights");
		mV.visitVarInsn(Opcodes.ASTORE, 1);
		
		mV.visitLdcInsn("SwordsUp");
		mV.visitVarInsn(Opcodes.ASTORE, 2);
		
		mV.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
		mV.visitVarInsn(Opcodes.ALOAD, 1);
		mV.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
		
		mV.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
		mV.visitVarInsn(Opcodes.ALOAD, 2);
		mV.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
		
		mV.visitInsn(Opcodes.RETURN);
		mV.visitMaxs(0, 0);
		mV.visitEnd();
		
		FileOutputStream out = new FileOutputStream("/Users/samsafly/Desktop/CS 322/ASMBytecode_SJS/PrStr.class");
		out.write(cw.toByteArray());
		out.close();
	}

}
