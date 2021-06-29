package com.highy.modules.plot.controller.model;

public class AnalysisDataMessage {
	
	private String plotType;
	
	private String plotData;	// 绘图数据

	private String filePath;
	
	private String fileName;

	public String getPlotType() {
		return plotType;
	}

	public void setPlotType(String plotType) {
		this.plotType = plotType;
	}

	public String getPlotData() {
		return plotData;
	}

	public void setPlotData(String plotData) {
		this.plotData = plotData;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	

}
