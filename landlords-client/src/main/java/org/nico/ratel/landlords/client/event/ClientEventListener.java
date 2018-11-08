package org.nico.ratel.landlords.client.event;

import java.util.HashMap;
import java.util.Map;

import org.nico.ratel.landlords.channel.ChannelUtils;
import org.nico.ratel.landlords.entity.ClientTransferData;
import org.nico.ratel.landlords.enums.ClientEventCode;
import org.nico.ratel.landlords.enums.ServerEventCode;

import io.netty.channel.Channel;

public abstract class ClientEventListener {

	public abstract void call(Channel channel, ClientTransferData clientTransferData);

	public final static Map<ClientEventCode, ClientEventListener> LISTENER_MAP = new HashMap<>();
	
	private final static String LISTENER_PREFIX = "org.nico.ratel.landlords.client.event.ClientEventListener_";
	
	public static ClientEventListener get(ClientEventCode code){
		ClientEventListener listener = null;
		try {
			if(ClientEventListener.LISTENER_MAP.containsKey(code)){
				listener = ClientEventListener.LISTENER_MAP.get(code);
			}else{
				String eventListener = LISTENER_PREFIX + code.name();
				Class<ClientEventListener> listenerClass = (Class<ClientEventListener>) Class.forName(eventListener);
				listener = listenerClass.newInstance();
				ClientEventListener.LISTENER_MAP.put(code, listener);
			}
			return listener;
		}catch(ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return listener;
	}
	
	protected void pushToServer(Channel channel, ServerEventCode code, String datas){
		ChannelUtils.pushToServer(channel, code, datas);
	}
}
