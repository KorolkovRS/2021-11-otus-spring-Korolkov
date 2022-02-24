package ru.korolkovrs.spring20.rest.exception_handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LibraryError {
    private int HttpStatus;
    private Map<String, String> messages;
    private Date date;
}
