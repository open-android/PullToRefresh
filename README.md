# PullToRefresh

开源地址：[https://github.com/open-android/PullToRefresh](https://github.com/open-android/PullToRefresh)

## 运行效果 ##
![](img/pulltorefresh.gif)

* 爱生活,爱学习,更爱做代码的搬运工,分类查找更方便请下载黑马助手app


![黑马助手.png](http://upload-images.jianshu.io/upload_images/4037105-f777f1214328dcc4.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


## 使用步骤
### 1. 添加依赖 ###
	//在项目下的build.gradle
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

	//在app模块下的build.gradle
	dependencies {
	        compile 'com.github.open-android:PullToRefresh:v1.0'
	}
	
![](http://oi5nqn6ce.bkt.clouddn.com/itheima/booster/code/jitpack.png)	


### 2. 在布局中添加PullToRefreshListView ###
	<?xml version="1.0" encoding="utf-8"?>
	<RelativeLayout
	    xmlns:android="http://schemas.android.com/apk/res/android"
	    xmlns:tools="http://schemas.android.com/tools"
	    android:id="@+id/activity_main"
	    android:layout_width="match_parent"
	    android:layout_height="match_parent">
	
	    <com.itheima.pulltorefreshlib.PullToRefreshListView
	        android:id="@+id/pull_to_refresh_list_view"
	        android:layout_width="wrap_content"
	        android:layout_height="wrap_content"/>
	</RelativeLayout>

### 3. 设置Adapter ###
    private ArrayAdapter mArrayAdapter;
    private PullToRefreshListView mPullToRefreshListView;

    mPullToRefreshListView = (PullToRefreshListView) findViewById(R.id.pull_to_refresh_list_view);
    mArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mockList(0, 30));
    mPullToRefreshListView.setAdapter(mArrayAdapter);

### 4. 添加模拟数据 ###
    private ArrayList<String> mItems = new ArrayList<String>();
    /**
     * 创建或动态添加测试用的数据列表
     * @param start 数据的起始位置
     * @param count 数据的个数
     * @return 当前的数据列表
     */
    private List<String> mockList(int start, int count) {
        for (int i = start; i < start + count; i++) {
            mItems.add("黑马程序员:" + i);
        }
        return mItems;
    }

### 5. 设置模式 ###
	//设置模式BOTH: 既能上拉也能下拉，
     mPullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);

### 6. 设置监听 ###
	//设置刷新监听
	mPullToRefreshListView.setOnRefreshListener(mListViewOnRefreshListener2);

	private PullToRefreshBase.OnRefreshListener2<ListView> mListViewOnRefreshListener2 = new PullToRefreshBase.OnRefreshListener2<ListView>() {
	
	        /**
	         * 下拉刷新回调
	         * @param refreshView
	         */
	        @Override
	        public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
	            //模拟延时三秒刷新
	            mPullToRefreshListView.postDelayed(new Runnable() {
	                @Override
	                public void run() {
	                    mItems.clear();
	                    mockList(0, 20);
	                    mArrayAdapter.notifyDataSetChanged();
	                    mPullToRefreshListView.onRefreshComplete();//下拉刷新结束，下拉刷新头复位
	
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
	            mPullToRefreshListView.postDelayed(new Runnable() {
	                @Override
	                public void run() {
	                    mockList(mItems.size(), 20);
	                    mArrayAdapter.notifyDataSetChanged();
	                    mPullToRefreshListView.onRefreshComplete();//上拉加载更多结束，上拉加载头复位
	                }
	            }, 3000);
	        }
	    };





* 详细的使用方法在DEMO里面都演示啦,如果你觉得这个库还不错,请赏我一颗star吧~~~

* 欢迎关注微信公众号

![](http://upload-images.jianshu.io/upload_images/4037105-8f737b5104dd0b5d.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

