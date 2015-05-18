package com.novbank.ndp.metrics;

import javax.servlet.annotation.WebListener;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.health.HealthCheckRegistry;
import com.codahale.metrics.servlets.AdminServletContextListener;

/**
 * A ServletContextListener to set the ServletContext attributes that the
 * Metrics servlets expect.
 *
 * Created by hp on 2015/5/18.
 */
@WebListener
public class MetricsContextListener extends AdminServletContextListener{

    /**
     * Get the metrics registry for fcrepo
     * @return the metrics registry
     */
    @Override
    protected MetricRegistry getMetricRegistry() {
        return RegistryService.getInstance().getMetrics();
    }

    /**
     * Provide a health-check registry
     * TODO actually populate the health-check registry with checks
     * @return a new health check registry
     */
    @Override
    protected HealthCheckRegistry getHealthCheckRegistry() {
        return new HealthCheckRegistry();
    }
}
