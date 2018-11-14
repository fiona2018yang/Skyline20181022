import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
/**
 * @Auther: heyc
 * @Date: 2018/6/20 14:55
 * @Description:
 */
public class Test {
    public static void main(String[] args) throws IOException {
        int num=16;
        //用来缓存数据ByteArrayOutputStream
       ByteArrayOutputStream baos=new ByteArrayOutputStream();
     //   InputStream is = new InputStream(baos);
        DataOutputStream out=new DataOutputStream(baos);
        out.writeInt(num);
        byte[] bs=  baos.toByteArray();
        System.out.println();
        for(byte b:bs) {
           System.out.print("0x"+ Integer.toString(b>>4&0xF,16).toUpperCase()+Integer.toString(b&0xF,16).toUpperCase()+" ");
        }
    }
        //
    public void Dechex(){

    }

}

