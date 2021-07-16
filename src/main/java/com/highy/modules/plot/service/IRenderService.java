package com.highy.modules.plot.service;

import com.highy.modules.plot.controller.model.AnalysisDataMessage;
import com.highy.modules.plot.controller.model.RestResponse;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface IRenderService {
    RestResponse render(HttpServletRequest request, AnalysisDataMessage message);

     RestResponse renderWithFile(HttpServletRequest request,MultipartFile dataFile, AnalysisDataMessage message);
}
