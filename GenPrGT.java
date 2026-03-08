package asmintro;

import java.io.FileOutputStream;
import java.io.IOException;

import org.objectweb.asm.*;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

public class GenPrGT {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
		cw.visit(V11, ACC_PUBLIC, "PrGT", null, "java/lang/Object", null);
			
		MethodVisitor mV = cw.visitMethod(ACC_STATIC+ACC_PUBLIC, "main", "([Ljava/lang/String;)V", null, null);
		mV.visitCode();
			
		mV.visitIntInsn(Opcodes.BIPUSH, 5);
		mV.visitVarInsn(Opcodes.ISTORE, 1);
			
		mV.visitIntInsn(Opcodes.BIPUSH, 10);
		mV.visitVarInsn(Opcodes.ISTORE, 2);
			
		Label secondNum = new Label();
		Label finish = new Label();
			
		/*had problems with wrong number being printed
		*swapped which number was loaded first
		*this makes it so 2nd number is compared, not first compared
		*Load2 > Load1, not Load1 > Load2 like if 1 is loaded before 2
		did this to continue using IF_ICMPGT for clarity*/
		//Already an if-else comparison??
		mV.visitVarInsn(Opcodes.ILOAD, 2);
		mV.visitVarInsn(Opcodes.ILOAD, 1);
		mV.visitJumpInsn(Opcodes.IF_ICMPGT, secondNum);
			
		//secondNum not greater than the first, so print first and then go to the end to skip linear operation
		mV.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
		mV.visitVarInsn(Opcodes.ILOAD, 1);
		mV.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
		mV.visitJumpInsn(Opcodes.GOTO, finish);
			
		mV.visitLabel(secondNum);
		mV.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
		mV.visitVarInsn(Opcodes.ILOAD, 2);
		mV.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
			
		mV.visitLabel(finish);
		mV.visitInsn(Opcodes.RETURN);
		mV.visitMaxs(0,0);
		mV.visitEnd();
			
		FileOutputStream out = new FileOutputStream("/Users/samsafly/Desktop/CS 322/ASMBytecode_SJS/PrGT.class");
		out.write(cw.toByteArray());
		out.close();
	}

}
