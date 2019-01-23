package ir.piana.dev.jpos.qp.core.data.database;

import ir.piana.dev.jpos.qp.core.error.QPException;

import java.sql.*;
import java.util.List;
import java.util.Map;

/**
 * @author Mohammad Rahmati, 12/12/2018
 */
public class QPDatabaseManager {
    QPDataSourceManager dataSourceManager;

    public QPDatabaseManager(QPDataSourceManager dataSourceManager) {
        this.dataSourceManager = dataSourceManager;
    }

    public boolean isExistTable(
            String instanceName, String tableName)
            throws Exception {
        Connection connection = dataSourceManager
                .getDataSource(instanceName)
                .getConnection();
        boolean exist = false;
        try {
            DatabaseMetaData meta = connection.getMetaData();
            ResultSet res = meta.getTables(null, null, tableName.toUpperCase(),
                    new String[] {"TABLE"});
            while (res.next()) {
//                System.out.println(
//                        "   " + res.getString("TABLE_CAT")
//                                + ", " + res.getString("TABLE_SCHEM")
//                                + ", " + res.getString("TABLE_NAME")
//                                + ", " + res.getString("TABLE_TYPE")
//                                + ", " + res.getString("REMARKS"));
                exist = true;
            }
        } catch (SQLException e) {
            throw new Exception(e);
        }

        return exist;
    }

    public boolean isExistSequence(
            String instanceName, String sequenceName)
            throws Exception {
        Connection connection = dataSourceManager
                .getDataSource(instanceName)
                .getConnection();
        boolean exist = false;
        try {
            DatabaseMetaData meta = connection.getMetaData();
            ResultSet res = meta.getTables(null, null, sequenceName.toUpperCase(),
                    new String[] {"SEQUENCE"});
            while (res.next()) {
//                System.out.println(
//                        "   " + res.getString("TABLE_CAT")
//                                + ", " + res.getString("TABLE_SCHEM")
//                                + ", " + res.getString("TABLE_NAME")
//                                + ", " + res.getString("TABLE_TYPE")
//                                + ", " + res.getString("REMARKS"));
                exist = true;
            }
        } catch (SQLException e) {
            throw new Exception(e);
        }

        return exist;
    }

    public boolean isExistUniqueIndex(
            String instanceName, String uniqueIndexName)
            throws Exception {
        Connection connection = dataSourceManager
                .getDataSource(instanceName)
                .getConnection();
        boolean exist = false;
        try {
            DatabaseMetaData meta = connection.getMetaData();
            ResultSet res = meta.getTables(null, null, uniqueIndexName.toUpperCase(),
                    new String[] {"INDEX"});
            while (res.next()) {
//                System.out.println(
//                        "   " + res.getString("TABLE_CAT")
//                                + ", " + res.getString("TABLE_SCHEM")
//                                + ", " + res.getString("TABLE_NAME")
//                                + ", " + res.getString("TABLE_TYPE")
//                                + ", " + res.getString("REMARKS"));
                exist = true;
            }
        } catch (SQLException e) {
            throw new Exception(e);
        }

        return exist;
    }

    public boolean createTable(
            String instanceName, String tableName,
            List<FieldInfo> fieldInfoList, PartitionInfo partitionInfo)
            throws Exception {
        Connection connection = dataSourceManager
                .getDataSource(instanceName)
                .getConnection();
        try {
            StringBuilder sqlCreate = new StringBuilder(
                    "CREATE TABLE " + tableName.toUpperCase() + "(");
            sqlCreate.append("id NUMBER(18,0) NOT NULL,");
            for(FieldInfo info : fieldInfoList) {
                if (info.getSqlType() != null && !info.getSqlType().isEmpty())
                    sqlCreate.append(info.getName()).append(" ")
                            .append(info.getSqlType()).append(",");
            }
            if(partitionInfo != null) {
                sqlCreate.append("PARTITION_KEY ").append(partitionInfo.getColumnType()).append(",");
            }
//            sqlCreate.deleteCharAt(sqlCreate.length());
            sqlCreate.append("CONSTRAINT ").append(tableName.toUpperCase())
                    .append("_PK PRIMARY KEY (ID)").append(")")
                    .append(" partition by ").append(partitionInfo.getType())
                    .append("(").append("PARTITION_KEY")
                    .append(") interval (")
                    .append(partitionInfo.getInterval()).append(") ");
            if(partitionInfo.getEmptyValue() > 0) {
                sqlCreate.append("(partition empty values less than (").append(101).append("))");
            }
            Statement stmt = connection.createStatement();
            stmt.execute(sqlCreate.toString());
        } catch (SQLException e) {
            throw new Exception(e);
        }
        return true;
    }

    public boolean createSequence(
            String instanceName, String tableName,
            SequenceInfo sequenceInfo)
            throws Exception {
        Connection connection = dataSourceManager
                .getDataSource(instanceName)
                .getConnection();
        try {
            StringBuilder sqlCreate = new StringBuilder("CREATE SEQUENCE ");
            sqlCreate.append(sequenceInfo.getName().toUpperCase())
                    .append(" minvalue ").append(sequenceInfo.getMinValue())
                    .append(" maxvalue ").append(sequenceInfo.getMaxValue())
                    .append(" cycle")
                    .append(" START WITH ")
                    .append(sequenceInfo.getStartWith())
                    .append(" INCREMENT BY ")
                    .append(sequenceInfo.getIncrementBy())
                    .append(" cache ").append(sequenceInfo.getCacheSize());
            Statement stmt = connection.createStatement();
            stmt.execute(sqlCreate.toString());
        } catch (SQLException e) {
            throw new Exception(e);
        }
        return true;
    }

    public boolean createUniqueIndex(
            String instanceName, String tableName,
            UniqueIndexInfo uniqueIndexInfo)
            throws Exception {
        Connection connection = dataSourceManager
                .getDataSource(instanceName)
                .getConnection();
        try {
            StringBuilder sqlCreate = new StringBuilder("create unique index ")
                    .append(uniqueIndexInfo.getName().toUpperCase())
                    .append(" on ").append(tableName)
                    .append(" (");
            if(uniqueIndexInfo.isLocal())
                sqlCreate.append("partition_key) local");
            else {
                sqlCreate.deleteCharAt(sqlCreate.length() - 1);
                sqlCreate.append(")");
            }
            Statement stmt = connection.createStatement();
            stmt.execute(sqlCreate.toString());
        } catch (SQLException e) {
            throw new Exception(e);
        }
        return true;
    }

    public Connection getConnection(String instanceName)
            throws SQLException {
        return dataSourceManager
                .getDataSource(instanceName)
                .getConnection();
    }

    public ResultSet executeQuery(
            String instanceName,
            QPQueryStruct queryStruct,
            Map<String, Object> paramValueMap)
            throws QPException {
        try {
            Connection connection = dataSourceManager
                    .getDataSource(instanceName)
                    .getConnection();
            PreparedStatement statement = connection
                    .prepareStatement(queryStruct.query);
            for (QPQueryParamStruct param : queryStruct.params) {
                if(param.type.equalsIgnoreCase("string"))
                    statement.setString(param.order,
                            (String) paramValueMap.get(param.name));
                if(param.type.equalsIgnoreCase("int")) {
                    statement.setInt(param.order,
                            (Integer)paramValueMap.get(param.name));
                }
            }
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new QPException(e);
        }
    }
}
