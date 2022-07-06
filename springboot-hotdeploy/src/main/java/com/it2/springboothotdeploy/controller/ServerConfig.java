package com.it2.springboothotdeploy.controller;


import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DataSizeUnit;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.stereotype.Component;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

//@Component
@Data
@ConfigurationProperties(prefix = "servers")
@Validated  //开启对当前bean的属性注入校验
public class ServerConfig {

    private String ipAddress;

    @Max(value = 10000,message="最大值不能超过10000")
    private int port;

    /**
     * 时间计量
     */
    @DurationUnit(ChronoUnit.HOURS)
    private Duration timeout;

    /**
     * 空间计量单位
     */
//    @DataSizeUnit(DataUnit.MEGABYTES) //MB
    private DataSize dataSize;


}
