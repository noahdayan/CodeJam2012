package com.codejam.listener;

import com.codejam.listener.event.ConnectionEvent;

public abstract class ConnectionListener extends Listener
{

	public abstract void onConnectionEvent(ConnectionEvent event);	

}
