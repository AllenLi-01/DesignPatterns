package ProxyPatten;
public class RealSubject implements Subject {
    private String uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public void login() {
        System.out.println("login");
        setUid("11");
    }

    @Override
    public void register() {
        System.out.println("register");
        System.out.println(this.getUid());
    }
}
