#! /bin/sh   
export PATH=$PATH:/usr/local/bin  
  
#进入.py脚本所在目录  
cd /Spring-Cloud-SkadiHouse/Zillow_House_Info_Clawer 
  
#执行.py中定义的项目example，并指定日志文件，其中nohup....&表示可以在后台执行，不会因为关闭终端而导致程序执行中断。  
nohup scrapy crawl skadihouse &