package com.poo.domain.interfaces;

import org.springframework.stereotype.Component;
import org.bson.Document;

@Component
public interface IImuttableMapper<TClass> {
    TClass Map(Document data);
}
