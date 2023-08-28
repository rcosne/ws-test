package com.example.test.websocket;

import jakarta.websocket.CloseReason;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@ServerEndpoint(value = "/wstest",
        configurator = CustomSpringConfigurator.class)
public class WsServer {

    protected static final Logger LOG = LoggerFactory.getLogger(WsServer.class);

    @OnOpen
    public void onSessionOpened(Session session) {
        LOG.info("onSessionOpened - session id: " + session.getId());
        // 1 day
        session.setMaxIdleTimeout(1000 * 60 * 60 * 24);
    }

    @OnMessage
    public String onMessageReceived(String message, Session session)  {
        LOG.info("onMessageReceived: " + message);
        return "something";
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        LOG.info("onClose - session id: " + session.getId() + " - close reason: " + closeReason
                .toString());
    }

    @OnError
    public void onErrorReceived(Throwable t) {
        LOG.error(t.getMessage(), t);
    }

}
