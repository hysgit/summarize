package com.wolsx.summarize.javautils.apache.dbutils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wolsx.summarize.javautils.apache.dbutils.bean.Bean1;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by hy on 16-5-18.
 */
public class Demo3 {

    public static void main(String[] args) throws SQLException {
        QueryRunner qr = new QueryRunner(JdbcUtils2.getDataSource());
        String sql = "select n.id as id, t.subject_id as subject_id, " +
                "n.`name` as `name`, n.`level` as `level`, " +
                "n.type as type, n.difficulty as difficulty," +
                "n.usual_exam as usual_exam, n.right_index as rank," +
                "n.left_index as l, n.right_index as r, n.node_sign as node_sign " +
                "from t_knowledge_tree t, t_knowledge_node n " +
                "where t.id = n.knowledge_tree_id and n.is_delete = 0 and t.is_delete = 0 ";
        List<Map<String, Object>> list = qr.query(sql, new MapListHandler());
        String s = JSON.toJSONString(list);
        JSONObject jsonObject = JSON.parseObject(s);
        System.out.println(s);

    }
}
