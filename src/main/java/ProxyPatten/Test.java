package ProxyPatten;

import ProxyPatten.Proxy.ByteBuddyDynamicProxy;

import java.lang.reflect.InvocationTargetException;

public class Test {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        JDKDynamicProxy proxy = new JDKDynamicProxy();
        Subject realSubject =(Subject)proxy.createProxy(new RealSubject());
        realSubject.login();
        realSubject.register();


        ByteBuddyDynamicProxy proxy2 = new ByteBuddyDynamicProxy();
        NoInterfaceRealSubject realSubject2 =proxy2.createProxy(new NoInterfaceRealSubject());
        realSubject2.login();
    }
}
