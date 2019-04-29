package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTest {
    @InjectMocks
    private DbService dbService;

    @Mock
    private TaskRepository repository;

    @Test
    public void getAllTasksTest() {
        //Given
        Task task = new Task(1L, "title", "content");
        List <Task> taskList = Arrays.asList(task);

        when(repository.findAll()).thenReturn(taskList);

        //When
        List <Task> resultingTask = dbService.getAllTasks();

        //Then
        assertNotNull(resultingTask);
        assertEquals(1, resultingTask.size());
    }

    @Test
    public void getTaskWhichDoesNotExists() {
        //Given
        when(repository.findById(2L)).thenReturn(Optional.empty());

        //When
        Optional <Task> resultingTaskById = dbService.getTaskById(2L);

        //Then
        assertEquals(Optional.empty(), resultingTaskById);
    }

    @Test
    public void saveTaskTest(){
        //Given
        Task task = new Task(1L, "title", "content");
        when(repository.save(task)).thenReturn(task);

        //When
        Task savedTask = dbService.saveTask(task);

        //Then
        assertEquals(task.getId(), savedTask.getId());
        assertEquals(task.getTitle(), savedTask.getTitle());
        assertEquals(task.getContent(), savedTask.getContent());
    }

    @Test
    public void deleteTaskTest(){
        //Given
        Task task = new Task(2L, "title", "content");
        Task savedTask = repository.save(task);

        //When
        dbService.deleteTask(2L);

        //Then
        verify(repository, times(1)).delete(2L);
        assertFalse(repository.findAll().contains(savedTask));
    }
}