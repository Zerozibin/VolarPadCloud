/**
 * All rights Reserved, Designed By www.sf-tech.com.cn
 *
 * @Package: com.sf.etps.ins.front
 * @Description:
 * @author: 01383875
 * @date: 2019/4/22 20:22
 * @version: V1.0
 * @Copyright: 2019 www.sf-tech.com.cn Inc. All rights reserved.
 * 注意：本内容仅限于顺丰科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.sf.etps.boot;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Description：eureka注册中心入口
 * @Author：
 * @Date：2019/4/22 20:22
 * @Version：V1.0
 **/
@SpringBootApplication
@EnableEurekaServer
@EnableDiscoveryClient
public class SpringBootApp {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootApp.class, args);
    }
}
