package com.tweetroo.api.controllers;

import com.tweetroo.api.models.TweetModel;
import com.tweetroo.api.repositories.TweetRepository;
import com.tweetroo.api.repositories.UserRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tweets")
public class TweetController {

    private final TweetRepository tweetRepository;
    private final UserRepository userRepository;

    public TweetController(TweetRepository tweetRepository, UserRepository userRepository) {
        this.tweetRepository = tweetRepository;
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<Void> createTweet(@RequestBody TweetRequest tweetRequest) {
        // Verifica se o usuário existe
        if (!userRepository.existsById(tweetRequest.getUserId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        // Cria um novo tweet
        TweetModel tweet = new TweetModel();
        tweet.setText(tweetRequest.getText());
        tweet.setUser(userRepository.getById(tweetRequest.getUserId()));

        // Salva o tweet no banco de dados
        tweetRepository.save(tweet);

        // Retorna o status 201 Created
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public static class TweetRequest {
        private String text;
        private Long userId;

        // Construtor vazio
        public TweetRequest() {
        }

        // Construtor com parâmetros
        public TweetRequest(String text, Long userId) {
            this.text = text;
            this.userId = userId;
        }

        // Getter para o campo 'text'
        public String getText() {
            return text;
        }

        // Setter para o campo 'text'
        public void setText(String text) {
            this.text = text;
        }

        // Getter para o campo 'userId'
        public Long getUserId() {
            return userId;
        }

        // Setter para o campo 'userId'
        public void setUserId(Long userId) {
            this.userId = userId;
        }
    }

}
