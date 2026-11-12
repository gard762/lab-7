public class MyClass{
    public void publicMethod1(){
        System.out.println("Вызван publicMethod1");
    }

    public void publicMethod2(){
        System.out.println("Вызван publicMethod2");
    }

    @Repeat(times = 2)
    protected void protectedMethod1(String msg){
        System.out.println("Вызван protectedMethod1 с сообщением: " + msg);
    }


    @Repeat(times = 1)
    protected void protectedMethod2(int nmb){
        System.out.println("Вызван protectedMethod2 с числом: " + nmb);
    }

    @Repeat(times = 3)
    private void privateMethod1(String msg, int cnt){
        System.out.println("Вызван privateMethod1 " + msg + " (итерация " + cnt + ")");
    }

    @Repeat(times = 2)
    private void privateMethod2(double val){
        System.out.println("Вызван privateMethod2 со значением: " + val);
    }
}