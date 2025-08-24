package com.starshine.domain;

import com.starshine.common.entities.BasicAggregateRootWithKey;
import lombok.Data;

import java.util.List;

/**
 * 租户
 * @author starshine
 */
@Data
public class Tenant extends BasicAggregateRootWithKey<Long> {

    /**
     * 租户名称
     */
    private String name;

    /**
     * 租户名称
     */
    private String normalizedName;

    /**
     * 连接字符串
     */
    private List<TenantConnectionString> connectionStrings;

    /**
     * 获取默认连接字符串
     * @return
     */
    public String FindDefaultConnectionString()
    {
        return FindConnectionString("Default");
    }

    /**
     * 获取连接字符串
     * @param name
     * @return
     */
    public String FindConnectionString(String name)
    {
        return connectionStrings.stream()
                .filter(x -> x.getName().equals(name))
                .findFirst()
                .map(x -> x.getValue())
                .orElse(null);
    }

    /**
     * 设置默认连接字符串
     * @param connectionString
     */
    public void SetDefaultConnectionString(String connectionString)
    {
        SetConnectionString("Default", connectionString);
    }

    /**
     * 设置连接字符串
     * @param name
     * @param connectionString
     */
    public void SetConnectionString(String name, String connectionString)
    {
        TenantConnectionString tenantConnectionString = connectionStrings.stream()
                .filter(x -> x.getName().equals(name))
                .findFirst()
                .orElse(null);
        if (tenantConnectionString != null)
        {
            tenantConnectionString.setValue(connectionString);
        }
        else
        {
            connectionStrings.add(new TenantConnectionString(getId(), name, connectionString));
        }
    }

    /**
     * 移除连接字符串
     * @param name
     */
    public void RemoveDefaultConnectionString()
    {
        RemoveConnectionString("Default");
    }

    /**
     * 移除连接字符串
     * @param name
     */
    public void RemoveConnectionString(String name)
    {
        TenantConnectionString tenantConnectionString = connectionStrings.stream()
                .filter(x -> x.getName().equals(name))
                .findFirst()
                .orElse(null);
        if (tenantConnectionString != null)
        {
            connectionStrings.remove(tenantConnectionString);
        }
    }

}
