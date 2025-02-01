package com.magiarium.domain.dto.content_master;


import com.magiarium.domain.enums.ContentTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChildContent {

    Long childContentId;

    ContentTypeEnum contentType;

    String label;

    String contentJson;

}
