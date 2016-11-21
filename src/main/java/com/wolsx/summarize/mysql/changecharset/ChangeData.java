package com.wolsx.summarize.mysql.changecharset;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by hy on 10/20/16.
 */
public class ChangeData {
    public static void main(String[] args) {
        //获取所有表
        Connection connOld = null;
        Connection connNew = null;
        try {
            connOld = DbUtilsTool.openConn("MySQL", "192.168.1.71", "3307", "gljk", "bbkj", "bbkj123");
            connNew = DbUtilsTool.openConn("MySQL", "192.168.1.220", "3306", "bbkjweb", "root", "root");
            String sql = "select * from product";
            QueryRunner qr = new QueryRunner();
            List<Product> productList = qr.query(connOld, sql, new BeanListHandler<Product>(Product.class));


            for(Product product: productList)
            {
                sql = "insert into product(did,pname,ptitle,pstitle,pprice,plogo,psort,pcontent,ptype,snum,delflag,addtimes)"+
                        "values(?,?,?,?,?,?,?,?,?,?,?,?)";
                Object[] params = new Object[12];
                params[0] = product.getDid();
                params[1] = product.getPname();
                params[2] = product.getPtitle();
                params[3] = product.getPstitle();
                params[4] = product.getPprice();
                params[5] = product.getPlogo();
                params[6] = product.getPsort();
                params[7] = product.getPcontent();
                params[8] = product.getPtype();
                params[9] = product.getSnum();
                params[10] = product.getDelflag();
                params[11] = product.getAddtimes();
                qr.update(connNew, sql, params);
            }


        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (connOld != null) {
                try {
                    connOld.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
