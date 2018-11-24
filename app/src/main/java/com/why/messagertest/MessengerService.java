package com.why.messagertest;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;

//Messenger
public class MessengerService extends Service {
	private static final String TAG = "MessengerService";
	public MessengerService() {
	}

	@Override
	public IBinder onBind(Intent intent) {
		Messenger messenger = new Messenger(new Handler(){
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);

				Log.i(TAG, "handleMessage: "+msg.getData().get("data"));
			}
		});

		return messenger.getBinder();
	}
}
