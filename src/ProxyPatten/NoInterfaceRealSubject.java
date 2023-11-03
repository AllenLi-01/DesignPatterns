package ProxyPatten;

/**
 * 无接口的真实目标（被代理类）
 */
public class NoInterfaceRealSubject {
    public void login() {
        System.out.println("NoInterfaceRealSubject login");
    }

    public void register() {
        System.out.println("NoInterfaceRealSubject register");
    }
}
