package com.highy.modules.plot.service;

import com.highy.modules.plot.controller.model.AnalysisDataMessage;
import com.highy.modules.plot.controller.model.RestResponse;

import javax.servlet.http.HttpServletRequest;

public interface IRenderService {
    RestResponse render(HttpServletRequest request, AnalysisDataMessage message);
}
