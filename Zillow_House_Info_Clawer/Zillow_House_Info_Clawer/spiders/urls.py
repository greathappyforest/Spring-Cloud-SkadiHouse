import scrapy
import os
class urls(scrapy.Spider):
    name = "urls"
    global search_area
    global filename 
    
    #search_area="irvine-ca"
    #search_area="los-angeles-ca"
    #search_area = "san-diego-ca"
    #search_area = "san-jose-ca"
    #search_area = "lake-forest-ca"
    #search_area = "90025"
    #search_area = "pasadena-ca"
    #search_area = "fountain-valley-ca"
    #search_area = "tustin-ca"
    #search_area = "orange-ca"
    search_area = "garden-grove-ca"
   
    filename = 'data/'+search_area +'-href.txt'
  
    if os.path.isfile('data/tmp.txt'):
       os.remove('data/tmp.txt')
    def start_requests(self):
        # https://www.zillow.com/homes/for_sale/irvine-CA/90_days/1_p/0_mmm
        #print search_area
        urls=[]
        for i in range(20):
            urls.append('https://www.zillow.com/homes/for_sale/'+search_area+'/90_days/'+str(i+1)+'_p/0_mmm')
        
        for url in urls:
            print url
            yield scrapy.Request(url=url, callback =self.parse)
    
    def parse(self, response):
        hrefs= response.xpath("//*[@id='list-results']/div/ul/li/article/div/a/@href").extract()
        #write file
        for href in hrefs:
            data ='https://www.zillow.com'+href +'\n'
            with open(filename, 'a') as f:
                f.write(data)
            self.log('Saved file %s' % filename)
    
    