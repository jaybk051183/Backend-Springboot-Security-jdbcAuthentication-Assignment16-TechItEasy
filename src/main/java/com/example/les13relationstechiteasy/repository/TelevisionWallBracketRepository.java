package com.example.les13relationstechiteasy.repository;
import com.example.les13relationstechiteasy.model.TelevisionWallBracket;
import com.example.les13relationstechiteasy.model.TelevisionWallBracketKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TelevisionWallBracketRepository extends JpaRepository<TelevisionWallBracket, TelevisionWallBracketKey> {
    // custom query om alle TelevisionWallBrackets te vinden die bij een bepaalde tv horen
    List<TelevisionWallBracket> findAllByTelevisionId(Long televisionId);
    // custom query om alle TelevisionWallBrackets te vinden die bij een bepaalde wallbracket horen
    List<TelevisionWallBracket> findAllByWallBracketId(Long wallBracketId);
}
