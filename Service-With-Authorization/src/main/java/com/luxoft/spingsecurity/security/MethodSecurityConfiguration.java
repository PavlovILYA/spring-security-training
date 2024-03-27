package com.luxoft.spingsecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@EnableGlobalMethodSecurity(
        jsr250Enabled = false,
        prePostEnabled = true,
        securedEnabled = true
)
public class MethodSecurityConfiguration extends GlobalMethodSecurityConfiguration {

    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private MethodSecurityExpressionHandler defaultMethodSecurityExpressionHandler;
    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        return defaultMethodSecurityExpressionHandler;
    }
}
