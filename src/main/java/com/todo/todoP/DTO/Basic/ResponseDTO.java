package com.todo.todoP.DTO.Basic;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ResponseDTO<T> {

    private String etc;
    private List<T> data;

}
