package com.cm.secret_recipe;

import com.squareup.otto.Bus;

/**
 * Created by ijaebeom on 2015. 9. 29..
 */
public final class BusProvider {
    private static final Bus BUS = new Bus();

    public static Bus getInstance() {
        return BUS;
    }

    private BusProvider() {
        // No instances.
    }
}