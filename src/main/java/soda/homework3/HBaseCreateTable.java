package soda.homework3;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

import java.io.IOException;

public class HBaseCreateTable {
    private final static String TABLE_NAME = "soda:student";
    private final static String COLUMN_FAMILY_NAME = "name";
    private final static String COLUMN_FAMILY_INFO = "info";
    private final static String COLUMN_FAMILY_SCORE = "score";

    public static void main(String[] args) throws IOException {
        Configuration config = HBaseConfiguration.create();

        config.set("hbase.rootdir", "hdfs://47.101.206.249:8020/hbase");
        config.set("hbase.zookeeper.quorum", "47.101.206.249");
        config.set("hbase.zookeeper.property.clientPort", "2181");


        createSchemaTables(config);
    }

    public static void createSchemaTables(Configuration config) throws IOException {
        try (Connection conn = ConnectionFactory.createConnection(config);
             Admin admin = conn.getAdmin();
        ) {
            HTableDescriptor hTableDescriptor = new HTableDescriptor(TableName.valueOf(TABLE_NAME));
            hTableDescriptor.addFamily(new HColumnDescriptor(COLUMN_FAMILY_NAME));
            hTableDescriptor.addFamily(new HColumnDescriptor(COLUMN_FAMILY_INFO));
            hTableDescriptor.addFamily(new HColumnDescriptor(COLUMN_FAMILY_SCORE));
            System.out.println("create table");
            createOrOverwrite(admin, hTableDescriptor);
            System.out.println("done");
        }
    }

    public static void createOrOverwrite(Admin admin, HTableDescriptor table) throws IOException {
        TableName tableName = table.getTableName();
        if (admin.tableExists(tableName)) {
            admin.disableTable(tableName);
            admin.disableTable(tableName);
        }
        admin.createTable(table);
    }


}
