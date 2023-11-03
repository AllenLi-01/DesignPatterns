package ProxyPatten.Proxy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.reflect.InvocationTargetException;

public class ByteBuddyDynamicProxy {

    public <T> T createProxy(T realObject) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return (T) new ByteBuddy()
                .subclass(realObject.getClass())
                .method(ElementMatchers.any()) // 拦截所有方法
                .intercept(MethodDelegation.to(new ProxyInterceptor(realObject)))
                .make()
                .load(realObject.getClass().getClassLoader())
                .getLoaded()
                .getDeclaredConstructor()
                .newInstance();
    }

}
