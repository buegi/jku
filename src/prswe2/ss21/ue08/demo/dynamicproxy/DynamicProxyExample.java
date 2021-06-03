package prswe2.ss21.ue08.demo.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxyExample {

	public interface Foo {
		void foo();
	}

	static class SimpleHandler implements InvocationHandler {
		private final Foo foo;

		public SimpleHandler(Foo foo) {
			this.foo = foo;
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			System.out.println("Intercepting proxy call to method " + method.toString());
			return method.invoke(foo, args);
		}

	}

	public static void main(String[] args) {

		// create a foo object

		Foo f = (Foo) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[] { Foo.class },
				new SimpleHandler(new Foo() {
					@Override
					public void foo() {
						System.out.println("hello world...");
					}
				}));
		f.foo();
	}
}
