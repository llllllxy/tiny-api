package org.tinycloud.tinyapi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.tinycloud.tinyapi.common.utils.LocalHostUtils;

@Slf4j
@SpringBootApplication(scanBasePackages = {"org.tinycloud.tinyapi"})
public class TinyApiApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext application = SpringApplication.run(TinyApiApplication.class, args);
        Environment env = application.getEnvironment();
        String ip = LocalHostUtils.getLocalHost();
        String port = env.getProperty("server.port");

        log.info("\n----------------------------------------------------------\n\t" +
                "tiny-api 启动成功！\n\t" +
                "┌─┐┬ ┬┌─┐┌─┐┌─┐┌─┐┌─┐  ┌─┐┌┬┐┌─┐┬─┐┌┬┐┌─┐┌┬┐   ┬\n\t" +
                "└─┐│ ││  │  ├┤ └─┐└─┐  └─┐ │ ├─┤├┬┘ │ ├┤  ││   │\n\t" +
                "└─┘└─┘└─┘└─┘└─┘└─┘└─┘  └─┘ ┴ ┴ ┴┴└─ ┴ └─┘─┴┘   o\n\t" +
                "-------------------------------------------------------------------------\n\t" +
                "Access URLs:\n\t" +
                "Local: \t\thttp://localhost:" + port + "/\n\t" +
                "External: \thttp://" + ip + ":" + port + "/\n\t" +
                "Swagger-UI: http://" + ip + ":" + port + "/doc.html\n\t" +
                "-------------------------------------------------------------------------");
    }

}
