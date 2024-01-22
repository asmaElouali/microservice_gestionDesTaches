package com.example.client.client;

import com.example.client.service.Note;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name ="service-note" , url = "${application.config.note-url}")
public interface UserClient {
   @GetMapping("/client/{user-id}")
    List<Note> findAllNotesByUser(@PathVariable("user-id") Long UserId);
}
