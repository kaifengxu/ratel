package org.nico.ratel.landlords.client.event;

import org.nico.ratel.landlords.entity.ClientTransferData;
import org.nico.ratel.landlords.print.SimplePrinter;

import io.netty.channel.Channel;

public class ClientEventListener_CODE_GAME_OVER extends ClientEventListener{

	@Override
	public void call(Channel channel, ClientTransferData clientTransferData) {
		SimplePrinter.println("Game over");
		
	}

}
