from Zillow_House_Info_Clawer.items import ZillowHouseInfoClawerItem
import scrapy
import json
import re

class houses(scrapy.Spider):
    name = "houses"
    global search_area
    #search_area ="irvine-ca"
    #search_area = "san-diego-ca"
    #search_area = "san-jose-ca"
    #search_area = "lake-forest-ca"
    #search_area = "pasadena-ca"
    #search_area="los-angeles-ca"
    #search_area = "fountain-valley-ca"
    #search_area = "tustin-ca"
    #search_area = "orange-ca"
    search_area = "garden-grove-ca"

    global filename
    global data
    data = ""
    filename= 'data/'+search_area+'-href.txt'

    def start_requests(self):
        # https://www.zillow.com/homes/for_sale/irvine-CA/90_days/1_p/0_mmm
        with open(filename) as f:
            for line in f:
                url =  line.rstrip('\n')
                print('url:' + url)
                yield scrapy.Request(url=url, callback =self.parse)
                
    def parse(self, response):  
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


        # house_info = {
        #     "url" : url,
        #     "address": address,
        #     "city": city,
        #     "state": state,
        #     "zip": zip,
        #     "houseType": houseType,
        #     "yearBuild": yearBuild,
        #     "heating" : heating,
        #     "cooling" : cooling,
        #     "parking" : parking,
        #     "lot" : lot,
        #     "badrooms" : badrooms,
        #     "bathrooms": bathrooms,
        #     "size" : size,
        #     "sellPrice" : sellPrice,
        #     "description" : description,
        #     "daysOnZillow": daysOnZillow,
        #     "pricePerSqft": pricePerSqft
        # }
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
            yield item
        # global data
        # data += json.dumps(house_info)+'\n'
        # print data
        # with open('data/'+search_area+'_house_info.txt', 'w') as f:
        #     f.write(data)
        # self.log('Saved file %s' % f)