package com.github.cooker.core.msg;

/**
 * grant
 * 27/5/2020 2:31 下午
 * 描述：
 */
public class RickLogMessageBuilder {
    MessageBuilder messageBuilder = null;

    protected RickLogMessageBuilder() {

    }

    public static RickLogMessageBuilder newBuilderWithLog(){
        return new RickLogMessageBuilder();
    }


    public static RickHeartLogMessageBuilder newBuilderWithHeartLog(){
        return new RickHeartLogMessageBuilder();
    }

    public static RickUploadLogMessageBuilder newBuilderWithUploadLog(){
        return new RickUploadLogMessageBuilder();
    }

    public static RickExceptionLogMessageBuilder newBuilderWithExceptionLog(){
        return new RickExceptionLogMessageBuilder();
    }

}
