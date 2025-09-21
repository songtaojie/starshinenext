package com.starshine.web.rest.controller.common;

import com.google.code.kaptcha.Producer;
import com.starshine.common.cache.RedisCache;
import com.starshine.common.config.StarshineConfig;
import com.starshine.common.utils.CommonUtils;
import com.starshine.common.web.RestfulResult;
import com.starshine.application.service.SysConfigService;
import com.starshine.shared.constant.CacheConstants;
import com.starshine.shared.constant.CommonConstants;
import com.starshine.shared.constant.ConfigConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

/**
 * 验证码
 * @author songtaojie
 * @version 1.0
 * @since 2025-08-30 下午 周六
 */
@RestController
public class CaptchaController {
    private final Producer captchaProducer;
    private final Producer captchaProducerMath;
    private final RedisCache redisCache;
    private final SysConfigService sysConfigService;
    private final StarshineConfig starshineConfig;

    public CaptchaController(@Qualifier("captchaProducer") Producer captchaProducer,
                             @Qualifier("captchaProducerMath") Producer captchaProducerMath,
                             RedisCache redisCache,
                             SysConfigService sysConfigService,
                             StarshineConfig starshineConfig) {
        this.captchaProducer = captchaProducer;
        this.captchaProducerMath = captchaProducerMath;
        this.redisCache = redisCache;
        this.sysConfigService = sysConfigService;
        this.starshineConfig = starshineConfig;
    }


    @GetMapping("/captcha")
    public RestfulResult getCaptcha() {
        RestfulResult result = RestfulResult.success(null);
        boolean captchaEnabled = sysConfigService.getConfigValue(ConfigConstants.SYS_CAPTCHA, Boolean.class);
        result.with("captchaEnabled", captchaEnabled);
        if (!captchaEnabled) {
            return result;
        }
        String uuid = CommonUtils.simpleUUID();
        String cacheKey = CacheConstants.CAPTCHA_KEY + uuid;
        String capchaType = starshineConfig.getCaptchaType();

        String capStr = null;
        String code = null;
        BufferedImage bufferedImage = null;
        if ("math".equals(capchaType)) {
            String capText = captchaProducerMath.createText();
            capStr = capText.substring(0, capText.lastIndexOf("@"));
            code = capText.substring(capText.lastIndexOf("@") + 1);
            bufferedImage = captchaProducerMath.createImage(capStr);
        } else if ("char".equals(capchaType)) {
            capStr = code = captchaProducer.createText();
            bufferedImage = captchaProducer.createImage(capStr);
        }
        redisCache.setCacheObject(cacheKey, code, CommonConstants.CAPTCHA_EXPIRE_TIME, TimeUnit.MINUTES);
        FastByteArrayOutputStream outputStream = new FastByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "jpg", outputStream);
        } catch (Exception e) {
            return RestfulResult.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        }
        // ✅ 将字节数组转为 Base64 编码字符串
        String base64Image = Base64.getEncoder().encodeToString(outputStream.toByteArray());
        // ✅ 添加 Data URL 前缀，前端可以直接用
        String dataUrl = "data:image/jpg;base64," + base64Image;
        result.with("uuid", uuid);
        result.with("img", dataUrl);
        return result;
    }
}
