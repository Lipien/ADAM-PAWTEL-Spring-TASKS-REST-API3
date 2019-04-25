package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TaskMapperTest {
    @InjectMocks
    private TaskMapper taskMapper;

    @Test
    public void mapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "title", "content");

        //When
        Task resultingTask = taskMapper.mapToTask(taskDto);

        //Then
        assertEquals(taskDto.getId(), resultingTask.getId());
        assertEquals(taskDto.getTitle(), resultingTask.getTitle());
        assertEquals(taskDto.getContent(), resultingTask.getContent());
    }

    @Test
    public void mapToTaskDto() {
        //Given
        Task task = new Task(1L, "title", "content");

        //When
        TaskDto resultingTaskDto = taskMapper.mapToTaskDto(task);

        //Then
        assertEquals(task.getId(), resultingTaskDto.getId());
        assertEquals(task.getTitle(), resultingTaskDto.getTitle());
        assertEquals(task.getContent(), resultingTaskDto.getContent());
    }

    @Test
    public void mapToTaskDtoList() {
        //Given
        Task task = new Task(1L, "title", "content");
        List <Task> tasksList = Arrays.asList(task);

        //When
        List <TaskDto> resultingDtoList = taskMapper.mapToTaskDtoList(tasksList);

        //Then
        assertEquals(tasksList.size(), resultingDtoList.size());
        assertEquals(tasksList.get(0).getId(), resultingDtoList.get(0).getId());
        assertEquals(tasksList.get(0).getTitle(), resultingDtoList.get(0).getTitle());
        assertEquals(tasksList.get(0).getContent(), resultingDtoList.get(0).getContent());
    }
}