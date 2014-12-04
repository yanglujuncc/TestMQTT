package ylj.TestMQTT.line.client;

import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

import org.fusesource.hawtbuf.Buffer;
import org.fusesource.hawtbuf.UTF8Buffer;
import org.fusesource.mqtt.client.BlockingConnection;
import org.fusesource.mqtt.client.CallbackConnection;
import org.fusesource.mqtt.client.Future;
import org.fusesource.mqtt.client.FutureConnection;
import org.fusesource.mqtt.client.Listener;
import org.fusesource.mqtt.client.MQTT;
import org.fusesource.mqtt.client.QoS;


public class Client {
	
	MQTT mqtt = new MQTT();
	

	public static final String WillTopicName="Will";
	public static final QoS WillQos=QoS.AT_LEAST_ONCE;
	public static final boolean WillClean=true;
	
	String user;
	String password;
	String host;

	
	FutureConnection connection;
	//CallbackConnection  connection;
	
	short keepAlive;
	
	public Client(String user,String password,String host){
		this.user=user;
		this.password=password;
		this.host=host;
	
	}
	
	public String createWillMessage(){
		return user;
	}
	
	public boolean connectTo(short keepAlive,int maxWaitMS ) throws Exception{
		
	
		this.keepAlive=keepAlive;
		
		MQTT mqtt = new MQTT();
		
		mqtt.setHost(host);
		mqtt.setUserName(user);
		mqtt.setPassword(password);

		mqtt.setCleanSession(WillClean);
	//	mqtt.setClientId("1234567890");
		mqtt.setKeepAlive(keepAlive);
		

		mqtt.setWillTopic(WillTopicName);
		mqtt.setWillQos(WillQos);
		mqtt.setWillMessage(createWillMessage());
		mqtt.setWillRetain(WillClean);
		
		mqtt.setConnectAttemptsMax(1);
	//	mqtt.setReconnectAttemptsMax(100);
	//	mqtt.setReconnectBackOffMultiplier(reconnectBackOffMultiplier);
	//	mqtt.setReconnectDelay(reconnectDelay);
	//	mqtt.setReconnectDelayMax(reconnectDelayMax);
		
		  connection = mqtt.futureConnection();
		
		  Future<Void> future=  connection.connect();
		  future.await(maxWaitMS, TimeUnit.MILLISECONDS);

		  
		  if(connection.isConnected()){
			  
			  return true;
		  }else{
			  connection.kill();
			  connection=null;
			  return false;
		  }
		
	}
	
	public void subscribeTopic(String topic,MessageHandler handler){
		
		connection.
	}
	
	public class MessageHandler implements Listener{

		@Override
		public void onConnected() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onDisconnected() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onPublish(UTF8Buffer topic, Buffer body, Runnable ack) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onFailure(Throwable value) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
