package com.github.greendaolib;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.github.greendaolib.entity.Person;
import com.greendao.gen.PersonDaoUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        TextView tv_insert = findViewById(R.id.tv_insert);
        tv_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PersonDaoUtil.insert(context, new Person(null, "zhangsan"));
            }
        });
        TextView tv_insertMul = findViewById(R.id.tv_insertMul);
        tv_insertMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Person> list = new ArrayList<>();
                list.add(new Person(null, "Lisi"));
                list.add(new Person(null, "Wangwu"));
                PersonDaoUtil.insertMult(context, list);
            }
        });
        TextView tv_update = findViewById(R.id.tv_update);
        tv_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Person person = new Person();
                person.set_id(1L);
                person.setName("lisilisi");
                PersonDaoUtil.update(context, person);
            }
        });
        TextView tv_delete = findViewById(R.id.tv_delete);
        tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Person person = new Person();
                person.set_id(1L);
                PersonDaoUtil.delete(context, person);
            }
        });
        TextView tv_deleteAll = findViewById(R.id.tv_deleteAll);
        tv_deleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PersonDaoUtil.deleteAll(context);
            }
        });
        TextView tv_queryAll = findViewById(R.id.tv_queryAll);
        tv_queryAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Person> list = PersonDaoUtil.queryAll(context);
                for(Person person : list){
                    System.out.println("aaaaaaaaaa  person id="+person.get_id());
                    System.out.println("aaaaaaaaaa  person name="+person.getName());
                }
            }
        });
        TextView tv_query = findViewById(R.id.tv_query);
        tv_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Person person = PersonDaoUtil.queryById(context, 1L);
                System.out.println("aaaaaaaaaa  person id="+person.get_id());
                System.out.println("aaaaaaaaaa  person name="+person.getName());
            }
        });
        TextView tv_query_native_sql = findViewById(R.id.tv_query_native_sql);
        tv_query_native_sql.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sql = "where _id > ?";
                String[] condition = new String[]{"1"};
                List<Person> list = PersonDaoUtil.queryByNativeSql(context, sql, condition);
                for(Person person : list){
                    System.out.println("aaaaaaaaaa  person id="+person.get_id());
                    System.out.println("aaaaaaaaaa  person name="+person.getName());
                }
            }
        });
        TextView tv_query_builder = findViewById(R.id.tv_query_builder);
        tv_query_builder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Person> list = PersonDaoUtil.queryByQueryBuilder(context, 1);
                for(Person person : list){
                    System.out.println("aaaaaaaaaa  person id="+person.get_id());
                    System.out.println("aaaaaaaaaa  person name="+person.getName());
                }
            }
        });
    }
}
