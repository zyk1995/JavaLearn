package com.zyk.javassist;

import javassist.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MathUtilGenerate {

    public static void main(String[] args) throws CannotCompileException, NotFoundException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        ClassPool pool = ClassPool.getDefault();

        CtClass ctClass = pool.makeClass("com.zyk.javassist.MathUtil");

        //属性字段
        CtField ctField = new CtField(CtClass.doubleType, "pi", ctClass);
        ctField.setModifiers(Modifier.PRIVATE + Modifier.STATIC + Modifier.FINAL);
        ctClass.addField(ctField, "3.14");


        //方法：求圆面积
        CtMethod calculateCircularArea = new CtMethod(CtClass.doubleType, "calculateCircularArea",
                new CtClass[] {CtClass.doubleType}, ctClass);
        calculateCircularArea.setModifiers(Modifier.PUBLIC);
        calculateCircularArea.setBody("{return pi * $1 * $1;}");
        ctClass.addMethod(calculateCircularArea);
        //方法: 两数之和
        CtMethod sumOfTwoNumbers = new CtMethod(pool.get(Double.class.getName()), "sumOfTwoNumbers",
                new CtClass[]{CtClass.doubleType, CtClass.doubleType}, ctClass);
        sumOfTwoNumbers.setModifiers(Modifier.PUBLIC);
        sumOfTwoNumbers.setBody("{ return Double.valueOf($1 + $2);}");
        ctClass.addMethod(sumOfTwoNumbers);



        ctClass.writeFile("target/classes");
        // 测试调用
        ClassLoader loader = new Loader(pool);
        Class<?> clazz = loader.loadClass("com.zyk.javassist.MathUtil");
        Object obj = clazz.newInstance();

        Method method_calculateCircularArea = clazz.getDeclaredMethod("calculateCircularArea", double.class);
        Object obj_01 = method_calculateCircularArea.invoke(obj, 1.23);
        System.out.println("圆面积： " + obj_01);

    }
}
