package com.yaxuansheng;

import com.yaxuansheng.resources.MicroServiceResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Created by shengy on 1/19/16.
 */
public class MicroServiceApplication extends Application<MicroServiceConfiguration> {
    public static void main(String[] args) throws Exception {
        new MicroServiceApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<MicroServiceConfiguration> bootstrap)
    {
        // nothing to do yet
    }

    @Override
    public void run(MicroServiceConfiguration configuration,
                    Environment environment) {
        final MicroServiceResource resource = new MicroServiceResource(
            configuration.getTemplate(),
            configuration.getDefaultName()
        );
        final TemplateHealthCheck healthCheck =
            new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);
    }
}
