package com.orangeboy.listener;

import org.apache.commons.fileupload.ProgressListener;

import javax.servlet.http.HttpSession;

public class FileUploadProgressListener implements ProgressListener {
    private HttpSession session;
    public FileUploadProgressListener(HttpSession session){
        this.session = session;
    }

    @Override
    public void update(long read, long sum, int i) {
        session.setAttribute("fileProcess",(int)(((double)read/sum)*100));
    }
}
