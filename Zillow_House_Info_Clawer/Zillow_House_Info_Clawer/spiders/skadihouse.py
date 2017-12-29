# -*- coding: utf-8 -*-
from Zillow_House_Info_Clawer.items import ZillowHouseInfoClawerItem
import scrapy
import json
import re
import urlparse
import time
import datetime
from fake_useragent import UserAgent
from random import *




class SkadihouseSpider(scrapy.Spider):
    name = 'skadihouse'
    allowed_domains = ['zillow.com']
  
    search_area = []
    with open('search_area.txt') as f:
    #with open('test.txt') as f:
        for line in f:
            area_code = line.rstrip('\n')
            search_area.append(area_code)
      # print search_area


    start_urls= []
    for item in search_area:
        for i in range(2): 
            start_urls.append('https://www.zillow.com/homes/for_sale/'+item+'/90_days/'+str(i+1)+'_p/0_mmm')

    def parse(self, response):
        
        hrefs= response.xpath("//*[@id='list-results']/div/ul/li/article/div/a/@href").extract()
        for href in hrefs:
            url = urlparse.urljoin('https://www.zillow.com',href)
               
            user_agent = UserAgent().random
            self.logger.info("RANDOM user_agent = %s", user_agent)
            header ={
            'authority':'www.zillow.com',
            'method':'GET',
            'path':str(href),
            'scheme':'https',
            'accept':'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8',
            'accept-encoding':'gzip, deflate, br',
            'accept-language':'en,zh-CN;q=0.8,zh;q=0.6',
            'upgrade-insecure-requests':'1',
            'user-agent':str(user_agent)
            }
            #self.logger.info("Header: %s", header)
            random_time = uniform(1, 2)
            time.sleep(random_time)
            self.logger.info("Sent request to : %s,wait: %s second.", url,random_time)  
            yield scrapy.Request(url=url, callback=self.url_content_parse,headers=header)  
    def url_content_parse(self,response):
      
        # try catch extract data
       
        try:
            url = response.url.lower()
        except:
            url = ""
        try:
            address = str(response.xpath("//*[@id='hdp-content']/div/div/header/h1/text()").extract()[0]).strip(', ').lower()
        except:
            address = ""
        try:
            a = str(response.xpath("//*[@id='hdp-content']/div/div/header/h1/span/text()").extract()[0])
            tmp = (a[:len(a)-5]+ ',' + a[-5:]).split(',')
            city = tmp[0].lower()
            state = tmp[1].strip().lower()
            zip = tmp [2].lower()
        except:
            tmp=['','','']
            city = tmp[0]
            state = tmp[1]
            zip = tmp [2]
        try:
            features = response.xpath("//div[contains(@class, 'zsg-g zsg-g_gutterless')]/div/div/div/div/text()").extract()
            houseType = str(features[0]).lower()
            yearBuild = str(features[1]).lower()
            heating = str(features[2]).lower()
            cooling = str(features[3]).lower()
            parking =  str(features[4]).lower()
            lot = str(features[5]).lower()
        except: 
            features = ['','','','','','','','','']
            houseType = str(features[0])
            yearBuild = str(features[1])
            heating = str(features[2])
            cooling = str(features[3])
            parking =  str(features[4])
            lot = str(features[5])
        try:
            badrooms =str(response.xpath("//*[@id='hdp-content']/div/div/header/h3/span/text()").extract()[1]).lower()
        except:
            badrooms =""
        try:
            bathrooms = str(response.xpath("//*[@id='hdp-content']/div/div/header/h3/span/text()").extract()[3]).lower()
        except:
            bathrooms =""
        try:
            size = str(response.xpath("//*[@id='hdp-content']/div/div/header/h3/span/text()").extract()[5]).lower()
        except:
            size = ""
        try:
            sellPrice =  str(response.xpath("//*[@id='home-value-wrapper']/div/div[2]/span/text()").extract()[0]).lower()
        except:
            sellPrice = ""
        try:
            description =  str(response.xpath("//*[@id='hdp-content']/div/section/div[2]/div/div/text()").extract()[0]) + \
            response.xpath("//*[@id='hdp-content']/div/section/div[2]/div/div/span/text()").extract()[0].encode('utf-8')
        except:
            description = ""
        if description == "":
            try:
                str(response.xpath("//section/div/div[1]/div/text()").extract()[0])
            except:
                description = ""
        try:
            daysOnZillow = str(features[6]).lower()
        except:
            daysOnZillow = ""
        try:
            pricePerSqft = str(features[7]).lower()
        except:
            pricePerSqft = ""


        # clean up data, lower case, change type, 
        zip = re.sub("[^0-9^.]", "", zip)
        yearBuild = re.sub("[^0-9^.]", "", yearBuild)
        if yearBuild !="":
            yearBuild=int(float(yearBuild))
        else:
            yearBuild = 0

        parking = re.sub("[^0-9^.]", "", parking)
        if parking !="":
            parking=int(float(parking))
        else:
            parking =0

        lot = re.sub("[^0-9^.]", "", lot)
        if lot =="":
            lot =0 
        else:
            if float(lot)<10:
                lot=int(float(lot)*43560)
            else:
                lot=int(float(lot))

        badrooms = re.sub("[^0-9^.]", "", badrooms)
        if badrooms !="":
            badrooms=int(float(badrooms))
        else:
            badrooms = 0

        bathrooms = re.sub("[^0-9^.]", "", bathrooms)
        if bathrooms !="":
            bathrooms=int(float(bathrooms))
        else:
            bathrooms = 0

        size = re.sub("[^0-9^.]", "", size)
        if size =="":
            size = 0
        else:
            if float(size)<10:
                size=int(float(size)*43560)
            else:
                size =int(float(lot))

        sellPrice = re.sub("[^0-9^.]", "", sellPrice)
        if sellPrice !="":
            sellPrice=int(float(sellPrice))
        else:
            sellPrice = 0

        daysOnZillow = re.sub("[^0-9^.]", "", daysOnZillow)
        if daysOnZillow !="":
            daysOnZillow=int(float(daysOnZillow))
        else:
            daysOnZillow = 100

        pricePerSqft = re.sub("[^0-9^.]", "", pricePerSqft)
        if pricePerSqft !="":
            pricePerSqft=int(float(pricePerSqft))
        else:
            pricePerSqft = 0

        ts = time.time()
        timeStamp = datetime.datetime.fromtimestamp(ts).strftime('%Y-%m-%d %H:%M:%S')
        pat = ".*captchaperimeterx.*"
        if bool(re.search(pat,url)):
            print url
            print "'captchaperimeterx' detected, invaild Data!"
        else:
            item = ZillowHouseInfoClawerItem()
            item['url']  = url
            item['address'] = address
            item['city'] = city
            item['state'] = state
            item['zip'] = zip
            item['houseType']= houseType
            item['yearBuild'] = yearBuild
            item['heating'] = heating
            item['cooling'] = cooling
            item['parking'] = parking
            item['lot'] = lot
            item['badrooms'] =badrooms
            item['bathrooms'] = bathrooms
            item['size'] = size
            item['sellPrice'] = sellPrice
            item['description'] = description
            item['daysOnZillow'] =daysOnZillow
            item['pricePerSqft'] = pricePerSqft
            item['lastUpdated']= timeStamp
            yield item

