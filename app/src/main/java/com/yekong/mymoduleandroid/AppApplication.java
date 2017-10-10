package com.yekong.mymoduleandroid;

import com.alibaba.android.arouter.launcher.ARouter;
import com.yekong.common.base.BaseApplication;

import static com.yekong.common.constant.Constant.APP_DEBUG;

/**
 * Created by xigua on 2017/9/13.
 */

public class AppApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        // 这两行必须写在init之前，否则这些配置在init过程中将无效
        if (APP_DEBUG) {
            ARouter.openLog();     // 打印日志}
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
//        ARouter.getInstance().build("/main/homeActivity").navigation();

            //配置IM组件
//            IMDbHelper.getInstance();
//            IMHelper.getInstance();
//
//            JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
//            JPushInterface.init(this);     		// 初始化 JPush
    }

}
