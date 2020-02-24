package com.orangeboy.resolver;

import com.orangeboy.listener.FileUploadProgressListener;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class MultipartListenerResolver extends CommonsMultipartResolver {
    @Override
    protected MultipartParsingResult parseRequest(HttpServletRequest request) throws MultipartException {
        String encoding = this.determineEncoding(request);
        FileUpload fileUpload = this.prepareFileUpload(encoding);
        fileUpload.setProgressListener(new FileUploadProgressListener(request.getSession()));
        try {
            List<FileItem> fileItems = ((ServletFileUpload)fileUpload).parseRequest(request);
            return this.parseFileItems(fileItems, encoding);
        } catch (FileUploadBase.SizeLimitExceededException var5) {
            throw new MaxUploadSizeExceededException(fileUpload.getSizeMax(), var5);
        } catch (FileUploadBase.FileSizeLimitExceededException var6) {
            throw new MaxUploadSizeExceededException(fileUpload.getFileSizeMax(), var6);
        } catch (FileUploadException var7) {
            throw new MultipartException("Failed to parse multipart servlet request", var7);
        }
    }
}
