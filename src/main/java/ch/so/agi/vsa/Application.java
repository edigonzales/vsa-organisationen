package ch.so.agi.vsa;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;

import jakarta.annotation.PreDestroy;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.configuration.server.ServerRuntime;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
@Theme(value = "flowcrmtutorial")
public class Application implements AppShellConfigurator {

    ServerRuntime cayenneRuntime;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    ObjectContext objectContext() {
        cayenneRuntime = ServerRuntime.builder()
                .url("jdbc:sqlite:"+new File(appProperties.getIlicachedb()).getAbsolutePath())
                .jdbcDriver("org.h2.Driver")
                .addConfig("cayenne/cayenne-project.xml")
                .build();
        
        ObjectContext context = cayenneRuntime.newContext();
        
        return context;
    }
    
    @PreDestroy
    public void shutdownCayenne() {
        cayenneRuntime.shutdown();
    }

}
