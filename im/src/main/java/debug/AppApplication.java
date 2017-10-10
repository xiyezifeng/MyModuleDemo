package debug;

import com.yekong.common.base.BaseApplication;
import com.yekong.im.helper.IMDbHelper;
import com.yekong.im.helper.IMHelper;

/**
 * Created by xigua on 2017/9/14.
 */

public class AppApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        IMDbHelper.getInstance();
        IMHelper.getInstance();
    }
}


