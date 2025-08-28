DROP TABLE IF EXISTS public.sys_user;
CREATE TABLE public.sys_user (
   id BIGINT NOT NULL PRIMARY KEY,
   username VARCHAR(255) NOT NULL,
   normalized_username VARCHAR(255) NOT NULL,
   password VARCHAR(255) DEFAULT NULL,
   name VARCHAR(255) DEFAULT NULL,
   email VARCHAR(255) DEFAULT NULL,
   normalized_email VARCHAR(255) DEFAULT NULL,
   email_confirmed BOOLEAN NOT NULL DEFAULT FALSE,
   password_hash VARCHAR(255) DEFAULT NULL,
   security_stamp VARCHAR(255) DEFAULT NULL,
   external BOOLEAN NOT NULL DEFAULT FALSE,
   phone_number VARCHAR(255) DEFAULT NULL,
   phone_number_confirmed BOOLEAN NOT NULL DEFAULT FALSE,
   status VARCHAR(2) DEFAULT NULL,
   lockout_enabled BOOLEAN NOT NULL DEFAULT TRUE,
   lockout_end TIMESTAMP WITH TIME ZONE DEFAULT NULL,
   access_failed_count INTEGER NOT NULL DEFAULT 0,
   should_change_password BOOLEAN NOT NULL DEFAULT FALSE,
   last_password_change_time TIMESTAMP WITH TIME ZONE DEFAULT NULL,
   last_login_ip INET DEFAULT NULL,
   last_login_location VARCHAR(255) DEFAULT NULL,
   last_login_time TIMESTAMP WITH TIME ZONE DEFAULT NULL,
   last_login_device VARCHAR(255) DEFAULT NULL,
   avatar VARCHAR(255) DEFAULT NULL,
   user_type VARCHAR(50) DEFAULT NULL,
   extra_info JSONB DEFAULT NULL,
   tenant_id BIGINT DEFAULT NULL,
   creator_id BIGINT DEFAULT NULL,
   created_time TIMESTAMP WITH TIME ZONE DEFAULT NULL,
   last_modifier_id BIGINT DEFAULT NULL,
   last_modified_time TIMESTAMP WITH TIME ZONE DEFAULT NULL
) ;

-- 添加注释（COMMENT）
COMMENT ON TABLE public.sys_user IS '系统用户表';

COMMENT ON COLUMN public.sys_user.username IS '用户名';
COMMENT ON COLUMN public.sys_user.normalized_username IS '此用户的规范化用户名。';
COMMENT ON COLUMN public.sys_user.email_confirmed IS '电子邮箱已确认';
COMMENT ON COLUMN public.sys_user.external IS '是否外部用户';
COMMENT ON COLUMN public.sys_user.phone_number IS '手机号码';
COMMENT ON COLUMN public.sys_user.phone_number_confirmed IS '手机号码已确认';
COMMENT ON COLUMN public.sys_user.status IS '状态 0=正常,1=停用';
COMMENT ON COLUMN public.sys_user.lockout_enabled IS '锁定';
COMMENT ON COLUMN public.sys_user.lockout_end IS '锁定到期时间';
COMMENT ON COLUMN public.sys_user.access_failed_count IS '访问失败次数';
COMMENT ON COLUMN public.sys_user.should_change_password IS '下次登录时应更改密码。';
COMMENT ON COLUMN public.sys_user.last_password_change_time IS '最后一次密码更改时间';
COMMENT ON COLUMN public.sys_user.last_login_ip IS '最后一次登录ip';
COMMENT ON COLUMN public.sys_user.last_login_location IS '最后一次登录位置';
COMMENT ON COLUMN public.sys_user.last_login_time IS '最后一次登录时间';
COMMENT ON COLUMN public.sys_user.last_login_device IS '最后一次登录设备';
COMMENT ON COLUMN public.sys_user.avatar IS '头像';
COMMENT ON COLUMN public.sys_user.user_type IS '用户类型';
COMMENT ON COLUMN public.sys_user.extra_info IS '扩展信息';
COMMENT ON COLUMN public.sys_user.tenant_id IS '租户id';