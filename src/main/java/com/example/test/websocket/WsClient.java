package com.example.test.websocket;

import jakarta.websocket.ClientEndpoint;
import jakarta.websocket.CloseReason;
import jakarta.websocket.Endpoint;
import jakarta.websocket.EndpointConfig;
import jakarta.websocket.MessageHandler;
import jakarta.websocket.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

@ClientEndpoint
public class WsClient extends Endpoint implements MessageHandler {

    protected static final Logger LOG = LoggerFactory.getLogger(WsServer.class);
    @Override
    @SuppressWarnings("java:S1854")
    public void onOpen(Session session, EndpointConfig endpointConfig) {
        String requestId = UUID.randomUUID().toString();
        session.addMessageHandler(new MessageHandler.Whole<String>() {
            public void onMessage(String text) {
                    LOG.info("onMessage: " + text);
            }
        });

        session.getAsyncRemote().sendText("Youhouuuu");
    }

    @Override
    public void onClose(Session session, CloseReason closeReason) {
        LOG.info("onClose - session id: " + session.getId() + " - close reason: " + closeReason.toString());
    }


    @Override
    public void onError(Session session, Throwable t) {
        LOG.error(t.getMessage(), t);
    }

}
