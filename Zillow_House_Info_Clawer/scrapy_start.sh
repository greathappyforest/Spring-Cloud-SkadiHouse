#! /bin/sh   
export PATH=$PATH:/usr/local/bin  
  
#����.py�ű�����Ŀ¼  
cd /Spring-Cloud-SkadiHouse/Zillow_House_Info_Clawer 
  
#ִ��.py�ж������Ŀexample����ָ����־�ļ�������nohup....&��ʾ�����ں�ִ̨�У�������Ϊ�ر��ն˶����³���ִ���жϡ�  
nohup scrapy crawl skadihouse &