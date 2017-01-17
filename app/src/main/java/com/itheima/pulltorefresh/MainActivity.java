package com.itheima.pulltorefresh;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.itheima.pulltorefreshlib.PullToRefreshBase;
import com.itheima.pulltorefreshlib.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> mItems = new ArrayList<String>();
    ;
    private ArrayAdapter mArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final PullToRefreshListView pullToRefreshListView = (PullToRefreshListView) findViewById(R.id.pull_to_refresh_list_view);
        mArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mockList(0, 30));
        pullToRefreshListView.setAdapter(mArrayAdapter);
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {

            /**
             * 下拉刷新回调
             * @param refreshView
             */
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                //模拟延时三秒刷新
                pullToRefreshListView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mItems.clear();
                        mockList(0, 20);
                        mArrayAdapter.notifyDataSetChanged();
                        pullToRefreshListView.onRefreshComplete();

                    }
                }, 3000);
            }

            /**
             * 上拉加载更多回调
             * @param refreshView
             */
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                //模拟延时三秒加载更多数据
                pullToRefreshListView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mockList(mItems.size(), 20);
                        mArrayAdapter.notifyDataSetChanged();
                        pullToRefreshListView.onRefreshComplete();
                    }
                }, 3000);
            }
        });
    }


    private List<String> mockList(int start, int count) {
        for (int i = start; i < start + count; i++) {
            mItems.add("黑马程序员:" + i);
        }
        return mItems;
    }
}
