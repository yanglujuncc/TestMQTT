package ylj.TestMQTT.mqtt.paho;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttPublishSample {
	public static class AMqttCallback implements MqttCallback{

		@Override
		public void connectionLost(Throwable arg0) {
			System.out.println("connectionLost<---");
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("connectionLost--->");
		}

		@Override
		public void deliveryComplete(IMqttDeliveryToken arg0) {
			System.out.println("deliveryComplete<---");
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("deliveryComplete--->");
		}

		@Override
		public void messageArrived(String arg0, MqttMessage arg1) throws Exception {
			System.out.println("messageArrived<---");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("messageArrived--->");
		}

	}
    public static void main(String[] args) {

    	 String topic        = "MQTT/sample";
        String content      = "Message from MqttPublishSample";
        int qos             = 2;
        String broker       = "tcp://localhost:61613";
       String clientId     = "1";
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            MqttClient sampleClient = new MqttClient(broker,clientId , persistence);
        
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setUserName("admin");
            connOpts.setPassword("password".toCharArray());          
            connOpts.setCleanSession(true);
            
            System.out.println("Connecting to broker: "+broker);
            sampleClient.connect(connOpts);
            
            System.out.println("Connected");
            System.out.println("Publishing message: "+content);
            
            sampleClient.setCallback(new AMqttCallback());
            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(qos);
            
            sampleClient.publish(topic, message);
            System.out.println("Message published");
            sampleClient.disconnect();
            System.out.println("Disconnected");
          //  System.exit(0);
        } catch(MqttException me) {
            System.out.println("reason "+me.getReasonCode());
            System.out.println("msg "+me.getMessage());
            System.out.println("loc "+me.getLocalizedMessage());
            System.out.println("cause "+me.getCause());
            System.out.println("excep "+me);
            me.printStackTrace();
        }
    }
}
