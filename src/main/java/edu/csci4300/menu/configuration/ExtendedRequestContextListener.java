package edu.csci4300.menu.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextListener;

import javax.servlet.annotation.WebListener;

/* Enables the Request Context Listener -- Required for Spring Session */

@Configuration
@WebListener
public class ExtendedRequestContextListener extends RequestContextListener {
}
