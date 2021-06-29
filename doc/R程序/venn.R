filename<-commandArgs(trailingOnly = TRUE)#linux命令行传入参数

#venn函数，参数为K1
venn<-function(K1){
  if (!require("VennDiagram",character.only = TRUE)){
    install.packages("VennDiagram",dep=TRUE)
  }#判断是否安装VennDiagram包，如若没有自动安装
  if (!require("readxl",character.only = TRUE)){
    install.packages("readxl",dep=TRUE)
  }#判断是否安装readxl包，如若没有自动安装
  library("VennDiagram")#加载VennDiagram包
  library("readxl")#加载readxl包
  #判断文件类型为xlsx时
  if (unlist(strsplit(K1,'[.]'))[2] == 'xlsx'){
    data<-read_excel(K1,col_names = F)#读取xlsx文件,不需要输入文件有行名
    data[is.na(data)]<-' '#有缺失值的内容替换为空
    data<-as.data.frame(data)#将数据集转换为R语言的data.frame格式
    venn_list<-list(A=data[,1],B=data[,2])#构造合适venn.diagram()函数的读取的数据类型
    K<-paste0('venn',Sys.time(),as.character(sample(1:100000000000000,1)),'.png')#构造以'venn'、系统时间、1:100000000000000随机数生成的文件名
    venn.diagram(venn_list, fill=c("red","green"),alpha=0.5, cex=1, cat.fontface=4,filename=K)#绘图函数
    return(paste0(getwd(),'/',K))#返回文件路径和文件名
  }
  #判断文件类型为txt时
  if (unlist(strsplit(K1,'[.]'))[2] == 'txt'){
    data<-read.table(K1,sep = "\t",header = F)
    venn_list<-list(A=data[,1],B=data[,2])
    K<-paste0('venn',Sys.time(),as.character(sample(1:100000000000000,1)),'.png')
    venn.diagram(venn_list, fill=c("red","green"),alpha=0.5, cex=1, cat.fontface=4,filename=K)
    return(paste0(getwd(),'/',K))
  }
  #判断文件类型为csv时
  if (unlist(strsplit(K1,'[.]'))[2] == 'csv'){
    data<-read.csv(K1,sep=",",header=F)
    venn_list<-list(A=data[,1],B=data[,2])
    K<-paste0('venn',Sys.time(),as.character(sample(1:100000000000000,1)),'.png')
    venn.diagram(venn_list, fill=c("red","green"),alpha=0.5, cex=1, cat.fontface=4,filename=K)
    return(paste0(getwd(),'/',K))
  }
}

venn(filename)#运行venn函数



