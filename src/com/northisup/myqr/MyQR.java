package com.northisup.myqr;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class MyQR extends Activity{
	EditText inputUrl;
	
	private static final String TAG = "MyQR";
	
	OnClickListener getImageBtnOnClick = new OnClickListener() {		
		public void onClick(View view) {
			Log.v(TAG, "getImageBtnOnClick");
			Context context = view.getContext();
			Editable ed = inputUrl.getText();		
			Drawable image = ImageOperations(context,ed.toString(),"image.jpg");		
			ImageView imgView = new ImageView(context);			
			imgView = (ImageView)findViewById(R.id.image1);
			imgView.setImageDrawable(image);
		}
	};

	
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		Log.e(TAG, "error");
		Log.w(TAG, "warn");
		Log.d(TAG, "debug");
		Log.i(TAG, "info");
		Log.v(TAG, "verbose");
		
		Log.println(1, TAG, "LOG 1");
		Log.println(100, TAG, "LOG 100");
		
		setContentView(R.layout.main);
		inputUrl = ((EditText)findViewById(R.id.imageUrl));
		inputUrl.setSingleLine();
		inputUrl.setTextSize(11);

		Button getImageButton = (Button)findViewById(R.id.getImageButton);
		getImageButton.setOnClickListener(getImageBtnOnClick);
		
		Editable ed = inputUrl.getText();
		Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.image1);
		
		ImageView imgView;
		imgView = (ImageView)findViewById(R.id.image1);
		imgView.setImageBitmap(mBitmap);
		
		Log.v(TAG, "setting image from url");
		Drawable image = ImageOperations2("http://upload.wikimedia.org/wikipedia/en/thumb/9/9b/Wikipedia_mobile_en.svg/232px-Wikipedia_mobile_en.svg.png","image.jpg");
		imgView = (ImageView)findViewById(R.id.image1);
		imgView.setImageDrawable(image);
	}

	public void onStart(){
		super.onStart();
		Log.e(TAG, "onStart");
		Log.e(TAG, "error");
		Log.w(TAG, "warn");
		Log.d(TAG, "debug");
		Log.i(TAG, "info");
		Log.v(TAG, "verbose");
	}
	
	private Drawable ImageOperations(Context ctx, String url, String saveFilename) {
		Log.v(TAG, url);
		try {			
			InputStream is = (InputStream) this.fetch(url);
			Drawable d = Drawable.createFromStream(is, "src");						
			return d;
		} catch (MalformedURLException e) {			
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} 
	}


	private Drawable ImageOperations2(String url, String saveFilename) {
		try {
			Log.v(TAG, url);
			InputStream is = (InputStream) this.fetch(url);
			Drawable d = Drawable.createFromStream(is, "src");
			Log.v(TAG, d.toString());
			return d;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Object fetch(String address) throws MalformedURLException,IOException {
		URL url = new URL(address);
		Object content = url.getContent();
		return content;
	}
}