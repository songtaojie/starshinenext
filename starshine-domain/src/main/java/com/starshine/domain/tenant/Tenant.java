package com.starshine.domain.tenant;

import com.starshine.common.entities.IAggregateRootWithKey;
import com.starshine.common.entities.BaseEntityWithKey;
import com.starshine.shared.constant.CommonConstants;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 租户
 * @author starshine
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class Tenant extends BaseEntityWithKey<Long> implements IAggregateRootWithKey<Long> {

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
    public String findDefaultConnectionString()
    {
        return findConnectionString(CommonConstants.DEFAULT_TENANT_NAME);
    }

    /**
     * 获取连接字符串
     * @param name
     * @return
     */
    public String findConnectionString(String name)
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
    public void setDefaultConnectionString(String connectionString)
    {
        setConnectionString(CommonConstants.DEFAULT_TENANT_NAME, connectionString);
    }

    /**
     * 设置连接字符串
     * @param name
     * @param connectionString
     */
    public void setConnectionString(String name, String connectionString)
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
     */
    public void removeDefaultConnectionString()
    {
        removeConnectionString("Default");
    }

    /**
     * 移除连接字符串
     * @param name
     */
    public void removeConnectionString(String name)
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
