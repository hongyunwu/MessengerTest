package com.why.messagertest;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		bindService(new Intent(this, MessengerService.class), new ServiceConnection() {
			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				Messenger messenger = new Messenger(service);


				for (int i = 0; i < 10; i++) {
					Message message = Message.obtain();
					try {
						message.getData().putString("data","我是来自另外进程的消息"+i);
						messenger.send(message);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}
			}

			@Override
			public void onServiceDisconnected(ComponentName name) {

			}
		}, Context.BIND_AUTO_CREATE);
	}
}
