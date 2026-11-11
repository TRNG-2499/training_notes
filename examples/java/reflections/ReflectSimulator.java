package com.revature.reflections;

// import Classes from java.lang.reflect | Note: 'Class' is in java.lang
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/*
 * The Reflections API in Java is used to examine (and potentially modify/access)
 * the behavior of an application, during runtime - remember this doesn't change
 * the compiled code, all actions are done using Reflections API classes and
 * methods which can introspect the compiled class data
 * 
 * You can think of the Class 'Class' as the Root of the Reflections API.
 */
public class ReflectSimulator {
	public static void main(String[] args) throws ClassNotFoundException,
			NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// Most Common: Creating Class Objects:
		Class<Data> dataClass = Data.class;
		
		Data d = new Data();
		// Alternative way to get the class # 1:
		Class<Data> dClass = (Class<Data>) d.getClass();

		// Alternative way to get the class # 2:
		Class<Data> dClassForName = (Class<Data>) Class.forName("com.revature.reflections.Data");

		System.out.println("Public Constructors [getConstructors]");
		Constructor<Data> [] pubDataConstructors = (Constructor<Data>[]) dataClass.getConstructors();
		for (Constructor<Data> c : pubDataConstructors) System.out.println("\t"+c);

		System.out.println("All Constructors [getDeclaredConstructors]");
		Constructor<Data> [] allDataConstructors = (Constructor<Data>[]) dataClass.getDeclaredConstructors();
		for (Constructor<Data> c : allDataConstructors) System.out.println("\t"+c);

		System.out.println("Get Specific Constructor [getDeclaredConstructor]");
		Constructor<Data> specificDataConstructor = (Constructor<Data>) dataClass.getDeclaredConstructor( long.class );
		System.out.println("\t"+specificDataConstructor);

		System.out.println("Invoke a Specific, Private Constructor");
		// 'setAccessible' allows Reflections to invoke a method that it otherwise wouldn't be able to
		specificDataConstructor.setAccessible(true);
		Data privateD = specificDataConstructor.newInstance(12345L);
		System.out.println("\t"+privateD.getId());

		System.out.println("Public Methods [getMethods()]");
		Method [] pubDataMethods = dataClass.getMethods();
		for(Method m : pubDataMethods) System.out.println("\t" + m); // all public methods, including inherited methods

		System.out.println("Declared Methods [getDeclaredMethods()]");
		Method [] decDataMethods = dataClass.getDeclaredMethods();
		for(Method m : decDataMethods) System.out.println("\t" + m); // all methods, regardless of access modifier, written in the class.

		System.out.println("Invoke a Specific method [getDeclaredMethod()]");
		Method specificMethod = dataClass.getDeclaredMethod("add", int.class, int.class);
		// The 'add' method is private so we change the accessibility of this method (like the constructor above)
		specificMethod.setAccessible(true);
		System.out.println("\tInvoking "+specificMethod.getName()+"(1, 3): "+specificMethod.invoke(d, 1, 3));

		System.out.println("Public Fields [getFields()]");
		Field[] pubDataFields = dataClass.getFields();
		for(Field f : pubDataFields) System.out.println("\t" + f);

		System.out.println("Declared Fields [getDeclaredFields()]");
		Field[] decDataFields = dataClass.getDeclaredFields();
		for(Field f : decDataFields) System.out.println("\t" + f);

		System.out.println("Declared Field Modifiers [getModifiers()]");
		for (Field f : decDataFields) {
			int mod = f.getModifiers();
			System.out.println("\t" + f);
			System.out.println("\t\t" + mod);
			System.out.println("\t\tisPublic? : " + Modifier.isPublic(mod));
			System.out.println("\t\tisProtected? : " + Modifier.isProtected(mod));
			System.out.println("\t\tisPrivate? : " + Modifier.isPrivate(mod));
		}

		System.out.println("MODIFIERS: ");
		System.out.printf( "%s %012d %n", "Public: " + Modifier.PUBLIC
				+ " | ", Long.parseLong(Integer.toBinaryString(Modifier.PUBLIC)));				// 0000 0000 0001
		System.out.printf( "%s %012d %n", "Private: " + Modifier.PRIVATE
				+ " | ", Long.parseLong(Integer.toBinaryString(Modifier.PRIVATE)));				// 0000 0000 0010
		System.out.printf( "%s %012d %n", "Protected: " + Modifier.PROTECTED
				+ " | ", Long.parseLong(Integer.toBinaryString(Modifier.PROTECTED)));			// 0000 0000 0100
		System.out.printf( "%s %012d %n", "Static: " + Modifier.STATIC
				+ " | ", Long.parseLong(Integer.toBinaryString(Modifier.STATIC)));				// 0000 0000 1000
		System.out.printf( "%s %012d %n", "Final: " + Modifier.FINAL
				+ " | ", Long.parseLong(Integer.toBinaryString(Modifier.FINAL)));				// 0000 0001 0000
		System.out.printf( "%s %012d %n", "Synchronized: " + Modifier.SYNCHRONIZED
				+ " | ", Long.parseLong(Integer.toBinaryString(Modifier.SYNCHRONIZED)));		// 0000 0010 0000
		System.out.printf( "%s %012d %n", "Volatile: " + Modifier.VOLATILE
				+ " | ", Long.parseLong(Integer.toBinaryString(Modifier.VOLATILE)));			// 0000 0100 0000
		System.out.printf( "%s %012d %n", "Transient: " + Modifier.TRANSIENT
				+ " | ", Long.parseLong(Integer.toBinaryString(Modifier.TRANSIENT)));			// 0000 1000 0000
		System.out.printf( "%s %012d %n", "Native: " + Modifier.NATIVE
				+ " | ", Long.parseLong(Integer.toBinaryString(Modifier.NATIVE)));				// 0001 0000 0000
		System.out.printf( "%s %012d %n", "Interface: " + Modifier.INTERFACE
				+ " | ", Long.parseLong(Integer.toBinaryString(Modifier.INTERFACE)));			// 0010 0000 0000
		System.out.printf( "%s %012d %n", "Abstract: " + Modifier.ABSTRACT
				+ " | ", Long.parseLong(Integer.toBinaryString(Modifier.ABSTRACT)));			// 0100 0000 0000
		System.out.printf( "%s %012d %n", "Strict: " + Modifier.STRICT
				+ " | ", Long.parseLong(Integer.toBinaryString(Modifier.STRICT)));				// 1000 0000 0000
	}
}
