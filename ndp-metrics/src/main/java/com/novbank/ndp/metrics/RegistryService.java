package com.novbank.ndp.metrics;

import static com.codahale.metrics.SharedMetricRegistries.getOrCreate;

import com.codahale.metrics.MetricRegistry;

/**
 * Provide helpers for working with the Metrics registry
 *
 * Created by hp on 2015/5/18.
 */
public class RegistryService {
    private static final MetricRegistry METRICS = getOrCreate("ndp-metrics");
    private static volatile RegistryService instance = null;

    private RegistryService() {
        // New instances should come from the singleton
    }

    /**
     * Create the instance
     * @return the local object
     */
    public synchronized static RegistryService getInstance() {
        RegistryService local = instance;
        if (null == local) {
            instance = local = new RegistryService();
        }
        return local;
    }

    /**
     * Get the current registry service
     *
     * TODO the new upstream SharedMetricRegistries may make this obsolete
     * @return the current registry service
     */
    public MetricRegistry getMetrics() {
        return METRICS;
    }
}

