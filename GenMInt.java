package asmintro;

import java.io.FileOutputStream;
import java.io.IOException;

import org.objectweb.asm.*;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

public class GenMInt {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
		cw.visit(V11, ACC_PUBLIC, "MInt", null, "java/lang/Object", null);
		
		MethodVisitor mV = cw.visitMethod(ACC_PUBLIC+ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
		mV.visitCode();
		
		mV.visitIntInsn(Opcodes.BIPUSH, 4);
		mV.visitIntInsn(Opcodes.BIPUSH, 5);
		mV.visitInsn(Opcodes.IMUL);
		mV.visitVarInsn(Opcodes.ISTORE, 1); //slot 0 is reserved for args in main (not sure why? need to look into that)
		
		mV.visitLdcInsn((Double) 2.5);
		mV.visitLdcInsn((Double) 6.0);
		mV.visitInsn(Opcodes.DMUL);
		mV.visitVarInsn(Opcodes.DSTORE, 2);
		
		mV.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
		mV.visitVarInsn(Opcodes.ILOAD, 1);
		mV.visitMethodInsn(Opcodes.INVOKEVIRTUAL,  "java/io/PrintStream", "println", "(I)V", false);
		
		mV.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
		mV.visitVarInsn(Opcodes.DLOAD, 2);
		mV.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(D)V", false);
		
		mV.visitInsn(Opcodes.RETURN);
		mV.visitMaxs(0, 0);
		mV.visitEnd();
		
		//save bytecode into disk
		FileOutputStream out=new FileOutputStream("/Users/samsafly/Desktop/CS 322/ASMBytecode_SJS/MInt.class");
		out.write(cw.toByteArray());
		out.close();
	}

}
