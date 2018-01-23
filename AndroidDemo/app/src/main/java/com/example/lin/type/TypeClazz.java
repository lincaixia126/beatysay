package com.example.lin.type;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;

/**
 * Created by lin on 18/1/11.
 */

public class TypeClazz<T1, T2 extends Number> {

    public T2 member;

    public T1 member2;

    public Collection<? extends Number> collection;

    public Collection<T2> collection2;

    public T2[] array;

    public <T extends Type> void method(T p1, T2 p2) {

    }

    public static Type printFieldType(String name) {
        System.out.println("name: " + name);
        Class clazzType = TypeClazz.class;

        Type type = null;

        try {
            Field field = clazzType.getDeclaredField(name);
            type = field.getGenericType();
            System.out.println(type.getClass().getName());

        } catch (Exception e) {

        }

        if (type instanceof ParameterizedType) {
            ParameterizedType ptype = (ParameterizedType)type;
            Type[] types = ptype.getActualTypeArguments();
            for (Type t : types) {
                System.out.println("t: " + t);
            }
        }
        return type;
    }

    public static void printMethodReturnType(String name) {
        System.out.print("name: " + name);
        Class clazzType = TypeClazz.class;

        Method[] ms = clazzType.getDeclaredMethods();
        Method method = null;
        for (Method m : ms) {
            if (m.getName().equals(name)) {
                method = m;
                break;
            }
        }

        System.out.println("method: " + method.getGenericReturnType());
    }


    public static void printMethodParamTypes(String name) {
        System.out.println("name: " + name);
        Class clazzType = TypeClazz.class;

        Method[] ms = clazzType.getDeclaredMethods();
        Method method = null;
        for (Method m : ms) {
            if (m.getName().equals(name)) {
                method = m;
                break;
            }
        }

        Type[] types = method.getGenericParameterTypes();
        for (Type type : types) {
            System.out.println("type: " + type);
        }
    }




    public static void main(String[] args) {
        printFieldType("member");
        printFieldType("member2");
        printFieldType("collection");
        printFieldType("collection2");

        printFieldType("array");

        printMethodReturnType("method");
        printMethodParamTypes("method");
    }

}
