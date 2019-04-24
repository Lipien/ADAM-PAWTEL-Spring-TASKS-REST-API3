package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TrelloMapperTest {
    @InjectMocks
    private TrelloMapper trelloMapper;

    @Test
    public void mapToBoards() {
        //Given
        List <TrelloListDto> trelloListDto = new ArrayList <>();
        List <TrelloBoardDto> trelloBoardDto = new ArrayList <>();
        TrelloBoardDto boardDto = new TrelloBoardDto("1", "Adam", trelloListDto);
        trelloBoardDto.add(boardDto);

        List <TrelloList> trelloList = new ArrayList <>();
        List <TrelloBoard> expectedTrelloBoardList = new ArrayList <>();
        TrelloBoard boardExpected = new TrelloBoard("1", "Adam", trelloList);
        expectedTrelloBoardList.add(boardExpected);

        //When
        List <TrelloBoard> resultingTrelloBoardList = trelloMapper.mapToBoards(trelloBoardDto);

        //Then
        assertEquals(expectedTrelloBoardList.size(), resultingTrelloBoardList.size());
        assertEquals(expectedTrelloBoardList.get(0).getId(), resultingTrelloBoardList.get(0).getId());
        assertEquals(expectedTrelloBoardList.get(0).getName(), resultingTrelloBoardList.get(0).getName());
        assertEquals(expectedTrelloBoardList.get(0).getLists(), resultingTrelloBoardList.get(0).getLists());
    }

    @Test
    public void mapToBoardsDto() {
        //Given
        List <TrelloList> trelloList = new ArrayList <>();
        List <TrelloBoard> trelloBoards = new ArrayList <>();
        TrelloBoard board = new TrelloBoard("1", "Thomas", trelloList);
        trelloBoards.add(board);

        List <TrelloListDto> trelloListDto = new ArrayList <>();
        List <TrelloBoardDto> expectedTrelloBoardDtoList = new ArrayList <>();
        TrelloBoardDto boardDtoList = new TrelloBoardDto("1", "Thomas", trelloListDto);
        expectedTrelloBoardDtoList.add(boardDtoList);

        //When
        List <TrelloBoardDto> resultingTrelloBoardDtoList = trelloMapper.mapToBoardsDto(trelloBoards);

        //Then
        assertEquals(expectedTrelloBoardDtoList.size(), resultingTrelloBoardDtoList.size());
        assertEquals(expectedTrelloBoardDtoList.get(0).getId(), resultingTrelloBoardDtoList.get(0).getId());
        assertEquals(expectedTrelloBoardDtoList.get(0).getName(), resultingTrelloBoardDtoList.get(0).getName());
        assertEquals(expectedTrelloBoardDtoList.get(0).getLists(), resultingTrelloBoardDtoList.get(0).getLists());
    }

    @Test
    public void mapToList() {
        //Given
        TrelloListDto trelloListDto = new TrelloListDto("1", "Aneta", false);
        List <TrelloListDto> listDto = Arrays.asList(trelloListDto);

        //When
        List <TrelloList> resultingList = trelloMapper.mapToList(listDto);

        //Then
        assertEquals(listDto.size(), resultingList.size());
        assertEquals(listDto.get(0).getId(), resultingList.get(0).getId());
        assertEquals(listDto.get(0).getName(), resultingList.get(0).getName());
        assertEquals(listDto.get(0).isClosed(), resultingList.get(0).isClosed());
    }

    @Test
    public void mapToListDto() {
        //Given
        TrelloList trelloList = new TrelloList("1", "Eva", false);
        List <TrelloList> list = Arrays.asList(trelloList);

        //When
        List <TrelloListDto> resultingList = trelloMapper.mapToListDto(list);

        //Then
        assertEquals(list.size(), resultingList.size());
        assertEquals(list.get(0).getId(), resultingList.get(0).getId());
        assertEquals(list.get(0).getName(), resultingList.get(0).getName());
        assertEquals(list.get(0).isClosed(), resultingList.get(0).isClosed());
    }

    @Test
    public void mapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("name", "description", "pos", "listId");

        //When
        TrelloCardDto resultingTrelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //Then
        assertEquals(trelloCard.getName(), resultingTrelloCardDto.getName());
        assertEquals(trelloCard.getDescription(), resultingTrelloCardDto.getDescription());
        assertEquals(trelloCard.getPos(), resultingTrelloCardDto.getPos());
        assertEquals(trelloCard.getListId(), resultingTrelloCardDto.getListId());
    }

    @Test
    public void mapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("card", "description", "pos", "2");

        //When
        TrelloCard resultingCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        assertEquals(trelloCardDto.getName(), resultingCard.getName());
        assertEquals(trelloCardDto.getDescription(), resultingCard.getDescription());
        assertEquals(trelloCardDto.getPos(), resultingCard.getPos());
        assertEquals(trelloCardDto.getListId(), resultingCard.getListId());
    }
}