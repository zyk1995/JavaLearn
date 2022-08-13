package com.zyk.javassist;

import javassist.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class GenerateClazzMethod {

    public static void main(String[] args) throws NotFoundException, CannotCompileException, IOException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException {
        ClassPool pool = ClassPool.getDefault();

        // 创建类 classname：创建类路径和名称
        CtClass ctClass = pool.makeClass("com.zyk.hello.HelloWorld");

        //添加方法
        CtMethod mainMethod = new CtMethod(CtClass.voidType, "main",
                new CtClass[]{pool.get(String[].class.getName())}, ctClass);
        mainMethod.setModifiers(Modifier.PUBLIC + Modifier.STATIC);
        mainMethod.setBody("{        System.out.println(\"Hello javassist !\");\n}");
        ctClass.addMethod(mainMethod);
        //创建无参数构造方法
        CtConstructor ctConstructor = new CtConstructor(new CtClass[] {}, ctClass);
        ctConstructor.setBody("{}");
        ctClass.addConstructor(ctConstructor);

        ctClass.writeFile("target/classes");
        // 测试调用
        ClassLoader loader = new Loader(pool);
        Class<?> clazz = loader.loadClass("com.zyk.hello.HelloWorld");
        Object obj = clazz.newInstance();

        Method main = clazz.getDeclaredMethod("main", String[].class);
        main.invoke(obj, (Object) new String[1]);

    }
}
