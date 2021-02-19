import com.urise.webapp.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {

    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Resume r = new Resume("Name");
        Field field = r.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        System.out.println(field.get(r));
        field.get(r);
        field.set(r, "new uuid");
        System.out.println(r);


        //TODO : invoke r.toString via reflection
        //В MainReflection вызовите у Resume, через отражение, метод toString. Выведите результат на консоль
        Method method = r.getClass().getMethod("toString");
        Object result = method.invoke(r);
        System.out.println("Результат: " + result);
    }

}
