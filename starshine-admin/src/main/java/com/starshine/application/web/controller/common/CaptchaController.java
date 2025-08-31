package com.starshine.application.web.controller.common;

import com.google.code.kaptcha.Producer;
import com.starshine.application.common.cache.RedisCache;
import com.starshine.application.common.config.StarshineConfig;
import com.starshine.application.common.constant.CacheConstants;
import com.starshine.application.common.constant.CommonConstants;
import com.starshine.application.common.constant.ConfigConstants;
import com.starshine.application.common.utils.CommonUtils;
import com.starshine.application.common.web.RESTfulResult;
import com.starshine.application.service.SysConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
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
@RequiredArgsConstructor
public class CaptchaController {

    @Qualifier("captchaProducer")
    private final Producer captchaProducer;

    @Qualifier("captchaProducerMath")
    private final Producer captchaProducerMath;
    private final RedisCache redisCache;
    private final SysConfigService sysConfigService;
    private final StarshineConfig starshineConfig;

    @GetMapping("/captcha")
    public RESTfulResult getCaptcha() {
        RESTfulResult result = RESTfulResult.ok();
        boolean captchaEnabled = sysConfigService.getConfigValue(ConfigConstants.SYS_CAPTCHA, Boolean.class);
        result.put("captchaEnabled", captchaEnabled);
        if (!captchaEnabled) {
            return result;
        }
        String uuid = CommonUtils.simpleUUID();
        String cacheKey = CacheConstants.CAPTCHA_KEY+ uuid;
        String capchaType = starshineConfig.getCaptchaType();

        String capStr = null;
        String code = null;
        BufferedImage bufferedImage = null;
        if("math".equals(capchaType)) {
            String capText = captchaProducerMath.createText();
            capStr = capText.substring(0, capText.lastIndexOf("@"));
            code = capText.substring(capText.lastIndexOf("@") + 1);
            bufferedImage = captchaProducerMath.createImage(capStr);
        } else if("char".equals(capchaType)) {
            capStr = code = captchaProducer.createText();
            bufferedImage = captchaProducer.createImage(capStr);
        }
        redisCache.setCacheObject(cacheKey, code, CommonConstants.CAPTCHA_EXPIRE_TIME, TimeUnit.MINUTES);
        FastByteArrayOutputStream outputStream = new FastByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "jpg", outputStream);
        }catch (Exception e){
            return RESTfulResult.error(e.getMessage());
        }
        // ✅ 将字节数组转为 Base64 编码字符串
        String base64Image = Base64.getEncoder().encodeToString(outputStream.toByteArray());
        // ✅ 添加 Data URL 前缀，前端可以直接用
        String dataUrl = "data:image/jpg;base64," + base64Image;
        result.put("uuid", uuid);
        result.put("img", dataUrl);
        return result;
    }
}
