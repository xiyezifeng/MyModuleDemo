package debug;

import com.yekong.common.DataInit;
import com.yekong.common.base.BaseApplication;

/**
 * Created by xigua on 2017/9/13.
 */

public class AppApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        //模拟数据
        DataInit.simulateLogin(this,"123","123456");
    }
}
