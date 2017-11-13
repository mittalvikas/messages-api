package com.mittalvm.spring.messages.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    @JsonFormat(pattern = "MM-dd-yyyy hh:mm:ss")
    private LocalDateTime messageDateTime;
    @OneToOne
    private MessageUser createdBy;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public LocalDateTime getMessageDateTime() {
		return messageDateTime;
	}
	public void setMessageDateTime(LocalDateTime messageDateTime) {
		this.messageDateTime = messageDateTime;
	}
	public MessageUser getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(MessageUser createdBy) {
		this.createdBy = createdBy;
	}
	@Override
	public String toString() {
		return "Message [id=" + id + ", message=" + message + ", messageDateTime=" + messageDateTime + ", createdBy="
				+ createdBy + "]";
	}
	

    
}