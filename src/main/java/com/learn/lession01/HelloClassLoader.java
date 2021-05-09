package com.learn.lession01;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.Base64;

public class HelloClassLoader extends ClassLoader{

    public static void main(String[] args) throws Exception {

        Class<?> clazz = new HelloClassLoader().findClass("Hello");
        Method method = clazz.getMethod("Hello");
        method.invoke(clazz.newInstance());
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException{

        String path = "D:\\资料\\课程\\01JVM\\作业相关\\Hello\\Hello.xlass";
        byte[] readFileToByteArray = fileConvertToByteArray(new File(path));
        String helloBase64 = Base64.getEncoder().encodeToString(readFileToByteArray);
        //System.out.println(helloBase64);
        helloBase64 = "NQFFQf///8v/4/X/+f/x9v/w/+/3/+71/+3/7Pj/6/j/6v7/+cOWkZaLwf7//NfWqf7/+7yQm5r+//CzlpGasYqSnZqNq56dk5r+//qXmpOTkP7/9ayQio2cmrmWk5r+//W3mpOTkNGVnome8//4//f4/+nz/+j/5/7/7Leak5OQ09+ck56MjLOQnpuajd74/+bz/+X/5P7/+reak5OQ/v/vlZ6JntCTnpGY0LCdlZqci/7/75WeiZ7Qk56RmNCshoyLmpL+//yQiov+/+qzlZ6JntCWkNCvjZaRi6yLjZqeksT+/+yVnome0JaQ0K+NlpGLrIuNmp6S/v/4j42WkYuTkf7/6tezlZ6JntCTnpGY0KyLjZaRmMTWqf/e//r/+f///////f/+//j/9//+//b////i//7//v////rVSP/+Tv////7/9f////n//v////7//v/0//f//v/2////2v/9//7////2Tf/97fxJ//tO/////v/1////9f/9////+//3//r//v/z/////f/y";

        byte[] bytes = decode(helloBase64);
        byte[] newbytes = new byte[bytes.length];
        for(int i=0;i<bytes.length;i++){
            //newbytes[i] =(byte)((byte)255 - bytes[i]);
            newbytes[i] = (byte)~bytes[i];
            System.out.println(newbytes[i]);
        }

        return defineClass(name,bytes,0,bytes.length);
    }

    /**
     * 把一个文件转化为byte字节数组。
     *
     * @return
     */
    private byte[] fileConvertToByteArray(File file) {
        byte[] data = null;

        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            int len;
            byte[] buffer = new byte[1024];
            while ((len = fis.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            data = baos.toByteArray();
            fis.close();
            baos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public byte[] decode(String base64){
        return Base64.getDecoder().decode(base64);
    }
}
