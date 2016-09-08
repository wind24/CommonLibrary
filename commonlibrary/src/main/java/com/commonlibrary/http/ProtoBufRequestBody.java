package com.commonlibrary.http;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;

/**
 * Created by android-dev on 8/9/16.
 */
public class ProtoBufRequestBody extends RequestBody {

    public static final MediaType MEDIA_TYPE_OCTET_STREAM = MediaType.parse("application/octet-stream");

    private final byte[] data;

    public ProtoBufRequestBody(byte[] data) {
        this.data = data;
    }

    @Override
    public MediaType contentType() {
        return MEDIA_TYPE_OCTET_STREAM;
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        sink.write(data);
    }
}
