/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ylj.TestMQTT;

import java.util.concurrent.TimeUnit;

import org.fusesource.hawtbuf.*;
import org.fusesource.mqtt.client.*;

/**
 * Uses an callback based interface to MQTT. Callback based interfaces are
 * harder to use but are slightly more efficient.
 */
class SyncSubscriber {

	public static void main(String[] args) throws Exception {

		String user = env("APOLLO_USER", "admin");
		String password = env("APOLLO_PASSWORD", "password");
		String host = env("APOLLO_HOST", "localhost");
		int port = Integer.parseInt(env("APOLLO_PORT", "61613"));
		final String destination = arg(args, 0, "/topic/event");

		MQTT mqtt = new MQTT();
		mqtt.setHost(host, port);
		mqtt.setUserName(user);
		mqtt.setPassword(password);

		mqtt.setCleanSession(true);
	//	mqtt.setClientId("1234567890");
		mqtt.setKeepAlive((short) 10);

		mqtt.setWillTopic("/topic/will");
		mqtt.setWillQos( QoS.AT_MOST_ONCE);
		mqtt.setWillMessage("hell i am will message,SyncSubscriber");
		mqtt.setWillRetain(false);
		
		
		Topic[] topics = { new Topic(destination, QoS.AT_LEAST_ONCE) };

		BlockingConnection connection = mqtt.blockingConnection();

		connection.connect();
		byte[] dates=connection.subscribe(topics);

		while (true) {
			System.out.println("waiting  msg... ");
			Message msg = connection.receive();
			if (msg != null) {			
				byte[] data = msg.getPayload();			
				System.out.println("receive msg:" + new String(data,"utf-8"));
				msg.ack();  
			}
		}

	}

	private static String env(String key, String defaultValue) {
		String rc = System.getenv(key);
		if (rc == null)
			return defaultValue;
		return rc;
	}

	private static String arg(String[] args, int index, String defaultValue) {
		if (index < args.length)
			return args[index];
		else
			return defaultValue;
	}
}
