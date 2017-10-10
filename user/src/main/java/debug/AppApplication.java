package debug;

import com.yekong.common.DataInit;
import com.yekong.common.base.BaseApplication;

/**
 * Created by xigua on 2017/9/14.
 */

public class AppApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        DataInit.simulateLogin(this,"123","123456");
    }
}
