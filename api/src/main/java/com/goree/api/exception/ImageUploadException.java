package com.goree.api.exception;

import java.io.IOException;

/**
 * Created by 원영 on 2015-08-21.
 */
public class ImageUploadException extends RuntimeException {
    public ImageUploadException(String message, IOException cause) {
        super(message, cause);
    }
}
