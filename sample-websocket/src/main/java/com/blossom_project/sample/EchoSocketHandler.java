package com.blossom_project.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;


public class EchoSocketHandler extends TextWebSocketHandler {

  private static Logger logger = LoggerFactory.getLogger(EchoSocketHandler.class);

  @Override
  public void afterConnectionEstablished(WebSocketSession session) {
    logger.debug("Opened new session in instance " + this);
  }

  @Override
  public void handleTextMessage(WebSocketSession session, TextMessage message)
    throws Exception {
    String echoMessage = message.getPayload();
    logger.debug(echoMessage);
    session.sendMessage(new TextMessage(echoMessage));
  }

  @Override
  public void handleTransportError(WebSocketSession session, Throwable exception)
    throws Exception {
    session.close(CloseStatus.SERVER_ERROR);
  }
}
