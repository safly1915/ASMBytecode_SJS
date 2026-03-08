package asmintro;

import java.io.FileOutputStream;
import java.io.IOException;

import org.objectweb.asm.*;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

public class GenDFlo {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
		cw.visit(V11, ACC_PUBLIC, "DFlo", null, "java/lang/Object", null);
		
		MethodVisitor mV = cw.visitMethod(ACC_PUBLIC+ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
		mV.visitCode();
		
		mV.visitLdcInsn(15.0f);
		mV.visitLdcInsn(5.0f);
		mV.visitInsn(Opcodes.FDIV);
		mV.visitVarInsn(Opcodes.FSTORE, 1);
		
		mV.visitLdcInsn(12.0);
		mV.visitLdcInsn(3.0);
		mV.visitInsn(Opcodes.DDIV);
		mV.visitVarInsn(Opcodes.DSTORE, 2);
		
		mV.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
		mV.visitVarInsn(Opcodes.FLOAD, 1);
		mV.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(F)V", false);
		
		mV.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
		mV.visitVarInsn(Opcodes.DLOAD, 2);
		mV.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(D)V", false);
		
		mV.visitInsn(Opcodes.RETURN);
		mV.visitMaxs(0, 0);
		mV.visitEnd();
		
		FileOutputStream out = new FileOutputStream("/Users/samsafly/Desktop/CS 322/ASMBytecode_SJS/DFlo.class");
		out.write(cw.toByteArray());
		out.close();
	}

}
