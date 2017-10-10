package com.yekong.im.helper;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.yekong.common.base.BaseApplication;
import com.yekong.common.constant.Constant;
import com.yekong.im.R;
import com.yekong.im.db.GroupInfoEntity;
import com.yekong.im.db.GroupInfoEntityDao;
import com.yekong.im.db.SystemNotifyEntity;
import com.yekong.im.db.SystemNotifyEntityDao;
import com.yekong.im.db.UserInfoEntity;
import com.yekong.im.db.UserInfoEntityDao;
import com.yekong.im.entity.MotioncEntity;
import com.yekong.im.listener.MyConversationListBehaviorListener;
import com.yekong.im.message.EmotionMessage;
import com.yekong.im.message.EmotionMessageItemProvider;
import com.yekong.im.message.SystemNotifyMessage;
import com.yekong.im.message.SystemNotiryItemProvider;
import com.yekong.im.plugin.MyExtensionModule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.rong.imkit.DefaultExtensionModule;
import io.rong.imkit.IExtensionModule;
import io.rong.imkit.RongExtensionManager;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Group;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.MessageContent;
import io.rong.imlib.model.UserInfo;

public class IMHelper {
    private static final String TAG = "IMHelper";
    private static volatile IMHelper singleton;
    private Context appContext;
    private Map<String,List<MotioncEntity>> motionMap;
    private String currentConversationID;//当前对话用户ID
    private Conversation.ConversationType type;


    private IMHelper() {
        RongIM.init(BaseApplication.getApplication());
        init();
        if (Constant.IM_DEBUG)
//        login("HlF7ZXh9SB+Z7ilgvEVFPX/S0Xz7a4VWJxE8ySw0tU1LnuShVawZDh1Jjwj9Xggzu0mtS6oioMSvUrzGE6bybw==");
        login("EbaNkap8OgriIJ9iGSNUAH/S0Xz7a4VWJxE8ySw0tU1LnuShVawZDrGK7mIXL2rSz79gc3tXangOJIFJ8nk5cw==");
    }
    /**
     * {"code":200,"userId":"00001","token":"058fTimsGbhc8cbUGE0dAXFH7/+7WR2UoiiEp6skkJjUuSF2dMYERhdmLHYAk/iNaB2XU17j54BBTI6Vrn52bQ=="}
     * {"code":200,"userId":"00002","token":"3mVr35NaCgW9VxxwmX+SY3/S0Xz7a4VWJxE8ySw0tU1LnuShVawZDm1mbNXqA9iRZzGSuFe7kBubWESRrMgt/Q=="}
     * {"code":200,"userId":"00004","token":"HlF7ZXh9SB+Z7ilgvEVFPX/S0Xz7a4VWJxE8ySw0tU1LnuShVawZDh1Jjwj9Xggzu0mtS6oioMSvUrzGE6bybw=="}
     * {"code":200,"userId":"00005","token":"EbaNkap8OgriIJ9iGSNUAH/S0Xz7a4VWJxE8ySw0tU1LnuShVawZDrGK7mIXL2rSz79gc3tXangOJIFJ8nk5cw=="}
     */

    public static IMHelper getInstance() {
        if (singleton == null) {
            synchronized (IMHelper.class) {
                if (singleton == null) {
                    singleton = new IMHelper();
                }
            }
        }
        return singleton;
    }
    public void init(){
        appContext = BaseApplication.getApplication();
        RongIM.setUserInfoProvider(userInfoProvider,true);
        RongIM.setGroupInfoProvider(groupInfoProvider,true);
        RongIM.setOnReceiveMessageListener(receiveMessageListener);
        /**
         * 设置会话界面操作的监听器。
         */
        RongIM.setConversationListBehaviorListener(new MyConversationListBehaviorListener());
        /**
         * 设置消息体内是否携带用户信息。
         *
         * @param state 是否携带用户信息，true 携带，false 不携带。
         */
        RongIM.getInstance().setMessageAttachedUserInfo(false);
        setMyExtensionModule();
        RongIM.getInstance().registerMessageTemplate(new EmotionMessageItemProvider());
        RongIM.getInstance().registerMessageTemplate(new SystemNotiryItemProvider());
        RongIM.registerMessageType(EmotionMessage.class);
        RongIM.registerMessageType(SystemNotifyMessage.class);
        motionMap = new HashMap<>();
        initMotion();
    }



