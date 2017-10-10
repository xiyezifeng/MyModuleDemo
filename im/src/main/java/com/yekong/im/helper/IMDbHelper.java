package com.yekong.im.helper;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.yekong.common.base.BaseApplication;
import com.yekong.common.constant.Constant;
import com.yekong.im.db.DaoMaster;
import com.yekong.im.db.DaoSession;
import com.yekong.im.db.GroupInfoEntity;
import com.yekong.im.db.GroupInfoEntityDao;
import com.yekong.im.db.MyOpenHelper;
import com.yekong.im.db.SystemNotifyEntity;
import com.yekong.im.db.SystemNotifyEntityDao;
import com.yekong.im.db.UserInfoEntity;
import com.yekong.im.db.UserInfoEntityDao;

import java.util.List;

public class IMDbHelper {
    private static IMDbHelper instance;
    private MyOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    private IMDbHelper() {
        init();
        if (Constant.DB_DEBUG) {
            setTenData();
        }
    }



    public static IMDbHelper getInstance() {
        if (instance == null) {
            instance = new IMDbHelper();
        }
        return instance;
    }
    private void init(){
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        mHelper = new MyOpenHelper(BaseApplication.getApplication(), "app-db", null);
//        MigrationHelper.getInstance();
        db = mHelper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }
    public DaoSession getDaoSession() {
        return mDaoSession;
    }
    public SQLiteDatabase getDb() {
        return db;
    }

    /**
     * 存入10条用户数据
     */
    private void setTenData() {
        UserInfoEntityDao dao  =  getDaoSession().getUserInfoEntityDao();
        /**
         * 用户temp
         */
        UserInfoEntity entity;
        entity = new UserInfoEntity(null,"00004","紫","https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3787749211,2964191266&fm=27&gp=0.jpg");
        long cout = dao.queryBuilder().where(UserInfoEntityDao.Properties.UserId.eq(entity.getUserId())).count();
        if (cout<=0) {
            dao.insert(entity);
        }else{
        }

        entity = new UserInfoEntity(null,"00005","灵梦","https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2889831978,481292099&fm=27&gp=0.jpg");
        cout = dao.queryBuilder().where(UserInfoEntityDao.Properties.UserId.eq(entity.getUserId())).count();
        if (cout<=0) {
            dao.insert(entity);
        }else{
            /*UserInfoEntity up = dao.queryBuilder().where(UserInfoEntityDao.Properties.UserId.eq(entity.getUserId())).list().get(0);*/
        }

        entity = new UserInfoEntity(null,"00000","系统通知","https://developer.rongcloud.cn/static/img/yunyinguser.jpg");
        cout = dao.queryBuilder().where(UserInfoEntityDao.Properties.UserId.eq(entity.getUserId())).count();
        if (cout<=0) {
            dao.insert(entity);
        }else{
            /*UserInfoEntity up = dao.queryBuilder().where(UserInfoEntityDao.Properties.UserId.eq(entity.getUserId())).list().get(0);*/
        }

        List<UserInfoEntity> list = dao.loadAll();
        Log.e("IMDbHelper", "list.size():" + list.size());

        /**
         * 群组temp
         */
        GroupInfoEntityDao groupInfoEntityDao  =  getDaoSession().getGroupInfoEntityDao();
        GroupInfoEntity groupInfoEntity;
        groupInfoEntity = new GroupInfoEntity(null,"g_001","群组1","https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1687534265,1158113696&fm=27&gp=0.jpg");
        cout = groupInfoEntityDao.queryBuilder().where(GroupInfoEntityDao.Properties.GroupId.eq(groupInfoEntity.getGroupId())).count();
        if (cout<=0) {
            groupInfoEntityDao.insert(groupInfoEntity);
        }else{
        }


        /**
         * 通知消息temp
         *
         private  String content;//消息属性，可随意定义
         private  String messageId;
         private  String form;
         private  String to;
         private  String state;
         private  String inviter;
         private  String messageKey;
         private  String startTime;
         private  String endTime;
         */
         /* systemNotifyEntity = systemNotifyEntityDao.queryBuilder().where(SystemNotifyEntityDao.Properties.MessageId.eq(systemNotifyEntity.getMessageId())).list().get(0);
            systemNotifyEntity.setState("1");
            systemNotifyEntity.setMessageKey("0xkey_1");*/
        SystemNotifyEntityDao systemNotifyEntityDao  =  getDaoSession().getSystemNotifyEntityDao();
        SystemNotifyEntity systemNotifyEntity;
        systemNotifyEntity = new SystemNotifyEntity(null,"好友申请","00004用户申请成为您的好友","1","00004","00005","0","","0xkey","1","10");
        cout = systemNotifyEntityDao.queryBuilder().where(SystemNotifyEntityDao.Properties.MessageId.eq(systemNotifyEntity.getMessageId())).count();
        if (cout<=0) {
            systemNotifyEntityDao.insert(systemNotifyEntity);
        }else{

        }

        systemNotifyEntity = new SystemNotifyEntity(null,"好友申请","00006用户申请成为您的好友","2","00006","00005","0","","0xkey1","1","10");
        cout = systemNotifyEntityDao.queryBuilder().where(SystemNotifyEntityDao.Properties.MessageId.eq(systemNotifyEntity.getMessageId())).count();
        if (cout<=0) {
            systemNotifyEntityDao.insert(systemNotifyEntity);
        }else{

        }

    }


}