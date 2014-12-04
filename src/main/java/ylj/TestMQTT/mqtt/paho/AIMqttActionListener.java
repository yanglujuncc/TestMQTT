package ylj.TestMQTT.mqtt.paho;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;

public class AIMqttActionListener implements IMqttActionListener{

	@Override
	public void onFailure(IMqttToken arg0, Throwable arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSuccess(IMqttToken arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	public static void main(String[] args){
		
		MqttAsyncClient client;
		
		client.setCallback(callback);
		
		client.connect(options, userContext, callback);
		client.subscribe(topicFilter, qos, userContext, callback)
		client.publish(topic, payload, qos, retained, userContext, callback);
		client.disconnect(quiesceTimeout, userContext, callback);
		
		
		
	}

}
