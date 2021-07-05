venn_matrix<-function(K1,K2){
  if (!require("VennDiagram",character.only = TRUE)){
    install.packages("VennDiagram",dep=TRUE)
  }
  if (!require("uuid",character.only = TRUE)){
    install.packages("uuid",dep=TRUE)
  }
  library(VennDiagram)
  library(uuid)
  eval(parse(text=paste0('venn_list<-list(',K1[1],'=K1,',K2[1],'=K2)')))
  K<-paste0('ven_',UUIDgenerate(),'.png')
  venn.diagram(venn_list, fill=c("red","green"),alpha=0.5, cex=1, cat.fontface=4,filename=K)
  return(paste0(getwd(),'/',K))
}