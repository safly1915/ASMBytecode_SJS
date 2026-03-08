package asmintro;

import java.io.FileOutputStream;
import java.io.IOException;

import org.objectweb.asm.*;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

public class GenNegTest {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
		cw.visit(V11, ACC_PUBLIC, "NegTest", null, "java/lang/Object", null);
		
		MethodVisitor mV = cw.visitMethod(ACC_PUBLIC+ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
		mV.visitCode();
		
		mV.visitTypeInsn(Opcodes.NEW, "java/util/Scanner");
		mV.visitInsn(Opcodes.DUP);
		
		mV.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "in", "Ljava/io/InputStream;");
		mV.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/util/Scanner", "<init>", "(Ljava/io/InputStream;)V", false);
		mV.visitVarInsn(Opcodes.ASTORE, 1);
		
		mV.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
		mV.visitLdcInsn("Enter your integer now:");
		mV.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
		
		mV.visitVarInsn(Opcodes.ALOAD, 1);
		mV.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/util/Scanner", "nextInt", "()I", false);
		mV.visitVarInsn(Opcodes.ISTORE, 2);
		
		Label negativeNum = new Label();
		Label finish = new Label();
		
		mV.visitVarInsn(Opcodes.ILOAD, 2);
		mV.visitJumpInsn(Opcodes.IFLT, negativeNum); //using IFLT instead of IF_CMPLT because IFLT compares to 0. don't need two integers
		
		mV.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
		mV.visitLdcInsn("Your number is >= 0.");
		mV.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
		mV.visitJumpInsn(Opcodes.GOTO, finish);
		
		mV.visitLabel(negativeNum);
		mV.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
		mV.visitLdcInsn("Your number is < 0.");
		mV.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
		
		mV.visitLabel(finish);
		mV.visitInsn(Opcodes.RETURN);
		mV.visitMaxs(0, 0);
		mV.visitEnd();
		
		FileOutputStream out = new FileOutputStream("/Users/samsafly/Desktop/CS 322/ASMBytecode_SJS/NegTest.class");
		out.write(cw.toByteArray());
		out.close();
	}

}
