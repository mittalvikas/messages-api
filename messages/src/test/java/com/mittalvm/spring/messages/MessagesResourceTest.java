package com.mittalvm.spring.messages;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.mittalvm.spring.messages.domain.Message;
import com.mittalvm.spring.messages.domain.MessageUser;
import com.mittalvm.spring.messages.service.MessageService;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MessagesResourceTest {

	@MockBean
	MessageService messageService;
	
	@Autowired
    private MockMvc mvc;

	@Before
	@WithMockUser(username="vikas.mittal",password="Welcome1",roles="USER")
	public void initilizeTest(){
		
	}


    @Test
    @WithMockUser(username="vikas.mittal",password="Welcome1",roles="USER")
    public void testgetMessageByid() throws Exception {
    	
    	MessageUser user=new MessageUser();
		user.setId((long)1);
		user.setLogin("vikas.mittal");
		Message msg1=new Message();
		msg1.setMessage("Hello Test");
		msg1.setMessageDateTime(LocalDateTime.now());
		
		Message msg2=new Message();
		msg2.setMessage("Hello Test1");
		msg2.setMessageDateTime(LocalDateTime.now());
		
		when(messageService.fetchMessage(anyLong(),any())).thenReturn(msg1);
        mvc.perform(MockMvcRequestBuilders.get("/messages/message/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(msg1.getId())))
                .andExpect(jsonPath("$.message",is(msg1.getMessage())));
    }
    
    
    @Test
    @WithMockUser(username="vikas.mittal",password="Welcome1",roles="USER")
    public void testSearchMessages() throws Exception {
    	
    	MessageUser user=new MessageUser();
		user.setId((long)1);
		user.setLogin("vikas.mittal");
		
		Message msg1=new Message();
		msg1.setMessage("Hello Test");
		msg1.setMessageDateTime(LocalDateTime.now());
		
		Message msg2=new Message();
		msg2.setMessage("Hello Test1");
		msg2.setMessageDateTime(LocalDateTime.now());
		
		when(messageService.searchMessages(any(), any())).thenReturn(Arrays.asList(msg1,msg2));
        mvc.perform(MockMvcRequestBuilders.get("/messages").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(msg1.getId())))
                .andExpect(jsonPath("$[0].message",is(msg1.getMessage())))
                .andExpect(jsonPath("$[1].id", is(msg2.getId())))
                .andExpect(jsonPath("$[1].message", is(msg2.getMessage())));
    }
    
}