    public RongIM.UserInfoProvider userInfoProvider = new RongIM.UserInfoProvider() {
        @Override
        public UserInfo getUserInfo(String s) {
            return findUserById(s);
        }
    };
    public RongIM.GroupInfoProvider groupInfoProvider = new RongIM.GroupInfoProvider() {
        @Override
        public Group getGroupInfo(String s) {
            return findGroupById(s);
        }
    };
    public UserInfo findUserById(String s){
        long count = IMDbHelper.getInstance().getDaoSession().getUserInfoEntityDao().queryBuilder().where(UserInfoEntityDao.Properties.UserId.eq(s)).count();
        if (count > 0) {
            //数据库获取
            UserInfoEntity entity = IMDbHelper.getInstance().getDaoSession().getUserInfoEntityDao().queryBuilder().where(UserInfoEntityDao.Properties.UserId.eq(s)).list().get(0);
            RongIM.getInstance().refreshUserInfoCache(new UserInfo(s,entity.getUserName(), Uri.parse(entity.getUserAvata())));
            return new UserInfo(s, entity.getUserName(), Uri.parse(entity.getUserAvata()));
        }else{
            //网络抓取
            return new UserInfo(s, "yekong",  Uri.parse("http://img4.imgtn.bdimg.com/it/u=2628255248,1582396004&fm=27&gp=0.jpg"));
        }
    }
    public Group findGroupById(String s){
        long count = IMDbHelper.getInstance().getDaoSession().getGroupInfoEntityDao().queryBuilder().where(GroupInfoEntityDao.Properties.GroupId.eq(s)).count();
        if (count > 0) {
            GroupInfoEntity entity = IMDbHelper.getInstance().getDaoSession().getGroupInfoEntityDao().queryBuilder().where(GroupInfoEntityDao.Properties.GroupId.eq(s)).list().get(0);
            RongIM.getInstance().refreshGroupInfoCache(new Group(s,entity.getGroupName(), Uri.parse(entity.getGroupAvata())));
            return new Group(s, entity.getGroupName(), Uri.parse(entity.getGroupAvata()));
        }
        return new Group(s,"组1", Uri.parse("http://img4.imgtn.bdimg.com/it/u=966083992,3822833134&fm=27&gp=0.jpg"));
    }
    RongIMClient.OnReceiveMessageListener receiveMessageListener = new RongIMClient.OnReceiveMessageListener() {
        @Override
        public boolean onReceived(Message message, int i) {
            MessageContent messageContent = message.getContent();
            if (messageContent instanceof EmotionMessage) {
                Log.e(TAG, "收到一条表情消息");
            }else if (messageContent instanceof SystemNotifyMessage) {
                //存入数据库
                Log.e(TAG, "收到一条系统通知消息");
                SystemNotifyEntityDao systemNotifyEntityDao  = IMDbHelper.getInstance().getDaoSession().getSystemNotifyEntityDao();
                SystemNotifyMessage systemNotify = (SystemNotifyMessage)messageContent;
                SystemNotifyEntity systemNotifyEntity = new SystemNotifyEntity(null,systemNotify.getDes() , systemNotify.getContent(),systemNotify.getMessageId(),
                        systemNotify.getForm(),systemNotify.getTo(),systemNotify.getState(),systemNotify.getInviter(),
                        systemNotify.getMessageKey(),systemNotify.getStartTime(),systemNotify.getEndTime());
                long cout = systemNotifyEntityDao.queryBuilder().where(SystemNotifyEntityDao.Properties.MessageId.eq(systemNotifyEntity.getMessageId())).count();
                if (cout<=0) {
                    systemNotifyEntityDao.insert(systemNotifyEntity);
                }else{
                    SystemNotifyEntity temp = systemNotifyEntityDao.queryBuilder().where(SystemNotifyEntityDao.Properties.MessageId.eq(systemNotifyEntity.getMessageId())).list().get(0);
                    if (!temp.getMessageKey().equals(systemNotifyEntity.getMessageKey())) {
                        systemNotifyEntity.setId(temp.getId());
                        systemNotifyEntityDao.update(systemNotifyEntity);
                    }else{
                        return true;
                    }
                }
            }
            Log.e(TAG, "收到一条消息");
            return false;
        }
    };

