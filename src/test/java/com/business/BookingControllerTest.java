package com.business;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BookingControllerTest {

    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    @Mock
    private BookRepo bookRepo;

    @InjectMocks
    private BookController bookController;

    Book record1 = new Book(1L,"The Hobbit", "Success behin the wall", 3);
    Book record2 = new Book(2L,"The Wall Street", "The Wall Street tells about stocks", 2);
    Book record3 = new Book(3L,"Start up", "Become a million dollar rich", 5);


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @Test
    public void getAllBooksTest() throws Exception {
        List<Book> recordList = new ArrayList<>(Arrays.asList(record1,record2,record3));

        Mockito.when(bookRepo.findAll()).thenReturn(recordList);

        mockMvc.perform(MockMvcRequestBuilders.get("/book")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$",hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name",is("The Wall Street")));
    }

    @Test
    public void getBookByBookIdTest() throws Exception {

        List<Book> recordList = new ArrayList<>(Arrays.asList(record1,record2,record3));

        Mockito.when(bookRepo.findAll()).thenReturn(recordList);

        mockMvc.perform(MockMvcRequestBuilders.get("/book")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$",hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name",is("The Wall Street")));
    }




}
