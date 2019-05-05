package com.greendao.gen;

import android.content.Context;

import com.github.greendaolib.entity.Person;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * 对Person数据表的具体操作
 */
public class PersonDaoUtil {

    /**
     * 插入一条数据，如果表未创建，先创建表
     */
    public static boolean insert(Context context, Person person){
        long flag = DaoManager.getConnection(context).getPersonDao().insert(person);
        return flag == -1 ? false : true;
    }

    /**
     * 插入多条数据，在子线程执行
     */
    public static boolean insertMult(final Context context, final List<Person> list) {
        boolean flag = false;
        try {
            DaoManager.getConnection(context).runInTx(new Runnable() {
                @Override
                public void run() {
                    for (Person person : list) {
                        DaoManager.getConnection(context).insertOrReplace(person);
                    }
                }
            });
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 修改一条数据
     * 其中原有的数据不会保存，如果新建的对象有属性没有设置，会为空，不为空的字段没有设置，会报错
     */
    public static boolean update(Context context, Person person){
        boolean flag = false;
        try {
            DaoManager.getConnection(context).update(person);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 删除一条数据
     */
    public static boolean delete(Context context, Person person){
        boolean flag = false;
        try {
            DaoManager.getConnection(context).delete(person);//按照id删除
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 删除所有数据
     */
    public static boolean deleteAll(Context context){
        boolean flag = false;
        try {
            DaoManager.getConnection(context).deleteAll(Person.class);//按照id删除
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 查询所有数据
     */
    public static List<Person> queryAll(Context context){
        return DaoManager.getConnection(context).loadAll(Person.class);
    }

    /**
     * 根据主键id查询数据
     */
    public static Person queryById(Context context, long key){
        return DaoManager.getConnection(context).load(Person.class, key);
    }

    /**
     * 使用native sql进行查询操作
     */
    public static List<Person> queryByNativeSql(Context context, String sql, String[] conditions){
        return DaoManager.getConnection(context).queryRaw(Person.class, sql, conditions);
    }

    /**
     * 使用queryBuilder进行查询
     */
    public static List<Person> queryByQueryBuilder(Context context, long id){
        QueryBuilder<Person> queryBuilder = DaoManager.getConnection(context).queryBuilder(Person.class);
        return queryBuilder.where(PersonDao.Properties._id.eq(id)).list();
    }

}
