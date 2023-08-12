package com.coderscampus.assignment14.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderscampus.assignment14.domain.Message;

public interface MessageRepository extends JpaRepository<Message, Long>{

}
