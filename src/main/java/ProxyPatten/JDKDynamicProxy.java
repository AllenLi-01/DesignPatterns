package ProxyPatten;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//基于JDK的动态代理。特点：被代理类实现了某个接口
public class JDKDynamicProxy{

    public Object createProxy(Object  realObject){
        Class<?>[] interfaces =  realObject.getClass().getInterfaces();
        DynamicProxyHandler handler = new DynamicProxyHandler(realObject);

        /**
         * Proxy类的newProxyInstance用于创建一个代理对象。
         * @loader ClassLoader loader, 类加载器，用于加载代理对象。
         * @interfaces Class<?>[] interfaces, 被代理类实现的接口。
         * @h InvocationHandler h,实现了InvocationHandler接口的对象
         */
        return Proxy.newProxyInstance(realObject.getClass().getClassLoader(),interfaces,handler);

    }


    private class DynamicProxyHandler  implements InvocationHandler {
        private Object realObject;//被代理对象
        public DynamicProxyHandler(Object realObject){
            this.realObject = realObject;
        }

        /**
         * 动态代理对象调用一个方法时，这个方法的调用就会被转发到实现InvocationHandler 接口类的 invoke 方法来调用
         * @param proxy  动态生成的代理对象
         * @param method 被代理方法
         * @param args   被代理方法的参数列表
         * @return {@link Object}
         */
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("before method invoke proxy by jdk");
            Object result = method.invoke(realObject, args);
            System.out.println("after method invoke proxy by jdk");
            return result;
        }
    }


}
