package com.greendao.gen;

import android.content.Context;

import org.greenrobot.greendao.query.QueryBuilder;

/**
 * 创建数据库、创建数据库表、包含增删改查的操作以及数据库的升级
 * https://blog.csdn.net/bskfnvjtlyzmv867/article/details/71250101/
 */
public class DaoManager {
    private static final String DB_NAME = "test";
    private static DaoMaster.DevOpenHelper mHelper;
    private static DaoMaster mDaoMaster;
    private static DaoSession mDaoSession;

    private DaoManager() {
    }

    /**
     * 获取连接
     */
    public static synchronized DaoSession getConnection(Context context){
        if(mHelper == null){
            Context appContext = context.getApplicationContext();
            mHelper = new DaoMaster.DevOpenHelper(appContext, DB_NAME, null);
        }
        if(mDaoMaster == null){
            mDaoMaster = new DaoMaster(mHelper.getWritableDatabase());
        }
        if(mDaoSession == null){
            mDaoSession = mDaoMaster.newSession();
        }
        return mDaoSession;
    }

    /**
     * 关闭连接
     */
    public static synchronized void closeConnection(){
        if(mDaoSession != null){
            mDaoSession.clear();
            mDaoSession = null;
        }
        if(mDaoMaster != null){
            mDaoMaster = null;
        }
        if(mHelper != null){
            mHelper.close();
            mHelper = null;
        }
    }

    /**
     * 打开输出日志，默认关闭
     */
    public static void setDebug(){
        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;
    }
}
