package com.example.event_management.repository;

import com.example.event_management.model.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<Bookmark, String> {

}
