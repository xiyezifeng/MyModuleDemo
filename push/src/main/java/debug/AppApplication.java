package debug;

import com.yekong.common.base.BaseApplication;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by xigua on 2017/9/18.
 */

public class AppApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);     		// 初始化 JPush
    }
}
