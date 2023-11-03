package ProxyPatten;

public class Test {
    public static void main(String[] args) {
        JDKDynamicProxy proxy = new JDKDynamicProxy();
        Subject realSubject =(Subject)proxy.createProxy(new RealSubject());
        realSubject.login();
        realSubject.register();
    }
}
