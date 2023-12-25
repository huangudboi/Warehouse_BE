package com.example.securityapp.controller;

import com.example.securityapp.model.Message;
import com.example.securityapp.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/message")
@RequiredArgsConstructor
public class MessageController {

    private static final Logger logger = LogManager.getLogger(MessageController.class);

    @Autowired
    private MessageService messageService;

    @GetMapping("/getAllMessage")
    public ResponseEntity<List<Message>> findALlMessage() {
        logger.info("=== Start call api get all messages ===");
        List<Message> messages = messageService.findAll();
        ResponseEntity<List<Message>> response;
        try {
            response = new ResponseEntity<>(messages, HttpStatus.OK);
        }catch (IndexOutOfBoundsException ex) {
            response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception ex){
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("=== End call api get all messages ===");
        return response;
    }

    @PostMapping(value = "/sendMessage")
    public ResponseEntity<Message> createMessage(@RequestBody Message message, UriComponentsBuilder builder) {
        logger.info("=== Start call api send message ===");
        ResponseEntity<Message> response;
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/{id}").buildAndExpand(message.getId()).toUri());
        try {
            messageService.save(message);
            response = new ResponseEntity<>(message, HttpStatus.CREATED);
        }catch (Exception ex){
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("=== End call api send message ===");
        return response;
    }

    @DeleteMapping(value = "/deleteMessage/{id}")
    public ResponseEntity<Message> deleteMessage(@PathVariable("id") Long id) {
        logger.info("=== Start call api delete message id: "+id+" ===");
        Optional<Message> message = messageService.findById(id);
        ResponseEntity<Message> response;
        try {
            messageService.remove(message.get());
            response = new ResponseEntity<>(HttpStatus.OK);
            Message actualMessage = message.orElseThrow(() -> new NoSuchElementException("Message with id:" +id+" don't found"));
        }catch (NoSuchElementException ex) {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception ex){
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("=== End call api delete message id: "+id+" ===");
        return response;
    }

    @GetMapping(value = "/detail/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Message> getMessageById(@PathVariable("id") Long id) {
        logger.info("=== Start call api get message's detail ===");
        Optional<Message> message = messageService.findById(id);
        ResponseEntity<Message> response;
        try {
            response = new ResponseEntity<>(message.get(), HttpStatus.OK);
            Message actualMessage = message.orElseThrow(() -> new NoSuchElementException("Message with id:" +id+" don't found"));
        }catch (NoSuchElementException ex) {
            response = new ResponseEntity<>(message.get(), HttpStatus.NO_CONTENT);
        }catch (Exception ex){
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("=== End call api get message's detail ===");
        return response;
    }

    @GetMapping(value = "",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Message>> getMessageByUsername(@RequestParam(value="username") String username) {
        logger.info("=== Start call api get list message with username: "+username+" ===");
        List<Message> messages = messageService.findByUserName(username);
        ResponseEntity<List<Message>> response;
        try {
            response = new ResponseEntity<>(messages, HttpStatus.OK);
        }catch (IndexOutOfBoundsException ex) {
            response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception ex){
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("=== End call api get list message with username: "+username+" ===");
        return response;
    }
}
