package asmintro;

import java.io.FileOutputStream;
import java.io.IOException;

import org.objectweb.asm.*;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

public class GenSLo {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
		cw.visit(V11, ACC_PUBLIC, "SLo", null, "java/lang/Object", null);
		
		MethodVisitor mV = cw.visitMethod(ACC_PUBLIC+ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
		mV.visitCode();
		
		mV.visitLdcInsn(15L);
		mV.visitLdcInsn(10L);
		mV.visitInsn(Opcodes.LSUB);
		mV.visitVarInsn(Opcodes.LSTORE, 1);
		
		mV.visitLdcInsn((Double) 8.0);
		mV.visitLdcInsn((Double) 3.0);
		mV.visitInsn(Opcodes.DSUB);
		mV.visitVarInsn(Opcodes.DSTORE, 3);
		
		mV.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
		mV.visitVarInsn(Opcodes.LLOAD, 1);
		mV.visitMethodInsn(Opcodes.INVOKEVIRTUAL,  "java/io/PrintStream", "println", "(J)V", false);
		
		mV.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
		mV.visitVarInsn(Opcodes.DLOAD, 3);
		mV.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(D)V", false);
		
		mV.visitInsn(Opcodes.RETURN); 
		mV.visitMaxs(0,0);
		mV.visitEnd();
		
		FileOutputStream out = new FileOutputStream("/Users/samsafly/Desktop/CS 322/ASMBytecode_SJS/SLo.class");
		out.write(cw.toByteArray());
		out.close();
	}

}
