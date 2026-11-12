import java.lang.reflect.Method;

public class Invoker{
    public static void main(String[] args){
        MyClass myclass = new MyClass();
        Class<?> clazz = myclass.getClass();
        Method[] methods = clazz.getDeclaredMethods();

        for(Method method : methods){
            if(method.isAnnotationPresent(Repeat.class)){
                Repeat repeat = method.getAnnotation(Repeat.class);
                int times = repeat.times();

                method.setAccessible(true);

                Object[] methodArgs = prepareArguments(method);

                try{
                    for(int i = 0; i < times; i++){
                        System.out.println("Попытка вызова " + method.getName() + " (" + (i + 1) + "/" + times + "): ");
                        method.invoke(myclass, methodArgs);
                    }
                }catch(Exception e){
                    System.err.println("Ошибка при вызове метода " + method.getName() + ": " + e.getCause());
                }
            }
        }
    }

    private static Object[] prepareArguments(Method m){
        Class<?>[] paramTypes = m.getParameterTypes();
        Object[] args = new Object[paramTypes.length];

        for (int i = 0; i < paramTypes.length; i++) {
            if (paramTypes[i] == String.class) {
                args[i] = "Тестовые данные";
            } else if (paramTypes[i] == int.class) {
                args[i] = 42;
            } else if (paramTypes[i] == double.class) {
                args[i] = 3.14;
            } else {
                args[i] = null;
            }
        }
        return args;
    }
}