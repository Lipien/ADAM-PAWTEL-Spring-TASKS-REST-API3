package com.crud.tasks.trello.client;

import com.crud.tasks.domain.TrelloBoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class TrelloClient {

    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;

    @Value("${trello.app.key}")
    private String trelloAppKey;

    @Value("${trello.app.token}")
    private String trelloToken;

    @Value("${trello.api.username}")
    private String trelloUsername;

    @Autowired
    private RestTemplate restTemplate;

    private URI urlTrelloBoardsAssembler() {
        return UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/members/adampawtel/boards")
                .queryParam("key", trelloAppKey)
                .queryParam("token", trelloToken)
                .queryParam("fields", "name,id")
              //  .queryParam("lists", "all")
                .build()
                .encode()
                .toUri();
    }

    public List <TrelloBoardDto> getTrelloBoards() {
        TrelloBoardDto[] boardsResponse = restTemplate
                .getForObject(urlTrelloBoardsAssembler(), TrelloBoardDto[].class);
        Optional <TrelloBoardDto[]> optionalBoardsResponse = Optional.ofNullable(boardsResponse);
        return Arrays.asList(optionalBoardsResponse.orElse(new TrelloBoardDto[0]));
    }
}
