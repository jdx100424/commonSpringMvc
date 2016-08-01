package com.maoshen.db;

public class ReplicationDataSourceHolder {
    public static final ThreadLocal<String> holder = new ThreadLocal<String>();

    public static String getDataSource() {
        return holder.get();
    }

    public static void putDataSource(String dataSourceName) {
        holder.set(dataSourceName);
    }
}
