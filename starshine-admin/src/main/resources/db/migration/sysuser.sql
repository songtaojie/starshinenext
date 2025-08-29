DROP TABLE IF EXISTS public.sys_user;
CREATE TABLE public.sys_user
(
    id                        BIGINT PRIMARY KEY       NOT NULL,

    -- 登录相关
    username                  VARCHAR(128)             NOT NULL,               -- 支持更灵活的用户名
    normalized_username       VARCHAR(128)             NOT NULL,               -- 大写/小写归一化

    password                  VARCHAR(255)                      DEFAULT NULL,  -- 原始密码（可选，通常不用）
    password_hash             VARCHAR(255)             NOT NULL,               -- 推荐只存 hash

    -- 用户信息
    name                      VARCHAR(100)                      DEFAULT NULL,  -- 姓名/昵称
    email                     VARCHAR(256)                      DEFAULT NULL,  -- RFC 5321: 最大 254，取 256 安全
    normalized_email          VARCHAR(256)                      DEFAULT NULL,

    email_confirmed           BOOLEAN                  NOT NULL DEFAULT FALSE,

    -- 安全相关
    security_stamp            CHAR(36)                 NOT NULL,               -- UUID 格式，固定长度
    concurrency_stamp         CHAR(36)                          DEFAULT NULL,  -- 乐观锁，可选

    external                  BOOLEAN                  NOT NULL DEFAULT FALSE, -- 第三方登录

    phone_number              VARCHAR(32)                       DEFAULT NULL,  -- 支持国际号码 +86-138-xxxx-xxxx
    phone_number_confirmed    BOOLEAN                  NOT NULL DEFAULT FALSE,

    -- 状态与锁定
    status                    VARCHAR(20)                       DEFAULT NULL,  -- 'ACTIVE', 'INACTIVE', 'LOCKED' 等
    lockout_enabled           BOOLEAN                  NOT NULL DEFAULT TRUE,
    lockout_end               TIMESTAMP WITH TIME ZONE          DEFAULT NULL,
    access_failed_count       INTEGER                  NOT NULL DEFAULT 0,

    should_change_password    BOOLEAN                  NOT NULL DEFAULT FALSE,
    last_password_change_time TIMESTAMP WITH TIME ZONE          DEFAULT NULL,

    -- 登录信息
    last_login_ip             INET                              DEFAULT NULL,
    last_login_location       VARCHAR(256)                      DEFAULT NULL,  -- 城市/国家
    last_login_time           TIMESTAMP WITH TIME ZONE          DEFAULT NULL,
    last_login_device         VARCHAR(256)                      DEFAULT NULL,  -- 设备型号或浏览器

    avatar                    VARCHAR(512)                      DEFAULT NULL,  -- 头像 URL，可能较长
    user_type                 VARCHAR(50)                       DEFAULT NULL,  -- 'ADMIN', 'USER' 等

    -- 扩展字段
    extra_info                JSONB                             DEFAULT NULL,  -- 自定义字段，JSON 格式

    -- 多租户 & 审计
    tenant_id                 BIGINT                            DEFAULT NULL,
    creator_id                BIGINT                            DEFAULT NULL,
    creation_time              TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    last_modifier_id          BIGINT                            DEFAULT NULL,
    last_modification_time        TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    deleter_id                BIGINT                            DEFAULT NULL,
    deletion_time              TIMESTAMP WITH TIME ZONE          DEFAULT NULL,
    deleted                   BOOLEAN                  NOT NULL DEFAULT FALSE  -- 建议用 BOOLEAN 而非 BIT
);

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
COMMENT ON COLUMN public.sys_user.creator_id IS '创建者id';
COMMENT ON COLUMN public.sys_user.creation_time IS '创建时间';
COMMENT ON COLUMN public.sys_user.last_modifier_id IS '最后更新者id';
COMMENT ON COLUMN public.sys_user.last_modification_time IS '最后更新时间';
COMMENT ON COLUMN public.sys_user.deleter_id IS '删除者id';
COMMENT ON COLUMN public.sys_user.deletion_time IS '删除时间';
COMMENT ON COLUMN public.sys_user.deleted IS '是否删除';