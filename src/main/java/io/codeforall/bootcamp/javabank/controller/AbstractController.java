package io.codeforall.bootcamp.javabank.controller;

import io.codeforall.bootcamp.javabank.services.AuthService;
import io.codeforall.bootcamp.javabank.view.View;

/**
 * A generic controller to be used as a base for concrete controller implementations
 * @see Controller
 */
public abstract class AbstractController implements Controller {

    protected AuthService authService;
    protected View view;

    /**
     * Sets the authentication service
     *
     * @param authService the authentication service to set
     */
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Sets the controller view
     *
     * @param view the view to be set
     */
    public void setView(View view) {
        this.view = view;
    }

    /**
     * @see Controller#init()
     */
    @Override
    public void init() {
        view.show();
    }
}
