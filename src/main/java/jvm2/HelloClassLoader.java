package jvm2;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.Base64;

public class HelloClassLoader extends ClassLoader{

    public static void main(String[] args) throws Exception {

        Class<?> clazz = new HelloClassLoader().findClass("jvm2.Hello");
        Method method = clazz.getMethod("hello");
        method.invoke(clazz.newInstance());
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException{

        String path = "D:\\workspace\\jkclass\\src\\main\\java\\jvm2\\Hello.class";
        byte[] readFileToByteArray = fileConvertToByteArray(new File(path));
        String helloBase64 = Base64.getEncoder().encodeToString(readFileToByteArray);

        byte[] bytes = decode(helloBase64);

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
