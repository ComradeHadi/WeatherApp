package com.hadi.apptemplate.ui;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.Router;

public interface ScreenNavigator {

    void initialiseWithRouter(Router router, Controller controller);

    boolean pop();

    void clear();
}
