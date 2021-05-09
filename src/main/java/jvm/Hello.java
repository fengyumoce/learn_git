package jvm;

public class Hello {

    public static void main(String[] args) {
        System.out.println("Hello jvm!");
        byte b = 0x0f;
        b = (byte)~b;
        System.out.println(b);
    }
}
