package com.ws.mobileline;

import java.io.File;

import org.apache.http.Header;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.ws.mobileline.utils.Files;
import com.ws.mobileline.utils.Logs;
/**
 * 主界面
 * @author ycf
 *
 */
public class MainActivity extends Activity {

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		View personalInfo = findViewById(R.id.personal_info);
		personalInfo.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(getApplicationContext(), EdtingLineActivity.class));
				finish();
			}
		});

//		AsyncHttpClient client = new AsyncHttpClient();
//		client.get("http://www.baidu.com/", new BinaryHttpResponseHandler() {
//
//			@Override
//			public void onSuccess(int statusCode, Header[] headers,
//					byte[] response) {
//				Logs.d("statusCode:" + statusCode);
//				Logs.d("headers:" + headers);
//				Logs.d("response:" + response);
//				Files.writeTo("sdcard/ycf/test.txt", response);
//			}
//
//			@Override
//			public void onFailure(int statusCode, Header[] headers,
//					byte[] response, Throwable t) {
//
//			}
//		});
//
//		File cacheDir = StorageUtils.getOwnCacheDirectory(
//				getApplicationContext(), "imageloader/Cache");
//		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
//				this)
//				.memoryCacheExtraOptions(480, 800)
//				// max width, max height，即保存的每个缓存文件的最大长宽
//				.discCacheExtraOptions(480, 800, null)
//				// Can slow ImageLoader, use it carefully (Better don't use
//				// it)/设置缓存的详细信息，最好不要设置这个
//				.threadPoolSize(3)
//				// 线程池内加载的数量
//				.threadPriority(Thread.NORM_PRIORITY - 2)
//				.denyCacheImageMultipleSizesInMemory()
//				.memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024))
//				// You can pass your own memory cache
//				// implementation/你可以通过自己的内存缓存实现
//				.memoryCacheSize(2 * 1024 * 1024)
//				.discCacheSize(50 * 1024 * 1024)
//				.discCacheFileNameGenerator(new Md5FileNameGenerator())
//				// 将保存的时候的URI名称用MD5 加密
//				.tasksProcessingOrder(QueueProcessingType.LIFO)
//				.discCacheFileCount(100)
//				// 缓存的文件数量
//				.discCache(new UnlimitedDiscCache(cacheDir))
//				// 自定义缓存路径
//				.defaultDisplayImageOptions(DisplayImageOptions.createSimple())
//				.imageDownloader(
//						new BaseImageDownloader(this, 5 * 1000, 30 * 1000)) // connectTimeout
//																			// (5
//																			// s),
//																			// readTimeout
//																			// (30
//																			// s)超时时间
//				.writeDebugLogs() // Remove for release app
//				.build();// 开始构建
//		ImageLoader.getInstance().init(config);
//
//		DisplayImageOptions options = new DisplayImageOptions.Builder()
//				.showImageOnLoading(R.drawable.ic_launcher) // 设置图片在下载期间显示的图片
//				.showImageForEmptyUri(R.drawable.ic_launcher)// 设置图片Uri为空或是错误的时候显示的图片
//				.showImageOnFail(R.drawable.ic_launcher) // 设置图片加载/解码过程中错误时候显示的图片
//				.cacheInMemory(true)// 设置下载的图片是否缓存在内存中
//				.cacheOnDisc(true)// 设置下载的图片是否缓存在SD卡中
//				.considerExifParams(true) // 是否考虑JPEG图像EXIF参数（旋转，翻转）
//				.imageScaleType(ImageScaleType.EXACTLY_STRETCHED)// 设置图片以如何的编码方式显示
//				.bitmapConfig(Bitmap.Config.RGB_565)// 设置图片的解码类型//
//				// .decodingOptions(BitmapFactory.Options)//设置图片的解码配置
//				// .delayBeforeLoading(int delayInMillis)//int
//				// delayInMillis为你设置的下载前的延迟时间
//				// 设置图片加入缓存前，对bitmap进行设置
//				// .preProcessor(BitmapProcessor preProcessor)
//				.resetViewBeforeLoading(true)// 设置图片在下载前是否重置，复位
//				.displayer(new RoundedBitmapDisplayer(20))// 是否设置为圆角，弧度为多少
//				.displayer(new FadeInBitmapDisplayer(100))// 是否图片加载好后渐入的动画时间
//				.build();// 构建完成
//
//		ImageLoader.getInstance().loadImage(
//				Uri.decode("http://www.baidu.com/img/bdlogo.png"), options,
//				new ImageLoadingListener() {
//
//					@Override
//					public void onLoadingStarted(String imageUri, View view) {
//						// TODO Auto-generated method stub
//
//					}
//
//					@Override
//					public void onLoadingFailed(String imageUri, View view,
//							FailReason failReason) {
//					}
//
//					@Override
//					public void onLoadingComplete(String imageUri, View view,
//							Bitmap loadedImage) {
//						Logs.d("onLoadingComplete:" + loadedImage);
//						TextView tv = (TextView) findViewById(R.id.tv);
//						tv.setBackgroundDrawable(new BitmapDrawable(loadedImage));
//					}
//
//					@Override
//					public void onLoadingCancelled(String imageUri, View view) {
//
//					}
//				});

	}
}
