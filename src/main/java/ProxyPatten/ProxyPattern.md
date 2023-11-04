# 结构型设计模式
## 代理模式 Proxy Pattern
代码仓：springDemo/DesignPattern/src/ProxyPatten

为某个对象提供一种代理以控制对对象的访问。即客户端可通过代理对象间接访问目标对象，同时可**限制、增强、修改**目标对象的一些特性。
访问者不想或者不能直接访问目标对象，代理对象作为目标对象和访问者之间的**中介**。

它在不改变原始类（或叫被代理类）代码的情况下，通过引入代理类来给原始类**附加功能**。

代理模式的结构如下：
- 抽象目标（代理接口）：通过接口或抽象类声明需要被代理的方法；
- 真实角色：实现抽象目标，定义真实角色所要实现的业务逻辑，供代理角色调用；
- 代理角色：通过组合与委托使用真实角色的业务逻辑实现抽象目标定义的业务方法，并可对真实角色附加增强操作。

优缺点：

- 优点：
  - 代理对象在目标对象和访问者之间起到一个中介的作用，且可以保护**目标对象**。
  - 可以**增强**目标对象的功能。
  - 将访问者与目标对象分离，在一定程度上降低了系统的**耦合度**。

- 缺点：
  - 当使用动态代理实现时，会使系统响应速度降低，因为其底层使用反射。
  - 增加代理对象或代理工厂会增加系统的复杂度。



比如，在Springboot项目中，需要获取Controller层每个请求接口的相关数据，比如访问时间、处理时长等等。如果把这个逻辑放到接口方法中，会导致非业务逻辑和业务逻辑耦合，违背单一职责原则，也不方便维护。
### 静态代理
代理类在编译期生成。需要为每个被代理类生成代理类。
类型1：原始类（真实角色）与代理类可以实现相同接口（原始类可改动）
```java
public interface Subject {
    void login();
    void regist();
}
 
public class RealSubject implements Subject {
    @Override
    public void login() {
        System.out.println("RealSubject:login()");
    }
	@Override
    public void regist() {
        System.out.println("RealSubject:regist()");
    }
}
 
public class ProxySubject implements Subject {
    private RealSubject realSubject;
 
    public ProxySubject(RealSubject realSubject) {
        this.realSubject = realSubject;//组合
    }
    
 
    @Override
    public void login() {
        System.out.println("ProxySubject:before login()");//增强功能
        realSubject.login();//委托
        System.out.println("ProxySubject:after login()");//增强功能
    }
	@Override
    public void regist() {
        System.out.println("ProxySubject:before regist()");
        realSubject.regist();//委托
        System.out.println("ProxySubject:after regist()");
    }
}
 
public class Client {
    public static void main(String[] args) {
        Subject proxySubject = new ProxySubject(new RealSubject());
        proxySubject.login();
        proxySubject.regist();
    }
}
```
类型2：第三方jar包，外部类，无法修改，使用继承、重写的方式进行代理。

### 动态代理
代理类在运行时动态生成，不需要手动编写代理类的代码。
#### 基于jdk的动态代理
此方式要求被代理类至少实现一个接口，运行时动态生成代理类。代理类会实现被代理类实现的接口，并为被代理类中的方法提供增强。
这种方式基于jdk中的java.lang.reflect.Proxy类和java.lang.reflect.InvocationHandler接口实现。

使用方式：
1. 定义代理接口，被代理类实现代理接口；
2. 定义代理类，实现InvocationHandler接口，在invoke方法中定义代理对象执行方法时的增强操作；
3. 使用代理接口声明一个代理对象，该对象使用Proxy类的newProxyInstance方法创建，该方法需要传递被代理类的类加载器、代理接口的类数组、实现了InvocationHandler接口的代理类实例对象。
4. 使用代理对象调用代理接口中的方法。


#### 基于ByteBuddy/Cglib的动态代理
此方法允许被代理类不实现接口。


#### 两种代理的区别
- JDK 动态代理：JDK 动态代理是基于 Java 标准库中的 `java.lang.reflect.Proxy` 和 `java.lang.reflect.InvocationHandler` 接口实现的。它使用 Java 反射机制来创建代理类和处理方法调用。
- Byte Buddy 动态代理：Byte Buddy 动态代理是基于 Java 字节码操作库 Byte Buddy 实现的。它直接生成字节码，允许更灵活地创建和修改类。