    public void login(String token) {
        if (BaseApplication.getApplication().getApplicationInfo().packageName.equals(BaseApplication.getCurProcessName())) {
            RongIM.connect(token, new RongIMClient.ConnectCallback() {

                /*
                 * Token 错误。可以从下面两点检查 1.  Token 是否过期，如果过期您需要向 App Server 重新请求一个新的 Token
                 *                  2.  token 对应的 appKey 和工程里设置的 appKey 是否一致
                 */
                @Override
                public void onTokenIncorrect() {
                    Log.d("LoginActivity", "--onTokenIncorrect" );
                }

                /*
                 * 连接融云成功
                 * @param userid 当前 token 对应的用户 id
                 * */

                @Override
                public void onSuccess(String userid) {
                    Log.d("LoginActivity", "--onSuccess" + userid);
//                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                    finish();
                    RongIM.getInstance().setCurrentUserInfo(findUserById(userid));
                }

                /*
                 * 连接融云失败
                 * @param errorCode 错误码，可到官网 查看错误码对应的注释
                 * */

                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {
                    Log.d("LoginActivity", "--onError" );
                }
            });
        }
    }

    public String[] getTitleAndId(String uri){
        String[] des = new String[2];
        if (uri.contains("&")) {
            String[] split = uri.split("&");
            for (String s : split) {
                if (s.contains("=")) {
                    String[] split_1 = s.split("=");
                    if (split_1[0].equals("targetId")) {
                        des[0] = split_1[1];
                    }
                    if (split_1[0].equals("title")){
                        des[1] = split_1[1];
                    }
                }
            }
        }else{
            if (uri.contains("=")) {
                String[] split_1 = uri.split("=");
                if (split_1[0].equals("targetId")) {
                    des[0] = split_1[1];
                }
                if (split_1[0].equals("title")){
                    des[1] = split_1[1];
                }
            }
        }
        return des;
    }

    public void setMyExtensionModule() {
        List<IExtensionModule> moduleList = RongExtensionManager.getInstance().getExtensionModules();
        IExtensionModule defaultModule = null;
        if (moduleList != null) {
            for (IExtensionModule module : moduleList) {
                if (module instanceof DefaultExtensionModule) {
                    defaultModule = module;
                    break;
                }
            }
            if (defaultModule != null) {
                RongExtensionManager.getInstance().unregisterExtensionModule(defaultModule);
                RongExtensionManager.getInstance().registerExtensionModule(new MyExtensionModule());
            }
        }
    }
    private void initMotion() {
        List<MotioncEntity> group_1 = new ArrayList<>();
        group_1.add(new MotioncEntity(R.mipmap.net_loading,"[加载中...]"));
        group_1.add(new MotioncEntity(R.mipmap.logo_m,"[logo]"));
        motionMap.put("group_1", group_1);
    }

    public Map<String, List<MotioncEntity>> getMotionMap() {
        return motionMap;
    }
    public List<MotioncEntity> getMotionList(String key){
        if (getMotionMap().containsKey(key)) {
            return getMotionMap().get(key);
        }
        return null;
    }

    public String getCurrentConversationID() {
        return currentConversationID;
    }

    public void setCurrentConversationID(String currentConversationID) {
        this.currentConversationID = currentConversationID;
    }

    public Conversation.ConversationType getType() {
        return type;
    }

    public void setType(Conversation.ConversationType type) {
        this.type = type;
    }

}