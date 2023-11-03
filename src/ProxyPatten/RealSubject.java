package ProxyPatten;

public class RealSubject implements Subject {
    @Override
    public void login() {
        System.out.println("login");
    }

    @Override
    public void register() {
        System.out.println("register");
    }
}
