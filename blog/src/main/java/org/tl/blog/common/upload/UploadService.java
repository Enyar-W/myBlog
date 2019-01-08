package org.tl.blog.common.upload;

import java.util.List;

public interface UploadService {

    /**
     * 处理外部链接
     * @param content
     */
    public String handlerOuterImage(String content);

}
