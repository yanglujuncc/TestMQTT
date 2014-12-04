package ylj.TestMQTT.mqtt.fusesource;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

    	SimpleDateFormat ISO_time_format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");
        System.out.println( "Hello World!" );
        System.out.println(ISO_time_format.format(new Date(1417575805087L)));
    }
}
