package rao.saikrishna.apps.credmgr.api.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PingResourceController {
    private static final Logger logger = LoggerFactory.getLogger(PingResourceController.class);

    @Value("${credmgr.api.ping-message}")
    private String pingMessage;

    @Value("${credmgr.api.admin-ping-message}")
    private String adminPingMessage;

    @GetMapping(value = "/ping", produces =  MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok(pingMessage);
    }

    @GetMapping(value = "/adminPing", produces =  MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> adminPing() {
        return ResponseEntity.ok(adminPingMessage);
    }

}
