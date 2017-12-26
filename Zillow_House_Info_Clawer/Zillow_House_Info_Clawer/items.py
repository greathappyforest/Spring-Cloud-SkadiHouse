# -*- coding: utf-8 -*-

# Define here the models for your scraped items
#
# See documentation in:
# http://doc.scrapy.org/en/latest/topics/items.html

import scrapy


class ZillowHouseInfoClawerItem(scrapy.Item):
    # define the fields for your  here like:
    # name = scrapy.Field()
    url = scrapy.Field()
    address = scrapy.Field()
    city = scrapy.Field()
    state = scrapy.Field()
    zip = scrapy.Field()
    houseType = scrapy.Field()
    yearBuild = scrapy.Field()
    heating = scrapy.Field()
    cooling = scrapy.Field()
    parking = scrapy.Field()
    lot = scrapy.Field()
    badrooms = scrapy.Field()
    bathrooms = scrapy.Field()
    size = scrapy.Field()
    sellPrice = scrapy.Field()
    description = scrapy.Field()
    daysOnZillow = scrapy.Field()
    pricePerSqft = scrapy.Field()
    lastUpdated = scrapy.Field()
