package com.github.greendaolib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 注解含义：
 *  @Entity：将普通类变为能够被greenDAO识别的数据库类型的实体类
 *  @nameInDb：在数据库中的名字，如不写则为实体中类名
 *  @Id：选择一个long / Long属性作为实体ID。 在数据库方面，它是主键。 参数autoincrement是设置ID值自增
 *  @NotNull：使该属性在数据库端成为“NOT NULL”列。 通常使用@NotNull标记原始类型（long，int，short，byte）是有意义的
 *  @Transient：表明这个字段不会被写入数据库，只是作为一个普通的java类字段，用来临时存储数据的，不会被持久化
 */
@Entity
public class Person {

    @Id(autoincrement = true)
    private Long _id;

    @NotNull
    private String name;

    @Generated(hash = 1585257758)
    public Person(Long _id, @NotNull String name) {
        this._id = _id;
        this.name = name;
    }

    @Generated(hash = 1024547259)
    public Person() {
    }

    public Long get_id() {
        return this._id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
