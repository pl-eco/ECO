package org.sunflow.core;

import org.sunflow.SunflowAPI;

import java.util.Map;

/**
 * This holds rendering objects as key, value pairs.
 */
public final class Options extends ParameterList implements RenderObject {
    public boolean update(ParameterList pl, SunflowAPI api) {
        // take all attributes, and update them into the current set
        for (Map.Entry<String, Parameter> e : pl.list.entrySet()) {
            list.put(e.getKey(), e.getValue());
            e.getValue().check();
        }
        return true;
    }
}
