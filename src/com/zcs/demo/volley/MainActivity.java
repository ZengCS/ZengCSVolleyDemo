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
	// �б���
	static final int LIST_SIZE = 2000;

	private ListView mListView;
	private VolleyListAdapter mAdapter;
	private RequestQueue mQueue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setTitle("Volley���ش���ͼƬ");

		// TODO ��ʼ��VolleyRequestQueue����,���������Volley���������ֱ�����
		mQueue = Volley.newRequestQueue(this);

		initList();
	}

	/**
	 * ��ʼ��List
	 */
	private void initList() {
		mListView = (ListView) findViewById(R.id.volley_listview);
		
		// TODO ��ʼ������
		ArrayList<VolleyItem> items = new ArrayList<VolleyItem>(LIST_SIZE);
		String imgUrl = "http://img0.bdstatic.com/img/image/%E6%9C%AA%E6%A0%87%E9%A2%98-1.jpg";
		for (int i = 1; i <= LIST_SIZE; i++) {
			VolleyItem item = new VolleyItem();
			item.setName("�������������-" + i);
			// TODO ΪͼƬ��ַ���һ��β��,��Ϊ�˶�η���ͼƬ,�����Ƕ�ȡ��һ��ͼƬ�Ļ���
			item.setImgUrl(imgUrl + "?rank=" + i);
			items.add(item);
		}
		
		// TODO ������
		mAdapter = new VolleyListAdapter(this, mQueue, items);
		mListView.setAdapter(mAdapter);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// TODO ȡ������δִ�������������
		mQueue.cancelAll(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
