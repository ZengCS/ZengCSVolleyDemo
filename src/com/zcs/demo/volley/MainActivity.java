package com.zcs.demo.volley;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.zcs.demo.volley.adapter.VolleyListAdapter;
import com.zcs.demo.volley.entity.VolleyItem;

public class MainActivity extends Activity {
	// 列表长度
	static final int LIST_SIZE = 2000;

	private ListView mListView;
	private VolleyListAdapter mAdapter;
	private RequestQueue mQueue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setTitle("Volley加载大量图片");

		// TODO 初始化VolleyRequestQueue对象,这个对象是Volley访问网络的直接入口
		mQueue = Volley.newRequestQueue(this);

		initList();
	}

	/**
	 * 初始化List
	 */
	private void initList() {
		mListView = (ListView) findViewById(R.id.volley_listview);
		
		// TODO 初始化数据
		ArrayList<VolleyItem> items = new ArrayList<VolleyItem>(LIST_SIZE);
		String imgUrl = "http://img0.bdstatic.com/img/image/%E6%9C%AA%E6%A0%87%E9%A2%98-1.jpg";
		for (int i = 1; i <= LIST_SIZE; i++) {
			VolleyItem item = new VolleyItem();
			item.setName("我所看到的香港-" + i);
			// TODO 为图片地址添加一个尾数,是为了多次访问图片,而不是读取第一张图片的缓存
			item.setImgUrl(imgUrl + "?rank=" + i);
			items.add(item);
		}
		
		// TODO 绑定数据
		mAdapter = new VolleyListAdapter(this, mQueue, items);
		mListView.setAdapter(mAdapter);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// TODO 取消所有未执行完的网络请求
		mQueue.cancelAll(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
