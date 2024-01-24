package com.tweetroo.api.controllers;

import com.tweetroo.api.models.TweetModel;
import com.tweetroo.api.repositories.TweetRepository;
import com.tweetroo.api.repositories.UserRepository;

import io.micrometer.common.lang.NonNull;
import lombok.experimental.NonFinal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public ResponseEntity<Map<String, Object>> createTweet(@RequestBody TweetRequest tweetRequest) {
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

        // Constrói o corpo da resposta
        Map<String, Object> response = new HashMap<>();
        response.put("id", tweet.getId());
        response.put("text", tweet.getText());

        // Constrói o objeto do usuário
        Map<String, Object> user = new HashMap<>();
        user.put("id", tweet.getUser().getId());
        user.put("username", tweet.getUser().getUsername());
        user.put("avatar", tweet.getUser().getAvatar());

        response.put("user", user);

        // Retorna o status 201 Created com o corpo formatado
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getAllTweets() {
        List<TweetModel> tweets = tweetRepository.findAll();

        // Constrói a lista de tweets formatados
        List<Map<String, Object>> formattedTweets = new ArrayList<>();
        for (TweetModel tweet : tweets) {
            Map<String, Object> formattedTweet = new HashMap<>();
            formattedTweet.put("id", tweet.getId());
            formattedTweet.put("text", tweet.getText());

            // Constrói o objeto do usuário
            Map<String, Object> user = new HashMap<>();
            user.put("id", tweet.getUser().getId());
            user.put("username", tweet.getUser().getUsername());
            user.put("avatar", tweet.getUser().getAvatar());

            formattedTweet.put("user", user);

            formattedTweets.add(formattedTweet);
        }

        // Retorna a lista de tweets com o status 200 (OK)
        return ResponseEntity.status(HttpStatus.OK).body(formattedTweets);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Map<String, Object>>> getTweetsByUser(@PathVariable Long userId) {
        // Verifica se o usuário existe

        if (userId != null) {
            // Conversão segura, pois userId não é nulo
            Long userIdNonNull = userId;
            // Restante do código...
        }

        if (!userRepository.existsById(userId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // Obtém todos os tweets do usuário
        List<TweetModel> tweets = tweetRepository.findByUserId(userId);

        // Constrói a lista de tweets formatados
        List<Map<String, Object>> formattedTweets = new ArrayList<>();
        for (TweetModel tweet : tweets) {
            Map<String, Object> formattedTweet = new HashMap<>();
            formattedTweet.put("id", tweet.getId());
            formattedTweet.put("text", tweet.getText());

            // Constrói o objeto do usuário
            Map<String, Object> user = new HashMap<>();
            user.put("id", tweet.getUser().getId());
            user.put("username", tweet.getUser().getUsername());
            user.put("avatar", tweet.getUser().getAvatar());

            formattedTweet.put("user", user);

            formattedTweets.add(formattedTweet);
        }

        // Retorna a lista de tweets com o status 200 (OK)
        return ResponseEntity.status(HttpStatus.OK).body(formattedTweets);
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
