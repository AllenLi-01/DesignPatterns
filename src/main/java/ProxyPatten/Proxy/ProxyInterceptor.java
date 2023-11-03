package ProxyPatten.Proxy;

import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

public class ProxyInterceptor {
        private Object realSubject;
        public ProxyInterceptor(Object realSubject) {
            this.realSubject = realSubject;
        }
        @RuntimeType
        public Object intercept(@SuperCall Callable<?> superCall, @Origin Method method) throws Exception {
            System.out.println("Before method invoke(by Byte Buddy): " + method.getName());
            Object result = superCall.call();
            System.out.println("After method invoke(by Byte Buddy): " + method.getName());
            return result;
        }

}
