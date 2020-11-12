package com.greeth.kit.marshalling;

import java.io.IOException;

public class MarshallingException extends RuntimeException {
    public MarshallingException(String s, IOException ex) {
        super(s, ex);
    }
}
